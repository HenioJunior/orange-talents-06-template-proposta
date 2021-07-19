package com.zupacademy.proposta.novaproposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.zupacademy.proposta.validacao.CPFOrCNPJ;
import com.zupacademy.proposta.validacao.UniqueValue;

public class NovaPropostaRequest {

	@NotBlank
	private String nome;
	@Email
	@NotBlank
	@UniqueValue(domainClass = NovaProposta.class, fieldName = "email")
	private String email;
	@CPFOrCNPJ
	@NotBlank
	@UniqueValue(domainClass = NovaProposta.class, fieldName = "documento")
	private String documento;
	@NotBlank
	private String endereco;
	@NotNull
	@PositiveOrZero
	private double salario;

	public NovaPropostaRequest(@NotBlank String nome, @Email @NotBlank String email, @NotBlank String documento,
			@NotBlank String endereco, @NotNull @PositiveOrZero double salario) {
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public NovaProposta toModel() {
		return new NovaProposta(nome, email, documento, endereco, salario);
	}
}
