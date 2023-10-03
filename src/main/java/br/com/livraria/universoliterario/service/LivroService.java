package br.com.livraria.universoliterario.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.livraria.universoliterario.model.entity.Livro;
import br.com.livraria.universoliterario.repository.AutorRepository;
import br.com.livraria.universoliterario.repository.EditoraRepository;
import br.com.livraria.universoliterario.repository.GeneroRepository;
import br.com.livraria.universoliterario.repository.LivroRepository;
import jakarta.transaction.Transactional;

@Service
public class LivroService {

	// objeto repository
	private LivroRepository livroRepository;
	private GeneroRepository generoRepository;
	private EditoraRepository editoraRepository;
	private AutorRepository autorRepository;

	// Injeção de dependência
	public LivroService(LivroRepository livroRepository, GeneroRepository generolivroRepository,
			EditoraRepository editoraRepository, AutorRepository autorRepository) {
		super();
		this.livroRepository = livroRepository;
		this.generoRepository = generolivroRepository;
		this.editoraRepository = editoraRepository;
		this.autorRepository = autorRepository;

	}

	public Livro findById(long id) {
		return livroRepository.findById(id).get();
	}

	public List<Livro> ListarTodos() {
		return livroRepository.findAll();
	
	
	}
	
	
	
	//Aqui pesquisar
	public List<Livro> listarTodosFiltro(String nome) {
		return livroRepository.findByNomeContaining(nome);
	
	
	}

	@Transactional
	public void inativarLivro(Livro livro) {

		Livro _livro = livro;

		_livro.setPreco(0.0);
		_livro.setQuantidade(0);
		_livro.setStatusLivro("INATIVO");
		livroRepository.save(_livro);
	}

	@Transactional
	public void reativarLivro(Livro livro) {

		Livro _livro = livro;


		_livro.setStatusLivro("ATIVO");
		livroRepository.save(_livro);
	}

	@Transactional
	public Livro gravarNovoLivro(MultipartFile file, Livro livro) {

		if (file != null && file.getSize() > 0) {
			try {
				livro.setImagem(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			livro.setImagem(null);
		}

		
		livro.setStatusLivro("ATIVO");

		return livroRepository.save(livro);
	}

	@Transactional
	public void atualizarLivro(MultipartFile file, Livro _livro, byte[] foto) {
//
//		Produto _produto = produto;
//
//		TpProduto tpproduto = tpprodutoRepository.findByTpProduto(produto.getTpProduto().getTpProduto());

//		System.out.println("fi" + file.getSize());
//		System.out.println("fo" + foto.length);

		if (file.getSize() == 0 && foto.length == 0) {
			_livro.setImagem(null);
		}

		if (file.getSize() == 0 && foto.length > 0) {
			_livro.setImagem(foto);
		}

		if (file != null && file.getSize() > 0) {
			try {
				_livro.setImagem(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	

		// _produto.setTpProduto(tpproduto);
		_livro.setStatusLivro("ATIVO");
		livroRepository.save(_livro);
	}

	// METODO INSERT INTO PRODUTO
	@Transactional
	public Livro save(Livro _livro) {
		return livroRepository.save(_livro);
	}

	// METODO SELECT * FROM PRODUTO
	public List<Livro> ListarTodosLivros() {
		List<Livro> lista = livroRepository.findAll();
		return lista;
	}

	
}
