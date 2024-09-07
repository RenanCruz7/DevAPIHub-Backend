package backend.DevAPIHub.domain.hub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateHub {
    @Autowired
    private HubRepository hubRepository;

    public Hub execute(CreateHubDTO dados) {
        var hub = new Hub(dados);
        return hubRepository.save(hub);
    }
}
