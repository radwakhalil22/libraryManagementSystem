package com.libraryManagement.libraryManagement.tasks.repositories;

import org.springframework.data.repository.CrudRepository;

import com.libraryManagement.libraryManagement.tasks.entities.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
