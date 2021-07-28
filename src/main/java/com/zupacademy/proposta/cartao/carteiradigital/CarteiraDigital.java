package com.zupacademy.proposta.cartao.carteiradigital;

import com.zupacademy.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate associadaEm = LocalDate.now();
    @NotBlank
    private String resultado;
    @NotBlank
    private String idCarteira;
    @Enumerated
    private ProvedorCarteira provedor;
    @ManyToOne
    private Cartao cartao;

    public CarteiraDigital(String resultado, String idCarteira, ProvedorCarteira provedor,Cartao cartao) {
        this.resultado = resultado;
        this.idCarteira = idCarteira;
        this.provedor = provedor;
        this.cartao = cartao;
    }

    public CarteiraDigital() {
    }

    public Cartao getCartao() {
        return cartao;
    }

    public ProvedorCarteira getProvedor() {
        return provedor;
    }

    @Override
    public String toString() {
        return "CarteiraDigital{" +
                "associadaEm=" + associadaEm +
                ", resultado='" + resultado + '\'' +
                ", idCarteira='" + idCarteira + '\'' +
                ", cartao=" + cartao +
                '}';
    }
}
