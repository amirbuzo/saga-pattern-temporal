package com.saga.orchestrator.application.query;

import com.saga.orchestrator.application.domain.model.PrRequest;

public interface PrRequestQuery {
    PrRequest getprRequest(String prRequestId);
}
