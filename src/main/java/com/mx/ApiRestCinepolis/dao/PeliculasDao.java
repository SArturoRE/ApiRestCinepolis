package com.mx.ApiRestCinepolis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.ApiRestCinepolis.model.Peliculas;

public interface PeliculasDao extends JpaRepository<Peliculas, Integer>{

	public Peliculas findByNombre(String nombre);
	
	@Query(value="SELECT * FROM PELICULAS WHERE GENERO=:GENERO", nativeQuery=true)
	public List<Peliculas> BuscarXGenero(@Param("GENERO") String genero);
}
