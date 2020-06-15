package com.example.miniprojet_oussema_jedidi.entities;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Employes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4900343724379196672L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	@NotNull
	private String login;
	@NotNull
	private String pwd;
	@NotNull
	private String prenom;
	@NotNull
	private String nom;
	@NotNull
	private String email;
	@NotNull
	private boolean actif;
	@NotNull
	private int valsync;
	
	@ManyToMany()
	@JoinTable(
	  name = "employes_interventions", 
	  joinColumns = @JoinColumn(name = "employe_id"), 
	  inverseJoinColumns = @JoinColumn(name = "intervention_id"))
	 private 	List<Interventions> interventionsList = new ArrayList<Interventions>(); 
	
	

	public Employes() {
	}

	

}
