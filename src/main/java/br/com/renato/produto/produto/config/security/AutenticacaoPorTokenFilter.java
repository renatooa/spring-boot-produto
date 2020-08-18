package br.com.renato.produto.produto.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.renato.produto.produto.model.entity.usuario.Usuario;
import br.com.renato.produto.produto.service.auth.TokenService;
import br.com.renato.produto.produto.service.auth.UsuarioService;

public class AutenticacaoPorTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private UsuarioService usuarioService;

	public AutenticacaoPorTokenFilter(TokenService tokenService, UsuarioService usuarioService) {
		super();
		this.tokenService = tokenService;
		this.usuarioService = usuarioService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);

		String login = tokenService.isTokenValido(token);

		if (login != null && !login.isEmpty()) {
			autenticarUsuario(login);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticarUsuario(String login) {

		Optional<Usuario> usuario = usuarioService.obterUsuario(login);

		if (usuario.isPresent()) {

			UsernamePasswordAuthenticationToken usuAuthenticationToken = new UsernamePasswordAuthenticationToken(
					usuario.get(), null, usuario.get().getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(usuAuthenticationToken);
		} else {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		String inicioToken = "Bearer ";

		if (token != null && !token.isEmpty() && token.startsWith(inicioToken)) {
			return token.replace(inicioToken, "");
		}

		return null;
	}
}