package backend.DevAPIHub.domain.hub;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateHubDTO(
        @NotBlank
        @Size(min = 5, max = 100)
        String apiName,

        @NotBlank
        @Size(min = 10, max = 500)
        String apiDescription,

        @NotBlank
        boolean authRequired,

        @NotBlank
        @Size(min = 5, max = 120)
        String apiLink,

        @NotBlank
        Category apiCategory
) {
}
