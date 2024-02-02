package org.acme.crud.hibernate.examples.panachedto;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.crud.hibernate.examples.panachedto.entity.DTOMetricsEntity;

@ApplicationScoped
public class PanacheDtoRepoMetrics implements
        PanacheRepository<DTOMetricsEntity> {

    // Can override or add methods in here
}
