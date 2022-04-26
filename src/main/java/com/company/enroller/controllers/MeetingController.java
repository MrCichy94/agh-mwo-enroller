package com.company.enroller.controllers;

import com.company.enroller.model.Meeting;
import com.company.enroller.persistence.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.logging.Logger;

@RestController
@RequestMapping("/meetings")
public class MeetingController {

    @Autowired
    MeetingService meetingService;

    private static final Logger logger = Logger.getLogger("MeetingController DebugLog: ");

    @GetMapping("")
    public ResponseEntity<?> getAllMeetings() {
        Collection<Meeting> meetings = meetingService.getAllMeetings();
        logger.info("Get all meetings.");
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<?> getMeetingById(@PathVariable("meetingId") long meetingId) {
        Meeting meeting = meetingService.getMeetingById(meetingId);
        logger.info("Get meeting with id: " + meetingId);
        return new ResponseEntity<>(meeting, HttpStatus.OK);
    }

}
