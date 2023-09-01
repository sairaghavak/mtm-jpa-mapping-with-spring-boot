package mtm;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

  @ManyToMany(cascade = CascadeType.ALL)
  // Not recommended to add fetch = FetchType.EAGER here
  @JoinTable(
      name = "film_actor",
      joinColumns = {@JoinColumn(name = "actor_id_fk", referencedColumnName = "actor_id_pk")},
      inverseJoinColumns = {@JoinColumn(name = "film_id_fk", referencedColumnName = "film_id_pk")})
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
