package org.acme.crud.hibernate.examples.panachedto;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MetricsService {

    @Inject
    MetricsEntityMapper metricsEntityMapper;
    @Inject
    PanacheDtoRepoMetrics repo;


    public Uni<MetricsDto> create(MetricsDto metrics) {
        return Panache.withTransaction(() -> repo.persist(metricsEntityMapper.toEntity(metrics)).map(metricsEntityMapper::fromEntity));
    }


}
