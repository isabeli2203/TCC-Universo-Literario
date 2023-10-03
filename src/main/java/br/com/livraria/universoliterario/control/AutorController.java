package br.com.livraria.universoliterario.control;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.universoliterario.model.entity.Autor;
import br.com.livraria.universoliterario.service.AutorService;

@RestController
@RequestMapping("/autor")
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
public class AutorController {

	// Criação do objeto de serviço
	final AutorService autorService;

	// INJEÇÃO DE DEPENDENCIA

	public AutorController(AutorService _autorService) {
		this.autorService = _autorService;
	}

	// ROTA POST
	@PostMapping("/save")
	public ResponseEntity<Object> saveAutor(Autor autor) {
		return ResponseEntity.status(HttpStatus.CREATED).body(autorService.save(autor));

	}

	@GetMapping("/all")
	public ResponseEntity<List<Autor>> getAllAutor() {
		return ResponseEntity.status(HttpStatus.OK).body(autorService.findAll());
	}

}