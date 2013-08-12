package com.moviesdb;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moviesdb.dao.GenreDao;
import com.moviesdb.dao.MovieDao;
import com.moviesdb.domain.Genre;
import com.moviesdb.domain.Movie;
import com.moviesdb.dto.GenreDto;
import com.moviesdb.dto.MovieDto;

@Component
@Path("/movies")
public class MoviesResource {

	@Autowired
	private MovieDao movieDao;
	
	@Autowired
	private GenreDao genreDao;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<MovieDto> findAll() {
		List<Movie> movies = movieDao.findAllMovies();
		List<MovieDto> movieDto = new ArrayList<MovieDto>();
		for (Movie m:movies) {
			movieDto.add(new MovieDto(m)); 
		}
		return movieDto;
	}
	
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public MovieDto findById(@PathParam("id") int id) {
		Movie movie = movieDao.findMovieByPrimaryKey(id);
		return new MovieDto(movie);
	}
	
	@GET @Path("status/{status}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<MovieDto> findByStatus(@PathParam("status") String status) {
		List<Movie> movies = movieDao.findByStatus(status);
		List<MovieDto> movieDto = new ArrayList<MovieDto>();
		for (Movie m:movies) {
			movieDto.add(new MovieDto(m)); 
		}
		return movieDto;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void create(MovieDto movieDto) {
		Movie movie = new Movie();
		movie.setName(movieDto.getName());
		movie.setReleaseYear(movieDto.getReleaseYear());
		movie.setStatus(movieDto.getStatus());
		movie.setType(movieDto.getType());
		Genre genre = genreDao.findGenreByName(movieDto.getGenreName());
		movie.setGenre(genre);
		movieDao.store(movie);
	}
	
	@GET @Path("genres")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<GenreDto> findAllGenres() {
		List<Genre> genres = genreDao.findAllGenres();
		List<GenreDto> genreDto = new ArrayList<GenreDto>();
		for (Genre g:genres) {
			genreDto.add(new GenreDto(g)); 
		}
		return genreDto;
	}
	
	@PUT @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void update(@PathParam("id") int id, MovieDto movieDto) {
		Movie movie = movieDao.findMovieByPrimaryKey(id);
		movie.setName(movieDto.getName());
		movie.setReleaseYear(movieDto.getReleaseYear());
		movie.setStatus(movieDto.getStatus());
		movie.setType(movieDto.getType());
		Genre genre = genreDao.findGenreByName(movieDto.getGenreName());
		movie.setGenre(genre);
		movieDao.store(movie);
	}
	
	@DELETE @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("id") int id) {
		Movie movie = movieDao.findMovieByPrimaryKey(id);
		movieDao.remove(movie);
	}

}
