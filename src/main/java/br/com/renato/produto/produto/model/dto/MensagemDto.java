package br.com.renato.produto.produto.model.dto;

public class MensagemDto {

	private String mensagem;
	private boolean sucesso;
	private String descricao;

	public MensagemDto() {
		this.mensagem = "ok";
		this.sucesso = true;
		this.descricao = "ok";
	}
	
	public MensagemDto(String mensagem, boolean sucesso, String descricao) {
		super();
		this.mensagem = mensagem;
		this.sucesso = sucesso;
		this.descricao = descricao;
	}
	
	public MensagemDto(String mensagem, boolean sucesso) {
		super();
		this.mensagem = mensagem;
		this.sucesso = sucesso;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}