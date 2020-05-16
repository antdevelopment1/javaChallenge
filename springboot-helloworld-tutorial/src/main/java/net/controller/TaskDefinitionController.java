package net.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.exception.ResourceNotFoundException;
import net.model.Task_definition;
import net.repository.TaskDefinition;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/task")
public class TaskDefinitionController {
	@Autowired
	private TaskDefinition taskDefinition;

	@GetMapping("/getAll")
	public List<Task_definition> getTask() {
		return taskDefinition.findAll();
	}

	@GetMapping("/getSingle/{id}")
	public ResponseEntity<Task_definition> getTaskById(@PathVariable(value = "id") Long taskId)
			throws ResourceNotFoundException {
		Task_definition task_definition = taskDefinition.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));
		return ResponseEntity.ok().body(task_definition);
	}

	@PostMapping("/create")
	public Task_definition createTask(@Valid @RequestBody Task_definition task) {
		return taskDefinition.save(task);
	}

	@PutMapping("/updateSingle/{id}")
	public ResponseEntity<Task_definition> updateTask(@PathVariable(value = "id") Long taskId,
			@Valid @RequestBody Task_definition taskDetails) throws ResourceNotFoundException {
		Task_definition task_definition = taskDefinition.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

		// employee.setEmailId(taskDetails.getEmailId());
		task_definition.setname(taskDetails.getname());
		task_definition.setdescription(taskDetails.getdescription());
		final Task_definition updatedTask = taskDefinition.save(task_definition);
		return ResponseEntity.ok(updatedTask);
	}

	@DeleteMapping("/deleteSingle/{id}")
	public Map<String, Boolean> deleteTask(@PathVariable(value = "id") Long taskId)
			throws ResourceNotFoundException {
		Task_definition task_definition = taskDefinition.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

		taskDefinition.delete(task_definition);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}



