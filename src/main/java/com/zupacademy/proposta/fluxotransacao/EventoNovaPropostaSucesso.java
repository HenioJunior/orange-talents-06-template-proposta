package com.zupacademy.proposta.fluxotransacao;

import com.zupacademy.proposta.novaproposta.NovaProposta;

public interface EventoNovaPropostaSucesso {
		
	    void processa(NovaProposta proposta);
	}
