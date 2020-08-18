package br.com.renato.produto.produto.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Credencias de Login", value="Login")
public class LoginDto {

	@NotNull
	@NotBlank
	@ApiModelProperty(example="admin")
	private String login;

	@NotNull
	@NotBlank
	@ApiModelProperty(example="@admin")
	private String senha;

	public LoginDto() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken toUsernamePasswordAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}
}