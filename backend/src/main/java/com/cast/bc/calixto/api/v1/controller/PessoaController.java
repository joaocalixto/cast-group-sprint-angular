package com.cast.bc.calixto.api.v1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cast.bc.calixto.api.v1.Constantes;
import com.cast.bc.calixto.api.v1.ErroMessage;
import com.cast.bc.calixto.data.entity.Pessoa;
import com.cast.bc.calixto.service.StorePessoaService;
import com.cast.bc.calixto.service.expection.PessoaNotFountException;

@CrossOrigin(maxAge = 3600)
@RestController
@Validated
@RequestMapping(path = "/api/v1/rest/",
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PessoaController {

	private StorePessoaService storePessoaService;

	@Autowired
	public PessoaController(StorePessoaService storePessoaService) {
		this.storePessoaService = storePessoaService;
	}

	@GetMapping(path = "/")
	public String root() {
		return "Service Ruinning";
	}

	@GetMapping(path = "/pessoas")
	public List<Pessoa> listPessoas() {
		return storePessoaService.listPessoas();
	}
	
	@GetMapping(path = "/pessoa/{id}")
	public ResponseEntity<?> getPessoa(@PathVariable Long id) {
		Pessoa pessoa = null;
		ResponseEntity<?> retorno = null;
		try {
			pessoa = storePessoaService.getPessoa(id);
		} catch (PessoaNotFountException e) {
			new ResponseEntity<>(ErroMessage.builder().error(Constantes.MSG_PESSOA_NAO_ENCONTRADA).build(), HttpStatus.OK);
		}
		retorno = new ResponseEntity<>(pessoa, HttpStatus.OK);
		return retorno;
	}
	

	@RequestMapping(method=RequestMethod.POST, value={"/pessoa/save", "/pessoa/save/{id}"})
	public ResponseEntity<?> saveOrUpdatePessoa(@PathVariable Optional<Long> id, @RequestBody Pessoa pessoa) {
		
		if(id.isPresent()) {
			pessoa.setId(id.get());
		}
		Pessoa savedPessoa = storePessoaService.savePessoa(pessoa);
		return new ResponseEntity<>(savedPessoa, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/pessoa/remove/{id}")
	public ResponseEntity<?> deletePessoa(@PathVariable long id) {
		ResponseEntity<?> retorno = null;
		try {
			storePessoaService.deletePessoa(id);
		} catch (PessoaNotFountException e) {
			retorno = new ResponseEntity<>(ErroMessage.builder().error(Constantes.MSG_PESSOA_NAO_ENCONTRADA).build(), HttpStatus.OK);
		}
		
		retorno = new ResponseEntity<>(HttpStatus.OK);
		return retorno;
	}
}
