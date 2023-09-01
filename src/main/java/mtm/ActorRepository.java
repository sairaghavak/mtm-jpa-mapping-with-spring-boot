package mtm;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sairaghavak
 */
public interface ActorRepository extends JpaRepository<Actor, Long> {

  @EntityGraph(attributePaths = {"films"})
  Optional<Actor> findById(Long id);

  @EntityGraph(attributePaths = {"films"})
  Actor findByName(String name);
}
