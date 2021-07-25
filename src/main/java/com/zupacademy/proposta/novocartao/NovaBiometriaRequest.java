package com.zupacademy.proposta.novocartao;

import java.time.LocalDateTime;
import java.util.Base64;

import javax.validation.constraints.NotBlank;

public class NovaBiometriaRequest {

	@NotBlank
	private String idCartao;
	@NotBlank
	private String biometria;

	private LocalDateTime dataCriacaoPassword = LocalDateTime.now();

	public NovaBiometriaRequest(@NotBlank String idCartao, @NotBlank String biometria) {
		this.idCartao = idCartao;
		this.biometria = biometria;
	}

	public String getIdCartao() {
		return idCartao;
	}

	public String getBiometria() {
		return biometria;
	}

	public LocalDateTime getDataCriacaoPassword() {
		return dataCriacaoPassword;
	}

	public String decodeBase64(String biometria) {
		byte[] byteValueBase64Decoded = Base64.getDecoder().decode(biometria);
		return new String(byteValueBase64Decoded);
	}

	public void ToModel(NovoCartao atualizaCartao) {
		atualizaCartao.setPassword(decodeBase64(biometria));
		atualizaCartao.setDataCriacaoPassword(dataCriacaoPassword);
	}
}
