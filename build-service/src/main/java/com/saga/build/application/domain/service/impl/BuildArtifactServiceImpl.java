package com.saga.build.application.domain.service.impl;

import java.util.UUID;

import com.saga.build.application.domain.service.BuildService;
import com.saga.build.config.ApplicationProperties;
import com.saga.common.error.ServiceException;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Mock implementation to represent call to external service to initiate
 * building
 */
@Slf4j
@RequiredArgsConstructor
public class BuildArtifactServiceImpl implements BuildService {
    
    private final ApplicationProperties applicationProperties;
    
    @SneakyThrows
    @Override
    public String buildArtifact(String path) {
        // Simulate Error condition
        if (applicationProperties.isSimulateError()) {
            log.error("Error occurred while building..");
            throw new ServiceException("Error executing Service");
        }
        UUID uuid = UUID.randomUUID();
        Thread.sleep(2000);
        return uuid.toString();
    }
}
