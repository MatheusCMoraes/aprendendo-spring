package com.javanauta.aprendendospring.controller.dtos;

public class UsuarioDTO {
	
	private String email;
	private String senha;
	
	public UsuarioDTO(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public UsuarioDTO() {
		super();
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
	
	

}
