package com.saga.orchestrator.application.command.impl;

import com.saga.orchestrator.application.command.PullRequestCommand;
import com.saga.orchestrator.application.domain.model.PrRequest;
import com.saga.orchestrator.application.domain.model.PrStatus;
import com.saga.orchestrator.application.domain.repository.PrRequestRepository;
import com.saga.orchestrator.application.orchestration.WorkflowOrchestrator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class PrRequestCommandImpl implements PullRequestCommand {
    
    private final PrRequestRepository prRequestRepository;
    private final WorkflowOrchestrator workflowOrchestrator;
    
    @Override
    public PrRequest createPrRequest(PrRequest prRequest) {
        log.info("Creating prRequest..");
        prRequest.setPrRequestStatus(PrStatus.PENDING);
        var persistedprRequest = prRequestRepository.save(prRequest);
        workflowOrchestrator.createprRequest(persistedprRequest);
        return persistedprRequest;
    }
    
}
