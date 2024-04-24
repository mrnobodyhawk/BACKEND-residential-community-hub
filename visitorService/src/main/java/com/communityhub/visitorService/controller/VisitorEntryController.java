package com.communityhub.visitorService.controller;

import com.communityhub.visitorService.model.VisitorEntry;
import com.communityhub.visitorService.service.VisitorEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communityhub/user/visitors")
public class VisitorEntryController {

    @Autowired
    private VisitorEntryService service;

    @PostMapping("/create")
    public VisitorEntry createVisitorEntry(@RequestBody VisitorEntry entry) {
        return service.createVisitorEntry(entry);
    }

    @GetMapping("/all")
    public List<VisitorEntry> getAllVisitorEntries() {
        return service.getAllVisitorEntries();
    }

    @GetMapping("/user/{userId}")
    public List<VisitorEntry> getAllVisitorEntriesByUser(
            @PathVariable Long userId) {
        return service.getVisitorEntriesByUserId(userId);
    }

    @DeleteMapping("/delete/{visitorId}")
    public String deleteVisitorEntry(@PathVariable Long visitorId) {
        boolean isDeleted = service.deleteVisitorEntry(visitorId);
        if (isDeleted) {
            return "Visitor entry with ID " + visitorId + " has been successfully deleted.";
        } else {
            return "Visitor entry with ID " + visitorId + " does not exist.";
        }
    }

}
