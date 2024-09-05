package backend.DevAPIHub.domain.user;

import backend.DevAPIHub.domain.hub.Hub;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String github;
    private String linkedin;

    @OneToMany(mappedBy = "user")
    private List<Hub> hubs;
}