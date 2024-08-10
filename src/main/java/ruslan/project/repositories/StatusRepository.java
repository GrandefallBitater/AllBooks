package ruslan.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.project.models.StatusModel;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<StatusModel, Long> {
    Optional<StatusModel> findById(Long id);
}
