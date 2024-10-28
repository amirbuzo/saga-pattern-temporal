package com.saga.orchestrator.application.command;

import com.saga.orchestrator.application.domain.model.PrRequest;

public interface PullRequestCommand {
  PrRequest createPrRequest(PrRequest prRequest);
}
