package backend.DevAPIHub.domain.user;

import backend.DevAPIHub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String PerformLogin(AuthenticationData data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        return tokenService.GenerateToken((User) authentication.getPrincipal());
    }


    public String RegisterUser(CreateUserDTO createUserDTO){
        if (!createUserDTO.password().equals(createUserDTO.confirmPassword())) {
            return null;
        }

        User user = new User();
        user.setUsername(createUserDTO.username());
        user.setEmail(createUserDTO.email());
        user.setPassword(passwordEncoder.encode(createUserDTO.password()));
        user.setGithub(createUserDTO.github());
        user.setLinkedin(createUserDTO.linkedin());

        userRepository.save(user);

        var authenticationToken = new UsernamePasswordAuthenticationToken(createUserDTO.username(), createUserDTO.password());
        var authentication = manager.authenticate(authenticationToken);

        return tokenService.GenerateToken((User) authentication.getPrincipal());
    }

}
