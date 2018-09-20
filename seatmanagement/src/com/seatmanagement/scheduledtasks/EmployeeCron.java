package com.seatmanagement.scheduledtasks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import com.seatmanagement.util.XMLParser;

@Configuration
@EnableScheduling
public class EmployeeCron {
	
	@Bean
	public TaskScheduler taskScheduler() {
	    return new ConcurrentTaskScheduler(); //single threaded by default
	}
	
	@Scheduled(cron="0 33 21 * * *")
    public void reportCurrentTime() {
		XMLParser.parseEmployees();
    }
}
