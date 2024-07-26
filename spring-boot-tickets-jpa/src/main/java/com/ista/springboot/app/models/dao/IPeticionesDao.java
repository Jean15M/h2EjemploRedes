package com.ista.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ista.springboot.app.entity.Peticiones;

public interface IPeticionesDao extends CrudRepository<Peticiones, Long> {

}
