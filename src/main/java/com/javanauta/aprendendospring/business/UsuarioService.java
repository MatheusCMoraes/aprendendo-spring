package com.javanauta.aprendendospring.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javanauta.aprendendospring.infraestructure.entity.Usuario;
import com.javanauta.aprendendospring.infraestructure.exceptions.ConflictException;
import com.javanauta.aprendendospring.infraestructure.exceptions.ResourceNotFoundException;
import com.javanauta.aprendendospring.infraestructure.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private  UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public Usuario salvaUsuario(Usuario usuario) {
		
		try {
			emailExiste(usuario.getEmail());
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		
			return usuarioRepository.save(usuario);
			
		}catch(ConflictException e) {
			throw new ConflictException("Email já cadastrado" + e.getCause());
		}
			
			
		}
		
		public void emailExiste(String email) {
			try {
				
				boolean existe = verificaEmailExistente(email);
				
				if(existe) {
					
					throw new ConflictException("Email já cadastrado" + email);
				
				}
			}catch(ConflictException e) {
				
				throw new ConflictException("Email já cadastrado" + e.getCause());
			}
			
		
		}
		
		public boolean verificaEmailExistente(String email) {	
			return usuarioRepository.existsByEmail(email);
	} 
	
	public Usuario buscarUsuarioPorEmail(String email) {
		return usuarioRepository.findByEmail(email).orElseThrow(
				() -> new ResourceNotFoundException("Email não encontrado: " + email));
		
	}
	
	public void deletarUsuarioPorEmail(String email) {
		usuarioRepository.deleteByEmail(email);
		
	}
}
