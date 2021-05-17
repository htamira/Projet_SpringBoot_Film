package com.amira.films.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amira.films.entities.Film;
import com.amira.films.entities.Scenariste;
import com.amira.films.reposit.FilmRepository;
import com.amira.films.reposit.ScenaristeRepository;
import com.amira.films.service.ScenaristeService;

@Controller
public class ScenaristeController {
	@Autowired
	ScenaristeService ScenaristService;
	  @Autowired 
	  ScenaristeRepository Screp;
	@RequestMapping("/showCreateSC")
	public String showCreateSC(ModelMap modelMap) {
		modelMap.addAttribute("scenariste", new Scenariste());
		return "formScenariste";
	}

	@RequestMapping("/saveSc")
	public String saveProduit(@Valid Scenariste scenariste,BindingResult bindingResult)

	{
		if (bindingResult.hasErrors())
			return "createSc";
		ScenaristService.saveSc(scenariste);
		return "listeScenariste";
		//return "formFilm";

	}

/*	@RequestMapping("/ListeScenaristes")
	public String listeProduits(ModelMap modelMap) {
		List<Scenariste> Scenaristes = ScenaristService.getAllScenaristes();
		modelMap.addAttribute("Scenaristes", Scenaristes);
		return "listeScenariste";
	}*/

	
	@RequestMapping("/ListeScenaristes")
	public String ListeScenaristes(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size)

	{
		Page<Scenariste> scenaristes = ScenaristService.getAllScenaristesParPage(page, size);
		modelMap.addAttribute("scenaristes", scenaristes);

		modelMap.addAttribute("pages", new int[scenaristes.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeScenariste";
	//return "formFilm";

	}
	@RequestMapping("/supprimerSc")
	public String supprimerSc(@RequestParam("id") Long id,

			ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size)

	{
		ScenaristService.deleteScenaristeById(id);
		Page<Scenariste> scenaristes = ScenaristService.getAllScenaristesParPage(page,

				size);

		modelMap.addAttribute("scenaristes", scenaristes);
		modelMap.addAttribute("pages", new int[scenaristes.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeScenariste";
	}

	@RequestMapping("/updateSc")
	public String updateSc(@ModelAttribute("scenariste") Scenariste scenariste, @RequestParam("date") String date,ModelMap modelMap) throws ParseException

	{

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateNaissance = dateformat.parse(String.valueOf(date));
		scenariste.setDateNaissance(dateNaissance);
		ScenaristService.updateScenariste(scenariste);
		List<Scenariste> scenaristes = ScenaristService.getAllScenaristes();
		modelMap.addAttribute("scenaristes", scenaristes);
		return "listeScenariste";

	}

	@RequestMapping("/modifierSc")
	public String modifierSc(@RequestParam("id") Long id, ModelMap modelMap) {
		Scenariste s = ScenaristService.getSc(id);
		modelMap.addAttribute("scenariste", s);
		return "editSc";
	}

	/*
	 * @RequestMapping("/search") public String search1(Model model, @Param("titre")
	 * String titre) { List<Film> listFilms = filmService.findByTitre(titre);
	 * model.addAttribute("listFilms", listFilms); model.addAttribute("titre",
	 * titre);
	 * 
	 * return "listeFilms"; }
	 */

}
