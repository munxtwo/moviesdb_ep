package com.moviesdb.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TGenre")
@NamedQueries({
	@NamedQuery(name = "findAllGenres", query = "select myGenre from Genre myGenre order by myGenre.id"),
	@NamedQuery(name = "findGenreByName", query = "select myGenre from Genre myGenre where genre_name = ?1"),
})
public class Genre {
	
	@Column(name = "id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "genre_name", nullable = false)
	private String name;
//	
//	@OneToMany(mappedBy = "genre")
//	private Set<Movie> movies;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public void setMovies(Set<Movie> movies) {
//		this.movies = movies;
//	}
//
//	public Set<Movie> getMovies() {
//		return movies;
//	}

}
