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

import com.ista.springboot.app.entity.Ticket;
import com.ista.springboot.app.entity.Usuario;
import com.ista.springboot.app.models.service.IPeticionesService;
import com.ista.springboot.app.models.service.ITicketService;
import com.ista.springboot.app.models.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;


@Controller
public class TicketController {
	@Autowired
	private ITicketService ticketService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPeticionesService peticionService;
	
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable (value= "id") Long id, Map<String, Object> model, RedirectAttributes flash, HttpSession sesion) {
		String usuarioLogeado = (String) sesion.getAttribute("username");
		peticionService.logRequest(usuarioLogeado, "GET /verTicket con ID: "+id);
		Ticket ticket = ticketService.findOne(id);
		Usuario usuario = usuarioService.findOne(ticket.getId_usuario());
		if(ticket == null || usuario == null) {
			flash.addFlashAttribute("error","El ticket o usuario no se encuentra en la base");
			return "redirect:/listar_ticket";
		}
		model.put("usuario", usuario);
		model.put("ticket", ticket);
		model.put("titulo", "Información del ticket");
		
		return "ticket/ver";
	}
	
	
	@RequestMapping(value="/listar_ticket", method = RequestMethod.GET)
	public String listar(Model model, HttpSession sesion) {
		String usuario = (String) sesion.getAttribute("username");
		peticionService.logRequest(usuario, "GET /listarTicket");
		model.addAttribute("titulo","Listado de tickets");
		model.addAttribute("tickets",ticketService.findAll());
		return "/ticket/listar_ticket";
	}
	
	@GetMapping(value="/form_ticket")
	public String mostrarFormulario(Model model) {
		Ticket ticket = new Ticket();
		model.addAttribute("titulo","Formulario de tickets");
        model.addAttribute("ticket", ticket);
		model.addAttribute("usuarios",usuarioService.findAll());
		return "/ticket/form_ticket";
	}
	
	@RequestMapping(value="/form_ticket")
	public String crear(Map<String, Object> model) {
		Ticket ticket = new Ticket();
		model.put("ticket", ticket);
		return "/ticket/form_ticket";
	}
	
	@RequestMapping(value="/form_ticket", method = RequestMethod.POST)
	public String guardar(Ticket ticket, RedirectAttributes flash, @RequestParam("file") MultipartFile foto, HttpSession sesion) {
		String usuariLogeado = (String) sesion.getAttribute("username");
		peticionService.logRequest(usuariLogeado, "POST /form_ticket");
		if(!foto.isEmpty()) {
			//Path directoryFotos = Paths.get("C://Temp//uploads");
			String rootPath = "C://Temp//uploads";
			
			try {
				byte[] bytes = foto.getBytes();
				Path Rutaarchivo = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(Rutaarchivo, bytes);
				flash.addFlashAttribute("info", "Se ha subido correctamente la foto del ticket '" + foto.getOriginalFilename() + "'");
				ticket.setImagen(foto.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String mensajeFls = (ticket.getId_ticket() != null)?"El registro del ticket se ha editado con éxito" : "El registro del ticket se ha creado con éxito";
		ticket.setEstado("Pendiente");
		ticketService.save(ticket);
		flash.addFlashAttribute("success",mensajeFls);
		return "redirect:listar_ticket";
	}
	
	@RequestMapping(value="/form_ticket/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash, HttpSession sesion) {
		String usuarioLogeado = (String) sesion.getAttribute("username");
		peticionService.logRequest(usuarioLogeado, "PUT /form_ticket con ID: "+id);
		Ticket ticket = null;
		
		if(id > 0) {
			ticket = ticketService.findOne(id);
			if(ticket == null) {
				flash.addFlashAttribute("info","El ticket no existe en la base de datos");
			}
		}else {
			flash.addFlashAttribute("error","El ID del ticket no puede ser cero");
			return "redirect:/listar_ticket";
		}
		ticket.setEstado("Aceptado");
		ticketService.save(ticket);
		return "redirect:/listar_ticket";
	}
	
}
