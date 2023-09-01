package mtm;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author sairaghavak
 */
@SpringBootApplication
public class SpringBootMainClass {

  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext =
        SpringApplication.run(SpringBootMainClass.class, args);

    ActorRepository actorRepository = applicationContext.getBean(ActorRepository.class);
    FilmRepository filmRepository = applicationContext.getBean(FilmRepository.class);

    Actor actor1 = new Actor("Leonardo DiCaprio");
    Actor actor2 = new Actor("Matt Damon");

    Film film1 = new Film("The Revenant");
    Film film2 = new Film("The Departed");
    Film film3 = new Film("Jason Bourne");

    /*- Save films via actor */
    /*
    actor1.addFilm(film1);
    actor1.addFilm(film2);
    actor2.addFilm(film2);
    actor2.addFilm(film3);
    actorRepository.saveAll(Arrays.asList(actor1, actor2)); */

    /*- Save actors via film */
    film1.addActor(actor1);
    film2.addActor(actor1);
    film2.addActor(actor2);
    film3.addActor(actor2);
    filmRepository.saveAll(Arrays.asList(film1, film2, film3));

    /*- BiDirectional Relationship verification */

    // Fetch an actor and get all the associated films.
    Actor leo = actorRepository.findByName("Leonardo DiCaprio");

    // Fetch a film and get all the associated actors
    Film jasonBourne = filmRepository.findByName("Jason Bourne");

    System.out.println("Fetch an actor and get all the associated films => " + leo);
    System.out.println("====================================================================");
    System.out.println("Fetch a film and get all the associated actors => " + jasonBourne);
  }
}
