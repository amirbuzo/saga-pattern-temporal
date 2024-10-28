package com.ikubinfo.orchestrator.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.ikubinfo.orchestrator.application.domain.model.PrRequest;
import com.ikubinfo.orchestrator.persistence.repository.model.PrRequestDocument;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PrRequestMapper {

  public static final PrRequestMapper MAPPER =
      Mappers.getMapper(PrRequestMapper.class);

  @Mapping(source = "prRequestId", target = "id")
  public abstract PrRequestDocument map(PrRequest prRequest);

  @Mapping(source = "id", target = "prRequestId")
  public abstract PrRequest map(PrRequestDocument prRequestPersistable);
}
