package com.example.miniprojet_oussema_jedidi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.miniprojet_oussema_jedidi.entities.Contrats;

@Repository
public interface ContratsDao extends JpaRepository<Contrats,Integer>
{
	
}