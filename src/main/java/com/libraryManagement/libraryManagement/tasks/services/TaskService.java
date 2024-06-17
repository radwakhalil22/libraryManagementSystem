package com.libraryManagement.libraryManagement.tasks.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryManagement.libraryManagement.tasks.entities.Task;
import com.libraryManagement.libraryManagement.tasks.repositories.TaskRepository;

@Service
public class TaskService  {
	
	  @Autowired
	    private TaskRepository taskRepository;

	    public void saveTask(String taskName) {
	        Task task = new Task();
	        task.setTaskName(taskName);
	        task.setExecuteDate(LocalDate.now());
	        taskRepository.save(task);
	    }

	    public String generateTaskName(String baseName) {
	        LocalDateTime now = LocalDateTime.now();
	        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	        return baseName + " - " + formattedDate;
	    }

}
