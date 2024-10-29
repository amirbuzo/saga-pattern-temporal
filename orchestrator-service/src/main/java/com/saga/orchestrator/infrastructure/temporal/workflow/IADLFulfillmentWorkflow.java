package com.saga.orchestrator.infrastructure.temporal.workflow;

import com.saga.common.model.JiraTaskDTO;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface IADLFulfillmentWorkflow {
    @WorkflowMethod
    void createprRequest(JiraTaskDTO prRequestDTO);
}
