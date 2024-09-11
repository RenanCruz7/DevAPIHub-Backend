package backend.DevAPIHub.controller;

import backend.DevAPIHub.domain.hub.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/hub")
@SecurityRequirement(name = "bearer-key")
public class HubController {

    @Autowired
    private HubService hubService;
    @Autowired
    private HubRepository hubRepository;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity CreateHub(@RequestBody CreateHubDTO dados, UriComponentsBuilder uriBuilder) {
        Hub hub = hubService.execute(dados);
        var uri = uriBuilder.path("/Posts/{id}").buildAndExpand(hub.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsHubDTO(hub));
    }

    @GetMapping
    public ResponseEntity<List<Hub>> List(){
        var hubs = hubRepository.findAll();
        return ResponseEntity.ok(hubs);
    }
}

