package com.ikubinfo.build.application.domain.repository;

import java.util.List;

import com.ikubinfo.build.application.domain.model.BuildArtifactDto;

/** Domain repository for the BuildArtifactDto. */
public interface BuildArtifactRepository {
  BuildArtifactDto save(BuildArtifactDto build);

  List<BuildArtifactDto> getAll();
}
