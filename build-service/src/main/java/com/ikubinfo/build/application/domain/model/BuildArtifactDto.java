package com.ikubinfo.build.application.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** BuildArtifactDto Domain object */
@Setter
@Getter
@ToString
@Builder
public class BuildArtifactDto {
  private String buildId;
  private String prRequestId;
  private String branchId;
  private String trackingId;
  private String path;
}
