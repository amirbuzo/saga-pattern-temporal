package com.saga.orchestrator.application.factory.impl;

import com.saga.orchestrator.application.command.PullRequestCommand;
import com.saga.orchestrator.application.factory.PrRequestFactory;
import com.saga.orchestrator.application.query.PrRequestQuery;

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
