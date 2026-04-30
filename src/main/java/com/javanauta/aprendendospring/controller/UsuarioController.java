package com.javanauta.aprendendospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javanauta.aprendendospring.business.UsuarioService;
import com.javanauta.aprendendospring.controller.dtos.UsuarioDTO;
import com.javanauta.aprendendospring.infraestructure.entity.Usuario;
import com.javanauta.aprendendospring.infraestructure.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping
	public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.salvaUsuario(usuario));
		
		
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UsuarioDTO usuarioDTO) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						usuarioDTO.getEmail(),
						usuarioDTO.getSenha()
						)
				);		
		return "Bearer " + jwtUtil.generateToken(authentication.getName());
	}
	
	@GetMapping
	public ResponseEntity<Usuario> buscaUsuarioPorEmail(@RequestParam("email") String email){
		return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
		
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email){
		usuarioService.deletarUsuarioPorEmail(email);
		return ResponseEntity.ok().build();
		
		
	}
	
}
