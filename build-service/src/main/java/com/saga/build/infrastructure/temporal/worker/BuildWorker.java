package com.saga.build.infrastructure.temporal.worker;

import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.PostConstruct;

import com.saga.build.infrastructure.temporal.orchestrator.WorkflowOrchestratorClient;
import com.saga.common.TaskQueue;
import com.saga.common.activities.BuildActivities;

@Slf4j
@RequiredArgsConstructor
public class BuildWorker {
    
    private final BuildActivities buildingActivities;
    private final WorkflowOrchestratorClient workflowOrchestratorClient;
    
    @PostConstruct
    public void createWorker() {
        
        log.info("Registering worker..");
        
        var workflowClient = workflowOrchestratorClient.getWorkflowClient();
        
        var workerFactory = WorkerFactory.newInstance(workflowClient);
        var worker = workerFactory.newWorker(TaskQueue.BUILDING_ACTIVITY_TASK_QUEUE.name());
        
        worker.registerActivitiesImplementations(buildingActivities);
        
        workerFactory.start();
        
        log.info("BuildArtifactDto worker started..");
    }
}
