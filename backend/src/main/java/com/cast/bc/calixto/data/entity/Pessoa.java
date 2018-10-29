package com.cast.bc.calixto.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Pessoa{
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	private String cpf;
	private String tipoLogradouro;
	private String logradouro;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String telefoneCelular;
	private String telefoneFixo;

}
