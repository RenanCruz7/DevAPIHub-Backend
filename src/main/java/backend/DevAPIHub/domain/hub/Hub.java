package backend.DevAPIHub.domain.hub;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "hub")
@Entity(name = "hub")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apiName;
    private String apiDescription;
    private boolean authRequired;
    private String apiLink;
    private Category apiCategory;
    @Min(1)
    @Max(5)
    private float rating;

    public Hub(String apiName, String apiDescription, boolean authRequired, String apiLink, Category apiCategory, @Min(1) @Max(5) float rating) {
        this.apiName = apiName;
        this.apiDescription = apiDescription;
        this.authRequired = authRequired;
        this.apiLink = apiLink;
        this.apiCategory = apiCategory;
        this.rating = 0.0f;
    }
}
