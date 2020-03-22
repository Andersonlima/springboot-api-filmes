package com.angularapiangularapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angularapiangularapi.model.Paciente;

public interface PacienteRepository  extends JpaRepository<Paciente, Long>{

}
