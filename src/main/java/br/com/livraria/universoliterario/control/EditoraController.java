package br.com.livraria.universoliterario.control;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.livraria.universoliterario.model.entity.Editora;
import br.com.livraria.universoliterario.service.EditoraService;

@RestController
@RequestMapping("/editora")
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
public class EditoraController {

	// Criação do objeto de serviço
	final EditoraService editoraService;

	// INJEÇÃO DE DEPENDENCIA

	public EditoraController(EditoraService _editoraService) {
		this.editoraService = _editoraService;
	}

	// ROTA POST
	@PostMapping("/save")
	public ResponseEntity<Object> saveGenero(Editora editora) {
		return ResponseEntity.status(HttpStatus.CREATED).body(editoraService.save(editora));

	}

	@GetMapping("/all")
	public ResponseEntity<List<Editora>> getAllGenero() {
		return ResponseEntity.status(HttpStatus.OK).body(editoraService.findAll());
	}

}