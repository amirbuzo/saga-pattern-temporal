package com.saga.sonar.infrastructure.temporal.worker;

import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.PostConstruct;

import com.saga.common.TaskQueue;
import com.saga.common.activities.SonarActivities;
import com.saga.sonar.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;

@Slf4j
@RequiredArgsConstructor
public class SonarWorker {

  private final SonarActivities buildActivities;
  private final WorkflowOrchestratorClient workflowOrchestratorClient;

  @PostConstruct
  public void createWorker() {

    log.info("Registering worker..");

    var workerOptions = WorkerOptions.newBuilder().build();

    var workflowClient = workflowOrchestratorClient.getWorkflowClient();

    var workerFactory = WorkerFactory.newInstance(workflowClient);
    var worker =
        workerFactory.newWorker(TaskQueue.SONAR_ACTIVITY_TASK_QUEUE.name(), workerOptions);

    worker.registerActivitiesImplementations(buildActivities);

    workerFactory.start();

    log.info("SonarResultDto worker started..");
  }
}
