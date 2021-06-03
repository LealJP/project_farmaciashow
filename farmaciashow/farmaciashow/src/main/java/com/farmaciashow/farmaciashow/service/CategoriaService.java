package com.farmaciashow.farmaciashow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.farmaciashow.farmaciashow.model.Categoria;
import com.farmaciashow.farmaciashow.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public ResponseEntity<Optional<Categoria>> getByIdCategoria(Long idCategoria){
		Optional<Categoria> optId = repository.findById(idCategoria);
		if(optId.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(201).body(optId);
		}
		
	}

	
	public Optional<Object> cadastrarNovaCategoria(Categoria novaCategoria) {
		//verificando se esta tentando cadastrar nome de categoria já existente 
		Optional<Object> verificaCategoria = repository.findByNome(novaCategoria.getNome());
		if(verificaCategoria.isPresent()) {
			//return Optional.empty();
			return null;
		} else {
			return Optional.ofNullable(repository.save(novaCategoria));
		}
	}
	
}

