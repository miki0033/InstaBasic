package com.instabasic.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.instabasic.backend.common.util.ErrorHandler;
import com.instabasic.backend.model.Resource;
import com.instabasic.backend.service.ResourceService;

@RestController
public class ResourceController {
    static final Logger logger = LogManager.getLogger(FollowController.class.getName());
    @Autowired
    private ResourceService resourceService;

    // C
    @PostMapping("/v1/createResource")
    public ResponseEntity<String> createResource(@RequestBody Resource resourceRequest) {
        try {
            Resource newResource = resourceService.createResource(resourceRequest.getType(), resourceRequest.getUrl());
            if (newResource != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Risorsa creata con successo");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Impossibile creare la risorsa");
            }

        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            return ResponseEntity.status(err.getStatus()).body(err.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    // R
    @GetMapping("/v1/getResourceById/{resourceId}")
    public ResponseEntity<Resource> getResource(@PathVariable Long resourceId) {
        try {
            Resource resource = resourceService.findById(resourceId);
            return ResponseEntity.status(200).body(resource);
        } catch (ErrorHandler err) {
            logger.warn(err.getMessage());
            throw new ResponseStatusException(err.getStatus(), err.getMessage(), err);
        } catch (Exception e) {
            logger.error("An unexpected error occurred", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", e);
        }
    }
    // U

    // D
}
