package com.saga.orchestrator.application.domain.repository;

import com.saga.orchestrator.application.domain.model.PrRequest;

/** Domain repository for the prRequest. */
public interface PrRequestRepository {
  PrRequest save(PrRequest prRequest);

  PrRequest get(String prRequestId);
}
