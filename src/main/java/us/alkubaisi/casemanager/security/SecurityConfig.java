package us.alkubaisi.casemanager.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		/*auth.inMemoryAuthentication().withUser("mustafa").password("samara")
				.roles("USER", "ADMIN");*/
		auth
        .jdbcAuthentication()
            .dataSource(dataSource).passwordEncoder(passwordEncoder())
            .usersByUsernameQuery("SELECT users.username, users.password, users.enabled FROM USERS users WHERE users.username = ?")
            //.authoritiesByUsernameQuery("SELECT user_roles.username, user_roles.role FROM USER_ROLES user_roles WHERE user_roles.username = ?");
			.authoritiesByUsernameQuery("SELECT users.username, roles.role "
					+ "from ROLES roles "
					+ "inner join USER_ROLES userroles on userroles.role_id = roles.id "
					+ "inner join USERS users on userroles.username = users.username "
					+ "where users.username=?");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/login").permitAll()
				.antMatchers("/").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERVISOR') or hasRole('ROLE_WORKER')")
				.antMatchers("/case/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERVISOR') or hasRole('ROLE_WORKER')")
				.antMatchers("/worker/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERVISOR')")
				.antMatchers("/applicant/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERVISOR')")
				.antMatchers("/location/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERVISOR')")
				.antMatchers("/users/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/webjars/**").permitAll().anyRequest().permitAll()
				.antMatchers("/resources/**").permitAll().anyRequest().permitAll()
				.antMatchers("/favicon.ico").permitAll().anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/loginError")
				.and()
				.logout().logoutSuccessUrl("/login");
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}