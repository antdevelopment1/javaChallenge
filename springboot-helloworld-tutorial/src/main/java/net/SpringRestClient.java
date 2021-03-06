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

	private static final String GET_ALL_TASK_ENDPOINT_URL = "http://localhost:8080/task/getAll";
	private static final String GET_TASK_ENDPOINT_URL = "http://localhost:8080/task/getSingle/{id}";
	private static final String CREATE_TASK_ENDPOINT_URL = "http://localhost:8080/task/create";
	private static final String UPDATE_TASK_ENDPOINT_URL = "http://localhost:8080/task/updateSingle/{id}";
	private static final String DELETE_TASK_ENDPOINT_URL = "http://localhost:8080/task/deleteSingle/{id}";

	private static final String GET_ALL_TASK_MIRROR_ENDPOINT_URL = "http://localhost:8080/taskMirror/getAll";
	private static final String GET_TASK_MIRROR_ENDPOINT_URL = "http://localhost:8080/taskMirror/getSingle/{id}";
	private static final String CREATE_TASK_MIRROR_ENDPOINT_URL = "http://localhost:8080/taskMirror/create";
	private static final String UPDATE_TASK_MIRROR_ENDPOINT_URL = "http://localhost:8080/taskMirror/updateSingle/{id}";
	private static final String DELETE_TASK_MIRROR_ENDPOINT_URL = "http://localhost:8080/taskMirror/deleteSingle/{id}";


	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {

		SpringRestClient springRestClient = new SpringRestClient();
		
		// Step1: first create a new Task
		springRestClient.createTask();
		
		// Step 2: get new created Task from step1
		springRestClient.getTaskById();
		
		// Step3: get all Tasks
		springRestClient.getAllTask();
		
		// Step4: Update Task with id = 1
		springRestClient.updateTask();
		
		// Step5: Delete Task with id = 1
		springRestClient.deleteTask();
	
		springRestClient.createTaskMirror();

		springRestClient.getTaskMirrorById();

		springRestClient.getAllTaskMirror();

		springRestClient.updateTaskMirror();

		springRestClient.deleteTaskMirror();
	}

	// Task Methods

	private void getAllTask() {

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

	// Task Mirror Methods

	private void getAllTaskMirror() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_ALL_TASK_MIRROR_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);

		System.out.println(result);
	}

	private void getTaskMirrorById() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		Task_definition_mirror result = restTemplate.getForObject(GET_TASK_MIRROR_ENDPOINT_URL, Task_definition_mirror.class, params);

		System.out.println(result);
	}

	private void createTaskMirror() {

		Task_definition_mirror newTest = new Task_definition_mirror("admin", "admin");

		RestTemplate restTemplate = new RestTemplate();
		Task_definition_mirror result = restTemplate.postForObject(CREATE_TASK_MIRROR_ENDPOINT_URL, newTest, Task_definition_mirror.class);

		System.out.println(result);
	}

	private void updateTaskMirror() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		Task_definition_mirror updatedTask = new Task_definition_mirror("admin123", "admin123");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_TASK_MIRROR_ENDPOINT_URL, updatedTask, params);
	}

	private void deleteTaskMirror() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_TASK_MIRROR_ENDPOINT_URL, params);
	}

}
