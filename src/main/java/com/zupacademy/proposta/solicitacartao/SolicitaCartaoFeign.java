package com.zupacademy.proposta.solicitacartao;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.zupacademy.proposta.fluxotransacao.SolicitaAnaliseRequest;

@FeignClient(name = "cartao-resource", url = "http://localhost:8888", path = "/api/cartoes")
public interface SolicitaCartaoFeign {
	
	@PostMapping
	SolicitaCartaoResponse solicitarCartao(@Valid SolicitaAnaliseRequest request);

}