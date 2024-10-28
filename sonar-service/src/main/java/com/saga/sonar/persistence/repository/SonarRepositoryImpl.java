package com.saga.sonar.persistence.repository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import com.saga.sonar.application.domain.model.SonarResultDto;
import com.saga.sonar.application.domain.repository.SonarRepository;
import com.saga.sonar.persistence.mapper.SonarPersistableMapper;
import com.saga.sonar.persistence.repository.mongo.SonarMongoRepository;

@Slf4j
public class SonarRepositoryImpl implements SonarRepository {

  private final SonarMongoRepository buildMongoRepository;

  public SonarRepositoryImpl(SonarMongoRepository buildJpaRepository) {
    this.buildMongoRepository = buildJpaRepository;
  }

  @Override
  public SonarResultDto save(SonarResultDto build) {
    log.info("Saving SonarResultDto");
    var buildPersistable = SonarPersistableMapper.MAPPER.map(build);
    buildPersistable = buildMongoRepository.save(buildPersistable);
    log.info("SonarResultDto saved, id {}", buildPersistable.getId());
    return SonarPersistableMapper.MAPPER.map(buildPersistable);
  }

  @Override
  public List<SonarResultDto> getAll() {
    log.info("Getting all build from DB..");
    var buildPersistables = buildMongoRepository.findAll();
    return buildPersistables.stream().map(SonarPersistableMapper.MAPPER::map).toList();
  }

  @Override
  public Optional<SonarResultDto> getByprRequestId(String prRequestId) {
    return buildMongoRepository.findByprRequestId(prRequestId).stream()
        .map(SonarPersistableMapper.MAPPER::map)
        .findFirst();
  }

@Override
public Optional<SonarResultDto> getByBranchId(String prRequestId) {
	 return buildMongoRepository.findByBranchId(prRequestId).stream()
		        .map(SonarPersistableMapper.MAPPER::map)
		        .findFirst();
}
}
