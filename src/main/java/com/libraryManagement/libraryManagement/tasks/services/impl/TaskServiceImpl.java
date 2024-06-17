package com.libraryManagement.libraryManagement.tasks.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.libraryManagement.tasks.entities.Task;
import com.libraryManagement.libraryManagement.tasks.repositories.TaskRepository;
import com.libraryManagement.libraryManagement.tasks.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	  @Autowired
	    private TaskRepository taskRepository;

	  	@Override
	    public void checkAndExecuteTasks() {
	        LocalDate today = LocalDate.now();
	        List<Task> tasks = taskRepository.findByExecuteDate(today);
	        
	        for (Task task : tasks) {
	            // Execute the task logic here
	            System.out.println("Executing task: " + task.getTaskName());
	        }
	    }

}
