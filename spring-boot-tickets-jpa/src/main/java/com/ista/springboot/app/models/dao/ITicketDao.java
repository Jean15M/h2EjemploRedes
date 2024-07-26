package com.ista.springboot.app.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.ista.springboot.app.entity.Ticket;

public interface ITicketDao extends CrudRepository<Ticket, Long> {
	

}
