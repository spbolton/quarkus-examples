package org.acme.crud.hibernate.examples.panachedto;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.crud.hibernate.examples.panachedto.entity.DTOMetricsEntity;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MetricsEntityMapper {
    private static final Logger LOGGER = Logger.getLogger(MetricsEntityMapper.class.getName());
    public DTOMetricsEntity toEntity(MetricsDto metricsDto) {
        return new DTOMetricsEntity(metricsDto.clientEnv(),
                metricsDto.serverId(), metricsDto.schemaVersion(), metricsDto.data(),
                metricsDto.modDate());
    }

    public MetricsDto fromEntity(DTOMetricsEntity metricsEntity) {
        return MetricsDto.builder().id(metricsEntity.id)
                .clientEnv(metricsEntity.clientEnv)
                .serverId(metricsEntity.serverId)
                .schemaVersion(metricsEntity.schemaVersion)
                .data(metricsEntity.data)
                .modDate(metricsEntity.modDate).build();
    }
}
