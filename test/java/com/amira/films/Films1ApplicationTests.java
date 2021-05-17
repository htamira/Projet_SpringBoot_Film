package com.amira.films;

import java.text.ParseException;
import java.text.SimpleDateFormat;
// les imports 
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import com.amira.films.entities.Film;
import com.amira.films.reposit.FilmRepository;
import com.amira.films.service.FilmService;


@SpringBootTest
class Films1ApplicationTests {

	/*
	 * @Test void contextLoads() { }
	 */

	@Autowired
	// l'interface
	private FilmRepository filmRepository;
	private FilmService filmService;
	@Test
//creer un film 
	public void testCreateFilm() {
		// creer un objet film
		Film film = new Film("Miracle",  "MG",new Date());
		// enregister l'objet dans la base de donnée
		filmRepository.save(film);
	}//recherche produit
	@Test
	public void testFindFilm() {
		// trouver le film a partir son id
		Film f = filmRepository.findById(1L).get();
		// appel a tostring pour afficher le film
		System.out.print(f);
	}

	@Test
	public void testUpdateFilm() {
		// trouver le film a partir son id
		Film f = filmRepository.findById(1L).get();

		// update sponsor du film

		f.setSponsor("Carfour");
		filmRepository.save(f);

//;
		// appel a tostring pour afficher le film
		System.out.print(f);
	}

	@Test
	public void testDeleteFilm() {
		// supprimer un film a partir son id
		filmRepository.deleteById(1L);

	}

	@Test
	public void testFindallFilm() {
		List<Film> films = filmRepository.findAll();

		for (Film f : films)
			System.out.println(f);
	}
	@Test
	public void testFindByNomFilmContains() {
		
		Page<Film> films = filmService.getAllFilmsParPage(0, 2);
		System.out.println(films.getSize());
		System.out.println(films.getTotalElements());
		System.out.println(films.getTotalPages());
		films.getContent().forEach(f -> {
			System.out.println(f.toString());
		});
		/*
		 * ou bien for (Produit p : prods) { System.out.println(p); }
		 */
	}
}