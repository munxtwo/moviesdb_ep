package com.moviesdb;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/movies")
public class MoviesResource {

	MoviesDatabase db = new MoviesDatabase();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ArrayList<Movie> findAll() {
		db.connectDB();
		ArrayList<Movie> moviesList = db.findAll();
		db.closeDB();
		return moviesList;
	}
	
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Movie findById(@PathParam("id") String id) {
		db.connectDB();
		Movie movie = db.findById(Integer.parseInt(id));
		db.closeDB();
		return movie;
	}
	
	@GET @Path("status/{status}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ArrayList<Movie> findByStatus(@PathParam("status") String status) {
		db.connectDB();
		ArrayList<Movie> media = db.findByStatus(status);
		db.closeDB();
		return media;
	}
	
}
