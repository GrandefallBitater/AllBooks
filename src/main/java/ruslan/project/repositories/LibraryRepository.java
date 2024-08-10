package ruslan.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.project.models.UserModel;
import ruslan.project.models.LibraryModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryModel, Long> {
    List<LibraryModel> findAllByUserId(UserModel user);
    Optional<LibraryModel> findById(Long id);
}
