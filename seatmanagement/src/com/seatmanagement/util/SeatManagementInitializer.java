package com.seatmanagement.util;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;

public class SeatManagementInitializer implements WebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(SeatManagementInitializer.class);

	private String activeProfile;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		InputStream input = null;
		Properties p = new Properties();

		try {

			input = getClass().getClassLoader().getResourceAsStream("build.properties");
			p.load(input);

			activeProfile = p.getProperty("profiles.active");
			
		} catch (Exception e) {
			logger.error("Error while reading properties file for application initialisation", e);
		}

		servletContext.setInitParameter("spring.profiles.active", activeProfile);
	}
}