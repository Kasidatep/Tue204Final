package sit.int204.tue.tue204final.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import sit.int204.tue.tue204final.entities.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    boolean existsTodoByOwner(String owner);

    @Transactional
    @Modifying
    void deleteAllByOwner(String owner);
}
