package com.company.enroller.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "participant")
public class Participant {

	@Id
	private String login;

	@Column
	private String password;

}
