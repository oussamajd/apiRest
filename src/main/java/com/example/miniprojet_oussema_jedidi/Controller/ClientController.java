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

import com.example.miniprojet_oussema_jedidi.dao.ClientsDao;
import com.example.miniprojet_oussema_jedidi.entities.Clients;


@RestController
@RequestMapping("/Client")
public class ClientController {
	@Autowired  
	private ClientsDao clientRepository;
	@PostMapping(path = "/", consumes = { "application/json" }, produces = "application/json")
	public ResponseEntity<Clients> addClient(@RequestBody Clients c) {
		try {
			clientRepository.save(c);
			return new ResponseEntity<Clients>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Clients>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Clients> updateClients(@PathVariable Integer id, @RequestBody Clients Clients) {
		try {
			//String pw_hash = BCrypt.hashpw(Clients.getPassword(), BCrypt.gensalt());
			//Clients.setPassword(pw_hash);
			return clientRepository.findById(id).isPresent()
					? new ResponseEntity<Clients>(clientRepository.save(Clients), HttpStatus.OK)
					: new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Clients>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Clients> deleteClients(@PathVariable Integer id) {
		try {
			if (clientRepository.findById(id).isPresent()) {
				clientRepository.deleteById(id);
				return new ResponseEntity<Clients>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Clients>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<Clients>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/")
	public ResponseEntity<Iterable<Clients>> getClients() {
		try {
			Iterable<Clients> Clientss = clientRepository.findAll(Sort.by("id").descending());
			return new ResponseEntity<Iterable<Clients>>(Clientss, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Clients>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Clients> getClients(@PathVariable Integer id) {
		try {
			Optional<Clients> Clients = clientRepository.findById(id);
			return Clients.isPresent() ? new ResponseEntity<Clients>(Clients.get(), HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



}
