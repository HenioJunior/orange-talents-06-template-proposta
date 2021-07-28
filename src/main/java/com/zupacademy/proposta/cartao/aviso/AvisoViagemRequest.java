package com.zupacademy.proposta.cartao.aviso;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import com.zupacademy.proposta.cartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class AvisoViagemRequest {
    @NotBlank
    private String destino;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future(message = "A data do termino da viagem deve ser futura.")
    @NotNull
    private LocalDate validoAte;

    @JsonCreator
    public AvisoViagemRequest(@NotBlank String destino,
                              @NotBlank @Future(message = "A data do termino da viagem deve ser futura.") LocalDate validoAte) {
        super();
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public AvisoViagem toModel(Cartao cartao, String ipClient, String userAgent) {
        return new AvisoViagem(cartao, destino, validoAte, ipClient, userAgent);
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    @Override
    public String toString() {
        return "Aviso de Viagem{" +
                "destino='" + destino + '\'' +
                ", validoAte=" + validoAte +
                '}';
    }
}
