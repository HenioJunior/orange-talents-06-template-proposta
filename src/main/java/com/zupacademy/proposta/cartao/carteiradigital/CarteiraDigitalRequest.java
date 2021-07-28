package com.zupacademy.proposta.cartao.carteiradigital;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CarteiraDigitalRequest {

    @Email
    private String email;
    @NotNull
    private LocalDate associadaEm;

    private ProvedorCarteira emissor;;

    public CarteiraDigitalRequest(String email, LocalDate associadaEm, ProvedorCarteira emissor) {
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getAssociadaEm() {
        return associadaEm;
    }

    public ProvedorCarteira getEmissor() {
        return emissor;
    }
}






