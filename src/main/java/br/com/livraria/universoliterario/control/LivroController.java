package br.com.livraria.universoliterario.control;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.com.livraria.universoliterario.model.entity.Livro;
import br.com.livraria.universoliterario.service.AutorService;
import br.com.livraria.universoliterario.service.EditoraService;
import br.com.livraria.universoliterario.service.LivroService;
import br.com.livraria.universoliterario.service.GeneroService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/universoliterario/livros")
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
public class LivroController {

	final LivroService livroService;
	final GeneroService generoService;
	final EditoraService editoraService;
	final AutorService autorService;

	// CASO O PRODUTO NÃO TENHA UMA IMAGEM CADASTRADA NO BANCO DE DADOS
	private String semImagem = "/img/semImagem.png";
	private String foto = "";

	public LivroController(LivroService livroService, GeneroService generoService,
			EditoraService editoraService, AutorService autorService) {
		super();
		this.livroService = livroService;
		this.generoService = generoService;
		this.editoraService = editoraService;
		this.autorService = autorService;

	}

	// CARREGA A IMAGEM DO SERVIDOR NA PÁGINA DE ACORDO COM O "ID" DO PRODUTO
	// NA PÁGINA HTML FICA ASSIM: src="/api/v1/produto/show/image/1"
	// CARREGA A URL DA IMAGEM
	@GetMapping("/show/image/{id}")
	@ResponseBody
	public void mostrarImagem(@PathVariable("id") long id, HttpServletResponse response, Livro livro)
			throws ServletException, IOException {

		livro = livroService.findById(id);

		response.setContentType("+image/jpeg, image/jpg, image/png, image/gif");
		if (livro.getImagem() != null) {
			response.getOutputStream().write(livro.getImagem());
		} else {
			response.getOutputStream().write(null);
		}

		response.getOutputStream().close();
	}

	@GetMapping("/home")
	public String home(ModelMap model) {

//		List<Produto> produtos = produtoService.listar3Destaques();

		// PARA EXIBIR UMA QUANTIDADE MAIOR DE PRODUTOS EM DESTAQUE
		List<Livro> livros = livroService.ListarTodos();

		model.addAttribute("semImagem", semImagem);
		model.addAttribute("livros", livros);

		return "home";
	}

	// ROTA POST
	@PostMapping("/save")
	public String gravarLivroFunc(@RequestParam(value = "file", required = false) MultipartFile file, Livro livro,
			ModelMap model) {
		livroService.gravarNovoLivro(file, livro);
		return "redirect:/universoliterario/livros/Estoque";
	}

	@PostMapping("/saveADM")
	public String gravarLivroAdm(@RequestParam(value = "file", required = false) MultipartFile file, Livro livro,
			ModelMap model) {
		livroService.gravarNovoLivro(file, livro);
		return "redirect:/universoliterario/funcionario/EstoqueADM";
	}

	// arrumar aqui pra atualizar
	@PostMapping("/atualizar/{id}")
	public String atualizarLivro(@RequestParam(value = "file", required = false) MultipartFile file,
			@PathVariable("id") int id, Livro livro, ModelMap model) {

		byte[] _foto = Base64.getDecoder().decode(foto);

		livroService.atualizarLivro(file, livro, _foto);

		foto = "";

		return "redirect:/universoliterario/livros/Estoque";
	}

	// ROTA GET
	@GetMapping("/all")
	public ResponseEntity<List<Livro>> getAllLivro() {
		return ResponseEntity.status(HttpStatus.OK).body(livroService.ListarTodos());
	}

	@GetMapping("/AdicionarLivro")
	public String getadicionarLivros(ModelMap model) {
		model.addAttribute("genero", generoService.findAll());
		model.addAttribute("editora", editoraService.findAll());
		model.addAttribute("autor", autorService.findAll());
		model.addAttribute("livro", new Livro());
		return "AdicionarLivro";
	}

	@GetMapping("/Editarlivro")
	public String getEditarLivro(ModelMap model) {
		model.addAttribute("livros", livroService.ListarTodos());
		return "Editarlivro";
	}

	@GetMapping("/Editarlivro/{id}")
	public String editarLivro(@PathVariable("id") int id, ModelMap model) {

		Livro livro = livroService.findById(id);

		if (livro.getImagem() != null) {
			if (livro.getImagem().length > 0) {
				foto = Base64.getEncoder().encodeToString(livro.getImagem());
			}
		}

		model.addAttribute("genero", generoService.findAll());
		model.addAttribute("editora", editoraService.findAll());
		model.addAttribute("autor", autorService.findAll());
		model.addAttribute("semImagem", semImagem);
		model.addAttribute("livro", livro);

		return "Editarlivro";
	}

	@GetMapping("/inativar/{id}")
	public String inativarLivro(@PathVariable("id") int id, ModelMap model) {

		Livro livro = livroService.findById(id);

		livroService.inativarLivro(livro);

		return "redirect:/universoliterario/livros/Estoque";
	}

	@GetMapping("/reativar/{id}")
	public String reativarLivro(@PathVariable("id") int id, ModelMap model) {

		Livro livro = livroService.findById(id);

		livroService.reativarLivro(livro);
//paramos aqui
		return "redirect:/universoliterario/livros/Estoque";
	}

	// ADM//

	@GetMapping("/EditarLivroADM/{id}")
	public String getEditarLivroADM(@PathVariable("id") int id, ModelMap map) {

		Livro livro = livroService.findById(id);
		map.addAttribute("genero", generoService.findAll());
		map.addAttribute("editora", editoraService.findAll());
		map.addAttribute("autor", autorService.findAll());
		map.addAttribute("livro", livro);
		return "EditarLivroADM";
	}

	@PostMapping("/atualizarLivroADM/{id}")
	public String atualizarLivroADm(@RequestParam(value = "file", required = false) MultipartFile file,
			@PathVariable("id") int id, Livro livro, ModelMap model) {

		byte[] _foto = Base64.getDecoder().decode(foto);

		livroService.atualizarLivro(file, livro, _foto);

		foto = "";

		return "redirect:/universoliterario/funcionario/EstoqueADM";
	}

	@GetMapping("/Estoque")
	public String getEstoque(ModelMap model) {
		model.addAttribute("livro", livroService.ListarTodos());
		return "Estoque";
	}

	@GetMapping("/FiltroLivroAdm")
	public String verLivrosAdm(ModelMap model, @RequestParam(value = "livro", required = false) String nome) {

		List<Livro> livros = null;

		if (nome == null) {
			livros = livroService.ListarTodos();
			model.addAttribute("livros", livros);
		} else {
			livros = livroService.listarTodosFiltro(nome);
			model.addAttribute("livros", livros);
		}

		return "EstoqueADM";
	}

	@GetMapping("/FiltroLivroFunc")
	public String verLivrosFunc(ModelMap model, @RequestParam(value = "livro", required = false) String nome) {

		List<Livro> livros = null;

		if (nome == null) {
			livros = livroService.ListarTodos();
			model.addAttribute("livros", livros);
		} else {
			livros = livroService.listarTodosFiltro(nome);
			model.addAttribute("livros", livros);
		}

		return "Estoque";
	}

}
