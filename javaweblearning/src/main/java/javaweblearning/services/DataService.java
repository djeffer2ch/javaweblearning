package javaweblearning.services;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import javaweblearning.entities.Quality;
import javaweblearning.entities.User;

@ApplicationScoped
public class DataService {

	@PersistenceContext(unitName = "PersistenceUnitName")
	EntityManager em;
	
	@Transactional
	public User createUser(String name, String username, String password, String group) {
		User newUser = new User(name, username, password, group);
		em.persist(newUser);
		em.flush();
		return newUser;
	}
	
	@Transactional
	public Quality createQuality(String description, User user) {
		Quality newQuality = new Quality(description, user);
		em.persist(newQuality);
		em.flush();
		return newQuality;
	}
	
	public List<User> getAllUsers() {
		return em.createNamedQuery("User.all", User.class).getResultList();
	}
	
	public Optional<User> getUser(String username) {
		return em.createNamedQuery("User.byUsername", User.class)
				.setParameter("username", username)
				.getResultList()
				.stream()
				.findFirst();
	}
	
	public List<Quality> getQualities(User user) {
		return em.createNamedQuery("Quality.byUser", Quality.class)
				.setParameter("userId", user.getId())
				.getResultList();
	}
}
