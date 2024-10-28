package com.saga.build.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.saga.build.application.domain.model.BuildArtifactDto;
import com.saga.build.persistence.repository.document.BuildArtifactDocument;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BuildPersistableMapper {

  public static final BuildPersistableMapper MAPPER =
      Mappers.getMapper(BuildPersistableMapper.class);

  @Mapping(source = "buildId", target = "id")
  public abstract BuildArtifactDocument map(BuildArtifactDto build);

  @Mapping(source = "id", target = "buildId")
  public abstract BuildArtifactDto map(BuildArtifactDocument buildPersistable);
}
