package com.communityhub.maintenanceService;

import com.communityhub.maintenanceService.model.MaintenanceRequest;
import com.communityhub.maintenanceService.repository.MaintenanceRequestRepository;
import com.communityhub.maintenanceService.service.MaintenanceRequestServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MaintenanceRequestServiceImplTests {

    @Mock
    private MaintenanceRequestRepository repository;

    @InjectMocks
    private MaintenanceRequestServiceImpl service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRaiseMaintenanceRequest() {
        MaintenanceRequest request = new MaintenanceRequest();
        request.setUserType("RESIDENT");
        
        when(repository.save(request)).thenReturn(request);
        
        MaintenanceRequest result = service.raiseMaintenanceRequest(request);
        
        assertEquals("PENDING", result.getStatus());
        verify(repository, times(1)).save(request);
    }

    @Test
    public void testGetMaintenanceRequestsByUserId() {
        Long userId = 1L;
        List<MaintenanceRequest> expectedRequests = Collections.emptyList();
        
        when(repository.findByUserId(userId)).thenReturn(expectedRequests);
        
        List<MaintenanceRequest> result = service.getMaintenanceRequestsByUserId(userId);
        
        assertEquals(expectedRequests, result);
        verify(repository, times(1)).findByUserId(userId);
    }

    @Test
    public void testGetAllMaintenanceRequests() {
        List<MaintenanceRequest> expectedRequests = Collections.emptyList();
        
        when(repository.findAll()).thenReturn(expectedRequests);
        
        List<MaintenanceRequest> result = service.getAllMaintenanceRequests();
        
        assertEquals(expectedRequests, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testUpdateRequestStatus() {
        Long requestId = 1L;
        String newStatus = "NEW_STATUS";
        MaintenanceRequest request = new MaintenanceRequest();
        request.setStatus("OLD_STATUS");
        
        when(repository.findById(requestId)).thenReturn(Optional.of(request));
        when(repository.save(request)).thenReturn(request);
        
        MaintenanceRequest result = service.updateRequestStatus(requestId, newStatus);
        
        assertEquals(newStatus, result.getStatus());
        verify(repository, times(1)).findById(requestId);
        verify(repository, times(1)).save(request);
    }
}
