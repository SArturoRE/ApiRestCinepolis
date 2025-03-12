package com.mx.ApiRestCinepolis.controller;
import com.mx.ApiRestCinepolis.model.Peliculas;
import com.mx.ApiRestCinepolis.service.PeliculasServImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "PeliculasWebService")
@CrossOrigin
public class PeliculasWebService {
	
	@Autowired
	PeliculasServImpl peliculasServImpl;
	
	//URL---> http://localhost:9000/PeliculasWebService/listar
	@GetMapping(path = "listar")
	public List<Peliculas> Listar(){
		return peliculasServImpl.Listar();
	}
	//URL---> http://localhost:9000/PeliculasWebService/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> Guardar(@RequestBody Peliculas peli) {
		boolean inserPeli = peliculasServImpl.Guardar(peli);
		if(!inserPeli) {
			return new ResponseEntity<>(peli,HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>("El nombre que quiere ingresar ya existe",HttpStatus.OK);
		}
	}
	
	//URL---> http://localhost:9000/PeliculasWebService/buscarxid
	@PostMapping(path = "buscarxid")
	public Peliculas BuscarXId(@RequestBody Peliculas pe) {
		return peliculasServImpl.BuscarXId(pe);
	}
	
	//URL---> http://localhost:9000/PeliculasWebService/editar
	@PostMapping(path="editar")
	public ResponseEntity<?> Editar(@RequestBody Peliculas peli){
		boolean pelEdit = peliculasServImpl.Editar(peli);
		
		if(pelEdit) {
			return new ResponseEntity<>(peli,HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>("La pelicula que desea editar no existe",HttpStatus.OK);
		}
	}
	//http://localhost:9000/PeliculasWebService/eliminarXId/2
	@PostMapping(path="eliminarXId/{idPeli}")
	public ResponseEntity<String> EliminarXId(@PathVariable("idPeli") Integer idPeli){
		boolean peliEncontrada = peliculasServImpl.EliminarXId(idPeli);
		
		if(peliEncontrada) {
			return new ResponseEntity<String>("El elemento se elimino correctamente",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<String>("El elemento no existe",HttpStatus.OK);
		}
	}
}
