package com.workspacemanagement.scheduledtasks;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import com.workspacemanagement.dao.GenericDao;
import com.workspacemanagement.model.Employee;
import com.workspacemanagement.util.XMLParser;

/**
 * 
 * @author Vijayakumar Selvaraj
 * 
 * Scheduler to sync application employee database with Rest input from another server everyday
 *
 */
@Configuration
@EnableScheduling
public class EmployeeCron {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeCron.class);
	
	@Autowired
	GenericDao genericDao;
	
	@Bean
	public TaskScheduler taskScheduler() {
	    return new ConcurrentTaskScheduler(); //single threaded by default
	}
	
	//@Scheduled(cron="0 33 21 * * *")
    public void syncEmployees() {
    	
    	logger.info("Begining Cron job");
    	
    	try {
    		List<Employee> employees = XMLParser.parseEmployees();
    		
    		if(null != employees && !employees.isEmpty()) {
    			genericDao.saveAll(employees);
    		}
    	}catch(Exception e) {
    		logger.info("Exception during cron job : " + e.getMessage());
    		logger.info("Exception stack : ");
    		logger.info(e.getStackTrace().toString());
    	}
    	
    	logger.info("Cron job finished");
    }
}
