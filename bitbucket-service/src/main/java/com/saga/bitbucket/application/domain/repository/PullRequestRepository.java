package com.saga.bitbucket.application.domain.repository;

import java.util.List;
import java.util.Optional;

import com.saga.bitbucket.application.domain.model.PullRequestDto;

/** Domain repository for the PullRequestDto. */
public interface PullRequestRepository {
  PullRequestDto save(PullRequestDto pullRequest);

  List<PullRequestDto> getAll();

  Optional<PullRequestDto> getByprRequestId(String prRequestId);
}
