package com.example.miniprojet_oussema_jedidi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.miniprojet_oussema_jedidi.entities.Priorites;

@Repository
public interface PrioritesDao  extends JpaRepository<Priorites,Integer>{
	
}