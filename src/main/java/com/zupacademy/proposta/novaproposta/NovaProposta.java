package com.zupacademy.proposta.novaproposta;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import com.zupacademy.proposta.analiseproposta.SolicitaAnaliseRequest;
import com.zupacademy.proposta.novatransacao.NovaTransacao;
import com.zupacademy.proposta.cartao.Cartao;

@Entity
public class NovaProposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String documento;
	@NotBlank
	private String endereco;
	@NotNull
	@PositiveOrZero
	private Double salario;
	
	@Enumerated
	private NovaPropostaStatus status;

	@OneToMany(mappedBy = "novaProposta", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Set<NovaTransacao> transacoes = new HashSet<>();

	@OneToMany(mappedBy = "novaProposta", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Set<Cartao> cartoes;
	
	public NovaProposta() {

	}

	public NovaProposta(@NotBlank String nome, @Email @NotBlank String email, @NotBlank String documento,
			@NotBlank String endereco, @NotNull @PositiveOrZero Double salario) {
		super();
		this.nome = nome;
		this.email = email;
		this.documento = new BCryptPasswordEncoder().encode(documento);
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}
				
	public NovaPropostaStatus getStatus() {
		return status;
	}

	public void setStatus(NovaPropostaStatus status) {
		this.status = status;
	}

	public Set<NovaTransacao> getTransacoes() {
		return transacoes;
	}

	
	@Override
	public String toString() {
		return "NovaProposta [id=" + id + ", nome=" + nome + ", email=" + email + ", documento=" + documento
				+ ", endereco=" + endereco + ", salario=" + salario + " ]";
	}

	public void adicionaTransacao(SolicitaAnaliseRequest request) {
		NovaTransacao novaTransacao = request.toTransacao(this);
		Assert.isTrue(!this.transacoes.contains(novaTransacao),
				"Já existe uma transaco igual a essa processada " + novaTransacao);

		Assert.isTrue(transacoesAprovadasComSucesso().isEmpty(), "Essa proposta já foi aprovada");

		this.transacoes.add(novaTransacao);

	}

	private Set<NovaTransacao> transacoesAprovadasComSucesso() {
		Set<NovaTransacao> transacoesAprovadasComSucesso = this.transacoes.stream()
				.filter(NovaTransacao::concluidaComSucesso).collect(Collectors.toSet());
		Assert.isTrue(transacoesAprovadasComSucesso.size() <= 1,
				"Erro: Tem mais de uma transação aprovada para a proposta");
		return transacoesAprovadasComSucesso;
	}

	public boolean processadaComSucesso() {
		return !transacoesAprovadasComSucesso().isEmpty();
	}

}
