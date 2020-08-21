package javaweblearning.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@DatabaseIdentityStoreDefinition(
		dataSourceLookup = "java:global/TheDataSourceName",
		callerQuery = "SELECT user_password FROM users WHERE username = ?",
		groupsQuery = "SELECT user_group FROM users WHERE username = ?"
)
/*
@DataSourceDefinition(
		name = "java:app/MyDataSource",
		className = "org.h2.jdbcx.JdbcDataSource",
		url="jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE"
)
*/
@CustomFormAuthenticationMechanismDefinition(
		loginToContinue = @LoginToContinue(
				loginPage = "/login.xhtml",
				errorPage = "",
				useForwardToLogin = false 
		)
)
@FacesConfig
@ApplicationScoped
public class ApplicationConfig {

}
