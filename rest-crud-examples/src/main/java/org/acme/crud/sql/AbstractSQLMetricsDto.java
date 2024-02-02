package org.acme.crud.sql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.Instant;
import java.util.Optional;
import org.immutables.value.Value;
import org.immutables.value.Value.Default;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value.Immutable
@Value.Style(
        deepImmutablesDetection = true, depluralize = true,
        typeAbstract = "Abstract*",
        typeImmutable = "*",
        visibility = Value.Style.ImplementationVisibility.PUBLIC)
@JsonSerialize
@JsonDeserialize(builder = SQLMetricsDto.Builder.class)
public interface AbstractSQLMetricsDto {

    Optional<Long> id();

    String clientEnv();

    String serverId();

    int schemaVersion();

    JsonNode data();

    @Default
    default Instant modDate() {
        return Instant.now();
    }

}
