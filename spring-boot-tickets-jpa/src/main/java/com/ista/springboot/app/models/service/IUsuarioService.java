package com.ista.springboot.app.models.service;

import java.util.List;

import com.ista.springboot.app.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll();

	public void save(Usuario usuario);

	public Usuario findOne(Long id);

	public void delete(Long id);
	
	boolean authenticate(String username, String password);
	
    Usuario findByUsername(String username);
}
