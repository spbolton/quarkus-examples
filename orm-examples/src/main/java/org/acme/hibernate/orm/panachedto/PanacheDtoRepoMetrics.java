package org.acme.hibernate.orm.panachedto;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.hibernate.orm.panachedto.entity.DTOMetricsEntity;

@ApplicationScoped
public class PanacheDtoRepoMetrics implements
        PanacheRepository<DTOMetricsEntity> {

    // Can override or add methods in here
}
