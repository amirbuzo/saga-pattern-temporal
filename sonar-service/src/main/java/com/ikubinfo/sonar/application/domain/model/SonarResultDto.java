package com.ikubinfo.sonar.application.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** SonarResultDto Domain object */
@Setter
@Getter
@ToString
@Builder
public class SonarResultDto {
  private String prRequestId;
  private String buildId;
  private String taskId;
  private String branchId;
  private String path;
  private SonarStatus sonarStatus;
  
 
}
