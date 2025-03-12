package pl.example.spring.security;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
}
