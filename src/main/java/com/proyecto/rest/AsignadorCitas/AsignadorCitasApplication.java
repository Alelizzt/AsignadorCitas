package com.proyecto.rest.AsignadorCitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.proyecto.rest.controller","com.proyecto.rest.model"})
public class AsignadorCitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsignadorCitasApplication.class, args);
	}
}
