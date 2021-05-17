package com.amira.films.entities;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

@Entity
@Component
public class Scenariste {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long idSc;
@NotNull
@Size (min = 4,max = 15)
private String nomSc;
@NotNull
@Size (min = 4,max = 15)
private String prenomSc;
@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "yyyy-MM-dd")
private Date dateNaissance;
@JsonIgnore
@OneToMany(mappedBy = "scenariste")
private List<Film> films;


public Scenariste(Long idSc, String nomSc, String prenomSc, Date dateNaissance, List<Film> films) {
	super();
	this.idSc = idSc;
	this.nomSc = nomSc;
	this.prenomSc = prenomSc;
	this.dateNaissance = dateNaissance;
	this.films = films;
}




public Long getIdSc() {
	return idSc;
}
public void setIdSc(Long idSc) {
	this.idSc = idSc;
}

public Date getDateNaissance() {
	return dateNaissance;
}
public void setDateNaissance(Date dateNaissance) {
	this.dateNaissance = dateNaissance;
}
public String getNomSc() {
	return nomSc;
}
public void setNomSc(String nomSc) {
	this.nomSc = nomSc;
}
public String getPrenomSc() {
	return prenomSc;
}
public void setPrenomSc(String prenomSc) {
	this.prenomSc = prenomSc;
}




@Override
public String toString() {
	return " "
			+ " Id Scenariste =" + idSc +
			
			"\n, son nom =" + nomSc + " ,\n son prenom=" + prenomSc + "";
}




}
