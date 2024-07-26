package com.ista.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ista.springboot.app.entity.Ticket;
import com.ista.springboot.app.models.dao.ITicketDao;


@Service
public class TicketServiceImpl implements ITicketService {

	@Autowired
	private ITicketDao ticketoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Ticket> findAll() {
		return (List<Ticket>) ticketoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Ticket ticket) {
		ticketoDao.save(ticket);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Ticket findOne(Long id) {
		return ticketoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ticketoDao.deleteById(id);
		
	}

}
