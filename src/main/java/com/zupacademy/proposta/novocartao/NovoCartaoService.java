package com.zupacademy.proposta.novocartao;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.zupacademy.proposta.analiseproposta.SolicitaAnaliseRequest;
import com.zupacademy.proposta.exceptions.DatabaseException;
import com.zupacademy.proposta.novaproposta.NovaProposta;
import com.zupacademy.proposta.novaproposta.NovaPropostaRepository;


@Service
public class NovoCartaoService {

	@Autowired
	private NovoCartaoFeign solicitaCartaoFeign;
	
	@Autowired
	private NovaPropostaRepository repository;
	
	@Autowired
	private NovoCartaoRepository cartaoRepository;
		
	public void emitirCartao(@RequestBody @Valid SolicitaAnaliseRequest request) {
		NovaProposta proposta = buscarProposta(request.getIdProposta());
		NovoCartaoResponse retornoCartao =  solicitaCartaoFeign.solicitarCartao(request);
		NovoCartao cartao = retornoCartao.toModel(proposta);
		cartaoRepository.save(cartao);
	}
	
	public NovaProposta buscarProposta(Long propostaId) {
		return repository.findById(propostaId).orElseThrow(() -> new DatabaseException("id não localizado."));
	}

}
