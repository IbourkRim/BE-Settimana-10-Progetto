package it.film.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.film.dao.FilmDao;
import it.film.dto.FilmDto;
import it.film.entity.Film;


@RequestMapping("/film")
@RestController
@Api(value = "FilmRest", tags = "crea , elimina e aggiorna")
public class FilmRest {

Logger log = LoggerFactory.getLogger(getClass());
	
	FilmDao filmdao;
	public FilmDao getFilmdao() {
		if(filmdao == null) {
			filmdao = new FilmDao();
		}
		return filmdao;
	}

	
	@GetMapping
	@ApiOperation(
			value= "Mostra la lista di tutti i Film presenti",
			produces = "application/json",
			response = Film.class, responseContainer = "List")
	public ResponseEntity<List<Film>> trovaTutti(){
		try {
			return new ResponseEntity<List<Film>>(getFilmdao().trovaTutti(), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<List<Film>>((List<Film>)null, HttpStatus.BAD_REQUEST);
		}
		
		
	}

	@GetMapping("/byregista")
	@ApiOperation(
			value= "Mostra Regista",
			produces = "application/json",
			response = Film.class, responseContainer = "List")
	public ResponseEntity<List<Film>> trovaRegista(@RequestParam String regista){
		try {
			return new ResponseEntity<List<Film>>(getFilmdao().trovaRegista(regista), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<List<Film>>((List<Film>)null, HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation(
			value = "Elimina un film")
	public ResponseEntity<String> deleteFilm(@PathVariable int id) {
		try {
			getFilmdao().elimina(id);
			return new ResponseEntity<String>("Eliminazione avvenuta", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Eliminazione non avvenuta", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping
	@ApiOperation(
			value = "Inserisci un nuovo film",
			consumes = "application/json")
	public ResponseEntity<String> inserisciFilm(@RequestBody FilmDto fDto) {
		if(fDto.getRegista() == null || fDto.getRegista().isBlank()) {
			log.error("aggiungi il regista!!!");
			return new ResponseEntity<String>("Il regista dev'essere inserito!", HttpStatus.I_AM_A_TEAPOT);
		}
		Film f = new Film();
		f.setTitolo(fDto.getTitolo());
		f.setAnno(fDto.getAnno());
		f.setRegista(fDto.getRegista());
		f.setTipo(fDto.getTipo());
		String incassoCriptato = BCrypt.hashpw(fDto.getIncasso(), BCrypt.gensalt());
		f.setIncasso(incassoCriptato);
		
		try {
			getFilmdao().save(f);
			return new ResponseEntity<String>("Inserimento avvenuto con successo!"+ f.toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Inserimento non avvenuto con successo!", HttpStatus.BAD_REQUEST);
		}
	}

	
	@PutMapping("/{id}")
	@ApiOperation(value = "Aggiorna il film presente nel Database",
				consumes = "application/json")
	public ResponseEntity<String> aggiornaFilm(@RequestBody FilmDto fDto, @PathVariable int id){
		Film f = new Film();
		f.setId(id);
		f.setTitolo(fDto.getTitolo());
		f.setAnno(fDto.getAnno());
		f.setRegista(fDto.getRegista());
		f.setTipo(fDto.getTipo());
		String incassoCriptato = BCrypt.hashpw(fDto.getIncasso(), BCrypt.gensalt());
		f.setIncasso(incassoCriptato);
		
		try {
			getFilmdao().aggiorna(f);
			return new ResponseEntity<String>("Aggiornamento avvenuto!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Aggiornamento non completato!", HttpStatus.METHOD_FAILURE);
		}
	}	
}
