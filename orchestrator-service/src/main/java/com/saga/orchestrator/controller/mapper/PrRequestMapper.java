package com.saga.orchestrator.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.saga.orchestrator.application.domain.model.PrRequest;
import com.saga.orchestrator.controller.model.PrRequestDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PrRequestMapper {

  public static final PrRequestMapper MAPPER = Mappers.getMapper(PrRequestMapper.class);

  public abstract PrRequest map(PrRequestDto request);
}
