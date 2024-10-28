package com.ikubinfo.orchestrator.application.factory;

import com.ikubinfo.orchestrator.application.command.PullRequestCommand;
import com.ikubinfo.orchestrator.application.query.PrRequestQuery;

public interface PrRequestFactory {

  PullRequestCommand getOrderCommand();

  PrRequestQuery getOrderQuery();
}
