package com.communityhub.visitorService.repository;

import com.communityhub.visitorService.model.VisitorEntry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorEntryRepository extends JpaRepository<VisitorEntry, Long> {

	List<VisitorEntry> findByUserId(Long userId);
}