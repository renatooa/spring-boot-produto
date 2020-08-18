package br.com.renato.produto.produto.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Retorno da Autenticação", value="Token")
public class TokenDto {

	@ApiModelProperty(example = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOi...")
	private String token;
	@ApiModelProperty(example = "Bearer")
	private String tipoAutenticacao;

	public TokenDto() {
	}

	public TokenDto(String token) {
		this(token, "Bearer");
	}

	public TokenDto(String token, String tipoAutenticacao) {
		super();
		this.token = token;
		this.tipoAutenticacao = tipoAutenticacao;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipoAutenticacao() {
		return tipoAutenticacao;
	}

	public void setTipoAutenticacao(String tipoAutenticacao) {
		this.tipoAutenticacao = tipoAutenticacao;
	}
}