package com.communityhub.visitorService.service;

import com.communityhub.visitorService.model.VisitorEntry;

import java.util.List;

public interface VisitorEntryService {
    VisitorEntry createVisitorEntry(VisitorEntry entry);
    List<VisitorEntry> getAllVisitorEntries();
    List<VisitorEntry> getVisitorEntriesByUserId(Long userId);
    boolean deleteVisitorEntry(Long visitorId);
}
