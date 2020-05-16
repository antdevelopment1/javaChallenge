package net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.model.Task_definition;

@Repository
public interface TaskDefinition extends JpaRepository<Task_definition, Long>{

}

