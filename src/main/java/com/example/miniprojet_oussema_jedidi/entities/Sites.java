package com.example.miniprojet_oussema_jedidi.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Sites implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3185139405361962852L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private long longitude;
	private long latitude;
	private String adresse;
	private String rue;
	private int codepostal;
	private String ville;
	private String contact;
	private String telcontact;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="client_id",referencedColumnName = "id")
	private Clients clients ;
	
	private int valsync;

	public Sites() {
	}

	public Sites(long longitude, long latitude, String adresse, String rue, int codepostal, String ville,
			String contact, String telcontact, Clients clients, int valsync) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.adresse = adresse;
		this.rue = rue;
		this.codepostal = codepostal;
		this.ville = ville;
		this.contact = contact;
		this.telcontact = telcontact;
		this.clients = clients;
		this.valsync = valsync;
	}

	
}
