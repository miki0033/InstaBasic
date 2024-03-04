package com.instabasic.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.instabasic.backend.common.util.ErrorHandler;
import com.instabasic.backend.model.Resource;
import com.instabasic.backend.repository.ResourceRepository;

@Service
public class ResourceService {
    ResourceRepository resourceRepository;

    // C
    public Resource createResource(String type, String url) {
        Resource newResource = new Resource(type, url);
        return resourceRepository.save(newResource);
    }

    // R
    public Resource findById(Long id) {
        if (id != null) {
            Optional<Resource> optional = resourceRepository.findById(id);
            if (optional.isPresent()) {
                return optional.get();
            } else {
                throw new ErrorHandler(404, "Resource not found");
            }
        } else {
            throw new ErrorHandler(404, "Id null");
        }
    }

    // U

    // D
}
