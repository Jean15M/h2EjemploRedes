package com.ista.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.app.entity.Usuario;
import com.ista.springboot.app.models.dao.IUsuarioDao;


@Service
public class UsuarioServiceImpl implements IUsuarioService {
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findOne(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

	@Override
	public boolean authenticate(String username, String password) {
		Usuario usuario = usuarioDao.findByNombre(username);
		return usuario != null && usuario.getContrasena().equals(password);
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioDao.findByNombre(username);
	}
	
	
}
