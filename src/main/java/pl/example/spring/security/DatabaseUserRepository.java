package pl.example.spring.security;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DatabaseUserRepository extends UserRepository, JpaRepository<User, Long> {
    @Override
    Optional<User> findByUsername(String username);
}
