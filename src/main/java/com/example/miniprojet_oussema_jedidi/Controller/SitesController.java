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

import com.example.miniprojet_oussema_jedidi.dao.SitesDao;

import com.example.miniprojet_oussema_jedidi.entities.Sites;

@RestController
@RequestMapping("/Sites")
public class SitesController {
	@Autowired  
	private SitesDao sitesRepository;
	
	@PostMapping(path = "/", consumes = { "application/json" }, produces = "application/json")
	public ResponseEntity<Sites> addContrat(@RequestBody Sites c) {
		try {
			sitesRepository.save(c);
			return new ResponseEntity<Sites>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Sites>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Sites> updateSites(@PathVariable Integer id, @RequestBody Sites Sites) {
		try {
			//String pw_hash = BCrypt.hashpw(Sites.getPassword(), BCrypt.gensalt());
			//Sites.setPassword(pw_hash);
			return sitesRepository.findById(id).isPresent()
					? new ResponseEntity<Sites>(sitesRepository.save(Sites), HttpStatus.OK)
					: new ResponseEntity<Sites>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Sites>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Sites> deleteSites(@PathVariable Integer id) {
		try {
			if (sitesRepository.findById(id).isPresent()) {
				sitesRepository.deleteById(id);
				return new ResponseEntity<Sites>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Sites>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<Sites>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/")
	public ResponseEntity<Iterable<Sites>> getSitess() {
		try {
			Iterable<Sites> Sitess = sitesRepository.findAll(Sort.by("id").descending());
			return new ResponseEntity<Iterable<Sites>>(Sitess, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Sites>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Sites> getSites(@PathVariable Integer id) {
		try {
			Optional<Sites> Sites = sitesRepository.findById(id);
			return Sites.isPresent() ? new ResponseEntity<Sites>(Sites.get(), HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	

}
