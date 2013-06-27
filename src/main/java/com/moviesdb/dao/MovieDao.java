package com.moviesdb.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.moviesdb.domain.Movie;

public interface MovieDao extends JpaDao<Movie> {

	/**
	 * JPQL Query - findMovieByPrimaryKey
	 */
	Movie findMovieByPrimaryKey(long eventId) throws DataAccessException;

	/**
	 * JPQL Query - findAllMovies
	 */
	List<Movie> findAllMovies() throws DataAccessException;

	/**
	 * JPQL Query - findByStatus
	 */
	List<Movie> findByStatus(String status) throws DataAccessException;
}