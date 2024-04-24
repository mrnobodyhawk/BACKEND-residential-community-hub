package com.communityhub.visitorService;

import com.communityhub.visitorService.model.VisitorEntry;
import com.communityhub.visitorService.repository.VisitorEntryRepository;
import com.communityhub.visitorService.service.VisitorEntryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VisitorEntryServiceImplTests {

    @Mock
    private VisitorEntryRepository repository;

    @InjectMocks
    private VisitorEntryServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateVisitorEntry() {
        VisitorEntry entry = new VisitorEntry();
        when(repository.save(entry)).thenReturn(entry);
        VisitorEntry result = service.createVisitorEntry(entry);
        assertEquals(entry, result);
        verify(repository, times(1)).save(entry);
    }

    @Test
    void testGetAllVisitorEntries() {
        List<VisitorEntry> entries = new ArrayList<>();
        when(repository.findAll()).thenReturn(entries);
        List<VisitorEntry> result = service.getAllVisitorEntries();
        assertEquals(entries, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetVisitorEntriesByUserId() {
        Long userId = 1L;
        List<VisitorEntry> entries = new ArrayList<>();
        when(repository.findByUserId(userId)).thenReturn(entries);
        List<VisitorEntry> result = service.getVisitorEntriesByUserId(userId);
        assertEquals(entries, result);
        verify(repository, times(1)).findByUserId(userId);
    }

    @Test
    void testDeleteExistingVisitorEntry() {
        Long visitorId = 1L;
        when(repository.findById(visitorId)).thenReturn(Optional.of(new VisitorEntry()));
        doNothing().when(repository).deleteById(visitorId);
        boolean result = service.deleteVisitorEntry(visitorId);
        assertTrue(result);
        verify(repository, times(1)).deleteById(visitorId);
    }

    @Test
    void testDeleteNonExistingVisitorEntry() {
        Long visitorId = 1L;
        when(repository.findById(visitorId)).thenReturn(Optional.empty());
        boolean result = service.deleteVisitorEntry(visitorId);
        assertFalse(result);
        verify(repository, never()).deleteById(visitorId);
    }
}
