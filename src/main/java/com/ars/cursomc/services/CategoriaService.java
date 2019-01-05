package com.ars.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ars.cursomc.domain.Categoria;
import com.ars.cursomc.repositories.CategoriaRepository;
import com.ars.cursomc.services.exception.ObjectNotFoundExcepetion;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundExcepetion("Objeto não encontrado! id: " + id + 
				", Tipo: " + Categoria.class.getName()));
	}

}
