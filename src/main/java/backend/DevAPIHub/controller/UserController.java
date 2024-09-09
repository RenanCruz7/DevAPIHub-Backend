package backend.DevAPIHub.controller;

import backend.DevAPIHub.domain.user.AuthenticationData;
import backend.DevAPIHub.domain.user.CreateUserDTO;
import backend.DevAPIHub.domain.user.User;
import backend.DevAPIHub.domain.user.UserRepository;
import backend.DevAPIHub.infra.security.TokenJWTData;
import backend.DevAPIHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity peformLogin(@RequestBody @Valid AuthenticationData data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.GenerateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        if (!createUserDTO.password().equals(createUserDTO.confirmPassword())) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User();
        user.setUsername(createUserDTO.username());
        user.setEmail(createUserDTO.email());
        user.setPassword(passwordEncoder.encode(createUserDTO.password()));
        user.setGithub(createUserDTO.github());
        user.setLinkedin(createUserDTO.linkedin());

        User savedUser = userRepository.save(user);


        var authenticationToken = new UsernamePasswordAuthenticationToken(createUserDTO.username(), createUserDTO.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.GenerateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }

}
