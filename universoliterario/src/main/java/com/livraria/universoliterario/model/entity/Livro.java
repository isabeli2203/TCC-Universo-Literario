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
	@Column(name = "Preco")
	private double preco;
	@Column(name = "Quantidade")
	private int quantidade;
	@Column(name = "Destaque")
	private String destaque;
	@Column(name = "StatusLivro")
	private String statusLivro;
	@Column(name = "Genero")
	private String nome;
	@Column(name = "ISBN")
	private int isbn;
	@Column(name = "Complemento")
	private String descricao;
	@JoinColumn(name ="imagem_id")
	private byte[] imagem;
	
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	


	

}
