package com.cast.bc.calixto;

import java.util.Arrays;
import java.util.List;

import com.cast.bc.calixto.data.entity.Pessoa;


public class DefaultEntityUtils {

	public static Pessoa samplePerson() {
		
		Pessoa pessoa = Pessoa.builder()
			.nome("Maria da Silva")
			.bairro("Nova Cidade")
			.cidade("Itaboraí")
			.estado("rj")
			.cpf("24732290400")
			.logradouro("Diácono Luiz Mattos Pacheco")
			.tipoLogradouro("rua")
			.telefoneCelular("(21) 99978-5457")
			.telefoneFixo("(21) 99978-5457")
			.build();
		
		return pessoa;
	}
	
	public static Pessoa samplePersonWithId() {
		
		Pessoa pessoa = Pessoa.builder()
			.id(1l)
			.nome("Maria da Silva")
			.bairro("Nova Cidade")
			.cidade("Itaboraí")
			.estado("rj")
			.cpf("24732290400")
			.logradouro("Diácono Luiz Mattos Pacheco")
			.tipoLogradouro("rua")
			.telefoneCelular("(21) 99978-5457")
			.telefoneFixo("(21) 99978-5457")
			.build();
		
		return pessoa;
	}
	
	public static List<Pessoa> samplePessoaList() {
		Pessoa maria = Pessoa.builder()
				.id(1l)
				.nome("Maria da Silva")
				.bairro("Nova Cidade")
				.cidade("Itaboraí")
				.estado("rj")
				.cpf("24732290400")
				.logradouro("Diácono Luiz Mattos Pacheco")
				.tipoLogradouro("rua")
				.telefoneCelular("(21) 99978-5457")
				.telefoneFixo("(21) 99978-5457")
				.build();

		Pessoa ryan = Pessoa.builder()
				.id(2l)
				.nome("Ryan Severino dos Santos")
				.bairro("Barra do Itaípe")
				.cidade("Ilhéus")
				.estado("ba")
				.cpf("29605840316")
				.logradouro("1ª Travessa da Jasiel Martins")
				.tipoLogradouro("rua")
				.telefoneCelular("(73) 99260-7627")
				.telefoneFixo("(73) 2663-6155")
				.build();


		return Arrays.asList(maria, ryan);
	}
}
