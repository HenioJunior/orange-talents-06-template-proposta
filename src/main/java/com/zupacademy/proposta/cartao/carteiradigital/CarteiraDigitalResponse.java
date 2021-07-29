package com.zupacademy.proposta.cartao.carteiradigital;

import com.zupacademy.proposta.cartao.Cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalResponse {

    @NotNull
    private String resultado;
    @NotBlank
    private String id;

    public CarteiraDigitalResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public CarteiraDigital toModel(Cartao cartao, ProvedorCarteira provedor) {

        return new CarteiraDigital(resultado, id, provedor,cartao);
    }

    public String getId() {
        return id;
    }

}






