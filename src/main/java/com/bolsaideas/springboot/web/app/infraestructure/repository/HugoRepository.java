package com.bolsaideas.springboot.web.app.infraestructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bolsaideas.springboot.web.app.domain.model.Hugo;

public interface HugoRepository extends MongoRepository<Hugo, String>{

}

