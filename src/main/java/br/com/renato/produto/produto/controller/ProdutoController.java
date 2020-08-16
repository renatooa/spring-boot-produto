package br.com.renato.produto.produto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renato.produto.produto.model.dto.ProdutoDto;
import br.com.renato.produto.produto.model.exception.NaoEncontradoException;
import br.com.renato.produto.produto.service.ProdutoService;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<ProdutoDto> listarProdutos() throws NaoEncontradoException {
		return produtoService.listarProdutos();
	}
}