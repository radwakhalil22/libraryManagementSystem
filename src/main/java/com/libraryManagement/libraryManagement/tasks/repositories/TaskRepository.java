package com.libraryManagement.libraryManagement.tasks.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.libraryManagement.libraryManagement.tasks.entities.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
	
	List<Task> findByExecuteDate(LocalDate executeDate);

}
