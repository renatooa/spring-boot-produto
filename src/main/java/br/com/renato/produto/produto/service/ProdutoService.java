package br.com.renato.produto.produto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renato.produto.produto.model.dto.ProdutoDto;
import br.com.renato.produto.produto.model.entity.Produto;
import br.com.renato.produto.produto.model.exception.NaoEncontradoException;
import br.com.renato.produto.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public List<ProdutoDto> listarProdutos() throws NaoEncontradoException {

		List<Produto> produtos = produtoRepository.findAll();

		if (produtos == null || produtos.isEmpty()) {
			throw new NaoEncontradoException();
		}

		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}

}
