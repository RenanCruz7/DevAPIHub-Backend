package backend.DevAPIHub.domain.hub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HubService {
    @Autowired
    private HubRepository hubRepository;

    public Hub execute(CreateHubDTO dados) {
        var hub = new Hub(dados);
        return hubRepository.save(hub);
    }

    public List<Hub> getAll() {
        return hubRepository.findAll();
    }
}
