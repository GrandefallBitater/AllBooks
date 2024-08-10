package ruslan.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.project.models.*;

@Repository
public interface AdditionalInformationRepository extends JpaRepository<AdditionalInformationModel, Long> {
}
