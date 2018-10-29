package com.cast.bc.calixto.api.v1.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cast.bc.calixto.DefaultEntityUtils;
import com.cast.bc.calixto.api.v1.Constantes;
import com.cast.bc.calixto.api.v1.ErroMessage;
import com.cast.bc.calixto.data.entity.Pessoa;
import com.cast.bc.calixto.service.StorePessoaService;
import com.cast.bc.calixto.service.expection.PessoaNotFountException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PessoaControllerTest {

	
	public static final String URL_TEMPLATE = "/api/v1/rest";
	
	public static final String URL_TEMPLATE_SAVE = URL_TEMPLATE + "/pessoa/save";
	public static final String URL_TEMPLATE_GET = URL_TEMPLATE + "/pessoa/{id}";
	public static final String URL_TEMPLATE_LIST = URL_TEMPLATE + "/pessoas";

	private PessoaController pessoaController;

	@Mock
	private StorePessoaService storePessoaService;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.pessoaController = new PessoaController(this.storePessoaService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.pessoaController).build();
	}
	
	@Test
	public void deletePessoaNotFound() throws Exception{
		//given
		final Pessoa pessoaRequest = DefaultEntityUtils.samplePerson();
		final String requestSavePessoaJson = new ObjectMapper().writeValueAsString(pessoaRequest);
		
		final Pessoa pessoaService = DefaultEntityUtils.samplePersonWithId();
		final String serivceSavePessoaJson = new ObjectMapper().writeValueAsString(pessoaService);

		when(this.storePessoaService.savePessoa(pessoaRequest)).thenReturn(pessoaService);

		//when
		final ResultActions perform = this.mockMvc.perform(post(URL_TEMPLATE_SAVE)
												.contentType(MediaType.APPLICATION_JSON)
												.content(requestSavePessoaJson));

		//then
		perform.andExpect(status().isOk());
		perform.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		perform.andExpect(content().json(serivceSavePessoaJson));

		verify(storePessoaService, Mockito.times(1)).savePessoa(pessoaRequest);
		verifyNoMoreInteractions(storePessoaService);
	}
	
	@Test
	public void getPessoaNaoEncontrada() throws Exception{
		//given
		Long idToGet = 1l;

		when(this.storePessoaService.getPessoa(1l)).thenThrow(new PessoaNotFountException());

		//when
		final ResultActions perform = this.mockMvc.perform(get(URL_TEMPLATE_GET.replace("{id}",idToGet.toString())));

		//then
		perform.andExpect(status().isOk());
		perform.andExpect(content().string(""));

		verify(storePessoaService, Mockito.times(1)).getPessoa(any());
		verifyNoMoreInteractions(storePessoaService);
	}
	
	@Test
	public void savePessoaOK() throws Exception{
		//given
		final Pessoa pessoaRequest = DefaultEntityUtils.samplePerson();
		final String requestSavePessoaJson = new ObjectMapper().writeValueAsString(pessoaRequest);
		
		final Pessoa pessoaService = DefaultEntityUtils.samplePersonWithId();
		final String serivceSavePessoaJson = new ObjectMapper().writeValueAsString(pessoaService);

		when(this.storePessoaService.savePessoa(pessoaRequest)).thenReturn(pessoaService);

		//when
		final ResultActions perform = this.mockMvc.perform(post(URL_TEMPLATE_SAVE)
												.contentType(MediaType.APPLICATION_JSON)
												.content(requestSavePessoaJson));

		//then
		perform.andExpect(status().isOk());
		perform.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		perform.andExpect(content().json(serivceSavePessoaJson));

		verify(storePessoaService, Mockito.times(1)).savePessoa(pessoaRequest);
		verifyNoMoreInteractions(storePessoaService);
	}


	@Test
	public void listPessoasEmpty() throws Exception {
		//given
		when(this.storePessoaService.listPessoas()).thenReturn(new ArrayList<Pessoa>());

		//when
		final ResultActions perform = mockMvc.perform(get(URL_TEMPLATE_LIST));

		//then
		perform.andExpect(status().isOk());
		perform.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		perform.andExpect(content().json("[]"));

		verify(storePessoaService, Mockito.times(1)).listPessoas();
		verifyNoMoreInteractions(storePessoaService);
	}

}