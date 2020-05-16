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
import net.model.Task_definition_mirror;
import net.repository.TaskDefinitionMirror;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/taskMirror")

public class TaskDefinitionMirrorController {
    @Autowired
    private TaskDefinitionMirror taskDefinitionMirror;
    

    @GetMapping("/getAll")
	public List<Task_definition_mirror> getAllTasks() {
		return taskDefinitionMirror.findAll();
	}

	@GetMapping("/getSingle/{id}")
	public ResponseEntity<Task_definition_mirror> getTaskById(@PathVariable(value = "id") Long taskId)
			throws ResourceNotFoundException {
		Task_definition_mirror task = taskDefinitionMirror.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));
		return ResponseEntity.ok().body(task);
	}

	@PostMapping("/create")
	public Task_definition_mirror createTasks(@Valid @RequestBody Task_definition_mirror task) {
		return taskDefinitionMirror.save(task);
	}

	@PutMapping("/updateSingle/{id}")
	public ResponseEntity<Task_definition_mirror> updateTasks(@PathVariable(value = "id") Long taskId,
			@Valid @RequestBody Task_definition_mirror taskDetails) throws ResourceNotFoundException {
		Task_definition_mirror task = taskDefinitionMirror.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

		// employee.setEmailId(employeeDetails.getEmailId());
		task.setname(taskDetails.getname());
		task.setdescription(taskDetails.getdescription());
		final Task_definition_mirror updatedTask = taskDefinitionMirror.save(task);
		return ResponseEntity.ok(updatedTask);
	}

	@DeleteMapping("/deleteSingle/{id}")
	public Map<String, Boolean> deleteTasks(@PathVariable(value = "id") Long taskId)
			throws ResourceNotFoundException {
		Task_definition_mirror task = taskDefinitionMirror.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + taskId));

		taskDefinitionMirror.delete(task);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
    
}