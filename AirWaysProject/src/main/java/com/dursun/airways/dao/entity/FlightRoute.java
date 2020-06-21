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
@Table(name= "FLIGHT_ROUTE")
@Getter
@Setter
@NoArgsConstructor
public class FlightRoute implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -8938029173954735877L;

	@Id
	@Column(name ="ID")
	@GeneratedValue
	@SequenceGenerator(name ="SEQ_FLIGHT_ROUTE",sequenceName ="SEQ_FLIGHT_ROUTE")
	private Long id;
	
	@Column(name ="AIRPORT_FROM")
	private String airPortFrom;
	
	@Column(name ="AIRPORT_TO")
	private String airPortTo;
}
