package ruslan.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.project.models.AuthorityModel;

import java.util.ArrayList;

@Repository
public interface AuthoritiesRepository extends JpaRepository<AuthorityModel, String> {
    ArrayList<AuthorityModel> findAllByUsernameNotNull();
    ArrayList<AuthorityModel> findByAuthority(String authority);

    void deleteByUsername(String username);

    AuthorityModel findByUsername(String username);
}
