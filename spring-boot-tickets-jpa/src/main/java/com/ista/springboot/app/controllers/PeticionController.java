package com.ista.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ista.springboot.app.models.service.IPeticionesService;

@Controller
public class PeticionController {
	@Autowired
	private IPeticionesService peticionesService;
	
	@RequestMapping(value="/listarPeticiones", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de peticiones");
		model.addAttribute("peticiones",peticionesService.findAll());
		return "/peticiones/listarPeticiones";
	}
}
