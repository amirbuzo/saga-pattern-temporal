package com.ikubinfo.orchestrator.application.query;

import com.ikubinfo.orchestrator.application.domain.model.PrRequest;

public interface PrRequestQuery {
  PrRequest getprRequest(String prRequestId);
}
