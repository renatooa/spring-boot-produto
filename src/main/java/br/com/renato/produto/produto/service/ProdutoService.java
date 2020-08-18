package br.com.renato.produto.produto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.renato.produto.produto.model.dto.ProdutoDto;
import br.com.renato.produto.produto.model.entity.Produto;
import br.com.renato.produto.produto.model.exception.NaoEncontradoException;
import br.com.renato.produto.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<ProdutoDto> listarProdutos(Sort ordem) throws NaoEncontradoException {

		List<Produto> produtos = produtoRepository.findAll(ordem);

		if (produtos == null || produtos.isEmpty()) {
			throw new NaoEncontradoException();
		}

		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}

	public ProdutoDto recuperarProduto(String idProduto) throws NaoEncontradoException {
		Produto produto = recuperarEVerificarProduto(idProduto);

		return new ProdutoDto(produto);
	}

	public void apagarProduto(String idProduto) throws NaoEncontradoException {

		Produto produto = recuperarEVerificarProduto(idProduto);

		produtoRepository.delete(produto);
	}

	public void editarProduto(String idProduto, ProdutoDto produtoDto) throws NaoEncontradoException {

		Produto produto = recuperarEVerificarProduto(idProduto);

		produtoDto.atualizar(produto);
	}

	public String inserirProduto(ProdutoDto produtoDto) {

		Produto produto = produtoDto.toProduto();

		produtoRepository.save(produto);
		
		return produto.getId();
	}

	private Produto recuperarEVerificarProduto(String idProduto) throws NaoEncontradoException {

		Optional<Produto> produto = produtoRepository.findById(idProduto);

		if (produto.isPresent()) {

			return produto.get();

		} else {

			throw new NaoEncontradoException();
		}
	}

}