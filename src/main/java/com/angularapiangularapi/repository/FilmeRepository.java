package com.angularapiangularapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angularapiangularapi.model.Filme;


public interface FilmeRepository  extends JpaRepository<Filme, Long>{

}
