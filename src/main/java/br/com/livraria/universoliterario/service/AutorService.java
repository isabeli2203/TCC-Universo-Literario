package br.com.livraria.universoliterario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.livraria.universoliterario.model.entity.Autor;
import br.com.livraria.universoliterario.repository.AutorRepository;
import jakarta.transaction.Transactional;

@Service
public class AutorService {

	// objeto repository
	final AutorRepository autorRepository;

	// injeção de dependência
	public AutorService(AutorRepository _autorService) {
		this.autorRepository = _autorService;

	}

	// Metodo Insert INTO GenerodoLivro
	@Transactional
	public Autor save(Autor _autor) {
		return autorRepository.save(_autor);
	}

	// Metodo Select * From GenerodoLivro
	public List<Autor> findAll() {
		List<Autor> lista = autorRepository.findAll();
		return lista;
	}

}
