package com.ars.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ars.cursomc.domain.Pedido;
import com.ars.cursomc.repositories.PedidoRepository;
import com.ars.cursomc.services.exception.ObjectNotFoundExcepetion;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);		
		return obj.orElseThrow(() -> new ObjectNotFoundExcepetion("Objeto não encontrado! id: " + id + 
				", Tipo: " + Pedido.class.getName()));
	}

}
