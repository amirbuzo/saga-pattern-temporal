package com.ikubinfo.bitbucket.application.service.impl;

import java.util.UUID;

import com.ikubinfo.bitbucket.application.service.PullRequestService;

public class PullRequestServiceImpl implements PullRequestService {
  @Override
  public String checkPath(String path) {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }
}
