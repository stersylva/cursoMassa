package br.ce.wcaquino.entidades;


public class Usuario {
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Long contaPrincipalId;
	
	public Usuario() {}
	
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getContaPrincipalId() {
		return contaPrincipalId;
	}
	public void setContaPrincipalId(Long contaPrincipalId) {
		this.contaPrincipalId = contaPrincipalId;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}
}
