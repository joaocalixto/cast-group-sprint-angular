package com.cast.bc.calixto.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cast.bc.calixto.DefaultEntityUtils;
import com.cast.bc.calixto.data.entity.Pessoa;
import com.cast.bc.calixto.data.repository.PessoaRepository;

public class StoreRateServiceImplTest {

	@Mock
	PessoaRepository pessoaRepository;

	StorePessoaService pessoaService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		this.pessoaService = new StorePessoaServiceImpl(this.pessoaRepository);
	}

	

	@Test
	public void saveRate() {
		//given
		Pessoa pessoa = DefaultEntityUtils.samplePerson();

		//when
		pessoaService.savePessoa(pessoa);
		//then
		verify(pessoaRepository, Mockito.times(1)).saveAndFlush(pessoa);
		verifyNoMoreInteractions(pessoaRepository);
	}
}