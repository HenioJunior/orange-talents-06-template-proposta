package com.zupacademy.proposta.cartao.aviso;


import com.sun.istack.NotNull;
import com.zupacademy.proposta.cartao.Cartao;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

         @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(optional = false)
        private Cartao cartao;

        @NotNull
        @Column(nullable = false)
        private String destino;

        private LocalDateTime instante = LocalDateTime.now();

        @Future
        @NotNull
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        private LocalDate validoAte;

        @NotNull
        @Column(nullable = false)
        private String ipCliente;

        @NotNull
        @Column(nullable = false)
        private String userAgent;

        public AvisoViagem() {

        }

        public AvisoViagem(Cartao cartao, @NotNull String destino,
                           @Future @NotNull LocalDate validoAte, @NotNull String ipCliente, @NotNull String userAgent) {
            this.cartao = cartao;
            this.destino = destino;
            this.validoAte = validoAte;
            this.ipCliente = ipCliente;
            this.userAgent = userAgent;
        }

    @Override
    public String toString() {
        return "AvisoViagem{" +
                "destino='" + destino + '\'' +
                ", instante=" + instante +
                ", dataTerminoViagem=" + validoAte +
                '}';
    }
}
