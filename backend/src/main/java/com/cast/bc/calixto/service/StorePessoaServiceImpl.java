package com.cast.bc.calixto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cast.bc.calixto.data.entity.Pessoa;
import com.cast.bc.calixto.data.repository.PessoaRepository;
import com.cast.bc.calixto.service.expection.PessoaNotFountException;

@Service
public class StorePessoaServiceImpl implements StorePessoaService{
	
	private PessoaRepository pessoaRepository;
	
	@Autowired
	public StorePessoaServiceImpl(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}


	@Override
	public Pessoa savePessoa(Pessoa pessoa) {
		return pessoaRepository.saveAndFlush(pessoa);
	}

	@Override
	public Pessoa getPessoa(Long id) throws PessoaNotFountException {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if(!pessoa.isPresent()) {
			throw new PessoaNotFountException();
		}
		return pessoa.get();
	}

	@Override
	public void deletePessoa(Long id) throws PessoaNotFountException {
		try {
			pessoaRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new PessoaNotFountException();
		}
	}

	@Override
	public Pessoa updatePessoa(Pessoa pesspa) {
		return pessoaRepository.saveAndFlush(pesspa);
	}


	@Override
	public List<Pessoa> listPessoas() {
		return pessoaRepository.findAll();
	}

}
