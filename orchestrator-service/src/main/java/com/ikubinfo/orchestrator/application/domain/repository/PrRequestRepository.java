package com.ikubinfo.orchestrator.application.domain.repository;

import com.ikubinfo.orchestrator.application.domain.model.PrRequest;

/** Domain repository for the prRequest. */
public interface PrRequestRepository {
  PrRequest save(PrRequest prRequest);

  PrRequest get(String prRequestId);
}
