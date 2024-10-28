package com.saga.orchestrator.application.factory;

import com.saga.orchestrator.application.command.PullRequestCommand;
import com.saga.orchestrator.application.query.PrRequestQuery;

public interface PrRequestFactory {

  PullRequestCommand getOrderCommand();

  PrRequestQuery getOrderQuery();
}
