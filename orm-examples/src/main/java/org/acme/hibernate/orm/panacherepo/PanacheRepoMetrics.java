package org.acme.hibernate.orm.panacherepo;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheRepoMetrics implements
        PanacheRepository<MetricsEntity> {

    // Can override or add methods in here
}
