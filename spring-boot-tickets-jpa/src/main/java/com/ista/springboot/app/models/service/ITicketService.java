package com.ista.springboot.app.models.service;

import java.util.List;

import com.ista.springboot.app.entity.Ticket;

public interface ITicketService {
	public List<Ticket> findAll();

	public void save(Ticket ticket);

	public Ticket findOne(Long id);

	public void delete(Long id);
}
