package backend.DevAPIHub.controller;

import backend.DevAPIHub.domain.hub.*;
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
public class HubController {

    @Autowired
    private CreateHub createHub;

    @PostMapping
    @Transactional
    public ResponseEntity CreateHub(@RequestBody CreateHubDTO dados, UriComponentsBuilder uriBuilder) {
        Hub hub = createHub.execute(dados);
        var uri = uriBuilder.path("/Posts/{id}").buildAndExpand(hub.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsHubDTO(hub));
    }
}

