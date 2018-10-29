package com.cast.bc.calixto.data.repository;

import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.cast.bc.calixto.DefaultEntityUtils;
import com.cast.bc.calixto.data.entity.Pessoa;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaRepositoryTest {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Test
	@DirtiesContext
	public void deltePessoaCadastrada() {
		//given
		Pessoa samplePerson = DefaultEntityUtils.samplePerson();

		Pessoa savedPerson = pessoaRepository.saveAndFlush(samplePerson);

		//when
		pessoaRepository.deleteById(savedPerson.getId());
		Optional<Pessoa> findById = pessoaRepository.findById(samplePerson.getId());

		//then
		assertFalse(findById.isPresent());
	}

}