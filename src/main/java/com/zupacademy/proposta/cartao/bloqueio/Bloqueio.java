package com.zupacademy.proposta.cartao.bloqueio;

import com.sun.istack.NotNull;
import com.zupacademy.proposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cartao cartao;

    private LocalDateTime instante = LocalDateTime.now();

    @NotNull
    @Column(nullable = false)
    private String ipCliente;

    @NotNull
    @Column(nullable = false)
    private String userAgent;

    @Deprecated
    public Bloqueio() {

    }

    public Bloqueio(Cartao cartao, @NotNull String ipCliente,
                    @NotNull String userAgent) {
        this.cartao = cartao;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }
}
