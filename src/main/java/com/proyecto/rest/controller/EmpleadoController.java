package com.proyecto.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.proyecto.rest.model.Cita;
import com.proyecto.rest.model.Empleado;

@RestController

@RequestMapping("/api")
public class EmpleadoController {
	
	List<Empleado> repo;
	
	@GetMapping("/empleado")
	public List<Empleado> list(){
		return repo;
	}
	
	/**
	 * 
	 * @param id Identificacion del empleado
	 * @param response	En caso de no existencia del empleado utilizamos la cabecera de http
	 * @return Empleado con la identificacion proporcionada
	 */
	@GetMapping("/empleado/{id}")
	public Empleado getEmpleado(@PathVariable Long id, HttpServletResponse response){
		if(id>0 && id<=repo.size()){
			return repo.get((int)(id-1));
		}
		else{
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("/empleado/{idEm}/cita")
	public List<Cita> getCitasEmpleado(@PathVariable Long idEm){
		if(idEm>0 && idEm<=repo.size()){
			return repo.get((int)(idEm-1)).getCitas();
		}
		return null;
	}
	
	/**
	 * 
	 * @param idE identificacion del empleado
	 * @param idC identificacion de la Cita
	 * @param request manejo de excepcion por cabecera
	 * @return Objeto de tipo Cita
	 * @throws NoHandlerFoundException Excepcion que retorna 404 
	 */
	@GetMapping("/empleado/{idE}/cita/{idC}")
	public Cita getCitaEmpleado(@PathVariable Long idE, @PathVariable Long idC, HttpServletRequest request) throws NoHandlerFoundException{
		Cita result = null;
		if(idE>0 && idE <= repo.size()){
			List<Cita> citas = repo.get((int)(idE-1)).getCitas();
			if(citas != null){
				for(Cita c: citas){
					if(c.getId() == idC){
						result =c;
					}
				}
			}
		}
		if(result!=null)
			return result;
		else
			throw new NoHandlerFoundException("GET", request.getRequestURL().toString(), null);
	}
	
	
	@PostConstruct
	private void init(){
		repo = new ArrayList<Empleado>();
		
		repo.add(new Empleado(1L, "Pepe","Gotera",new Date(),null));
		repo.get(0).getCitas().add(new Cita(1L, "Reunion de trabajo", new Date()));
		repo.get(0).getCitas().add(new Cita(2L, "Visita a un cliente", new Date()));
		
		repo.add(new Empleado(2L, "Otilio", "Garcia", new Date(), null));
		repo.get(1).getCitas().add(new Cita(3L, "Visita a un proveedor", new Date()));
		
		repo.add(new Empleado(3L, "Pepe","Gotera",new Date(),null));
		repo.add(new Empleado(4L, "Pepe","Gotera",new Date(),null));
		repo.add(new Empleado(5L, "Pepe","Gotera",new Date(),null));
			
	}
}
