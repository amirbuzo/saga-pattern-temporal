package com.saga.bitbucket.application.service.impl;

import java.util.UUID;

import com.saga.bitbucket.application.service.PullRequestService;

public class PullRequestServiceImpl implements PullRequestService {
  @Override
  public String checkPath(String path) {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }
}
