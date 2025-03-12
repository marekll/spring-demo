package pl.example.spring.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class UserDto {
    private final String username;
    private final String password;
    private final String role;
}
