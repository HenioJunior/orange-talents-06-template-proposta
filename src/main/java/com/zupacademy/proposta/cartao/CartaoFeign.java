package com.zupacademy.proposta.cartao;

import com.zupacademy.proposta.analiseproposta.SolicitaAnaliseRequest;
import com.zupacademy.proposta.cartao.aviso.AvisoViagemRequest;
import com.zupacademy.proposta.cartao.carteiradigital.CarteiraDigitalRequest;
import com.zupacademy.proposta.cartao.carteiradigital.CarteiraDigitalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@FeignClient(name = "cartao-resource", url = "http://localhost:8888", path = "/api/cartoes")
public interface CartaoFeign {

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/bloqueios", consumes = "application/json")
	String bloqueiaCartao(@PathVariable String id, @RequestBody Map<String,String> sistema);

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/avisos", consumes = "application/json")
	String avisoViagem(@PathVariable String id, @RequestBody AvisoViagemRequest request);

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/carteiras", consumes = "application/json")
	CarteiraDigitalResponse solicitarCarteira(@PathVariable String id, @RequestBody CarteiraDigitalRequest request);

	@PostMapping
	NovoCartaoResponse solicitarCartao(@Valid SolicitaAnaliseRequest request);

}