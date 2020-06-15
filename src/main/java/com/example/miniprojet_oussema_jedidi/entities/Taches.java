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
public class Taches implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8550505962307610045L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String refernce;
	private String nom;
	private long duree;
	private long prixheure;
	private Date dateaction;
	
	@ManyToOne()
	@JoinColumn(name= "intervention_id")
	private Interventions intervention;
	private int valsync;

	public Taches() {
	}

	

	
}
