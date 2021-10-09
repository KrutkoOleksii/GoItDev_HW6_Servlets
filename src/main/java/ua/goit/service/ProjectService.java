package ua.goit.service;

import ua.goit.model.Project;
import ua.goit.repository.ProjectRepository;

public class ProjectService extends BaseService<Long, Project>{

    private final ProjectRepository projectRepository = new ProjectRepository(Project.class);

    public ProjectService(Class<Project> aClass) {
        super(aClass);
    }

    public String salaryOfProject(Long id) {
        return projectRepository.salaryOfProject(id);
    }

    public String listOfProject() {
        return projectRepository.listOfProjects();
    }

}
