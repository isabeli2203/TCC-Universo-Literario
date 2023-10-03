package br.com.livraria.universoliterario.model.entity;

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
	
	@Column(name = "Título")
	private char titulo;
	
	@Column(name = "ISBN")
	private char isbn;
	
	@Column(name = "Sinopse")
	private char sinopse;
	
	@Column(name = "Preco")
	private double preco;
	
	@Column(name = "Quantidade")
	private int quantidade;
	
	
	@Column(name = "StatusProd")
	private String statusProd;
	
	
	@JoinColumn(name ="imagem_id")
	private byte[] imagem;
	
	@ManyToOne
	@JoinColumn(name = "genero_id")
	private Genero genero;
	
	@ManyToOne
	@JoinColumn(name = "editora_id")
	private Editora editora;
	
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Editora autor;


	public void setId(long id) {
		this.id = id;
	}

	public char getTitulo() {
		return titulo;
	}

	public void setTitulo(char titulo) {
		this.titulo = titulo;
	}

	public char getIsbn() {
		return isbn;
	}

	public void setIsbn(char isbn) {
		this.isbn = isbn;
	}

	public char getSinopse() {
		return sinopse;
	}

	public void setSinopse(char sinopse) {
		this.sinopse = sinopse;
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

	public String getStatusLivro() {
		return statusProd;
	}

	public void setStatusLivro(String statusProd) {
		this.statusProd = statusProd;
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

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Editora getAutor() {
		return autor;
	}

	public void setAutor(Editora autor) {
		this.autor = autor;
	}


	

	
}




	
