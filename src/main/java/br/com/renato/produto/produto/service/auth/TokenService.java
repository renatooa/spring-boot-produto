package br.com.renato.produto.produto.service.auth;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.renato.produto.produto.model.entity.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${produto.jwt.token.validade}")
	private String tokenValidadeMinutos;

	@Value("${produto.jwt.chave}")
	private String chave;

	public String gerarToken(Authentication authenticationToken) {

		Usuario usuario = (Usuario) authenticationToken.getPrincipal();

		Date dataAtual = new Date();

		Calendar dataValidede = gerarValidade(dataAtual);

		return Jwts.builder().setIssuer("API Produto").setSubject(usuario.getLogin()).setIssuedAt(dataAtual)
				.setExpiration(dataValidede.getTime()).signWith(SignatureAlgorithm.HS256, chave).compact();
	}

	private Calendar gerarValidade(Date dataAtual) {
		Calendar dataValidede = Calendar.getInstance();
		dataValidede.setTime(dataAtual);
		dataValidede.add(Calendar.MINUTE, Integer.parseInt(tokenValidadeMinutos));
		return dataValidede;
	}

	public String isTokenValido(String token) {

		try {

			Claims body = Jwts.parser().setSigningKey(chave).parseClaimsJws(token).getBody();

			if (body != null) {
				return body.getSubject();
			}

		} catch (Exception e) {
		}
		return null;
	}
}