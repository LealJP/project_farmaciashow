package com.farmaciashow.farmaciashow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmaciashow.farmaciashow.model.Categoria;
import com.farmaciashow.farmaciashow.repository.CategoriaRepository;
import com.farmaciashow.farmaciashow.service.CategoriaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping("/todas")
	public ResponseEntity<List<Categoria>> buscarTodas(){
		List<Categoria> listaCategorias = repository.findAll();
		if(listaCategorias.isEmpty()){
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(201).body(listaCategorias);
		}
	}
	
	@GetMapping("/id/{idCategoria}")
	public ResponseEntity<Optional<Categoria>> getById(@PathVariable Long idCategoria){
		return service.getByIdCategoria(idCategoria);
	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvarNovaCategoria(@RequestBody Categoria novaCategoria){
		return service.cadastrarNovaCategoria(novaCategoria)
				.map(verificaCategoria -> ResponseEntity.status(201).body(verificaCategoria))
				.orElse(ResponseEntity.status(204).build());
	}
}
