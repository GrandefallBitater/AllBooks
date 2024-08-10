package ruslan.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.project.models.ClassModel;
import ruslan.project.models.UserModel;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassModel, Long> {
    List<ClassModel> findAllByNameAndUserId(String name, UserModel user);
}
