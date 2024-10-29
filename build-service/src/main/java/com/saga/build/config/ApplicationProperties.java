package com.saga.build.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationProperties {
    private String target;
    private String workflow;
    private boolean simulateError;
}
