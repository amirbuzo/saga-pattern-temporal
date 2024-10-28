package com.ikubinfo.orchestrator.application.command;

import com.ikubinfo.orchestrator.application.domain.model.PrRequest;

public interface PullRequestCommand {
  PrRequest createPrRequest(PrRequest prRequest);
}
