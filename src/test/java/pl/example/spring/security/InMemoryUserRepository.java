package pl.example.spring.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public InMemoryUserRepository() {
        // Add some test users
        users.put("user", new User("user", "password", "USER"));
        users.put("admin", new User("admin", "admin", "ADMIN"));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }
}
