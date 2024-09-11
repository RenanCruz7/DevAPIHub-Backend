package backend.DevAPIHub.controller;

import backend.DevAPIHub.domain.hub.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/hub")
@SecurityRequirement(name = "bearer-key")
public class HubController {

    @Autowired
    private HubService hubService;

    @PostMapping
    @Transactional
    public ResponseEntity CreateHub(@RequestBody CreateHubDTO dados, UriComponentsBuilder uriBuilder) {
        Hub hub = hubService.execute(dados);
        var uri = uriBuilder.path("/Posts/{id}").buildAndExpand(hub.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsHubDTO(hub));
    }
}

