package com.saga.build.persistence.repository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.saga.build.application.domain.model.BuildArtifactDto;
import com.saga.build.application.domain.repository.BuildArtifactRepository;
import com.saga.build.persistence.mapper.BuildPersistableMapper;
import com.saga.build.persistence.repository.mongo.BuildMongoRepository;

@Slf4j
public class BuildRepositoryImpl implements BuildArtifactRepository {

  private final BuildMongoRepository buildMongoRepository;

  public BuildRepositoryImpl(BuildMongoRepository buildMongoRepository) {
    this.buildMongoRepository = buildMongoRepository;
  }

  @Override
  public BuildArtifactDto save(BuildArtifactDto build) {
    log.info("Saving BuildArtifactDto");
    var buildPersistable = BuildPersistableMapper.MAPPER.map(build);
    buildPersistable = buildMongoRepository.save(buildPersistable);
    log.info("BuildArtifactDto saved, id {}", buildPersistable.getId());
    return BuildPersistableMapper.MAPPER.map(buildPersistable);
  }

  @Override
  public List<BuildArtifactDto> getAll() {
    log.info("Getting all builds from DB..");
    var buildPersistables = buildMongoRepository.findAll();
    return buildPersistables.stream().map(BuildPersistableMapper.MAPPER::map).toList();
  }
}
