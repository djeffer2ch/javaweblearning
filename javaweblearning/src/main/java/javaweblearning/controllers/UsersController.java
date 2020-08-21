package javaweblearning.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import javaweblearning.entities.User;
import javaweblearning.services.DataService;

@RequestScoped
@Named
public class UsersController {

	@Inject
	DataService dataService;
	
	private List<User> allUsers;
	
	@PostConstruct
	public void initialize() {
		this.allUsers = dataService.getAllUsers();
	}

	public List<User> getAllUsers() {
		return allUsers;
	} 
	
	
	
}
