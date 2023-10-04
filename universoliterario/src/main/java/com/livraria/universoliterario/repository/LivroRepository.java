package com.livraria.universoliterario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.livraria.universoliterario.model.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
		
		// JPQL: A CONSULTA É FEITA NO OBJETO REFERENTE A TABELA
	
		List<Livro> findByNomeContaining(String nome);
		
		List<Livro> findByStatusLivro(String status);
		
		// JPQL: A CONSULTA É FEITA NO OBJETO REFERENTE A TABELA
		@Query("SELECT p FROM Produto p WHERE p.statusLivro = 'ATIVO'")
		List<Livro> listarLivrosAtivos();
		
		@Query("SELECT p FROM Produto p WHERE p.nome like %?1%")
		List<Livro> listarLivrosFiltro(@Param("nome") String nome);

		

}
