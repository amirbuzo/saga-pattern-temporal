package com.ikubinfo.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Jira DTO object */
@Setter
@Getter
@ToString
public class JiraTaskDTO {
  private String jiraTaskId;
  private String branchId;
  private String user;
  private String path;
  private String sonarId;
 }
