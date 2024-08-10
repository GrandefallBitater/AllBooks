package ruslan.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.project.models.PlaceModel;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceModel, Long> {
}
