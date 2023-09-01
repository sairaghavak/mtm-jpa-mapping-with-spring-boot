package mtm;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author sairaghavak
 */
@Entity(name = "Actor")
@Table(name = "actor")
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"films"})
public class Actor {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "actor_id_pk")
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "actors")
  // Not recommended to add fetch = FetchType.EAGER here
  private Set<Film> films = new HashSet<>();

  public Actor(String name) {
    this.name = name;
  }

  public boolean addFilm(Film film) {
    return films.add(film);
  }

  public Set<Film> getFilms() {
    return films;
  }

  public String getName() {
    return name;
  }

  public String toString() {
    return "{Actor {"
        + name
        + "} worked in films "
        + getFilms().stream().map(Film::getName).collect(Collectors.toList())
        + "}";
  }
}
