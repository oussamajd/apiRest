package com.example.miniprojet_oussema_jedidi.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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


import com.example.miniprojet_oussema_jedidi.dao.ContratsDao;
import com.example.miniprojet_oussema_jedidi.entities.Contrats;

import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/Contrat")
public class ContratController  {
	
	@Autowired  
	private ContratsDao contratsRepository;
	
	@PostMapping(path = "/", consumes = { "application/json" }, produces = "application/json")
	public ResponseEntity<Contrats> addContrat(@RequestBody Contrats c) {
		try {
			contratsRepository.save(c);
			return new ResponseEntity<Contrats>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Contrats>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Contrats> updateContrats(@PathVariable Integer id, @RequestBody Contrats contrats) {
		try {
			//String pw_hash = BCrypt.hashpw(Contrats.getPassword(), BCrypt.gensalt());
			//Contrats.setPassword(pw_hash);
			return contratsRepository.findById(id).isPresent()
					? new ResponseEntity<Contrats>(contratsRepository.save(contrats), HttpStatus.OK)
					: new ResponseEntity<Contrats>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Contrats>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Contrats> deleteContrats(@PathVariable Integer id) {
		try {
			if (contratsRepository.findById(id).isPresent()) {
				contratsRepository.deleteById(id);
				return new ResponseEntity<Contrats>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Contrats>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<Contrats>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/")
	public ResponseEntity<Iterable<Contrats>> getContrats() {
		try {
			Iterable<Contrats> Contrats = contratsRepository.findAll(Sort.by("id").descending());
			return new ResponseEntity<Iterable<Contrats>>(Contrats, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Contrats>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Contrats> getContrats(@PathVariable Integer id) {
		try {
			Optional<Contrats> Contrats = contratsRepository.findById(id);
			return Contrats.isPresent() ? new ResponseEntity<Contrats>(Contrats.get(), HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	

}
