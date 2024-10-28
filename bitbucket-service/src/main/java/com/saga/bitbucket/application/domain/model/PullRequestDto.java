package com.saga.bitbucket.application.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** PullRequestDto Domain object */
@Setter
@Getter
@ToString
@Builder
public class PullRequestDto {
  private String pullRequestId;
  private String prRequestId;
  private String branchId;
  private String path;
  private String externalId;
  private PullRequestStatus pullRequestStatus;
}
