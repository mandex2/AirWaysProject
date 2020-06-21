package com.dursun.airways.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name= "FLIGHT_DEFINITION_DETAILS")
@Getter
@Setter
@NoArgsConstructor
public class FlightDefinitionDetails implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1373114474365708552L;

	@Id
	@Column(name ="ID")
	@GeneratedValue
	@SequenceGenerator(name ="SEQ_FLIGHT_DEFINITION_DETAILS",sequenceName ="SEQ_FLIGHT_DEFINITION_DETAILS")
	private Long id;
	
	@Column(name ="AMOUNT")
	private BigDecimal amount;
	
	@Column(name ="QUOTA")
	private Integer quota;
	
	@Column(name ="FLIGHT_DATE")
	private Date flightDate;
	
	@Column(name ="TICKET_COUNT")
	private Integer ticketCount;
}
