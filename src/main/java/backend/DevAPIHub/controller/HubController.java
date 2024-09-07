package backend.DevAPIHub.controller;

import backend.DevAPIHub.domain.hub.CreateHubDTO;
import backend.DevAPIHub.domain.hub.Hub;
import backend.DevAPIHub.domain.hub.HubRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("/hub")
public class HubController {

    @Autowired
    private HubRepository hubRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody CreateHubDTO dados) {
        System.out.println(dados);
        hubRepository.save(new Hub(dados));
        return ResponseEntity.created(null).build();
    }
}

