package com.amira.films.reposit;


import java.util.List;

import org.springframework.data.domain.Page;

//import java.util.List;

//import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.amira.films.entities.Film;
import com.amira.films.entities.Scenariste;
//@RepositoryRestResource(path = "rest")
public interface FilmRepository extends JpaRepository<Film, Long> {
	@Query("SELECT f FROM Film f WHERE f.titre LIKE %?1%"
			+"Or f.sponsor Like %?1%"
			+ "Or f.scenariste like %?1%")
    public List<Film> findAll(String keyword);
	@Query("SELECT f FROM Film f WHERE f.scenariste LIKE %?1%"
			)
    public List<Film> findbyScenariste(String scenariste);
	/*
/*	@Query("SELECT f FROM Film f WHERE f.titre LIKE %?1%"
			+"Or f.sponsor Like %?1%")
    public Page<Film> findAllbypage(String keyword);

	public Page<Film> findAllbypage();*/
	List<Film> findByTitreContains(String titre);
	/*@Query("select f from film f where f.scenariste = ?1")

	List<Film> findByScenariste (Scenariste scenariste);
	List<Film>findByOrderByTitreFilmAsc();
	Film saveFilm(Film f);
	Film updateFilm(Film f);
	void deleteFilm(Film f);
	void deleteFilmById(Long id);
	Film getFilm(Long id);
	List<Film> getAllFilms();
	Page<Film> getAllFilmsParPage(int page, int size);

	List<Film> findByScenaristeIdSc(Long id);
	List<Film> findByOrderByTitreAsc();
	//List<Film> trierTitreSponsor();
	

	*/
}
