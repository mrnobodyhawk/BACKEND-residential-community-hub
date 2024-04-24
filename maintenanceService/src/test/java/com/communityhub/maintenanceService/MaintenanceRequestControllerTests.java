package com.communityhub.maintenanceService;


import com.communityhub.maintenanceService.controller.MaintenanceRequestController;
import com.communityhub.maintenanceService.dto.UpdateRequestDTO;
import com.communityhub.maintenanceService.model.MaintenanceRequest;
import com.communityhub.maintenanceService.service.MaintenanceRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MaintenanceRequestControllerTests {

    private MockMvc mockMvc;

    @Mock
    private MaintenanceRequestService service;

    @InjectMocks
    private MaintenanceRequestController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testRaiseMaintenanceRequest() throws Exception {
        MaintenanceRequest request = new MaintenanceRequest();
        request.setUserId(1L);
        request.setDescription("Test description");

        when(service.raiseMaintenanceRequest(any(MaintenanceRequest.class))).thenReturn(request);

        mockMvc.perform(post("/communityhub/user/maintenance/raise")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":1,\"description\":\"Test description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.description").value("Test description"));
    }

    @Test
    public void testGetMaintenanceRequestsByUserId() throws Exception {
        List<MaintenanceRequest> requests = new ArrayList<>();
        requests.add(new MaintenanceRequest());
        requests.add(new MaintenanceRequest());

        when(service.getMaintenanceRequestsByUserId(anyLong())).thenReturn(requests);

        mockMvc.perform(get("/communityhub/user/maintenance/user/{userId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetAllMaintenanceRequests() throws Exception {
        List<MaintenanceRequest> requests = new ArrayList<>();
        requests.add(new MaintenanceRequest());
        requests.add(new MaintenanceRequest());

        when(service.getAllMaintenanceRequests()).thenReturn(requests);

        mockMvc.perform(get("/communityhub/user/maintenance/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testUpdateRequestStatus() throws Exception {
        MaintenanceRequest request = new MaintenanceRequest();
        request.setUserId(1L);
        request.setDescription("Test description");

        UpdateRequestDTO updateRequestDTO = new UpdateRequestDTO();
        updateRequestDTO.setRequestId(1L);
        updateRequestDTO.setNewStatus("In Progress");

        when(service.updateRequestStatus(anyLong(), anyString())).thenReturn(request);

        mockMvc.perform(put("/communityhub/user/maintenance/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"requestId\":1,\"newStatus\":\"In Progress\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.description").value("Test description"));
    }

}
