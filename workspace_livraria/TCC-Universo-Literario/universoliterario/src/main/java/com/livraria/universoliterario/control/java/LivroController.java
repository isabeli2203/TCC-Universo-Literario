package com.livraria.universoliterario.control.java;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.livraria.universoliterario.model.entity.Funcionario;
import com.livraria.universoliterario.model.entity.Livro;
import com.livraria.universoliterario.service.AutorService;
import com.livraria.universoliterario.service.EditoraService;
import com.livraria.universoliterario.service.GeneroService;
import com.livraria.universoliterario.service.LivroService;

@Controller
@RequestMapping("/universoliterario/livros")
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
public class LivroController {

	final LivroService livroService;
	final GeneroService generoService;
	final AutorService autorService;
	final EditoraService editoraService;



	public LivroController(LivroService _livroService, GeneroService  _generoService, 
			AutorService  _autorService, EditoraService  _editoraService) {
		super();
		this.livroService = _livroService;
		this.generoService = _generoService;
		this.autorService = _autorService;
		this.editoraService = _editoraService;


	}
	@PostMapping("/save")
	public String gravarLivro(@RequestParam(value = "file", required = false) MultipartFile file, Livro livro,
			ModelMap model) {
		livroService.gravarNovoLivro(file, livro);
		return "redirect:/universoliterario/livros/Estoque";
	}
	
	//tela de adicionar livro
	@GetMapping("/AdicionarLivro")
	public String getAdd(ModelMap model) {

		model.addAttribute("funcionario", new Funcionario());

		return "AdicionarLivro";

	}
	//tela de estoque
	@GetMapping("/Estoque")
	public String getEstoque(ModelMap model) {

		model.addAttribute("funcionario", new Funcionario());

		return "listalivros";

	}

	

}
