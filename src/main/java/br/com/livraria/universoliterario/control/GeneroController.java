package br.com.livraria.universoliterario.control;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.universoliterario.model.entity.Genero;
import br.com.livraria.universoliterario.service.GeneroService;

@RestController
@RequestMapping("/genero")
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
public class GeneroController {

	// Criação do objeto de serviço
	final GeneroService generoService;

	// INJEÇÃO DE DEPENDENCIA

	public GeneroController(GeneroService _generoService) {
		this.generoService = _generoService;
	}

	// ROTA POST
	@PostMapping("/save")
	public ResponseEntity<Object> saveGenero(Genero genero) {
		return ResponseEntity.status(HttpStatus.CREATED).body(generoService.save(genero));

	}

	@GetMapping("/all")
	public ResponseEntity<List<Genero>> getAllGenero() {
		return ResponseEntity.status(HttpStatus.OK).body(generoService.findAll());
	}

}