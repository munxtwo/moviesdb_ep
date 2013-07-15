package com.moviesdb;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moviesdb.dao.MovieDao;
import com.moviesdb.domain.Movie;

@Component
@Path("/movies")
public class MoviesResource {

	@Autowired
	private MovieDao movieDao;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Movie> findAll() {
		return movieDao.findAllMovies();
	}
	
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Movie findById(@PathParam("id") String id) {
		return movieDao.findMovieByPrimaryKey(Integer.parseInt(id));
	}
	
	@GET @Path("status/{status}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Movie> findByStatus(@PathParam("status") String status) {
		return movieDao.findByStatus(status);
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void create(Movie movie) {
		movieDao.store(movie);
	}
	
//	public void create(@FormParam("name") String name, @FormParam("releaseYear") Integer releaseYear,
//			@FormParam("status") String status, @FormParam("type") String type) {
//		Movie movie = new Movie();
//		movie.setName(name);
//		movie.setReleaseYear(releaseYear);
//		movie.setStatus(status);
//		movie.setType(type);
}
