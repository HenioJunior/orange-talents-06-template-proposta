package com.zupacademy.proposta.novaproposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmissaoProposta {

	@Autowired
	NovaPropostaRepository repository;
	
	public NovaProposta emitir(NovaProposta proposta) {
				
		
		return repository.save(proposta);
	}

	
	
}
