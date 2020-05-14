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
@RequestMapping("/testMirror")

public class TaskDefinitionMirrorController {
    @Autowired
    private TaskDefinitionMirror taskDefinitionMirror;
    

    @GetMapping("/getAll")
	public List<Task_definition_mirror> getAllEmployees() {
		return taskDefinitionMirror.findAll();
	}

	@GetMapping("/getSingle/{id}")
	public ResponseEntity<Task_definition_mirror> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Task_definition_mirror employee = taskDefinitionMirror.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/create")
	public Task_definition_mirror createTest(@Valid @RequestBody Task_definition_mirror employee) {
		return taskDefinitionMirror.save(employee);
	}

	@PutMapping("/updateSingle/{id}")
	public ResponseEntity<Task_definition_mirror> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Task_definition_mirror employeeDetails) throws ResourceNotFoundException {
		Task_definition_mirror employee = taskDefinitionMirror.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		// employee.setEmailId(employeeDetails.getEmailId());
		employee.setname(employeeDetails.getname());
		employee.setdescription(employeeDetails.getdescription());
		final Task_definition_mirror updatedEmployee = taskDefinitionMirror.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/deleteSingle/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Task_definition_mirror employee = taskDefinitionMirror.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		taskDefinitionMirror.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
    
}