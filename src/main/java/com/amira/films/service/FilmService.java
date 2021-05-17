package com.amira.films.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.amira.films.entities.Film;
import com.amira.films.entities.Scenariste;

public interface FilmService {
	Film saveFilm(Film f);

	Film updateFilm(Film f);

	void deleteFilm(Film f);

	void deleteFilmById(Long id);

	Film getFilm(Long id);

	List<Film> getAllFilms();
/*
	@Query("select f from Film f where f.titre = ?1")
	/*Page<Film> getAllFilmsParPageandbytitre(int page, int size, String titre);

	Page<Film> listAllbyPage(String key, int page, int size);
*/
	Page<Film> getAllFilmsParPage(int page, int size);

	@Query("select f from Film f where f.titre = ?1")
	List<Film> findByTitre(String titre);
	
	
	
	List<Film> listAll(String key);
}
