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
import net.model.Task_definition_mirror;
import net.repository.TaskDefinition;
import net.repository.TaskDefinitionMirror;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private TaskDefinition taskDefinition;

	// @Autowired
	// private TaskDefinitionMirror taskDefinitionMirror;

	@GetMapping("/employees")
	public List<Task_definition> getAllEmployees() {
		return taskDefinition.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Task_definition> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Task_definition employee = taskDefinition.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Task_definition createEmployee(@Valid @RequestBody Task_definition employee) {
		return taskDefinition.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Task_definition> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Task_definition employeeDetails) throws ResourceNotFoundException {
		Task_definition employee = taskDefinition.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		// employee.setEmailId(employeeDetails.getEmailId());
		employee.setname(employeeDetails.getname());
		employee.setdescription(employeeDetails.getdescription());
		final Task_definition updatedEmployee = taskDefinition.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Task_definition employee = taskDefinition.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		taskDefinition.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


	// @PostMapping("/test")
	// public Task_definition_mirror createTest(@Valid @RequestBody Task_definition_mirror employee) {
	// 	return taskDefinition.save(employee);
	// }



	// @PostMapping("/newroute")
	// public Task_definition_mirror createEmployee(@Valid @RequestBody Task_definition_mirror taskDefinitionMirror) {
	// 	return Task_definition_mirror.save(taskDefinitionMirror);
	// }
}
