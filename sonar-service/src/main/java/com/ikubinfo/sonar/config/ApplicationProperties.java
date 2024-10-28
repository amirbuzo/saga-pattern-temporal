package com.ikubinfo.sonar.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationProperties {
  private String target;
  private String workflow;
  private String workflowTaskQueue;
}
