package com.company.enroller.persistence;

import java.util.Collection;

import com.company.enroller.model.Participant;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Meeting;

@Component("meetingService")
public class MeetingService {

	DatabaseConnector connector;
	Session connectorSession;

	public MeetingService() {
		connector = DatabaseConnector.getInstance();
		connectorSession = connector.getSession();
	}

	public Collection<Meeting> getAllMeetings() {
		String hql = "FROM Meeting";
		Query query = connectorSession.createQuery(hql);
		return query.list();
	}

	public Meeting getMeetingById(long meetingId) {
		return connectorSession.get(Meeting.class, meetingId);
	}
}
