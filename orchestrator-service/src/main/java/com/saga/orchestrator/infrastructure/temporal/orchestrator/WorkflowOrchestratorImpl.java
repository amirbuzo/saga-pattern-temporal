package com.saga.orchestrator.infrastructure.temporal.orchestrator;

import com.saga.common.TaskQueue;
import com.saga.common.model.JiraTaskDTO;
import com.saga.orchestrator.application.domain.model.PrRequest;
import com.saga.orchestrator.application.orchestration.WorkflowOrchestrator;
import com.saga.orchestrator.common.config.ApplicationProperties;
import com.saga.orchestrator.infrastructure.temporal.workflow.IADLFulfillmentWorkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkflowOrchestratorImpl implements WorkflowOrchestrator {
    
    private final WorkflowOrchestratorClient workflowOrchestratorClient;
    private final ApplicationProperties applicationProperties;
    
    @Override
    public void createprRequest(PrRequest prRequest) {
        
        var prRequestDTO = map(prRequest);
        
        var workflowClient = workflowOrchestratorClient.getWorkflowClient();
        var prRequestFulfillmentWorkflow = workflowClient.newWorkflowStub(IADLFulfillmentWorkflow.class,
                WorkflowOptions.newBuilder().setWorkflowId(applicationProperties.getWorkflow() + "-" + prRequestDTO.getJiraTaskId())
                        .setTaskQueue(TaskQueue.IADL_FULFILLMENT_WORKFLOW_TASK_QUEUE.name()).build());
        /**
         * Execute Sync
         * prRequestFulfillmentWorkflow.createprRequest(prRequestDTO);
         */
        // Async execution
        WorkflowClient.start(prRequestFulfillmentWorkflow::createprRequest, prRequestDTO);
    }
    
    private JiraTaskDTO map(PrRequest prRequest) {
        var prRequestDTO = new JiraTaskDTO();
        prRequestDTO.setJiraTaskId(prRequest.getPrRequestId());
        prRequestDTO.setBranchId(prRequest.getBranchId());
        prRequestDTO.setUser(prRequest.getUser());
        prRequestDTO.setPath(prRequest.getPath());
        return prRequestDTO;
    }
}
