package br.com.renato.produto.produto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.produto.produto.model.dto.LoginDto;
import br.com.renato.produto.produto.model.dto.TokenDto;
import br.com.renato.produto.produto.service.auth.TokenService;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> logar(@RequestBody @Valid LoginDto login) throws AuthenticationException {

		UsernamePasswordAuthenticationToken authenticationToken = login.toUsernamePasswordAuthenticationToken();

		Authentication authenticate = authenticationManager.authenticate(authenticationToken);
		
		String token = tokenService.gerarToken(authenticate);

		TokenDto tokenDto = new TokenDto(token);

		return ResponseEntity.ok(tokenDto);
	}
}
