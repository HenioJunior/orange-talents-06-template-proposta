package com.zupacademy.proposta.novatransacao;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
	@Enumerated
	private NovaTransacaoStatus status;
	@Valid
	@NotNull
	@ManyToOne
	private NovaProposta novaProposta;
		
	public NovaTransacao(String nome, @NotBlank String documento, NovaTransacaoStatus status,
			@Valid @NotNull NovaProposta novaProposta) {
		this.nome = nome;
		this.documento = documento;
		this.status = status;
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
	
	public NovaTransacaoStatus getStatus() {
		return status;
	}
		
	public void setStatus(NovaTransacaoStatus status) {
		this.status = status;
	}

	public NovaProposta getNovaProposta() {
		return novaProposta;
	}

	public boolean concluidaComSucesso() {
		return this.status.equals(NovaTransacaoStatus.ELEGIVEL);
	}

	@Override
	public String toString() {
		return "NovaTransacao [nome=" + nome + ", documento=" + documento + ", status="
				+ status + ", novaProposta=" + novaProposta + "]";
	}

		
}
