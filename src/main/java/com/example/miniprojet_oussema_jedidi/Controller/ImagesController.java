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


import com.example.miniprojet_oussema_jedidi.dao.ImagesDao;
import com.example.miniprojet_oussema_jedidi.entities.Images;

@RestController
@RequestMapping("/images")
public class ImagesController {
	@Autowired  
	private ImagesDao imagesRepository;
	
	@PostMapping(path = "/", consumes = { "application/json" }, produces = "application/json")
	public ResponseEntity<Images> addContrat(@RequestBody Images c) {
		try {
			imagesRepository.save(c);
			return new ResponseEntity<Images>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Images>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Images> updateImages(@PathVariable Integer id, @RequestBody Images Images) {
		try {
			//String pw_hash = BCrypt.hashpw(Images.getPassword(), BCrypt.gensalt());
			//Images.setPassword(pw_hash);
			return imagesRepository.findById(id).isPresent()
					? new ResponseEntity<Images>(imagesRepository.save(Images), HttpStatus.OK)
					: new ResponseEntity<Images>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Images>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Images> deleteImages(@PathVariable Integer id) {
		try {
			if (imagesRepository.findById(id).isPresent()) {
				imagesRepository.deleteById(id);
				return new ResponseEntity<Images>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Images>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<Images>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/")
	public ResponseEntity<Iterable<Images>> getImagess() {
		try {
			Iterable<Images> Imagess = imagesRepository.findAll(Sort.by("id").descending());
			return new ResponseEntity<Iterable<Images>>(Imagess, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Images>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Images> getImages(@PathVariable Integer id) {
		try {
			Optional<Images> Images = imagesRepository.findById(id);
			return Images.isPresent() ? new ResponseEntity<Images>(Images.get(), HttpStatus.OK)
					: new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	

}
