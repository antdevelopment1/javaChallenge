package net;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.model.Task_definition;
import net.model.Task_definition_mirror;

public class SpringRestClient {

	private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8080/api/v1/employees";
	private static final String GET_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees/{id}";
	private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees";
	private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees/{id}";
	private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees/{id}";
	private static final String CREATE_TEST_ENDPOINT_URL = "http://localhost:8080/api/v1/test";
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {

		SpringRestClient springRestClient = new SpringRestClient();
		
		// Step1: first create a new employee
		springRestClient.createEmployee();
		
		// Step 2: get new created employee from step1
		springRestClient.getEmployeeById();
		
		// Step3: get all employees
		springRestClient.getEmployees();
		
		// Step4: Update employee with id = 1
		springRestClient.updateEmployee();
		
		// Step5: Delete employee with id = 1
		springRestClient.deleteEmployee();

		springRestClient.createTest();


	}

	private void getEmployees() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_EMPLOYEES_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);

		System.out.println(result);
	}

	private void getEmployeeById() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		Task_definition result = restTemplate.getForObject(GET_EMPLOYEE_ENDPOINT_URL, Task_definition.class, params);

		System.out.println(result);
	}

	private void createEmployee() {

		Task_definition newEmployee = new Task_definition("admin", "admin");

		RestTemplate restTemplate = new RestTemplate();
		Task_definition result = restTemplate.postForObject(CREATE_EMPLOYEE_ENDPOINT_URL, newEmployee, Task_definition.class);

		System.out.println(result);
	}

	private void updateEmployee() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		Task_definition updatedEmployee = new Task_definition("admin123", "admin123");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL, updatedEmployee, params);
	}

	private void deleteEmployee() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL, params);
	}


	private void createTest() {

		Task_definition_mirror newTest = new Task_definition_mirror("admin", "admin");

		RestTemplate restTemplate = new RestTemplate();
		Task_definition_mirror result = restTemplate.postForObject(CREATE_TEST_ENDPOINT_URL, newTest, Task_definition_mirror.class);

		System.out.println(result);
	}
}
