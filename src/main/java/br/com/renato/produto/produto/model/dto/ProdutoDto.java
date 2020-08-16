package br.com.renato.produto.produto.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.renato.produto.produto.model.entity.Produto;

public class ProdutoDto {

	@NotNull
	@NotBlank
	private String id;

	@NotNull
	@NotBlank
	private String nome;

	@NotNull
	private Double valor;

	private boolean disponivel = true;

	public ProdutoDto() {
	}

	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.disponivel = produto.isDisponivel();
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
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
}