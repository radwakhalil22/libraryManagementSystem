package com.libraryManagement.libraryManagement.tasks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class ScheduledTaskChecker {

	  @Autowired
	    private TaskService taskService;

	  //secs mins hours
	    @Scheduled(cron = "0 0 0 * * *") // This cron expression means the method will run daily at midnight
	    public void checkTasks() {
	        taskService.checkAndExecuteTasks();
	    }
}
