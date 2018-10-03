/*package com.seatmanagement.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password, enabled from users where username=? ")
				.authoritiesByUsernameQuery("select username, role from user_roles where username=? ");
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return new UrlAuthenticationSuccessHandler();
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/index.jsp").anonymous().antMatchers("/**")
				.hasAnyRole("LEAD", "ADMIN").and().formLogin().loginPage("/index")
				.loginProcessingUrl("/j_spring_security_check").successHandler(myAuthenticationSuccessHandler())
				.failureHandler(authenticationFailureHandler()).usernameParameter("username")
				.passwordParameter("password").failureUrl("/error-login").permitAll().and().logout().logoutUrl("/index")
				.logoutSuccessUrl("/pagelogout");

	}
}
*/