package com.zupacademy.proposta.novaproposta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
			
	public NovaProposta() {
		
	}

	public NovaProposta(@NotBlank String nome, @Email @NotBlank String email, @NotBlank String documento,
			@NotBlank String endereco, @NotNull @PositiveOrZero Double salario) {
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
		this.salario = salario;
		System.out.println(id);
	}

	public Long getId() {
		return id;
	}
}
