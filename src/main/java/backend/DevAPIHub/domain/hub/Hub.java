package backend.DevAPIHub.domain.hub;

import backend.DevAPIHub.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "hub")
@Entity(name = "Hub")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "api_name")
    private String apiName;
    @Column(name = "api_description")
    private String apiDescription;
    @Column(name = "auth_required")
    private boolean authRequired;
    @Column(name = "api_link")
    private String apiLink;
    @Enumerated(EnumType.STRING)
    @Column(name = "api_category")
    private Category apiCategory;
    @Min(1)
    @Max(5)
    private float rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Hub(CreateHubDTO dados) {
        this.apiName = dados.apiName();
        this.apiDescription = dados.apiDescription();
        this.authRequired = dados.authRequired();
        this.apiLink = dados.apiLink();
        this.apiCategory = dados.apiCategory();
        this.rating = 1f;
    }
}
