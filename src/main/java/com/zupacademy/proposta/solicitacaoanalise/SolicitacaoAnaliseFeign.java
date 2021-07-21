package com.zupacademy.proposta.solicitacaoanalise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "solicitacao-analise-resource", url = "http://localhost:9999", path = "/api/solicitacao")
public interface SolicitacaoAnaliseFeign {
	
	@PostMapping
	RetornoAnaliseRequest enviarParaAnalise(SolicitacaoAnaliseRequest solicitacaoAnaliseRequest);

}