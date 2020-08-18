package br.com.renato.produto.produto.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.renato.produto.produto.model.entity.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Produto", value="Produto")
public class ProdutoDto {

	@NotNull
	@NotBlank
	@ApiModelProperty(dataType = "UUID", example = "3fa85f64-5717-4562-b3fc-2c963f66afb6")
	private String id;

	@NotNull
	@NotBlank
	@ApiModelProperty(example = "Caixa de Som")
	private String nome;

	@NotNull
	@ApiModelProperty(example = "2100.90")
	private Double valor;

	@ApiModelProperty(example = "true")
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

	public void atualizar(Produto produto) {
		produto.setNome(nome);
		produto.setDisponivel(disponivel);
		produto.setValor(valor);
	}

	public Produto toProduto() {
		return new Produto(id, nome, valor, disponivel);
	}
}