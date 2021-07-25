package com.zupacademy.proposta.analiseproposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "solicitacao-analise-resource", url = "http://localhost:9999", path = "/api/solicitacao")
public interface SolicitaAnaliseFeign {
	
	@PostMapping
	RetornoAnaliseRequest enviarParaAnalise(SolicitaAnaliseRequest request);
}