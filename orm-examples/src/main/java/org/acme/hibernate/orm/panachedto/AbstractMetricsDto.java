package org.acme.hibernate.orm.panachedto;

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
// Style can be added at the package level
// This form allows the Implementation object to match the naming in json
@Value.Style(
        deepImmutablesDetection = true, depluralize = true,
        typeAbstract = "Abstract*",
        typeImmutable = "*",
        visibility = Value.Style.ImplementationVisibility.PUBLIC)
@JsonSerialize
@JsonDeserialize(builder = MetricsDto.Builder.class)
/* Through use of the mapping between DTO and the Entity in the service we have more control
over what data we expose to the front end clients
 */
public interface AbstractMetricsDto {

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
