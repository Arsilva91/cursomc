package com.ars.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ars.cursomc.domain.Cliente;
import com.ars.cursomc.repositories.ClienteRepository;
import com.ars.cursomc.services.exception.ObjectNotFoundExcepetion;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundExcepetion("Objeto não encontrado! id: " + id + 
				", Tipo: " + Cliente.class.getName()));
	}

}
