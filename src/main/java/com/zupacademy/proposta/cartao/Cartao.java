package com.zupacademy.proposta.cartao;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zupacademy.proposta.cartao.aviso.AvisoViagem;
import com.zupacademy.proposta.cartao.bloqueio.Bloqueio;
import com.zupacademy.proposta.cartao.bloqueio.EstadoCartao;
import com.zupacademy.proposta.cartao.carteiradigital.CarteiraDigital;
import com.zupacademy.proposta.cartao.carteiradigital.ProvedorCarteira;
import com.zupacademy.proposta.novaproposta.NovaProposta;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Entity
public class Cartao {

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

	@OneToMany(mappedBy = "cartao",cascade = CascadeType.MERGE)
	private Set<Bloqueio> bloqueios = new HashSet<Bloqueio>();

	@OneToMany(mappedBy = "cartao",cascade = CascadeType.MERGE)
	private Set<CarteiraDigital> carteiras;

	@Enumerated
	private EstadoCartao estadoCartao = EstadoCartao.DESBLOQUEADO;

	@ManyToOne
	private NovaProposta novaProposta;

	public Cartao(@NotBlank String idCartao, @NotBlank String emitidoEm, @NotNull int limite,
				  NovaProposta novaProposta) {
		this.idCartao = idCartao;
		this.emitidoEm = emitidoEm;
		this.limite = limite;
		this.novaProposta = novaProposta;
	}

	public Cartao() {
	}

	public Long getId() {
		return id;
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

	public void verificaBloqueio(String ip, String userAgent) {

		if(ip!=null && ip.length()>0 &&
				userAgent!=null && userAgent.length()>0 &&
				bloqueado()) {

			this.estadoCartao = EstadoCartao.BLOQUEADO;
			this.bloqueios.add(new Bloqueio(this, ip, userAgent));

		}else if(!bloqueado())
			throw new ResponseStatusException(
					HttpStatus.UNPROCESSABLE_ENTITY,
					"Cartão já se encontra bloqueado.");

		else
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,
					"Dados incorretos");
	}

	private boolean bloqueado() {
		return this.bloqueios.isEmpty();
	}

	public void associarCarteira(CarteiraDigital carteira) {
		this.carteiras.add(carteira);
	}

	public boolean jaEAssociadoACarteira(ProvedorCarteira provedor) {
		return this.carteiras.stream()
				.anyMatch(carteira -> carteira.getProvedor().equals(provedor));
	}

}
