package com.ista.springboot.app.models.service;

import java.util.List;

import com.ista.springboot.app.entity.Peticiones;
import com.ista.springboot.app.entity.Ticket;

import jakarta.servlet.http.HttpServletRequest;

public interface IPeticionesService {
	public List<Peticiones> findAll();
	
	public void logRequest(String usuario, String requestType);
}
