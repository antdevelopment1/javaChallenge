package net.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_definition")
public class Task_definition {

	private long id;
	private String name;
	private String description;
	
	public Task_definition() {
		
	}
	
	public Task_definition(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	
	@Column(name = "description", nullable = true)
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	

	@Override
	public String toString() {
		return "Task_definition [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
}
