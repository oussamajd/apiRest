package com.example.miniprojet_oussema_jedidi.entities;

// default package
// Generated 14 juin 2020 07:14:23 by Hibernate Tools 5.4.14.Final

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import lombok.Data;


@Entity
@Data
public class Contrats implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3281036890379818154L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;
	@NotNull
	private Date datedebut;
	@NotNull
	private Date datefin;
	@NotNull
	private long redevence;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Client_id",referencedColumnName = "id")
	private  Clients clients;
	@NotNull
	private int valsync;

	public Contrats() {
	}

	


}