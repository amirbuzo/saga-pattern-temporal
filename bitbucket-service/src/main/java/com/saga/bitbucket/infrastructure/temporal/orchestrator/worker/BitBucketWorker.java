package com.saga.bitbucket.infrastructure.temporal.orchestrator.worker;

import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.PostConstruct;

import com.saga.bitbucket.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import com.saga.common.TaskQueue;
import com.saga.common.activities.BitbucketActivities;

@Slf4j
@RequiredArgsConstructor
public class BitBucketWorker {

  private final BitbucketActivities pullRequestActivities;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering PullRequestDto worker..");

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();

    var workerOptions = WorkerOptions.newBuilder().build();

    var workerFactory = WorkerFactory.newInstance(workflowClient);
    var worker =
        workerFactory.newWorker(TaskQueue.JIRA_ACTIVITY_TASK_QUEUE.name(), workerOptions);

    worker.registerActivitiesImplementations(pullRequestActivities);

    workerFactory.start();

    log.info("Registered PullRequestDto worker..");
  }
}
