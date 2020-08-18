package br.com.renato.produto.produto.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id
	private String id;
	
	private String nome;
	
	private Double valor;
	
	private boolean disponivel = true;
	
	public Produto() {
	}
	
	public Produto(String id, String nome, Double valor, boolean disponivel) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.disponivel = disponivel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
}