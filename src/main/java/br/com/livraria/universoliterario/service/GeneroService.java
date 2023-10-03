package br.com.livraria.universoliterario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.livraria.universoliterario.model.entity.Genero;
import br.com.livraria.universoliterario.repository.GeneroRepository;
import jakarta.transaction.Transactional;

@Service
public class GeneroService {

	// objeto repository
	final GeneroRepository generoRepository;

	// injeção de dependência
	public GeneroService(GeneroRepository _generoService) {
		this.generoRepository = _generoService;
	}

	// Metodo Insert INTO TipoDoProduto
	@Transactional
	public Genero save(Genero _genero) {
		return generoRepository.save(_genero);
	}

	// Metodo Select * From TipoDoProduto
	public List<Genero> findAll() {
		List<Genero> lista = generoRepository.findAll();
		return lista;
	}

}
