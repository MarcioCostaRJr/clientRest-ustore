package model.http;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pessoa {

	private Integer id;
	private String nome, email, telefone, senha;
	
	public Pessoa() {}
	
	public Pessoa(int id, String nome, String email, String telefone, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "{ \"nome\": \""+ this.nome + "\", \"email\": \"" +
				this.email + "\", \"telefone\" : \"" + this.telefone +
				"\", \"senha\":\"" + this.senha + "\"}";
	}
}
