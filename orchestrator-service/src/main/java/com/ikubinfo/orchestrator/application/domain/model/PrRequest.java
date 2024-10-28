package com.ikubinfo.orchestrator.application.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** PrRequestDto Domain object */
@Setter
@Getter
@ToString
public class PrRequest {
  private String prRequestId;
  private String branchId;
  private String user;
  private String path;
  private PrStatus prRequestStatus;
}
