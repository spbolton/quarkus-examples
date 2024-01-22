package org.acme.hibernate.orm.panache;

import com.fasterxml.jackson.databind.JsonNode;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;
import org.acme.hibernate.orm.converter.JsonAttributeConverter;

@Entity
@Cacheable
public class MetricsRecored extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    public Long id;

    @Column(nullable = false)
    public String clientEnv;

    @Column(nullable = false)
    public String serverId;

    @Column(nullable = false,columnDefinition = "integer default 0")
    public int schemaVersion;


    @Convert(converter = JsonAttributeConverter.class)
    @Column(nullable = false, columnDefinition = "jsonb")
    public JsonNode data;

    @Column(nullable = false,columnDefinition = "timestamptz")
    public Instant modDate;

    // This is not used to create the instance in the resource class from the rest json
    // It creates using the no arg constructor then sets the available properties
    public MetricsRecored(String clientEnv, String serverId, int schemaVersion, JsonNode data,
            Instant modDate) {
        this.clientEnv = clientEnv;
        this.serverId = serverId;
        this.schemaVersion = schemaVersion;
        this.data = data;
        this.modDate = modDate;
    }

    public MetricsRecored() {

    }
}
