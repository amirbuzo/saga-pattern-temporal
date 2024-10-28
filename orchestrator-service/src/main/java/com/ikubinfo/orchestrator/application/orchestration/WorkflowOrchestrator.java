package com.ikubinfo.orchestrator.application.orchestration;

import com.ikubinfo.orchestrator.application.domain.model.PrRequest;

public interface WorkflowOrchestrator {
  void createprRequest(PrRequest prRequest);
}
