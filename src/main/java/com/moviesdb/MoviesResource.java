package com.moviesdb;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.moviesdb.dao.MovieDao;
import com.moviesdb.domain.Movie;

@Path("/movies")
public class MoviesResource {

	@Autowired
	private MovieDao movieDao;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Movie> findAll() {
		return movieDao.findAllMovies();
	}
	
	@GET @Path("id/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Movie findById(@PathParam("id") String id) {
		return movieDao.findMovieByPrimaryKey(Integer.parseInt(id));
	}
	
	@GET @Path("status/{status}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Movie> findByStatus(@PathParam("status") String status) {
		List<Movie> list = movieDao.findByStatus(status);
		for (Movie  m:list) {
			System.out.println(m.toString());
		}
		return movieDao.findByStatus(status);
	}
	
}
