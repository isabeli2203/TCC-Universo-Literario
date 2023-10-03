package br.com.livraria.universoliterario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.livraria.universoliterario.model.entity.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
	
	Genero findByGenero(String Genero);

}
