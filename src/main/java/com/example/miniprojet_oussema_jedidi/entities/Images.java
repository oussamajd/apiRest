package com.example.miniprojet_oussema_jedidi.entities;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Data;

@Entity
@Data
public class Images implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7430474159270281682L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private byte[] img;
	private Date dateCapture;
	
	@ManyToOne()
	@JoinColumn(name="intervention_id")
	private Interventions interventions;
	
	
	private int valsync;

	public Images() {
	}

	

	
	
}
