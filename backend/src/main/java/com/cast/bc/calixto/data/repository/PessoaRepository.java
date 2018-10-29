package com.cast.bc.calixto.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cast.bc.calixto.data.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
