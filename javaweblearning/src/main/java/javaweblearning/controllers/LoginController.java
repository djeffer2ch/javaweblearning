package javaweblearning.controllers;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@RequestScoped
@Named
public class LoginController {

	@Inject
	FacesContext facesContext;
	
	@Inject
	SecurityContext securityContext;
	
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void execute() throws IOException {
		switch (processAuthentication()) {
		case SEND_CONTINUE:
			facesContext.getResponseComplete();
			break;
		case SEND_FAILURE:
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
			break;
		case SUCCESS:
			getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/app/main.xhtml");
			break;
		default:
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred", null));
			break;
		}
	}
	
	private AuthenticationStatus processAuthentication() {
		ExternalContext ec = getExternalContext();
		return securityContext.authenticate((HttpServletRequest)ec.getRequest(), 
											(HttpServletResponse)ec.getResponse(), 
											AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(username, password))); 
	}
	
	private ExternalContext getExternalContext() {
		return facesContext.getExternalContext();
	}
	
	
}
