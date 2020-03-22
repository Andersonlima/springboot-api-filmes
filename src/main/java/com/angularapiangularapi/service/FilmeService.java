package com.angularapiangularapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.angularapiangularapi.model.Filme;
import com.angularapiangularapi.repository.FilmeRepository;

@Service
public class FilmeService {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	public List<Filme> Todos(){
		return filmeRepository.findAll();
	}
	
	public Filme salvar(Filme filme) {
		return filmeRepository.save(filme);
	}

	public void removerFilme(Long id) {
		Filme filme = filmeRepository.getOne(id);
		if(filme!=null) {
			filmeRepository.deleteById(id);
		}
	}

	public Optional<Filme> buscarFilmePeloCodigo(Long id) {
		Optional<Filme> filmeSalva = filmeRepository.findById(id);
		if (filmeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return filmeSalva;
	}

}
