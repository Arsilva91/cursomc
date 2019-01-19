package com.ars.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.ars.cursomc.domain.Cliente;
import com.ars.cursomc.domain.enums.TipoCliente;
import com.ars.cursomc.dto.ClienteDTO;
import com.ars.cursomc.dto.ClienteNewDTO;
import com.ars.cursomc.repositories.ClienteRepository;
import com.ars.cursomc.resources.exception.FieldMessage;
import com.ars.cursomc.services.validation.utils.BR;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Override
	public void initialize(ClienteUpdate ann) {}
	
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);		
		Integer uriId = Integer.parseInt(map.get("id"));		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente aux = clienteRepo.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}