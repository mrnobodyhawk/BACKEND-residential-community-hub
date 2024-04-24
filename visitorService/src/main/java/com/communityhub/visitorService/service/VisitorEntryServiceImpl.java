package com.communityhub.visitorService.service;

import com.communityhub.visitorService.model.VisitorEntry;
import com.communityhub.visitorService.repository.VisitorEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitorEntryServiceImpl implements VisitorEntryService {

    @Autowired
    private VisitorEntryRepository repository;

    @Override
    public VisitorEntry createVisitorEntry(VisitorEntry entry) {
        return repository.save(entry);
    }

    @Override
    public List<VisitorEntry> getAllVisitorEntries() {
        return repository.findAll();
    }

    @Override
    public List<VisitorEntry> getVisitorEntriesByUserId(Long userId) {
        // Assuming userId is not stored in the visitor entry table, you may need to adjust this
        return repository.findByUserId(userId);
    }

    @Override
    public boolean deleteVisitorEntry(Long visitorId) {
        Optional<VisitorEntry> optionalVisitorEntry = repository.findById(visitorId);
        if (optionalVisitorEntry.isPresent()) {
            repository.deleteById(visitorId);
            return true;
        } else {
            return false;
        }
    }
}

