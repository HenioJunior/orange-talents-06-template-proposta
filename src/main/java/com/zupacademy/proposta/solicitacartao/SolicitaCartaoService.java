package com.zupacademy.proposta.solicitacartao;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.zupacademy.proposta.analiseproposta.SolicitaAnaliseRequest;
import com.zupacademy.proposta.exceptions.DatabaseException;
import com.zupacademy.proposta.novaproposta.NovaProposta;
import com.zupacademy.proposta.novaproposta.NovaPropostaRepository;


@Service
public class SolicitaCartaoService {

	@Autowired
	private SolicitaCartaoFeign solicitaCartaoFeign;
	
	@Autowired
	private NovaPropostaRepository repository;
		
	public void emitirCartao(@RequestBody @Valid SolicitaAnaliseRequest request) {
		NovaProposta proposta = buscarProposta(request.getIdProposta());
		SolicitaCartaoResponse retornoCartao =  solicitaCartaoFeign.solicitarCartao(request);
		proposta.setIdCartao(retornoCartao.getId());
		repository.save(proposta);
	}
	
	public NovaProposta buscarProposta(Long propostaId) {
		return repository.findById(propostaId).orElseThrow(() -> new DatabaseException("id não localizado."));
	}

}
