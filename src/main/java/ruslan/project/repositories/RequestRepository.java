package ruslan.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.project.models.RequestsModel;
import ruslan.project.models.UserModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<RequestsModel, Long> {
    Optional<RequestsModel> findById(Long id);

    List<RequestsModel> findAllByUserId(UserModel userModel);

    List<RequestsModel> findAll();
}
