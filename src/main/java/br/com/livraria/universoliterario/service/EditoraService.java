package br.com.livraria.universoliterario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.livraria.universoliterario.model.entity.Editora;
import br.com.livraria.universoliterario.repository.EditoraRepository;
import jakarta.transaction.Transactional;

@Service
public class EditoraService {

	// objeto repository
	final EditoraRepository editoraRepository;

	// injeção de dependência
	public EditoraService(EditoraRepository _editoraService) {
		this.editoraRepository = _editoraService;
	}

	// Metodo Insert INTO TipoDoProduto
	@Transactional
	public Editora save(Editora _editora) {
		return editoraRepository.save(_editora);
	}

	// Metodo Select * From TipoDoProduto
	public List<Editora> findAll() {
		List<Editora> lista = editoraRepository.findAll();
		return lista;
	}

}