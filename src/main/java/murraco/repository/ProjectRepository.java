package murraco.repository;

import murraco.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

  boolean existsById(String id);

  Project findById(String id);

  @Transactional
  void deleteById(String id);

}
