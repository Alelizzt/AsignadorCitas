package com.proyecto.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/empledo/{id}")
	public Empleado getEmpleado(@PathVariable Long id){
		if(id>0 && id<=repo.size()){
			return repo.get((int)(id-1));
		}
		return null;
	}
	
	@GetMapping("/empleado/{id}/cita")
	public List<Cita> getCitasEmpleado(@PathVariable Long id){
		if(id>0 && id<=repo.size()){
			return repo.get((int)(id-1)).getCitas();
		}
		return null;
	}
	
	@GetMapping("/empleado/{idE}/cita/{idC}")
	public Cita getCitaEmpleado(@PathVariable Long idE, @PathVariable Long idC){
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
		return result;
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
