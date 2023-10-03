package br.com.livraria.universoliterario.control;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.livraria.universoliterario.model.entity.Funcionario;
import br.com.livraria.universoliterario.model.entity.Livro;
import br.com.livraria.universoliterario.service.AutorService;
import br.com.livraria.universoliterario.service.EditoraService;
import br.com.livraria.universoliterario.service.FuncionarioService;
import br.com.livraria.universoliterario.service.GeneroService;
import br.com.livraria.universoliterario.service.LivroService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/universoliterario/funcionario")
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false")
public class FuncionarioController {

	// CRIAÇÃO DO OBJETO DE SERVIÇO
	final LivroService livroService;
	final FuncionarioService funcionarioService;
	final GeneroService generoLivroService;
	final EditoraService editoraService;
	private AutorService autorService;

	// INJEÇÃO DE DEPENDENCIA

	public FuncionarioController(FuncionarioService _funcionarioService, GeneroService generoLivroService,
			EditoraService mcEditoraService, LivroService livroService, EditoraService editoraService, AutorService autorService) {
		this.funcionarioService = _funcionarioService;
		this.generoLivroService = generoLivroService;
		this.editoraService = editoraService;
		this.livroService = livroService;
		this.autorService = autorService;

	}

	private String serverMessage = null;
	private String semImagem = "/img/semImagem.png";
	private String foto = "";

	@GetMapping("/login")
	public String getLogin(ModelMap model) {

		model.addAttribute("funcionario", new Funcionario());
		model.addAttribute("serverMessage", serverMessage);
		return "login";

	}

	@GetMapping("/all")
	public ResponseEntity<List<Livro>> getAllKivro() {
		return ResponseEntity.status(HttpStatus.OK).body(livroService.ListarTodos());
	}

	@PostMapping("/logar")
	public String Acessar(ModelMap map, @RequestParam("email") String email, @RequestParam("senha") String senha,
			HttpSession session) {

		Funcionario funclogado = funcionarioService.acessar(email, senha);

		if (funclogado != null) {
			session.setAttribute("funclogado", funclogado);
			if (funclogado.getAcesso().equals("FUNC")) {
				return "redirect:/universoliterario/livros/Estoque";
			} else if (funclogado.getAcesso().equals("ADMIN")) {
				return "redirect:/universoliterario/funcionario/ListaFunc";
			}
		}

		return "redirect:/universoliterario/funcionario/login";
	}

	@PostMapping("/save")
	public String saveFuncionario(@ModelAttribute Funcionario funcionario) {

		funcionario.setAcesso("FUNC");

		funcionarioService.saveNewFuncionario(funcionario);

		return "redirect:/universoliterario/funcionario/login";
	}

	@GetMapping("/loginfuncionario")
	public String getLoginFuncionario(ModelMap model) {
		model.addAttribute("funcionario", new Funcionario());
		return "loginFuncionario";
	}

	@GetMapping("/criarconta")
	public String getCriarConta(ModelMap map) {
		map.addAttribute("funcionario", new Funcionario());
		return "CriarConta";
	}

	@GetMapping("/ListaFunc_")
	public String getLista(ModelMap map) {
		map.addAttribute("funcionario", funcionarioService.ListarTodos());
		return "ListaFunc";
	}

	@GetMapping("/inativar/{id}")
	public String inativarFunc(@PathVariable("id") int id, ModelMap model) {

		Funcionario funcionario = funcionarioService.findById(id);

		funcionarioService.inativarFunc(funcionario);

		return "redirect:/universoliterario/funcionario/ListaFunc";
	}

	@GetMapping("/Filtro_Func")
	public String MostrarFiltroFunc(ModelMap map) {

		map.addAttribute("funcionario", funcionarioService.ListarTodos());
		return "FiltroFuc";

	}

	@GetMapping("/ListaFunc")
	public String verFuncionarios(ModelMap model, @RequestParam(value = "funcionario", required = false) String nome) {

		List<Funcionario> funcionarios = null;

		if (nome == null) {
			funcionarios = funcionarioService.TodosFuncionarios();
			model.addAttribute("funcionarios", funcionarios);
		} else {
			funcionarios = funcionarioService.FiltroFunc(nome);
			model.addAttribute("funcionarios", funcionarios);
		}

		serverMessage = null;

		// INDICA A PÁGINA QUE SERÁ CARREGADA NA EXECUÇÃO DO MÉTODO
		return "ListaFunc";
	}

	// ADM////

	@GetMapping("/EditarFuncionario/{id}")
	public String getEditarFuncionario(@PathVariable("id") int id, ModelMap map) {
		Funcionario funcionario = funcionarioService.findById(id);

		map.addAttribute("funcionario", funcionario);

		return "EditarFuncionario";
	}

	@GetMapping("/inativarLivroAdm/{id}")
	public String inativarProdFunc(@PathVariable("id") int id, Livro livro, ModelMap model) {

		Livro livros = livroService.findById(id);

		funcionarioService.inativarLivroFunc(livros);

		return "redirect:/universoliterario/funcionario/EstoqueADM";
	}

	@GetMapping("/EstoqueADM")
	public String getEstoque(ModelMap model) {
		model.addAttribute("livros", livroService.ListarTodos());
		return "EstoqueADM";
	}

	@GetMapping("/AdicionarLivroADM")
	public String getadicionarLivrosADM(ModelMap model) {
		model.addAttribute("generoLivro", generoLivroService.findAll());
		model.addAttribute("editora", editoraService.findAll());
		model.addAttribute("autor", autorService.findAll());
		model.addAttribute("livro", new Livro());
		return "AdicionarLivroADM";
	}

	@PostMapping("/atualizar")
	public String atualizarFuncionario(@ModelAttribute("funcionario") Funcionario funcionario) {

		funcionarioService.atualizarFunc(funcionario);
		return "redirect:/universoliterario/funcionario/ListaFunc";
	}

}