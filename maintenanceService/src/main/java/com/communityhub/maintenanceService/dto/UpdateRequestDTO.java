package com.communityhub.maintenanceService.dto;

public class UpdateRequestDTO {
    private Long requestId;
    private String newStatus;

    // Getters and setters
    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

	
}