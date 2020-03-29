package com.bolsaideas.springboot.web.app.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.bolsaideas.springboot.web.app.domain.model.Hugo;
import com.bolsaideas.springboot.web.app.service.HugoService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PreApplicationEventHandlerHugo  {
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	HugoService hugoService;
	
	@JmsListener(destination = "${message.outbound.hugoWithoutJMQueue}")
	public void saveHugoPreApplicationWithoutJM(String json) {
		this.processDevice(json);
	}
	
	private void processDevice(String json) {
		
		Hugo hugoEventDTO= null;
		
		try {
			
			hugoEventDTO=objectMapper.readValue(json, Hugo.class);
			
			if(hugoEventDTO != null) {
				Hugo hugoDto= hugoService.findById(hugoEventDTO.getId()).orElse(null);
				
				if(hugoDto != null) {
					
					hugoDto.setId(hugoDto.getId());
					hugoDto.setNombre(hugoDto.getNombre());
					hugoDto.setApellido(hugoDto.getApellido());
					hugoDto.setCedula(hugoDto.getCedula());
					hugoDto.setCorreo(hugoDto.getCorreo());
					hugoDto.setEstado(hugoDto.getEstado());
					
					hugoService.save(hugoDto);
				}
				
			}
			
		}catch(JsonParseException e) {
				log.error("[Not Save:: HugoDevice] :: - {}",e.getMessage());
				e.printStackTrace();
		} catch (JsonMappingException e) {
			 log.error("[JsonMappingException:: HugoDevice] ::  - {}", e.getMessage());
	         e.printStackTrace();
		} catch (JsonProcessingException e) {
			log.error("[IOException:: HugoDevice] ::  - {}", e.getMessage());
            e.printStackTrace();
		}
	}	

}
