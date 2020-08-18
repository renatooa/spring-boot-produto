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
import br.com.renato.produto.produto.model.dto.MensagemDto;
import br.com.renato.produto.produto.model.dto.TokenDto;
import br.com.renato.produto.produto.service.auth.TokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@ApiOperation(value = "Realiza a autenticacao na Web API")
	@ApiResponses({ @ApiResponse(code = 200, message = "O token e o tipo de autenticação"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = MensagemDto.class),
			@ApiResponse(code = 500, message = "Erro Interno Servidor", response = MensagemDto.class) })
	@PostMapping
	public ResponseEntity<TokenDto> logar(
			@ApiParam(value = "{\"login\": \"admin\",\"senha\": \"@admin\"}") @RequestBody @Valid LoginDto login)
			throws AuthenticationException {

		UsernamePasswordAuthenticationToken authenticationToken = login.toUsernamePasswordAuthenticationToken();

		Authentication authenticate = authenticationManager.authenticate(authenticationToken);

		String token = tokenService.gerarToken(authenticate);

		TokenDto tokenDto = new TokenDto(token);

		return ResponseEntity.ok(tokenDto);
	}
}
