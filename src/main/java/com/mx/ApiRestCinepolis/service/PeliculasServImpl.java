package com.mx.ApiRestCinepolis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestCinepolis.dao.PeliculasDao;
import com.mx.ApiRestCinepolis.model.Peliculas;
@Service
public class PeliculasServImpl {
	@Autowired
	PeliculasDao peliculasDao;
	
	@Transactional(readOnly = true)
	public List<Peliculas> Listar(){
		return peliculasDao.findAll();
	}
	
	@Transactional
	public boolean Guardar(Peliculas peli) {
		boolean bandera = false;
		
		for(Peliculas pe: peliculasDao.findAll()) {
			if(pe.getNombre().equalsIgnoreCase(peli.getNombre())) {
				bandera = true;
			}
		}
		
		if(!bandera) {
			peliculasDao.save(peli);
		}
		return bandera;
		
	}
	
	@Transactional
	public Peliculas BuscarXId(Peliculas peli) {
		
		return peliculasDao.findById(peli.getIdPeli()).orElse(null);
	}
	
	@Transactional
	public boolean Editar(Peliculas peli) {
		boolean edit = false;
		for(Peliculas p: this.Listar()) {
			if(p.getIdPeli().equals(peli.getIdPeli())) {
				edit = true;
				peliculasDao.save(peli);
				break;
			}
		}
		
		return edit;
	}
	@Transactional
	public boolean EliminarXId(Integer idPeli) {
		Peliculas pelEncontrada = peliculasDao.findById(idPeli).orElse(null);
		boolean ban = false;
		if(pelEncontrada != null) {
			peliculasDao.deleteById(idPeli);
			ban = true;
		}
		
		return ban;
	}
	
	@Transactional
	public Peliculas BuscarXNombre(String nom) {
		return peliculasDao.findByNombre(nom);
	}
	
	@Transactional
	public List<Peliculas> BuscarXGenero(String gen){
		return peliculasDao.BuscarXGenero(gen);
	}
	@Transactional
	public boolean EliminarXNombre(String nom) {
		boolean ban = false;
		for(Peliculas p:peliculasDao.findAll()) {
			if(p.getNombre().equalsIgnoreCase(nom)) {
				ban = true;
				peliculasDao.delete(p);
				break;
			}
		}
		
		return ban;
	}
}
