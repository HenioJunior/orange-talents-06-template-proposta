package com.zupacademy.proposta.cartao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	Optional<Cartao> findByIdCartao(String idCartao);

}
