package mtm;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
@Entity(name = "Film")
@Table(name = "film")
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"actors"})
public class Film {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "film_id_pk")
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  // Not recommended to add fetch = FetchType.EAGER here
  @JoinTable(
      name = "film_actor",
      joinColumns = {@JoinColumn(name = "film_id_fk", referencedColumnName = "film_id_pk")},
      inverseJoinColumns = {
        @JoinColumn(name = "actor_id_fk", referencedColumnName = "actor_id_pk")
      })
  private Set<Actor> actors = new HashSet<>();

  public Film(String name, Actor... actors) {
    this.name = name;
    this.actors = Stream.of(actors).collect(Collectors.toSet());
  }

  public boolean addActor(Actor actor) {
    return actors.add(actor);
  }

  public String getName() {
    return name;
  }

  public Set<Actor> getActors() {
    return actors;
  }

  public String toString() {
    return "{Film {"
        + name
        + "} has actors "
        + getActors().stream().map(Actor::getName).collect(Collectors.toList())
        + "}";
  }
}
