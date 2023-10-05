package com.livraria.universoliterario.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.livraria.universoliterario.model.entity.Livro;
import com.livraria.universoliterario.repository.GeneroRepository;
import com.livraria.universoliterario.repository.LivroRepository;

import jakarta.transaction.Transactional;

@Service
public class LivroService {

	// objeto repository
	private LivroRepository livroRepository;
	private GeneroRepository generoRepository;


	// Injeção de dependência
	public LivroService(LivroRepository _livroRepository, GeneroRepository _generoRepository) {
		super();
		this.livroRepository = _livroRepository;
		this.setGeneroRepository(_generoRepository);
		
	
	}



	public Livro findById(long id) {
		return livroRepository.findById(id).get();
	}

	public List<Livro> ListarTodos() {
		return livroRepository.findAll();

	}
	
	
	
	//Aqui pesquisar
	public List<Livro> listarTodosFiltro(String nome) {
		return null; // livroRepository.findByNomeContaining(nome);
	
	
	}

	@Transactional
	public void inativarLivro(Livro livro) {

		Livro _livro = livro;

		_livro.setPreco(0.0);
		_livro.setQuantidade(0);
		_livro.setDestaque("NÃO");
		_livro.setStatusLivro("INATIVO");
		livroRepository.save(_livro);
	}

	@Transactional
	public void reativarLivro(Livro livro) {

		Livro _livro = livro;

		_livro.setDestaque("NÃO");

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

		if (livro.getDestaque() == null) {
			livro.setDestaque("NÃO");
		}
		livro.setStatusLivro("ATIVO");

		return livroRepository.save(livro);
	}

	@Transactional
	public void atualizarLivro(MultipartFile file, Livro _livro, byte[] foto) {

		
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

		if (_livro.getDestaque() == null) {
			_livro.setDestaque("NÃO");
		}


		_livro.setStatusLivro("ATIVO");
		livroRepository.save(_livro);
	}

	// METODO INSERT INTO PRODUTO
	@Transactional
	public Livro save(Livro _livro) {
		return livroRepository.save(_livro);
	}

	// METODO SELECT * FROM PRODUTO
	public List<Livro> ListarTodosLivro() {
		List<Livro> lista = livroRepository.findAll();
		return lista;
	}



	public GeneroRepository getGeneroRepository() {
		return generoRepository;
	}



	public void setGeneroRepository(GeneroRepository generoRepository) {
		this.generoRepository = generoRepository;
	}

	
}
