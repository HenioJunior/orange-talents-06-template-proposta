package com.zupacademy.proposta.cartao;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.zupacademy.proposta.cartao.bloqueio.DadosCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zupacademy.proposta.exceptions.DatabaseException;

import java.util.Map;


@RestController
@RequestMapping(value = "/cartoes")
public class CartaoController {
	
	@Autowired
	private CartaoRepository repository;

	@Autowired
	DadosCliente dadosCliente;

	@Autowired
	CartaoFeign feign;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criarBiometria(@RequestBody @Valid NovaBiometriaRequest request) {
		Cartao atualizaCartao = repository.findByIdCartao(request.getIdCartao()).orElseThrow(() -> new DatabaseException("id não localizado."));
		request.ToModel(atualizaCartao);
						
		repository.save(atualizaCartao);
		return ResponseEntity.status(HttpStatus.CREATED).body(request);
		
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/bloqueios")
	@Transactional
	public ResponseEntity<?> bloqueiaCartao(@PathVariable String id, HttpServletRequest request){

		Cartao cartao = buscarCartao(id);
		cartao.verificaBloqueio(dadosCliente.buscaIpClient(request),
				request.getHeader("USER-AGENT"));

		feign.bloqueiaCartao(cartao.getIdCartao(),
				Map.of("sistemaResponsavel", "desafioproposta"));

		repository.save(cartao);

		return ResponseEntity.ok().build();
	}

	public Cartao buscarCartao(String idCartao) {
		return repository.findByIdCartao(idCartao).orElseThrow(() -> new DatabaseException("id não localizado."));
	}

}
