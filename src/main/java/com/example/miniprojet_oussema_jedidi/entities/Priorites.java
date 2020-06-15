package com.example.miniprojet_oussema_jedidi.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Priorites implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2835399304412479220L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private int valsync;
	
	@OneToMany(mappedBy = "priorites" , fetch = FetchType.LAZY)
	private List<Interventions> interventionList = new ArrayList<Interventions>();

	public Priorites() {
	}

	public Priorites(String nom, int valsync) {
		this.nom = nom;
		this.valsync = valsync;
	}

	
}
