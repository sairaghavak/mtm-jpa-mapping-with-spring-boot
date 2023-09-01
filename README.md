# Many-Many JPA Mapping With Spring Boot

This is a BiDirectional Many-Many Mapping example that demonstrates the JPA's Many-Many mapping with Spring's Data JPA.

Here are the important components in this Spring Boot application.

- **Entities**

  - `Actor`
  - `Film`

- **Repository Interfaces**

  - `ActorRepository`
  - `FilmRepository`

- **Tables**

  - `actor`
  - `film`
  - `film_actor`

Unlike other JPA mappings, this Many-Many mapping has no owning side at least from database pov i.e., both the `actor` and `film` tables are unrelated(No Foreign key ref). However, they are related through the join table `film_actor` which will be created by JPA after inferring from `@JoinTable` annotation.
