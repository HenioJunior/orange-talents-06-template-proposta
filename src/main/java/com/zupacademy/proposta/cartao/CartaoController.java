package com.zupacademy.proposta.cartao;

import com.zupacademy.proposta.cartao.aviso.AvisoViagem;
import com.zupacademy.proposta.cartao.aviso.AvisoViagemRequest;
import com.zupacademy.proposta.cartao.bloqueio.DadosCliente;
import com.zupacademy.proposta.cartao.carteiradigital.CarteiraDigital;
import com.zupacademy.proposta.cartao.carteiradigital.CarteiraDigitalRepository;
import com.zupacademy.proposta.cartao.carteiradigital.CarteiraDigitalRequest;
import com.zupacademy.proposta.cartao.carteiradigital.CarteiraDigitalResponse;
import com.zupacademy.proposta.exceptions.DatabaseException;
import com.zupacademy.proposta.exceptions.MethodNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/cartoes")
public class CartaoController {

	@Autowired
	private CartaoRepository repository;

	@Autowired
	private CarteiraDigitalRepository carteiraDigitalRepository;

	@PersistenceContext
	EntityManager manager;

	@Autowired
	DadosCliente dadosCliente;

	@Autowired
	CartaoFeign feign;

	@PostMapping
	@Transactional
	public ResponseEntity<NovaBiometriaRequest> criarBiometria(@RequestBody @Valid NovaBiometriaRequest request) {
		Cartao atualizaCartao = repository.findByIdCartao(request.getIdCartao()).orElseThrow(() -> new DatabaseException("id não localizado."));
		request.ToModel(atualizaCartao);

		repository.save(atualizaCartao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(atualizaCartao.getId()).toUri();
		return ResponseEntity.created(uri).body(request);

	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/bloqueios")
	@Transactional
	public ResponseEntity<?> bloqueiaCartao(@PathVariable String id, HttpServletRequest request) {

		Cartao cartao = buscarCartao(id);
		cartao.verificaBloqueio(dadosCliente.buscaIpClient(request),
				request.getHeader("USER-AGENT"));

		feign.bloqueiaCartao(cartao.getIdCartao(),
				Map.of("sistemaResponsavel", "desafioproposta"));

		repository.save(cartao);

		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/avisos")
	@Transactional
	public ResponseEntity<AvisoViagemRequest> aviso(@PathVariable(required = true, name = "id") String id,
													HttpServletRequest servletRequest,
													@RequestBody @Valid AvisoViagemRequest request) {

		Cartao cartao = buscarCartao(id);

		AvisoViagem avisoViagem = request.toModel(cartao,
				dadosCliente.buscaIpClient(servletRequest),
				dadosCliente.buscaUserAgent(servletRequest));

		feign.avisoViagem(cartao.getIdCartao(), request);

		manager.persist(avisoViagem);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cartao.getId()).toUri();
		return ResponseEntity.created(uri).body(request);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}/carteiras")
	@Transactional
	public ResponseEntity<CarteiraDigitalRequest> carteiraDigital(@PathVariable(required = true, name = "id") String id,
																  @RequestBody @Valid CarteiraDigitalRequest request){

		Cartao cartao = buscarCartao(id);
		boolean cartaoJaAssociadoACarteira = cartao.jaEAssociadoACarteira(request.getEmissor());
		if (cartaoJaAssociadoACarteira) {
			throw new MethodNotValidException(
					String.format("O cartao %d já está vinculado a uma carteira com provedor %s",
							cartao.getId(),
							request.getEmissor()));
		}
		try {
			CarteiraDigitalResponse retornoCarteiraDigital = feign.solicitarCarteira(cartao.getIdCartao(), request);
			CarteiraDigital carteiraDigital = retornoCarteiraDigital.toModel(cartao, request.getEmissor());
			carteiraDigitalRepository.save(carteiraDigital);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(cartao.getId()).toUri();
			return ResponseEntity.created(uri).body(request);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
	}

	public Cartao buscarCartao(String idCartao) {
		return repository.findByIdCartao(idCartao).orElseThrow(() -> new DatabaseException("id não localizado."));
	}
}
