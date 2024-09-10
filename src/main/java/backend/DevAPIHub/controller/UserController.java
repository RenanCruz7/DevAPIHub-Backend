package backend.DevAPIHub.controller;

import backend.DevAPIHub.domain.user.*;
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
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity peformLogin(@RequestBody @Valid AuthenticationData data) {
        var token = userService.PerformLogin(data);

        return ResponseEntity.ok(new TokenJWTData(token));
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        var token = userService.RegisterUser(createUserDTO);

        return ResponseEntity.ok(new TokenJWTData(token));
    }

}
