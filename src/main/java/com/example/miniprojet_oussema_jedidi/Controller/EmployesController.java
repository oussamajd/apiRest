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


import com.example.miniprojet_oussema_jedidi.dao.EmployeDao;
import com.example.miniprojet_oussema_jedidi.entities.Employes;

@RestController
@RequestMapping("/Employes")
public class EmployesController {
	@Autowired  
	private EmployeDao EmployeRepository;
	
	@PostMapping(path = "/", consumes = { "application/json" }, produces = "application/json")
	public ResponseEntity<Employes> addEmploye(@RequestBody Employes c) {
		try {
			EmployeRepository.save(c);
			return new ResponseEntity<Employes>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Employes>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employes> updateEmployes(@PathVariable Integer id, @RequestBody Employes Employes) {
		try {
			//String pw_hash = BCrypt.hashpw(Employes.getPassword(), BCrypt.gensalt());
			//Employes.setPassword(pw_hash);
			return EmployeRepository.findById(id).isPresent()
					? new ResponseEntity<Employes>(EmployeRepository.save(Employes), HttpStatus.OK)
					: new ResponseEntity<Employes>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Employes>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Employes> deleteEmployes(@PathVariable Integer id) {
		try {
			if (EmployeRepository.findById(id).isPresent()) {
				EmployeRepository.deleteById(id);
				return new ResponseEntity<Employes>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Employes>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<Employes>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/")
	public ResponseEntity<Iterable<Employes>> getEmployess() {
		try {
			Iterable<Employes> Employess = EmployeRepository.findAll(Sort.by("id").descending());
			return new ResponseEntity<Iterable<Employes>>(Employess, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Employes>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Employes> getEmployes(@PathVariable Integer id) {
		try {
			Optional<Employes> Employes = EmployeRepository.findById(id);
			return Employes.isPresent() ? new ResponseEntity<Employes>(Employes.get(), HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	

}
