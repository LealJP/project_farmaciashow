package com.farmaciashow.farmaciashow.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmaciashow.farmaciashow.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
	public Optional<Object> findByNome(String nome);
}
