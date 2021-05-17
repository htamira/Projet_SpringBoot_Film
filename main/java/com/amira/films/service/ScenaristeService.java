package com.amira.films.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.amira.films.entities.Scenariste;
@Service
public interface ScenaristeService {
	Scenariste saveSc(Scenariste f);
	Scenariste updateScenariste(Scenariste f);
	void deleteScenariste(Scenariste f);
	void deleteScenaristeById(Long id);
	Scenariste getSc(Long id);
	List<Scenariste> getAllScenaristes();
	Page<Scenariste> getAllScenaristesParPage(int page, int size);
	List<Scenariste> findByScenariste (Scenariste scenariste);
	
	
}
