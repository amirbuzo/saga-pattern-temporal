package com.ikubinfo.sonar.application.domain.repository;

import java.util.List;
import java.util.Optional;

import com.ikubinfo.sonar.application.domain.model.SonarResultDto;

/** Domain repository for the SonarResultDto. */
public interface SonarRepository {
  SonarResultDto save(SonarResultDto build);

  List<SonarResultDto> getAll();

  Optional<SonarResultDto> getByprRequestId(String prRequestId);
  Optional<SonarResultDto> getByBranchId(String prRequestId);
}
