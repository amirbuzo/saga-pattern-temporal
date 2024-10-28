package com.ikubinfo.orchestrator.application.factory.impl;

import com.ikubinfo.orchestrator.application.command.PullRequestCommand;
import com.ikubinfo.orchestrator.application.factory.PrRequestFactory;
import com.ikubinfo.orchestrator.application.query.PrRequestQuery;

public class PrRequestFactoryImpl implements PrRequestFactory {

  private final PullRequestCommand pullRequestCommand;
  private final PrRequestQuery prQuery;

  public PrRequestFactoryImpl(PullRequestCommand prReqCommand, PrRequestQuery prReqQuery) {
    this.pullRequestCommand = prReqCommand;
    this.prQuery = prReqQuery;
  }

  public PullRequestCommand getOrderCommand() {
    return pullRequestCommand;
  }

  @Override
  public PrRequestQuery getOrderQuery() {
    return prQuery;
  }
}
