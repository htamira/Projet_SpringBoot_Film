package com.amira.films.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amira.films.entities.Scenariste;
import com.amira.films.reposit.FilmRepository;
import com.amira.films.reposit.ScenaristeRepository;
@Service
public class ScenaristeServiceImpl implements ScenaristeService{
@Autowired
ScenaristeRepository scenaristeRepository;
	@Override
	public Scenariste saveSc(Scenariste s) {
		// TODO Auto-generated method stub
		return scenaristeRepository.save(s);
	}

	@Override
	public Scenariste updateScenariste(Scenariste s) {
		// TODO Auto-generated method stub
		return scenaristeRepository.save(s);
	}

	@Override
	public void deleteScenariste(Scenariste f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteScenaristeById(Long id) {
		scenaristeRepository.deleteById(id);	
	}

	@Override
	public Scenariste getSc(Long id) {
		// TODO Auto-generated method stub
		return scenaristeRepository.findById(id).get();
		
	}

	@Override
	public List<Scenariste> getAllScenaristes() {
		// TODO Auto-generated method stub
		return scenaristeRepository.findAll();
	}

	@Override
	public Page<Scenariste> getAllScenaristesParPage(int page, int size) {
		// TODO Auto-generated method stub
		return scenaristeRepository.findAll(PageRequest.of(page, size));	}

	@Override
	public List<Scenariste> findByScenariste(Scenariste scenariste) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
