package com.javanauta.aprendendospring.infraestructure.entity;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome", length =100)
	private String nome;
	@Column(name = "email", length = 100)
	private String email;
	@Column(name = "senha")
	private String senha;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="usuario_id", referencedColumnName ="id")
	private List<Endereco> enderecos;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="usuario_id", referencedColumnName = "id")
	private List<Telefone> telefones;

	public Usuario(Long id, String nome, String email, String senha, List<Endereco> enderecos,
			List<Telefone> telefones) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.enderecos = enderecos;
		this.telefones = telefones;
	}

	public Usuario() {
		super();
	}

	public long getId() {
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

	public List<Endereco> getEndereços() {
		return enderecos;
	}

	public void setEndereços(List<Endereco> endereços) {
		this.enderecos = endereços;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	
	
	
	
}
