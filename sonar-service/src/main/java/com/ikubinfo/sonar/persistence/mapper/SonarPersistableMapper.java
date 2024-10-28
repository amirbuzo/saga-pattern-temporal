package com.ikubinfo.sonar.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.ikubinfo.sonar.application.domain.model.SonarResultDto;
import com.ikubinfo.sonar.persistence.repository.model.SonarResultDocument;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SonarPersistableMapper {

  public static final SonarPersistableMapper MAPPER =
      Mappers.getMapper(SonarPersistableMapper.class);

  @Mapping(source = "buildId", target = "id")
  public abstract SonarResultDocument map(SonarResultDto build);

  @Mapping(source = "id", target = "buildId")
  public abstract SonarResultDto map(SonarResultDocument buildPersistable);
}
