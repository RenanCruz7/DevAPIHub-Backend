package backend.DevAPIHub.domain.hub;

public record DetailsHubDTO(
        Long id,
        String apiName,
        String apiDescription,
        boolean authRequired,
        String apiLink,
        Category apiCategory
) {
    public DetailsHubDTO(Hub hub) {
        this(hub.getId(), hub.getApiName(), hub.getApiDescription(), hub.isAuthRequired(), hub.getApiLink(), hub.getApiCategory());
    }
}
