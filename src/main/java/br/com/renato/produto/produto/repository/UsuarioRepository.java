package br.com.renato.produto.produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.produto.produto.model.entity.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}