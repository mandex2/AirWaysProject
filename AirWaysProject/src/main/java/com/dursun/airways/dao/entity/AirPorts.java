package com.dursun.airways.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "AIRPORTS")
@Getter
@Setter
@NoArgsConstructor
public class AirPorts implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 2093888175906340346L;

	@Id
	@Column(name ="ID")
	@GeneratedValue
	@SequenceGenerator(name ="SEQ_AIRPORTS",sequenceName ="SEQ_AIRPORTS")
	private Long id;
	
	@Column(name ="NAME")
	private String name;
	
	@Column(name ="SHORT_NAME")
	private String shortName;
	
	@Column(name ="CITY")
	private String city;
}
