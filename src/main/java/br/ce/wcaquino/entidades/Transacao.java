package br.ce.wcaquino.entidades;

import java.util.Date;

public class Transacao {

	private Long id;
	private String descricao;
	private String envolvido;
	private TipoTransacao tipo;
	private Date data;
	private Double valor;
	private Boolean status;
	private Conta conta;
	private Usuario usuario;
	
	public Transacao() {}
	
	public Transacao(String descricao, String envolvido, TipoTransacao tipo, Date data, Double valor, Boolean status,
			Conta conta, Usuario usuario) {
		this.descricao = descricao;
		this.envolvido = envolvido;
		this.tipo = tipo;
		this.data = data;
		this.valor = valor;
		this.status = status;
		this.conta = conta;
		this.usuario = usuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getEnvolvido() {
		return envolvido;
	}
	public void setEnvolvido(String envolvido) {
		this.envolvido = envolvido;
	}
	public TipoTransacao getTipo() {
		return tipo;
	}
	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", descricao=" + descricao + ", envolvido=" + envolvido + ", tipo=" + tipo
				+ ", data=" + data + ", valor=" + valor + ", status=" + status + ", conta=" + conta + ", usuario="
				+ usuario + "]";
	}
}
