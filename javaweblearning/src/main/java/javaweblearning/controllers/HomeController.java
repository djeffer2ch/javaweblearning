package javaweblearning.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javaweblearning.entities.Quality;
import javaweblearning.entities.User;
import javaweblearning.services.DataService;

@RequestScoped
@Named
public class HomeController {

	@Inject
	DataService dataService;
	
	@Inject
	SecurityContext securityContext;
	
	@Inject
	FacesContext facesContext;
	
	private Optional<User> currentUser;
	private List<Quality> currentQualities;
	
	@PostConstruct
	public void initialize() {
		String username = securityContext.getCallerPrincipal().getName();
		this.currentUser = dataService.getUser(username);
		this.currentUser.ifPresent(user -> {
			 this.currentQualities = dataService.getQualities(user);
		});
	}
	
	
	public User getCurrentUser() {
		return this.currentUser.orElse(null);
	}


	public List<Quality> getCurrentQualities() {
		return currentQualities;
	}
	
	public boolean isAllowedToSeeUsers() {
		return ((HttpServletRequest)facesContext.getExternalContext().getRequest()).isUserInRole("admin");
		//return securityContext.isCallerInRole("admin");
	}
	
	public String logout() throws ServletException {
		ExternalContext ec = facesContext.getExternalContext();
		((HttpServletRequest)ec.getRequest()).logout();;
		return "/login.xhtml?faces-redirect=true";
	}
	 
	
}
