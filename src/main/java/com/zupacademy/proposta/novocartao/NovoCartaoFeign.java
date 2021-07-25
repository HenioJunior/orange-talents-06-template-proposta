package com.zupacademy.proposta.novocartao;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.zupacademy.proposta.analiseproposta.SolicitaAnaliseRequest;

@FeignClient(name = "cartao-resource", url = "http://localhost:8888", path = "/api/cartoes")
public interface NovoCartaoFeign {
	
	@PostMapping
	NovoCartaoResponse solicitarCartao(@Valid SolicitaAnaliseRequest request);

}