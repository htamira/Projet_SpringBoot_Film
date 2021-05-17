package com.amira.films.reposit;


import java.util.List;

import org.springframework.data.domain.Page;

//import java.util.List;

//import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.amira.films.entities.Scenariste;
//@RepositoryRestResource(path = "rest")
public interface ScenaristeRepository extends JpaRepository<Scenariste, Long> {
	

}
