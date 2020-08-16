package br.com.renato.produto.produto.service.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renato.produto.produto.model.entity.usuario.Usuario;
import br.com.renato.produto.produto.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> obterUsuario(String login) {
		return usuarioRepository.findById(login);
	}
}