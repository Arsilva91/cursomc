package com.ars.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ars.cursomc.domain.Categoria;
import com.ars.cursomc.domain.Produto;
import com.ars.cursomc.repositories.CategoriaRepository;
import com.ars.cursomc.repositories.ProdutoRepository;
import com.ars.cursomc.services.exception.ObjectNotFoundExcepetion;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundExcepetion("Objeto não encontrado! id: " + id + 
				", Tipo: " +Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids); 
		// return repo.search(nome, categorias, pageRequest)		
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}

}
