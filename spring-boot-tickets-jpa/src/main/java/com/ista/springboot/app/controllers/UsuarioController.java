package com.ista.springboot.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ista.springboot.app.entity.Usuario;
import com.ista.springboot.app.models.service.IPeticionesService;
import com.ista.springboot.app.models.service.IUsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPeticionesService peticionService;
	
	//@GetMapping({"/","","/inicio"})
	//public String inicio() {
	
		//return "layout/layout";
	//}
	
	@RequestMapping(value="/listarUsuario", method = RequestMethod.GET)
	public String listar(Model model, HttpSession sesion ) {
		String usuario = (String) sesion.getAttribute("username");
		peticionService.logRequest(usuario, "GET /listarUsuario");
		model.addAttribute("titulo","Listado de usuario");
		model.addAttribute("usuarios",usuarioService.findAll());
		return "usuario/listarUsuario";
	}
	
	@RequestMapping(value="/formUsuario")
	public String crear(Map<String, Object> model) {
		Usuario usuario = new Usuario();
		model.put("usuario", usuario);
		model.put("titulo", "formulario de usuario");
		return "usuario/formUsuario";
	}
	
	@RequestMapping(value="/formUsuario", method = RequestMethod.POST)
	public String guardar(Usuario usuario, RedirectAttributes flash, HttpSession sesion) {
		String usuarioLogeado = (String) sesion.getAttribute("username");
		peticionService.logRequest(usuarioLogeado, "POST /formUsuario");
		String mensajeFls = (usuario.getId_usuario() != null)?"El registro del usuario se ha editado con éxito" : "El registro del usuario se ha creado con éxito";
		usuarioService.save(usuario);
		flash.addFlashAttribute("success",mensajeFls);
		return "redirect:/listarUsuario";
	}
	
	@RequestMapping(value="formUsuario/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash, HttpSession sesion) {
		String usuarioLogeado = (String) sesion.getAttribute("username");
		peticionService.logRequest(usuarioLogeado, "UPDATE /formUsuario con ID: "+id);
		Usuario usuario = null;
		
		if(id > 0) {
			usuario = usuarioService.findOne(id);
			if(usuario == null) {
				flash.addFlashAttribute("info","El usuario no existe en la base de datos");
			}
		}else {
			flash.addFlashAttribute("error","El ID del usuario no puede ser cero");
			return "redirect:/listarUsuario";
		}
		model.put("usuario", usuario);
		model.put("titulo", "formulario de usuario");
		return "usuario/formUsuario";
	}
	
	@RequestMapping(value="/eliminarUsuario/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash, HttpSession sesion) {
		String usuarioLogeado = (String) sesion.getAttribute("username");
		peticionService.logRequest(usuarioLogeado, "DELETE /eliminarUsuario con ID: "+id);
		if(id > 0) {
			usuarioService.delete(id);
			flash.addFlashAttribute("success","Usuario eliminado con éxito");
		}
		return "redirect:/listarUsuario";
	}
}
