package com.saga.orchestrator.infrastructure.temporal.workflow.impl;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

import com.saga.common.TaskQueue;
import com.saga.common.activities.BitbucketActivities;
import com.saga.common.activities.BuildActivities;
import com.saga.common.activities.JiraTaskActivities;
import com.saga.common.activities.SonarActivities;
import com.saga.common.model.JiraTaskDTO;
import com.saga.orchestrator.infrastructure.temporal.workflow.IADLFulfillmentWorkflow;

import java.time.Duration;

@Slf4j
public class IADLFulfillmentWorkflowImpl implements IADLFulfillmentWorkflow {
    
    private final ActivityOptions buildActivityOptions = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofMinutes(1))
            .setTaskQueue(TaskQueue.BUILDING_ACTIVITY_TASK_QUEUE.name()).setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build()).build();
    
    private final ActivityOptions jiraActivityOptions = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofMinutes(1))
            .setTaskQueue(TaskQueue.JIRA_ACTIVITY_TASK_QUEUE.name()).setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build()).build();
    private final ActivityOptions sonarActivityOptions = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofMinutes(1))
            .setTaskQueue(TaskQueue.SONAR_ACTIVITY_TASK_QUEUE.name()).setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(3).build()).build();
    /**
     * private final LocalActivityOptions localActivityOptions =
     * LocalActivityOptions.newBuilder()
     * .setStartToCloseTimeout(Duration.ofMinutes(1))
     * .setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(10).build())
     * .build();
     * 
     */
    private final ActivityOptions taskActivityOptions = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofMinutes(1))
            .setTaskQueue(TaskQueue.TASK_ACTIVITY_TASK_QUEUE.name()).setRetryOptions(RetryOptions.newBuilder().setMaximumAttempts(10).build()).build();
    
    private final BitbucketActivities bitBucketActivities = Workflow.newActivityStub(BitbucketActivities.class, jiraActivityOptions);
    
    private final SonarActivities sonarActivities = Workflow.newActivityStub(SonarActivities.class, sonarActivityOptions);
    
    private final BuildActivities buildActivities = Workflow.newActivityStub(BuildActivities.class, buildActivityOptions);
    
    private final JiraTaskActivities taskActivities = Workflow.newActivityStub(JiraTaskActivities.class, taskActivityOptions);
    
    @Override
    public void createprRequest(JiraTaskDTO taskDTO) {
        // Configure SAGA to run compensation activities in parallel
        Saga.Options sagaOptions = new Saga.Options.Builder().setParallelCompensation(true).build();
        Saga saga = new Saga(sagaOptions);
        try {
            bitBucketActivities.requestPr(taskDTO);
            saga.addCompensation(bitBucketActivities::cancelCleanPr, taskDTO);
            
            // Sonar Check
            boolean checkResultOk = sonarActivities.checkSonar(taskDTO);
            saga.addCompensation(sonarActivities::cleanSonar, taskDTO);
            if (!checkResultOk) {
                saga.compensate();
                return;
            }
            // Building
            buildActivities.buildProject(taskDTO);
            saga.addCompensation(buildActivities::cancelCleanProject, taskDTO);
            // Task
            taskActivities.completeTask(taskDTO);
            saga.addCompensation(taskActivities::failTask, taskDTO);
        } catch (ActivityFailure cause) {
            saga.compensate();
            throw cause;
        }
    }
}
