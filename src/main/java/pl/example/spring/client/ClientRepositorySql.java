package pl.example.spring.client;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository interface for managing {@link Client} entities.
 * This interface extends {@link JpaRepository}, providing CRUD (Create, Read, Update, Delete)
 * operations and additional query methods for the `Client` entity.
 *
 * <p>By extending `JpaRepository`, this interface inherits methods like `save`, `findById`, `findAll`,
 * `delete`, and more, without requiring explicit implementation.</p>
 *
 * <p>This repository is specifically designed for PostgreSQL, as indicated by the `Sql` suffix in the
 * interface name.
 */
public interface ClientRepositorySql extends ClientRepository, JpaRepository<Client, Long> {
}