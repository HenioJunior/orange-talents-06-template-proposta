package com.zupacademy.proposta.novocartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zupacademy.proposta.novaproposta.NovaProposta;

@Entity
public class NovoCartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String idCartao;
	@NotBlank
	private String emitidoEm;
	@NotNull
	private int limite;

	private String password;
	
	private LocalDateTime dataCriacaoPassword;
	
	@ManyToOne
	private NovaProposta novaProposta;

	public NovoCartao(@NotBlank String idCartao, @NotBlank String emitidoEm, @NotNull int limite,
			NovaProposta novaProposta) {
		this.idCartao = idCartao;
		this.emitidoEm = emitidoEm;
		this.limite = limite;
		this.novaProposta = novaProposta;
	}
		
	public NovoCartao() {
	}

	public String getIdCartao() {
		return idCartao;
	}

	public String getEmitidoEm() {
		return emitidoEm;
	}

	public int getLimite() {
		return limite;
	}

	public NovaProposta getNovaProposta() {
		return novaProposta;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDataCriacaoPassword(LocalDateTime dataCriacaoPassword) {
		this.dataCriacaoPassword = dataCriacaoPassword;
	}
		
}
