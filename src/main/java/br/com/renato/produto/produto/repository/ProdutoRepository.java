package br.com.renato.produto.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.produto.produto.model.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

}