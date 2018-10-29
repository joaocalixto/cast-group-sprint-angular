package com.cast.bc.calixto.service;

import java.util.List;

import com.cast.bc.calixto.data.entity.Pessoa;
import com.cast.bc.calixto.service.expection.PessoaNotFountException;

public interface StorePessoaService {
	
	Pessoa savePessoa(Pessoa user);
	
	Pessoa getPessoa(Long id) throws PessoaNotFountException;
	
	List<Pessoa> listPessoas();
	
	void deletePessoa(Long id) throws PessoaNotFountException;
	
	Pessoa updatePessoa(Pessoa pesspa);

}
