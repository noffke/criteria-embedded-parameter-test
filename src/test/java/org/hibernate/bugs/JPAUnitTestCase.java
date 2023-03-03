package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh123Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<WithEmbedded> query = cb.createQuery(WithEmbedded.class);
		final Root<WithEmbedded> root = query.from(WithEmbedded.class);
		//deliberately using Object type here, using AnEmbeddable works
		final ParameterExpression<Object> parameter = cb.parameter(Object.class);
		query.select(root).where(cb.equal(root.get("e"), parameter));

		final TypedQuery<WithEmbedded> typedQuery = entityManager.createQuery(query);
		typedQuery.setParameter(parameter, new AnEmbeddable("a", "b"));
		Assert.assertEquals(0, typedQuery.getResultList().size());

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
