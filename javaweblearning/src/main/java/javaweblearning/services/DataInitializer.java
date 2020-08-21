package javaweblearning.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import javaweblearning.entities.User;

@ApplicationScoped
public class DataInitializer {

	@Inject
	DataService dataService;
	
	public void execute(@Observes @Initialized(ApplicationScoped.class) Object event) {
		
		if (dataService.getAllUsers().isEmpty()) {
			
			User sally = dataService.createUser("Sally Addams", "saddams", "saddams", "admin");
			User tom = dataService.createUser("Tom Matthews", "tmatthews", "tmatthews", "user");
			
			dataService.createQuality("Wonderfull", sally);
			dataService.createQuality("Team player", sally);
			dataService.createQuality("Good judgment", sally);
			dataService.createQuality("Good leader", sally);
			
			dataService.createQuality("Diligent", tom);
			dataService.createQuality("Responsible", tom);
			dataService.createQuality("Cares for his teammates", tom);
		}
		else 
			System.out.println("DataInitializer|execute- Not empty DBB");
	}
	
}
