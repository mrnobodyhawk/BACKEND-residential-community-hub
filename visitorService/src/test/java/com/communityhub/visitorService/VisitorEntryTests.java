package com.communityhub.visitorService;

import org.junit.jupiter.api.Test;

import com.communityhub.visitorService.model.VisitorEntry;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class VisitorEntryTests {

    @Test
    void testGetterSetter() {
        VisitorEntry visitorEntry = new VisitorEntry();
        Long visitorId = 1001L;
        Long userId = 2001L;
        String userType = "Admin";
        String visitorFullName = "Prince";
        String buildingNumber = "Building A";
        String purpose = "Meeting";
        Date checkInDate = new Date();
        Date checkOutDate = new Date();

        visitorEntry.setVisitorId(visitorId);
        visitorEntry.setUserId(userId);
        visitorEntry.setUserType(userType);
        visitorEntry.setVisitorFullName(visitorFullName);
        visitorEntry.setBuildingNumber(buildingNumber);
        visitorEntry.setPurpose(purpose);
        visitorEntry.setCheckInDate(checkInDate);
        visitorEntry.setCheckOutDate(checkOutDate);

        assertEquals(visitorId, visitorEntry.getVisitorId());
        assertEquals(userId, visitorEntry.getUserId());
        assertEquals(userType, visitorEntry.getUserType());
        assertEquals(visitorFullName, visitorEntry.getVisitorFullName());
        assertEquals(buildingNumber, visitorEntry.getBuildingNumber());
        assertEquals(purpose, visitorEntry.getPurpose());
        assertEquals(checkInDate, visitorEntry.getCheckInDate());
        assertEquals(checkOutDate, visitorEntry.getCheckOutDate());
    }

    @Test
    void testNoArgsConstructor() {
        VisitorEntry visitorEntry = new VisitorEntry();
        assertNotNull(visitorEntry);
    }
}
