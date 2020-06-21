package com.dursun.airways.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name= "TICKET_SALES")
@Getter
@Setter
@NoArgsConstructor
public class TicketSale implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -5185832703721316536L;

	@Id
	@Column(name ="ID")
	@GeneratedValue
	private Long id;
	
	@Column(name ="FLIGHT_ID")
	private Long flightID;
	
	@Column(name ="STATU")
	private String statu;
	
	@Column(name ="CARD_NUMBER")
	private String cartNumber;
	
	@Column(name ="AMOUNT")
	private BigDecimal amount;
	
	@Column(name ="NAME")
	private String name;
	
	@Column(name ="SURNAME")
	private String surname;

}
