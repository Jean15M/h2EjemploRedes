package com.ista.springboot.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="ticket", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_usuario", "id_ticket" }) })
public class Ticket implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ticket;
	
	private String estado;
	private String departamento;
	
	@Temporal(TemporalType.DATE)
	private Date fecha_creacion;
	
	private Long id_usuario;
	private String imagen;
	private String asunto;
	private String detalle_ticket;
	private String localidad;
	
	@PrePersist
	public void prePersist() {
		fecha_creacion = new Date();
	}
	
	public Long getId_ticket() {
		return id_ticket;
	}
	public void setId_ticket(Long id_ticket) {
		this.id_ticket = id_ticket;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getDetalle_ticket() {
		return detalle_ticket;
	}
	public void setDetalle_ticket(String detalle_ticket) {
		this.detalle_ticket = detalle_ticket;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	
	
	
	
	
	
}
