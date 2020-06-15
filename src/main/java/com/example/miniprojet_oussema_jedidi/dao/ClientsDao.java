package com.example.miniprojet_oussema_jedidi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.miniprojet_oussema_jedidi.entities.Clients;

@Repository
public interface ClientsDao extends JpaRepository<Clients,Integer>
{
	
}