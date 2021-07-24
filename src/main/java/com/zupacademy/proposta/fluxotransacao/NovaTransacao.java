package com.zupacademy.proposta.fluxotransacao;

import java.util.Objects;

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
	@NotNull
	private long idProposta;
	@Enumerated
	private StatusTransacao status;
	@Valid
	@NotNull
	@ManyToOne
	private NovaProposta novaProposta;
		
	public NovaTransacao(String nome, @NotBlank String documento, @NotNull long idProposta, StatusTransacao status,
			@Valid @NotNull NovaProposta novaProposta) {
		this.nome = nome;
		this.documento = documento;
		this.idProposta = idProposta;
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

	public long getIdProposta() {
		return idProposta;
	}

	public StatusTransacao getStatus() {
		return status;
	}
		
	public void setStatus(StatusTransacao status) {
		this.status = status;
	}

	public NovaProposta getNovaProposta() {
		return novaProposta;
	}

	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.ELEGIVEL);
	}

	@Override
	public String toString() {
		return "NovaTransacao [nome=" + nome + ", documento=" + documento + ", idProposta=" + idProposta + ", status="
				+ status + ", novaProposta=" + novaProposta + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(documento, idProposta, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NovaTransacao other = (NovaTransacao) obj;
		return Objects.equals(documento, other.documento) && idProposta == other.idProposta
				&& Objects.equals(nome, other.nome);
	}
	
	
}
