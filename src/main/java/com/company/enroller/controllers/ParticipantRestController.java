package com.company.enroller.controllers;

import java.net.URI;
import java.util.Collection;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.enroller.model.Participant;
import com.company.enroller.persistence.ParticipantService;

@RestController
@RequestMapping("/participants")
public class ParticipantRestController {

	@Autowired
	ParticipantService participantService;

	private static final Logger logger = Logger.getLogger("ParticipantRestController DebugLog: ");

	@GetMapping("")
	public ResponseEntity<?> getParticipants() {
		Collection<Participant> participants = participantService.getAll();
		logger.info("Get all participants.");
		return new ResponseEntity<Collection<Participant>>(participants, HttpStatus.OK);
	}

	@GetMapping("/{participantLogin}")
	public ResponseEntity<?> getParticipant(@PathVariable("participantLogin") String login) {
		Participant participant = participantService.findByLogin(login);
		if (participant == null) {
			logger.info("Participant: " + login + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.info("Get participant: " + login);
		return new ResponseEntity<>(participant, HttpStatus.OK);
	}

	@PostMapping("/add")
	ResponseEntity<Participant> createNewParticipant(@RequestBody Participant newParticipantToAdd) {
		boolean result = participantService.addNewParticipant(newParticipantToAdd);
		if (!result) {
			logger.info("Participant CAN NOT be created!");
			return new ResponseEntity(
					"Unable to create. A participant with login " + newParticipantToAdd.getLogin() + " already exist.",
					HttpStatus.CONFLICT);
		} else {
			logger.info("New participant was created!");
			return ResponseEntity.created(URI.create("/" + newParticipantToAdd.getLogin())).body(newParticipantToAdd);
		}
	}

	@DeleteMapping("/{participantLogin}")
	ResponseEntity<Participant> deleteParticipant(@PathVariable String participantLoginToDelete) {
		boolean result = participantService.deleteParticipantByLogin(participantLoginToDelete);
		if (!result) {
			logger.info("Participant already DOES NOT exist!");
			return new ResponseEntity(
					"Unable to delete. No participant with login " + participantLoginToDelete,
					HttpStatus.CONFLICT);
		} else {
			logger.info("Participant with login: " + participantLoginToDelete + " was deleted!");
			return ResponseEntity.ok().build();
		}
	}

}
