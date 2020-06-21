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
@Table(name= "AIRWAY_COMPANY")
@Getter
@Setter
@NoArgsConstructor
public class AirWayCompany implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9159694129788776917L;

	@Id
	@Column(name ="ID")
	@GeneratedValue
	@SequenceGenerator(name ="SEQ_AIRWAY_COMPANY",sequenceName ="SEQ_AIRWAY_COMPANY")
	private Long id;
	
	@Column(name ="NAME")
	private String name;
}
