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

	private static final String GET_ALL_TASK_ENDPOINT_URL = "http://localhost:8080/test/getAll";
	private static final String GET_TASK_ENDPOINT_URL = "http://localhost:8080/test/getSingle/{id}";
	private static final String CREATE_TASK_ENDPOINT_URL = "http://localhost:8080/test/create";
	private static final String UPDATE_TASK_ENDPOINT_URL = "http://localhost:8080/test/updateSingle/{id}";
	private static final String DELETE_TASK_ENDPOINT_URL = "http://localhost:8080/test/deleteSingle/{id}";
	private static final String CREATE_TASK_MIRROR_ENDPOINT_URL = "http://localhost:8080/api/v1/test";
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {

		SpringRestClient springRestClient = new SpringRestClient();
		
		// Step1: first create a new Task
		springRestClient.createTask();
		
		// Step 2: get new created Task from step1
		springRestClient.getTaskById();
		
		// Step3: get all Tasks
		springRestClient.getTask();
		
		// Step4: Update Task with id = 1
		springRestClient.updateTask();
		
		// Step5: Delete Task with id = 1
		springRestClient.deleteTask();

		springRestClient.createTaskMirror();
	}

	private void getTask() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_ALL_TASK_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);

		System.out.println(result);
	}

	private void getTaskById() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		Task_definition result = restTemplate.getForObject(GET_TASK_ENDPOINT_URL, Task_definition.class, params);

		System.out.println(result);
	}

	private void createTask() {

		Task_definition newTask = new Task_definition("admin", "admin");

		RestTemplate restTemplate = new RestTemplate();
		Task_definition result = restTemplate.postForObject(CREATE_TASK_ENDPOINT_URL, newTask, Task_definition.class);

		System.out.println(result);
	}

	private void updateTask() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		Task_definition updatedTask = new Task_definition("admin123", "admin123");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_TASK_ENDPOINT_URL, updatedTask, params);
	}

	private void deleteTask() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_TASK_ENDPOINT_URL, params);
	}


	private void createTaskMirror() {

		Task_definition_mirror newTest = new Task_definition_mirror("admin", "admin");

		RestTemplate restTemplate = new RestTemplate();
		Task_definition_mirror result = restTemplate.postForObject(CREATE_TASK_MIRROR_ENDPOINT_URL, newTest, Task_definition_mirror.class);

		System.out.println(result);
	}
}
