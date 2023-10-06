package com.livraria.universoliterario.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Livro")
public class Livro {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Titulo")
	private String titulo;
	
	@Column(name = "ISBN")
	private int isbn;
	
	@Column(name = "Estoque")
	private int estoque;
	
	@Column(name = "Preco")
	private double preco;	
	
	@Column(name ="Imagem")
	private byte[] imagem;
	
	@Column(name = "Destaque")
	private String destaque;
	
	@Column(name = "StatusLivro")
	private String statusLivro;
	
	@ManyToOne
	@JoinColumn(name = "editora_id")
	private Editora editora;
	
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor;
	
	@ManyToOne
	@JoinColumn(name = "genero_id")
	private Genero genero;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getDestaque() {
		return destaque;
	}

	public void setDestaque(String destaque) {
		this.destaque = destaque;
	}

	public String getStatusLivro() {
		return statusLivro;
	}

	public void setStatusLivro(String statusLivro) {
		this.statusLivro = statusLivro;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public void setQuantidade(int i) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
