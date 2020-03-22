package com.angularapiangularapi.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.angularapiangularapi.model.Filme;
import com.angularapiangularapi.service.FilmeService;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/filmes")
public class FilmeResource {

	@Autowired
	private FilmeService filmeService;
	
	@GetMapping
	public ResponseEntity<Object> listar() {

		ResponseEntity<Object> retorno = null;

		try {
			List<Filme> lista = filmeService.Todos();

			retorno = ResponseEntity.ok(lista);
		} catch (Exception e) {
			e.printStackTrace();
			retorno = new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return retorno;
	}
	@CrossOrigin(origins="*",allowedHeaders="*")
	@PutMapping
	public ResponseEntity<Object> atualizar(@RequestBody Filme filme) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Object> retorno = null;

		try {

			if (filme == null || filme.getId() == null) {
				response.put("mensagem", "Filme ou Código de Filme não pode ser nulo!");
				retorno = new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
				return retorno;
			}

			filme = filmeService.salvar(filme);

			retorno = new ResponseEntity<Object>(filme, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();

			retorno = new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return retorno;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> listarPorId(@PathVariable("id") Long id) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Object> retorno = null;

		try {

			if (id == null) {
				response.put("mensagem", "Id do filme não pode ser nulo!");
				retorno = new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
				return retorno;
			}

			Optional<Filme> filme = filmeService.buscarFilmePeloCodigo(id);

			retorno = ResponseEntity.ok(filme);

		} catch (Exception e) {
			e.printStackTrace();

			retorno = new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return retorno;
	}
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Filme cadastrar(@RequestBody Filme filme){
		return filmeService.salvar(filme);
	}
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(@PathVariable("id") Long id) {
		filmeService.removerFilme(id);
	}
}
