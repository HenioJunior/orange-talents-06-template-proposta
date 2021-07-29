package com.zupacademy.proposta.novatransacao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zupacademy.proposta.novaproposta.NovaProposta;
import com.zupacademy.proposta.validacao.CPFOrCNPJ;

@Entity
public class NovaTransacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@CPFOrCNPJ
	@NotBlank
	private String documento;
	@Valid
	@NotNull
	@ManyToOne
	private NovaProposta novaProposta;
		
	public NovaTransacao(String nome, @NotBlank String documento,
			@Valid @NotNull NovaProposta novaProposta) {
		this.nome = nome;
		this.documento = documento;
		this.novaProposta = novaProposta;
	}

	public NovaTransacao() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}
		
	public NovaProposta getNovaProposta() {
		return novaProposta;
	}

	public boolean concluidaComSucesso() {
		return true;
	}
			
}
