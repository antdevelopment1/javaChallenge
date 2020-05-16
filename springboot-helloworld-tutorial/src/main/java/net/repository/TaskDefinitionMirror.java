package net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.model.Task_definition_mirror;

@Repository
public interface TaskDefinitionMirror extends JpaRepository<Task_definition_mirror, Long>{

}

