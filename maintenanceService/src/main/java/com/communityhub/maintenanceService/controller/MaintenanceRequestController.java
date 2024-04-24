package com.communityhub.maintenanceService.controller;

import com.communityhub.maintenanceService.dto.UpdateRequestDTO;
import com.communityhub.maintenanceService.model.MaintenanceRequest;
import com.communityhub.maintenanceService.service.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communityhub/user/maintenance")
public class MaintenanceRequestController {

    @Autowired
    private MaintenanceRequestService service;

    @PostMapping("/raise")
    public MaintenanceRequest raiseMaintenanceRequest(@RequestBody MaintenanceRequest request) {
        return service.raiseMaintenanceRequest(request);
    }

    @GetMapping("/user/{userId}")
    public List<MaintenanceRequest> getMaintenanceRequestsByUserId(@PathVariable Long userId) {
        return service.getMaintenanceRequestsByUserId(userId);
    }

    @GetMapping("/all")
    public List<MaintenanceRequest> getAllMaintenanceRequests() {
        return service.getAllMaintenanceRequests();
    }

//    @PutMapping("/update/{requestId}")
//    public MaintenanceRequest updateRequestStatus(@PathVariable Long requestId, @RequestParam String newStatus) {
//        return service.updateRequestStatus(requestId, newStatus);
//    }
    
    @PutMapping("/update")
    public MaintenanceRequest updateRequestStatus(@RequestBody UpdateRequestDTO updateRequestDTO) {
        Long requestId = updateRequestDTO.getRequestId();
        String newStatus = updateRequestDTO.getNewStatus();
        return service.updateRequestStatus(requestId, newStatus);
    }
    
    @DeleteMapping("/delete/{requestId}")
    public String deleteMaintenanceRecord(@PathVariable Long requestId) {
    	boolean isDeleted = service.deleteMaintenanceRecord(requestId);
        if (isDeleted) {
            return "Maintenance request with ID " + requestId + " has been successfully deleted.";
        } else {
            return "Maintenance request with ID " + requestId + " does not exist.";
        }
    }
    
  
}

