package mtm;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sairaghavak
 */
public interface FilmRepository extends JpaRepository<Film, Long> {

  @EntityGraph(attributePaths = {"actors"})
  Optional<Film> findById(Long id);

  @EntityGraph(attributePaths = {"actors"})
  Film findByName(String name);
}
