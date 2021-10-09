package ua.goit.service;

import ua.goit.model.Developer;
import ua.goit.repository.DeveloperRepository;

public class DeveloperService extends BaseService<Long, Developer>{

    private final DeveloperRepository developerRepository = new DeveloperRepository(Developer.class);

    public DeveloperService(Class<Developer> aClass) {
        super(aClass);
    }

    public String developersOfProject(Long id) {
        return developerRepository.developerOfProject(id);
    }
    public String developersWithLevel(String level) {
        return developerRepository.developerWithLevel(level);
    }
    public String developersWithSkill(String skill) {
        return developerRepository.developerWithSkill(skill);
    }
}
