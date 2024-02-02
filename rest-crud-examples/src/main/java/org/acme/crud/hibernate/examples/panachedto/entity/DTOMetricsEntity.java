package org.acme.crud.hibernate.examples.panachedto.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;
import org.acme.crud.hibernate.converter.JsonAttributeConverter;

@Entity
@Cacheable
public class DTOMetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    public Long id;

    // As we have mapped to use the org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy
    // in the configuration we do not need to specify this name.
    @Column(nullable = false)
    public String clientEnv;

    @Column(nullable = false)
    public String serverId;

    @Column(nullable = false, columnDefinition = "integer default 0")
    public int schemaVersion;


    @Convert(converter = JsonAttributeConverter.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    public JsonNode data;

    @Column(columnDefinition = "timestamptz", nullable = false)
    public Instant modDate;

    public DTOMetricsEntity(String clientEnv, String serverId, int schemaVersion, JsonNode data,
            Instant modDate) {
        this.clientEnv = clientEnv;
        this.serverId = serverId;
        this.schemaVersion = schemaVersion;
        this.data = data;
        this.modDate = modDate;
    }

    public DTOMetricsEntity() {

    }

    @Override
    public String toString() {
        return "DTOMetricsEntity{" +
                "id=" + id +
                ", clientEnv='" + clientEnv + '\'' +
                ", serverId='" + serverId + '\'' +
                ", schemaVersion=" + schemaVersion +
                ", data=" + data +
                ", modDate=" + modDate +
                '}';
    }



}
