package com.communityhub.maintenanceService.service;

import com.communityhub.maintenanceService.model.MaintenanceRequest;

import java.util.List;

public interface MaintenanceRequestService {
    MaintenanceRequest raiseMaintenanceRequest(MaintenanceRequest request);
    List<MaintenanceRequest> getMaintenanceRequestsByUserId(Long userId);
    List<MaintenanceRequest> getAllMaintenanceRequests();
    MaintenanceRequest updateRequestStatus(Long requestId, String newStatus);
	boolean deleteMaintenanceRecord(Long requestId);
}
