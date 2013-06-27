package com.moviesdb.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.moviesdb.dao.MovieDao;
import com.moviesdb.domain.Movie;

@Scope("singleton")
@Repository("MovieDao")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class MovieDaoImpl extends AbstractJpaDao<Movie> implements MovieDao {

	@PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/* (non-Javadoc)
	 * @see com.moviesdb.dao.impl.MovieDao#findMovieByPrimaryKey(long)
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public Movie findMovieByPrimaryKey(long eventId) throws DataAccessException {
		try {
			return find(Movie.class, eventId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.moviesdb.dao.impl.MovieDao#findAllMovies()
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public List<Movie> findAllMovies() throws DataAccessException {
		final Query query = createNamedQuery("findAll", -1, -1);
		return query.getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.moviesdb.dao.impl.MovieDao#findAllMovies()
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public List<Movie> findByStatus(String status) throws DataAccessException {
		final Query query = createNamedQuery("findByStatus", -1, -1, status);
		return query.getResultList();
	}
}

