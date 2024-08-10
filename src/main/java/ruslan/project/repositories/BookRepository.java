package ruslan.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.project.models.*;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
    List<BookModel> findAllByISBN(String ISBN);
}
