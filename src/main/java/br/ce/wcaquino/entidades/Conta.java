package br.ce.wcaquino.entidades;

public class Conta {

	private Long id;
	private String nome;
	private Usuario usuario;
	
	public Conta() {}
	
	public Conta(String nome, Usuario usuario) {
		this.nome = nome;
		this.usuario = usuario;
	}
	
	public Conta(String nome, Long usuarioId) {
		this.nome = nome;
		this.usuario = new Usuario();
		this.usuario.setId(usuarioId);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		return "Conta [id=" + id + ", nome=" + nome + ", usuario=" + usuario + "]";
	}
}
