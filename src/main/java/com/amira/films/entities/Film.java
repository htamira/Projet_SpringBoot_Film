package com.amira.films.entities;


import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFilm; // primary key
	@NotNull
	@Size (min = 4,max = 25)
	private String titre;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateSortie;
	@NotNull
	private String sponsor;
	@Column(length=45)
	private String logo;
	@ManyToOne
	private Scenariste scenariste;
	public Film() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Film(long idFilm, String titre, Date dateSortie, String sponsor, String photos) {
		super();
		this.idFilm = idFilm;
		this.titre = titre;
		this.dateSortie = dateSortie;
		this.sponsor = sponsor;
		this.logo = photos;
	}

	public Film(String titre, String sponsor, Date dateSortie) {
		super();
		this.titre = titre;
		this.dateSortie = dateSortie;
		this.sponsor = sponsor;
	}

	public long getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(long idFilm) {
		this.idFilm = idFilm;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	@Override
	public String toString() {
		return "Film [idFilm=" + idFilm + ", titre=" + titre + ", dateSortie=" + dateSortie + ", sponsor=" + sponsor
				+ "]";
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Scenariste getScenariste() {
		return scenariste;
	}

	public void setScenariste(Scenariste scenariste) {
		this.scenariste = scenariste;
	}



	/*public Scenariste getScenariste() {
		return scenariste;
	}

	public void setScenariste(Scenariste scenariste) {
		this.scenariste = scenariste;
	}
*/
	@Transient
	public String getLogoImagePath()
	{
		if(logo == null )return null;
		return "/filmphoto/"+idFilm+"/"+ logo;
	}
	//transient String LogoImagePath=getLogoImagePath();
}
