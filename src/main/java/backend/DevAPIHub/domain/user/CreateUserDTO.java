package backend.DevAPIHub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
        @NotBlank
        @Size(min = 3, max = 20)
        String username,
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String confirmPassword,
        @NotBlank
        String github,
        @NotBlank
        String linkedin
) {
}
