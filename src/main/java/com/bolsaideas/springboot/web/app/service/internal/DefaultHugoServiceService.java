package com.bolsaideas.springboot.web.app.service.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bolsaideas.springboot.web.app.domain.model.Hugo;
import com.bolsaideas.springboot.web.app.infraestructure.repository.HugoRepository;
import com.bolsaideas.springboot.web.app.service.HugoService;

@Service
public class DefaultHugoServiceService implements HugoService{
	
	@Autowired
	private HugoRepository hugoRepository;
	
	@Override
	public Hugo save(Hugo hugo) {
		return hugoRepository.save(hugo);
	}

	@Override
	public List<Hugo> findAll() {
		return hugoRepository.findAll();
	}

	@Override
	public Optional<Hugo> findById(String idHugo) {
		return hugoRepository.findById(idHugo);
		
	}

	@Override
	public void delete(String idHugo) {
		 hugoRepository.deleteById(idHugo);
		
	}

	@Override
	public Hugo update(Hugo hugo) {
		this.findById(hugo.getId());
		return hugoRepository.save(hugo);
	}
	
}
