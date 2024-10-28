package com.ikubinfo.bitbucket.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ikubinfo.bitbucket.application.domain.model.PullRequestDto;
import com.ikubinfo.bitbucket.application.domain.repository.PullRequestRepository;
import com.ikubinfo.bitbucket.persistence.mapper.PullRequestPersistableMapper;
import com.ikubinfo.bitbucket.persistence.repository.mongo.PullRequestMongoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PullRequestRepositoryImpl implements PullRequestRepository {

  private final PullRequestMongoRepository pullRequestMongoRepository;

  public PullRequestRepositoryImpl(PullRequestMongoRepository pullRequestJpaRepository) {
    this.pullRequestMongoRepository = pullRequestJpaRepository;
  }

  @Override
  public PullRequestDto save(PullRequestDto pullRequest) {
    log.info("Saving PullRequestDto");
    var pullRequestPersistable = PullRequestPersistableMapper.MAPPER.map(pullRequest);
    pullRequestPersistable = pullRequestMongoRepository.save(pullRequestPersistable);

    log.info("PullRequestDto saved, id {}", pullRequestPersistable.getId());
    return PullRequestPersistableMapper.MAPPER.map(pullRequestPersistable);
  }

  @Override
  public List<PullRequestDto> getAll() {
    log.info("Getting all prs from DB..");
    var pullRequestPersistables = pullRequestMongoRepository.findAll();
    return pullRequestPersistables.stream().map(PullRequestPersistableMapper.MAPPER::map).toList();
  }

  @Override
  public Optional<PullRequestDto> getByprRequestId(String prRequestId) {
    return pullRequestMongoRepository.findByprRequestId(prRequestId).stream()
        .map(PullRequestPersistableMapper.MAPPER::map)
        .findFirst();
  }
}
