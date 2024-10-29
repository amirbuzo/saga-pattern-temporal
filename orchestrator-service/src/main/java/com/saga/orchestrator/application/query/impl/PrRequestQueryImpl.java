package com.saga.orchestrator.application.query.impl;

import com.saga.orchestrator.application.domain.model.PrRequest;
import com.saga.orchestrator.application.domain.repository.PrRequestRepository;
import com.saga.orchestrator.application.query.PrRequestQuery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PrRequestQueryImpl implements PrRequestQuery {
    private final PrRequestRepository prRequestRepository;
    
    @Override
    public PrRequest getprRequest(String prRequestId) {
        log.info("Fetching PrRequestDto for id {}", prRequestId);
        return prRequestRepository.get(prRequestId);
    }
}
