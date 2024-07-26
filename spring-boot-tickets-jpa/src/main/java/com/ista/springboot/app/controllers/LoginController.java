package com.ista.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ista.springboot.app.models.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping({"/","","/login"})
	public String inicio() {
	
		return "login/loginUsuario";
	}
	
	@PostMapping("/loginUsuario")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        if (usuarioService.authenticate(username, password)) {
            session.setAttribute("username", username);
            return "layout/layout";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login/loginUsuario";
        }
    }
	
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login/loginUsuario";
    }
}
