package com.zupacademy.proposta.novocartao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NovoCartaoRepository extends JpaRepository<NovoCartao, Long> {

	Optional<NovoCartao> findByIdCartao(String idCartao);

}
