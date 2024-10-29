package com.saga.orchestrator.application.orchestration;

import com.saga.orchestrator.application.domain.model.PrRequest;

public interface WorkflowOrchestrator {
    void createprRequest(PrRequest prRequest);
}
