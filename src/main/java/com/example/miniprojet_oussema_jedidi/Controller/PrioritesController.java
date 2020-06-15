package com.example.miniprojet_oussema_jedidi.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.miniprojet_oussema_jedidi.dao.PrioritesDao;

import com.example.miniprojet_oussema_jedidi.entities.Priorites;

@RestController
@RequestMapping("/priorites")
public class PrioritesController {
	
	@Autowired  
	private PrioritesDao prioritesRepository;
	
	@PostMapping(path = "/", consumes = { "application/json" }, produces = "application/json")
	public ResponseEntity<Priorites> addContrat(@RequestBody Priorites c) {
		try {
			prioritesRepository.save(c);
			return new ResponseEntity<Priorites>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Priorites>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Priorites> updatePriorites(@PathVariable Integer id, @RequestBody Priorites Priorites) {
		try {
			//String pw_hash = BCrypt.hashpw(Priorites.getPassword(), BCrypt.gensalt());
			//Priorites.setPassword(pw_hash);
			return prioritesRepository.findById(id).isPresent()
					? new ResponseEntity<Priorites>(prioritesRepository.save(Priorites), HttpStatus.OK)
					: new ResponseEntity<Priorites>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Priorites>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Priorites> deletePriorites(@PathVariable Integer id) {
		try {
			if (prioritesRepository.findById(id).isPresent()) {
				prioritesRepository.deleteById(id);
				return new ResponseEntity<Priorites>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Priorites>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<Priorites>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/")
	public ResponseEntity<Iterable<Priorites>> getPrioritess() {
		try {
			Iterable<Priorites> Prioritess = prioritesRepository.findAll(Sort.by("id").descending());
			return new ResponseEntity<Iterable<Priorites>>(Prioritess, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Priorites>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Priorites> getPriorites(@PathVariable Integer id) {
		try {
			Optional<Priorites> Priorites = prioritesRepository.findById(id);
			return Priorites.isPresent() ? new ResponseEntity<Priorites>(Priorites.get(), HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



}
