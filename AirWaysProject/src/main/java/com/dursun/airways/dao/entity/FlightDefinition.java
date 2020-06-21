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
@Table(name= "FLIGHT_DEFINITION")
@Getter
@Setter
@NoArgsConstructor
public class FlightDefinition implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 2108816584924716964L;

	@Id
	@Column(name ="FLIGHT_ID")
	@GeneratedValue
	@SequenceGenerator(name ="SEQ_FLIGHT_DEFINITION",sequenceName ="SEQ_FLIGHT_DEFINITION")
	private Long flightID;
	
	@Column(name ="ROUTE_ID")
	private Long routeID;
	
	@Column(name ="COMPANY_ID")
	private Long companyID;
	
	@Column(name ="DETAILS_ID")
	private Long detailsID;
}
