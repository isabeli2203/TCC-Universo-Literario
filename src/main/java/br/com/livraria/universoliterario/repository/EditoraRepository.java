package br.com.livraria.universoliterario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.livraria.universoliterario.model.entity.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
	
	Editora findByTpProduto(String Editora);

}