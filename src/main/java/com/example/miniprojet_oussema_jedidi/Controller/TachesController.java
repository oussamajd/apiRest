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

import com.example.miniprojet_oussema_jedidi.dao.TachesDao;
import com.example.miniprojet_oussema_jedidi.entities.Taches;

@RestController
@RequestMapping("/taches")
public class TachesController {

	@Autowired  
	private TachesDao tachesRepository;
	
	@PostMapping(path = "/", consumes = { "application/json" }, produces = "application/json")
	public ResponseEntity<Taches> addContrat(@RequestBody Taches c) {
		try {
			tachesRepository.save(c);
			return new ResponseEntity<Taches>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Taches>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Taches> updateTaches(@PathVariable Integer id, @RequestBody Taches Taches) {
		try {
			//String pw_hash = BCrypt.hashpw(Taches.getPassword(), BCrypt.gensalt());
			//Taches.setPassword(pw_hash);
			return tachesRepository.findById(id).isPresent()
					? new ResponseEntity<Taches>(tachesRepository.save(Taches), HttpStatus.OK)
					: new ResponseEntity<Taches>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Taches>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Taches> deleteTaches(@PathVariable Integer id) {
		try {
			if (tachesRepository.findById(id).isPresent()) {
				tachesRepository.deleteById(id);
				return new ResponseEntity<Taches>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Taches>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<Taches>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/")
	public ResponseEntity<Iterable<Taches>> getTachess() {
		try {
			Iterable<Taches> Tachess = tachesRepository.findAll(Sort.by("id").descending());
			return new ResponseEntity<Iterable<Taches>>(Tachess, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Taches>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Taches> getTaches(@PathVariable Integer id) {
		try {
			Optional<Taches> Taches = tachesRepository.findById(id);
			return Taches.isPresent() ? new ResponseEntity<Taches>(Taches.get(), HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


}
