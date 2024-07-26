package com.ista.springboot.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="peticiones")
public class Peticiones implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_request;
	
	private String usuario;
	private LocalDateTime fecha_peticion;
	private String tipo_peticion;
	public Long getId_request() {
		return id_request;
	}
	public void setId_request(Long id_request) {
		this.id_request = id_request;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public LocalDateTime getFecha_peticion() {
		return fecha_peticion;
	}
	public void setFecha_peticion(LocalDateTime fecha_peticion) {
		this.fecha_peticion = fecha_peticion;
	}
	public String getTipo_peticion() {
		return tipo_peticion;
	}
	public void setTipo_peticion(String tipo_peticion) {
		this.tipo_peticion = tipo_peticion;
	}
	
	
}
