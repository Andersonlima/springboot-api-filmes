package com.angularapiangularapi.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.angularapiangularapi.model.Paciente;
import com.angularapiangularapi.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteResource {
	
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping
	public ResponseEntity<Object> listar() {

		ResponseEntity<Object> retorno = null;

		try {
			List<Paciente> lista = pacienteService.Todos();

			retorno = ResponseEntity.ok(lista);
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return retorno;
	}
	
	@PutMapping
	public ResponseEntity<Object> atualizar(@RequestBody Paciente paciente) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Object> retorno = null;

		try {

			if (paciente == null || paciente.getCodigo() == null) {
				response.put("mensagem", "Paciente ou Código da Pciente não pode ser nulo!");
				retorno = new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
				return retorno;
			}

			paciente = pacienteService.salvar(paciente);

			retorno = new ResponseEntity<Object>(paciente, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();

			retorno = new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return retorno;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> listarPorId(@PathVariable("id") Long codigo) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Object> retorno = null;

		try {

			if (codigo == null) {
				response.put("mensagem", "Id do paciente não pode ser nulo!");
				retorno = new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
				return retorno;
			}

			Optional<Paciente> paciente = pacienteService.buscarPacientePeloCodigo(codigo);

			retorno = ResponseEntity.ok(paciente);

		} catch (Exception e) {
			e.printStackTrace();

			retorno = new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return retorno;
	}
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Paciente cadastrar(@RequestBody Paciente paciente){
		return pacienteService.salvar(paciente);
	}
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{codigo}")
	public void remover(@PathVariable("codigo") Long codigo) {
		pacienteService.removerPaciente(codigo);
	}

}
