package com.angularapiangularapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.angularapiangularapi.model.Paciente;
import com.angularapiangularapi.repository.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public List<Paciente> Todos(){
		return pacienteRepository.findAll();
	}
	
	public Paciente salvar(Paciente paciente) {
		return pacienteRepository.save(paciente);
	}

	public void removerPaciente(Long codigo) {
		Paciente paciente = pacienteRepository.getOne(codigo);
		if(paciente!=null) {
			pacienteRepository.deleteById(codigo);
		}
	}

	public Optional<Paciente> buscarPacientePeloCodigo(Long codigo) {
		Optional<Paciente> pacienteSalva = pacienteRepository.findById(codigo);
		if (pacienteSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pacienteSalva;
	}
}
