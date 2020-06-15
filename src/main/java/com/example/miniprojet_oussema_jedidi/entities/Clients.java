package com.example.miniprojet_oussema_jedidi.entities;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Data
public class Clients implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1180198279650876075L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String nom;
	@NotNull
	private String adresse;
	@NotNull
	private String tel;
	@NotNull
	private String fax;
	@NotNull
	private String email;
	@NotNull
	private String contacts;
	@NotNull
	private String telcontact;
	@NotNull
	private int valsync;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy ="clients")
	private List<Contrats> listcontrats =  new ArrayList<Contrats>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy ="clients")
	private List<Sites> listsites =  new ArrayList<Sites>();
	

	public Clients() {
	}

	public Clients(String nom, String adresse, String tel, String fax, String email, String contacts, String telcontact,
			int valsync) {
		this.nom = nom;
		this.adresse = adresse;
		this.tel = tel;
		this.fax = fax;
		this.email = email;
		this.contacts = contacts;
		this.telcontact = telcontact;
		this.valsync = valsync;
	}
}