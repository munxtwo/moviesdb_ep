package com.moviesdb.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.moviesdb.domain.Genre;

public interface GenreDao {

	public abstract EntityManager getEntityManager();

	/* (non-Javadoc)
	 * @see com.moviesdb.dao.impl.GenreDao#findGenreByPrimaryKey(long)
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public abstract Genre findGenreByPrimaryKey(long eventId)
			throws DataAccessException;

	/* (non-Javadoc)
	 * @see com.moviesdb.dao.impl.GenreDao#findAllGenres()
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public abstract List<Genre> findAllGenres() throws DataAccessException;
	
	/* (non-Javadoc)
	 * @see com.moviesdb.dao.impl.GenreDao#findGenreByName()
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public abstract Genre findGenreByName(String genreName) throws DataAccessException;

}