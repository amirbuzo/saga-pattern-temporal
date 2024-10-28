package com.saga.bitbucket.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.saga.bitbucket.application.domain.model.PullRequestDto;
import com.saga.bitbucket.persistence.repository.document.PullRequestDocument;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PullRequestPersistableMapper {

  public static final PullRequestPersistableMapper MAPPER =
      Mappers.getMapper(PullRequestPersistableMapper.class);

  @Mapping(source = "pullRequestId", target = "id")
  public abstract PullRequestDocument map(PullRequestDto pullRequest);

  @Mapping(source = "id", target = "pullRequestId")
  public abstract PullRequestDto map(PullRequestDocument pullRequestPersistable);
}
