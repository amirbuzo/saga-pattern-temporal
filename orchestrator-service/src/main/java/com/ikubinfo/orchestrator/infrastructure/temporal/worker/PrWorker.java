package com.ikubinfo.orchestrator.infrastructure.temporal.worker;

import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import io.temporal.worker.WorkflowImplementationOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.PostConstruct;

import com.ikubinfo.common.TaskQueue;
import com.ikubinfo.common.activities.JiraTaskActivities;
import com.ikubinfo.orchestrator.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import com.ikubinfo.orchestrator.infrastructure.temporal.workflow.impl.IADLFulfillmentWorkflowImpl;

@Slf4j
@RequiredArgsConstructor
public class PrWorker {

  private final JiraTaskActivities prRequestActivities;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering worker..");

    var workerOptions = WorkerOptions.newBuilder().build();

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();
    WorkflowImplementationOptions workflowImplementationOptions =
        WorkflowImplementationOptions.newBuilder()
            .setFailWorkflowExceptionTypes(NullPointerException.class)
            .build();

    var workerFactory = WorkerFactory.newInstance(workflowClient);
    var worker =
        workerFactory.newWorker(
            TaskQueue.IADL_FULFILLMENT_WORKFLOW_TASK_QUEUE.name(), workerOptions);
    
    var workerCompleteprRequest =
            workerFactory.newWorker(
                TaskQueue.TASK_ACTIVITY_TASK_QUEUE.name(), workerOptions);

    // Can be called multiple times
    worker.registerWorkflowImplementationTypes(
        workflowImplementationOptions, IADLFulfillmentWorkflowImpl.class);

    workerCompleteprRequest.registerActivitiesImplementations(prRequestActivities);

    workerFactory.start();

    log.info("Registering prRequest worker..");
  }
}
