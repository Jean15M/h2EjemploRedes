package com.ista.springboot.app.models.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ista.springboot.app.entity.Peticiones;
import com.ista.springboot.app.models.dao.IPeticionesDao;


@Service
public class PeticionesServiceImpl implements IPeticionesService {

	@Autowired
	private IPeticionesDao peticionesDao;

	@Override
	public List<Peticiones> findAll() {
		// TODO Auto-generated method stub
		return (List<Peticiones>) peticionesDao.findAll();
	}

	@Override
	public void logRequest(String username, String requestType) {
		Peticiones peticiones = new Peticiones();
		peticiones.setUsuario(username);
		peticiones.setTipo_peticion(requestType);
		peticiones.setFecha_peticion(LocalDateTime.now());
		peticionesDao.save(peticiones);

	}

}
