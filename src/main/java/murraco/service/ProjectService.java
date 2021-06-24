package murraco.service;

import murraco.model.Project;
import murraco.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  public Project create(Project project) {
     return projectRepository.saveAndFlush(project);
  }

  public void delete(Project p) {
    projectRepository.deleteById(p.getId());
    projectRepository.flush();
  }

  public Optional<Project> findById(int id) {
    return projectRepository.findById(id);
  }

  public List<Project> find() {
    return projectRepository.findAll();
  }
}
