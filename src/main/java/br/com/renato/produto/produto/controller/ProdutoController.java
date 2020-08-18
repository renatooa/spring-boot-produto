package br.com.renato.produto.produto.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.renato.produto.produto.model.dto.MensagemDto;
import br.com.renato.produto.produto.model.dto.ProdutoDto;
import br.com.renato.produto.produto.model.exception.NaoEncontradoException;
import br.com.renato.produto.produto.service.ProdutoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/produtos")
public class ProdutoController {

	private static final String VALUE_CACHE_PRODUTOS = "listaDeProdutos";
	private static final String VALUE_CACHE_PRODUTO_ID = "produtoId";
	@Autowired
	private ProdutoService produtoService;

	@ApiOperation(value = "Recupera todos os produtos")
	@ApiResponses({ @ApiResponse(code = 200, message = "A lista de produtos"),
			@ApiResponse(code = 204, message = "Nenhum produto encontrado"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = MensagemDto.class),
			@ApiResponse(code = 500, message = "Erro Interno Servidor", response = MensagemDto.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = HttpHeaders.AUTHORIZATION, value = "Token de acesso", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer {token}"),
			@ApiImplicitParam(name = "ordem", value = "Ordenação de produto", paramType = "query", dataTypeClass = String.class, example = "id,desc") })
	@GetMapping
	@Cacheable(value = VALUE_CACHE_PRODUTOS)
	public List<ProdutoDto> listarProdutos(@ApiIgnore @PageableDefault(sort = { "id,desc" }) Sort ordem)
			throws NaoEncontradoException {
		return produtoService.listarProdutos(ordem);
	}

	@ApiOperation(value = "Recupera produto pelo ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto"),
			@ApiResponse(code = 204, message = "Nenhum produto encontrado"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = MensagemDto.class),
			@ApiResponse(code = 500, message = "Erro Interno Servidor", response = MensagemDto.class) })
	@ApiImplicitParam(name = HttpHeaders.AUTHORIZATION, value = "Token de acesso", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer {token}")
	@GetMapping("/{idProduto}")
	@Cacheable(value = VALUE_CACHE_PRODUTO_ID)
	public ProdutoDto recuperarProduto(@PathVariable(required = true) String idProduto) throws NaoEncontradoException {
		return produtoService.recuperarProduto(idProduto);
	}

	@ApiOperation(value = "Apaga um produto pelo ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Mensagem", response = MensagemDto.class),
			@ApiResponse(code = 204, message = "Nenhum produto encontrado"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = MensagemDto.class),
			@ApiResponse(code = 500, message = "Erro Interno Servidor", response = MensagemDto.class) })
	@ApiImplicitParam(name = HttpHeaders.AUTHORIZATION, value = "Token de acesso", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer {token}")
	@Transactional
	@DeleteMapping("/{idProduto}")
	@CacheEvict(value = { VALUE_CACHE_PRODUTOS, VALUE_CACHE_PRODUTO_ID }, allEntries = true)
	public ResponseEntity<MensagemDto> apagarProduto(@PathVariable(required = true) String idProduto)
			throws NaoEncontradoException {

		produtoService.apagarProduto(idProduto);

		return criarResponseMensagemOk();
	}

	@ApiOperation(value = "Edita um produto pelo ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Mensagem", response = MensagemDto.class),
			@ApiResponse(code = 204, message = "Nenhum produto encontrado"),
			@ApiResponse(code = 400, message = "Requisição inválida", response = MensagemDto.class),
			@ApiResponse(code = 500, message = "Erro Interno Servidor", response = MensagemDto.class) })
	@ApiImplicitParam(name = HttpHeaders.AUTHORIZATION, value = "Token de acesso", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer {token}")
	@Transactional
	@PutMapping("/{idProduto}")
	@CacheEvict(value = { VALUE_CACHE_PRODUTOS, VALUE_CACHE_PRODUTO_ID }, allEntries = true)
	public ResponseEntity<MensagemDto> editarProduto(@RequestBody(required = true) @Valid ProdutoDto produto,
			@PathVariable(required = true) String idProduto) throws NaoEncontradoException {

		produtoService.editarProduto(idProduto, produto);

		return criarResponseMensagemOk();
	}

	@ApiOperation(value = "Insere um produto")
	@ApiResponses({ @ApiResponse(code = 201, message = "Mensagem", response = MensagemDto.class),
			@ApiResponse(code = 400, message = "Requisição inválida", response = MensagemDto.class),
			@ApiResponse(code = 500, message = "Erro Interno Servidor", response = MensagemDto.class) })
	@ApiImplicitParam(name = HttpHeaders.AUTHORIZATION, value = "Token de acesso", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer {token}")
	@PostMapping
	@Transactional
	@CacheEvict(value = { VALUE_CACHE_PRODUTOS, VALUE_CACHE_PRODUTO_ID }, allEntries = true)
	public ResponseEntity<MensagemDto> inserirProduto(@RequestBody(required = true) @Valid ProdutoDto produto,
			UriComponentsBuilder uriBuilder) {

		String idProduto = produtoService.inserirProduto(produto);

		String path = "/produtos/{idProduto}";

		return ResponseEntity.created(criarUri(uriBuilder, path, idProduto)).body(new MensagemDto());
	}

	private ResponseEntity<MensagemDto> criarResponseMensagemOk() {
		return ResponseEntity.ok(new MensagemDto());
	}

	private URI criarUri(UriComponentsBuilder uriBuilder, String path, String id) {

		URI uri = uriBuilder.path(path).buildAndExpand(id).toUri();

		return uri;
	}
}