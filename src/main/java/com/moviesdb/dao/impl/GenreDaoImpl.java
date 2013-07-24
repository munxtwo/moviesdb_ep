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

import com.moviesdb.dao.GenreDao;
import com.moviesdb.domain.Genre;

@Scope("singleton")
@Repository("GenreDao")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class GenreDaoImpl extends AbstractJpaDao<Genre> implements GenreDao {

	@PersistenceContext(unitName = "entityManagerFactory")
	private EntityManager entityManager;
	
	/* (non-Javadoc)
	 * @see com.moviesdb.dao.impl.GenreDao#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	/* (non-Javadoc)
	 * @see com.moviesdb.dao.impl.GenreDao#findGenreByPrimaryKey(long)
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public Genre findGenreByPrimaryKey(long eventId) throws DataAccessException {
		try {
			return find(Genre.class, eventId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.moviesdb.dao.impl.GenreDao#findAllGenres()
	 */
	@SuppressWarnings("unchecked")
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public List<Genre> findAllGenres() throws DataAccessException {
		final Query query = createNamedQuery("findAllGenres", -1, -1);
		return query.getResultList();
	}
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public Genre findGenreByName(String genreName) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findGenreByName", genreName);
		} catch (NoResultException nre) {
			return null;
		}
	}

}
