package com.ikubinfo.orchestrator.infrastructure.temporal.workflow;

import com.ikubinfo.common.model.JiraTaskDTO;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface IADLFulfillmentWorkflow {
  @WorkflowMethod
  void createprRequest(JiraTaskDTO prRequestDTO);
}
