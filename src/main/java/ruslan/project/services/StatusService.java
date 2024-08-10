package ruslan.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruslan.project.models.StatusModel;
import ruslan.project.repositories.StatusRepository;

import java.util.Optional;

@Service
public class StatusService {
    private StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Optional<StatusModel> getStatus(String Name) {
        return statusRepository.findById(Long.parseLong(Name));
    }
}
