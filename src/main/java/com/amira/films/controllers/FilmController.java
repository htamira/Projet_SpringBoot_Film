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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.amira.films.entities.Film;
import com.amira.films.entities.Scenariste;
import com.amira.films.reposit.FilmRepository;
import com.amira.films.service.FilmService;
import com.amira.films.upl.FileUploadUtil;
import com.sun.xml.bind.api.impl.NameConverter.Standard;

@Controller
public class FilmController {
	@Autowired
	FilmService filmService;
    @Autowired 
    FilmRepository filmrep;
/*	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {
		modelMap.addAttribute("film", new Film());
		return "formFilm";
		
		//return "createFilm";
	}*/
    
    @RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {
		modelMap.addAttribute("film", new Film());
		modelMap.addAttribute("mode", "new");

		return "formFilm";
		
    }
    

	@RequestMapping("/saveFilm")
	public String saveFilm(@Valid Film film,BindingResult bindingResult)

	{
		if (bindingResult.hasErrors())
			return "createFilm";
		filmService.saveFilm(film);
		//return "listefilms";
		return "formFilm";

	}

	
	
	@RequestMapping("/ListeFilms")
	public String ListeFilms(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size)

	{
		Page<Film> films = filmService.getAllFilmsParPage(page, size);
		modelMap.addAttribute("films", films);

		modelMap.addAttribute("pages", new int[films.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listefilms";
	//return "formFilm";

	}

	@RequestMapping("/supprimerFilm")
	public String supprimerFilm(@RequestParam("id") Long id,

			ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size)

	{
		filmService.deleteFilmById(id);
		Page<Film> films = filmService.getAllFilmsParPage(page,

				size);

		modelMap.addAttribute("films", films);
		modelMap.addAttribute("pages", new int[films.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listefilms";
	//	return "formFilm";

	}

	@RequestMapping("/updateFilm")
	public String updateFilm(@ModelAttribute("film") Film film, @RequestParam("date") String date, ModelMap modelMap)
			throws ParseException

	{
//conversion de la date

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateSortie = dateformat.parse(String.valueOf(date));
		film.setDateSortie(dateSortie);
		filmService.updateFilm(film);
		List<Film> films = filmService.getAllFilms();
		modelMap.addAttribute("films", films);
		//modelMap.addAllAttributes("mode","new");)
		return "search";

	}

	/*@RequestMapping("/modifierFilm")
	public String editerFilm(@RequestParam("id") Long id, ModelMap modelMap) {
		Film p = filmService.getFilm(id);
		modelMap.addAttribute("film", p);
		return "editFilm";
	}*/
	
	@RequestMapping("/modifierFilm")
	public String editerFilm(@RequestParam("id") Long id,ModelMap modelMap)
	{
	Film p= filmService.getFilm(id);
	modelMap.addAttribute("film", p);
	modelMap.addAttribute("mode", "edit");
	return "formFilm";
	}

	/*
	 * @RequestMapping("/search") public String search1(Model model, @Param("titre")
	 * String titre) { List<Film> listFilms = filmService.findByTitre(titre);
	 * model.addAttribute("listFilms", listFilms); model.addAttribute("titre",
	 * titre);
	 * 
	 * return "listeFilms"; }
	 */
	@RequestMapping("/search")
	public String search(Model model, @Param("key") String key) {

		List<Film> list = filmService.listAll(key);
		model.addAttribute("list", list);
		model.addAttribute("titre", key);

		return "search";
	}
	/*
	@RequestMapping("/find")
	public String findbySc(Model model, @Param("key") Scenariste key) {

		List<Film> list = filmService.findByScenariste(key);
		model.addAttribute("list", list);
		model.addAttribute("titre", key);

		return "searchbyscenariste";
	}*/
	
	
	@PostMapping("/saveFilm")
    public RedirectView saveFilm(@ModelAttribute(name="film") Film film,
            @RequestParam("fileImage") MultipartFile multipartFile) throws IOException,ParseException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        film.setLogo(fileName);
          
        Film savedFilm = filmService.saveFilm(film);
 
        String uploadDir = "./filmphoto/" + savedFilm.getIdFilm();
 Path uploadPath= Paths.get(uploadDir);
 
 if(!Files.exists(uploadPath))
 {
	 Files.createDirectories(uploadPath);
 }
 
 try (InputStream inputStream = multipartFile.getInputStream()){
 Path filePath= uploadPath.resolve(fileName);
 System.out.print(filePath.toString());
 System.out.print(filePath.toFile().getAbsolutePath());

 Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
 }
 catch(IOException e)
 {
	 throw new IOException("counld note save"+fileName);
 }
        //FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
         
        return new RedirectView("ListeFilms", true);
    }
}
