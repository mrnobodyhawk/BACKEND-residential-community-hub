package com.communityhub.maintenanceService.service;

import com.communityhub.maintenanceService.model.MaintenanceRequest;
import com.communityhub.maintenanceService.repository.MaintenanceRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceRequestServiceImpl implements MaintenanceRequestService {

    @Autowired
    private MaintenanceRequestRepository repository;

    @Override
    public MaintenanceRequest raiseMaintenanceRequest(MaintenanceRequest request) {
        if ("RESIDENT".equalsIgnoreCase(request.getUserType())) {
            request.setStatus("PENDING");
        }
        return repository.save(request);
    }

    @Override
    public List<MaintenanceRequest> getMaintenanceRequestsByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<MaintenanceRequest> getAllMaintenanceRequests() {
        return repository.findAll();
    }

    @Override
    public MaintenanceRequest updateRequestStatus(Long requestId, String newStatus) {
        MaintenanceRequest request = repository.findById(requestId).orElse(null);
        if (request != null) {
            request.setStatus(newStatus);
            return repository.save(request);
        }
        return null;
    }
    
    @Override
    public boolean deleteMaintenanceRecord(Long requestId) {
    	Optional<MaintenanceRequest> optionalVisitorEntry = repository.findById(requestId);
    	if (optionalVisitorEntry.isPresent()) {
            repository.deleteById(requestId);
            return true;
        } else {
            return false;
        }
    }
    
    
}

