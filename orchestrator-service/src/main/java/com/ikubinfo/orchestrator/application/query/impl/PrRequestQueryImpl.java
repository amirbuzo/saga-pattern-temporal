package com.ikubinfo.orchestrator.application.query.impl;

import com.ikubinfo.orchestrator.application.domain.model.PrRequest;
import com.ikubinfo.orchestrator.application.domain.repository.PrRequestRepository;
import com.ikubinfo.orchestrator.application.query.PrRequestQuery;

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
