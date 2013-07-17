package com.moviesdb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TMovies")
@NamedQueries({
	@NamedQuery(name = "findAll", query = "select myMovie from Movie myMovie order by myMovie.releaseYear"),
	@NamedQuery(name = "findByStatus", query = "select myMovie from Movie myMovie where status = ?1")
})
public class Movie {
	
	@Column(name = "id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "release_year", nullable = false)
	private int releaseYear;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "mtype", nullable = false)
	private String type;
	
	@ManyToOne
	@JoinColumns( {
		@JoinColumn(name = "genre_id", referencedColumnName = "id")
	})
	private Genre genre;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Genre getGenre() {
		return genre;
	}

	public String toString() {
		return String.format("Movie info: %s, %d, %s, %s", name, releaseYear, status, type);
	}

}
