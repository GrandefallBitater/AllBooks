package ruslan.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ruslan.project.models.UserModel;

import java.util.ArrayList;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    ArrayList<UserModel> findAllByUsernameNotNull();

    @Modifying
    @Transactional
    void deleteByUsername(String username);

    UserModel findByUsername(String username);
}
