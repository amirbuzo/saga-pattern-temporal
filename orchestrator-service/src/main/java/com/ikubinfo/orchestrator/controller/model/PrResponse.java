package com.ikubinfo.orchestrator.controller.model;

import com.ikubinfo.orchestrator.application.domain.model.PrStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class PrResponse {
  private String prRequestId;
  private PrStatus prRequestStatus;
}
