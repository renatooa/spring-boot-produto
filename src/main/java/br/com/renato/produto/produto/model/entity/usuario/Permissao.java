package br.com.renato.produto.produto.model.entity.usuario;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Permissao implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	@Id
	private String permissao;

	public Permissao() {
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	@Override
	public String getAuthority() {
		return permissao;
	}

}
