package com.company.enroller.persistence;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Participant;

import javax.transaction.Transactional;

@Component("participantService")
public class ParticipantService {

	DatabaseConnector connector;
	Session connectorSession;

	public ParticipantService() {
		connector = DatabaseConnector.getInstance();
		connectorSession = connector.getSession();
	}

	public Collection<Participant> getAllParticipants() {
		String hql = "FROM Participant";
		Query query = connectorSession.createQuery(hql);
		return query.list();
	}

	public Participant findByLogin(String login) {
		return connectorSession.get(Participant.class, login);
	}

	@Transactional
	public boolean addNewParticipant(Participant newParticipantToAdd) {
		if (connectorSession.get(Participant.class, newParticipantToAdd.getLogin()) != null) {
			return false;
		} else {
			connectorSession.save(newParticipantToAdd);
			return true;
		}
	}

	@Transactional
	public boolean deleteParticipantByLogin(String participantLogin) {
		if (connectorSession.get(Participant.class, participantLogin) == null) {
			return false;
		} else {
			Object userToDelete = connectorSession.get(Participant.class, participantLogin);
			connectorSession.delete(userToDelete);
			return true;
		}
	}
}
