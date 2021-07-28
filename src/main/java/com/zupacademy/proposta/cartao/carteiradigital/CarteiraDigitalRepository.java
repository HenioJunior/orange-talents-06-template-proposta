package com.zupacademy.proposta.cartao.carteiradigital;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {

	Optional<CarteiraDigital> findByIdCarteira(String idCartao);

}
