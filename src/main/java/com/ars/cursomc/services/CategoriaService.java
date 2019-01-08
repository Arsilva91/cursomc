package com.ars.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ars.cursomc.domain.Categoria;
import com.ars.cursomc.repositories.CategoriaRepository;
import com.ars.cursomc.services.exception.DataIntegrityExcepetion;
import com.ars.cursomc.services.exception.ObjectNotFoundExcepetion;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundExcepetion("Objeto não encontrado! id: " + id + 
				", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityExcepetion("Não é possível excluir uma categoria que possui produtos.");
		}		
	}
}
