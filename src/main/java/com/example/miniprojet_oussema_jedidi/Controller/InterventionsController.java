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

import com.example.miniprojet_oussema_jedidi.dao.InterventionsDao;

import com.example.miniprojet_oussema_jedidi.entities.Interventions;

@RestController
@RequestMapping("/interventions")
public class InterventionsController {
	@Autowired  
	private InterventionsDao interventionsRepository;
	
	@PostMapping(path = "/", consumes = { "application/json" }, produces = "application/json")
	public ResponseEntity<Interventions> addContrat(@RequestBody Interventions c) {
		try {
			interventionsRepository.save(c);
			return new ResponseEntity<Interventions>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Interventions>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Interventions> updateInterventions(@PathVariable Integer id, @RequestBody Interventions Interventions) {
		try {
			//String pw_hash = BCrypt.hashpw(Interventions.getPassword(), BCrypt.gensalt());
			//Interventions.setPassword(pw_hash);
			return interventionsRepository.findById(id).isPresent()
					? new ResponseEntity<Interventions>(interventionsRepository.save(Interventions), HttpStatus.OK)
					: new ResponseEntity<Interventions>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Interventions>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Interventions> deleteInterventions(@PathVariable Integer id) {
		try {
			if (interventionsRepository.findById(id).isPresent()) {
				interventionsRepository.deleteById(id);
				return new ResponseEntity<Interventions>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Interventions>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<Interventions>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/")
	public ResponseEntity<Iterable<Interventions>> getInterventionss() {
		try {
			Iterable<Interventions> Interventionss = interventionsRepository.findAll(Sort.by("id").descending());
			return new ResponseEntity<Iterable<Interventions>>(Interventionss, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Interventions>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Interventions> getInterventions(@PathVariable Integer id) {
		try {
			Optional<Interventions> Interventions = interventionsRepository.findById(id);
			return Interventions.isPresent() ? new ResponseEntity<Interventions>(Interventions.get(), HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	

}
