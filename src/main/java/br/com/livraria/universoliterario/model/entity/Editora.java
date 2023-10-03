package br.com.livraria.universoliterario.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Editora")
public class Editora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Editora")
	private String editora;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

}