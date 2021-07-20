package com.zupacademy.proposta.novaproposta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NovaPropostaRepository extends JpaRepository<NovaProposta, Long> {
	
	Optional <NovaProposta> findByDocumento(String documento);

}
