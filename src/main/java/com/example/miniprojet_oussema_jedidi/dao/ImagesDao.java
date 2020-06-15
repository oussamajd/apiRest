package com.example.miniprojet_oussema_jedidi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miniprojet_oussema_jedidi.entities.Images;

public interface ImagesDao  extends JpaRepository<Images,Integer> {
	
	
}