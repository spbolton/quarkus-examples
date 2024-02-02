package org.acme.crud.hibernate.examples.panache;

import static jakarta.ws.rs.core.Response.Status.CREATED;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.CompositeException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.time.Instant;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

@Path("pmetrics")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class MetricsRecordResource {

    private static final String EXAMPLE_ONE = "{\n"
            + "\"clientEnv\": \"client-env-1\",\n"
            + "\"serverId\": \"server-id-1\",\n"
            + "\"schemaVersion\": 0,\n"
            + "\"data\": {\n"
            + "    \"client_env\": \"dotcmscorp_prod\",\n"
            + "    \"schema_version\": 0,\n"
            + "    \"server_id\": \"dotcmscorp_prod-1\",\n"
            + "    \"mod_date\": \"2024-01-01 21:56:43+00\",\n"
            + "    \"workflow_actions\": 9,\n"
            + "    \"content_types_assigned_schemes\": 0,\n"
            + "    \"workflow_schemes\": 1,\n"
            + "    \"is_default_language_not_english\": false\n"
            + " }\n"
            + "}";

    private static final Logger LOGGER = Logger.getLogger(MetricsRecordResource.class.getName());

    @POST
    public Uni<Response> create(@RequestBody(
            content = @Content(examples = {
                    @ExampleObject(name = "one", value = EXAMPLE_ONE)
            }
            ))
        MetricsRecored metrics) {
        if (metrics == null || metrics.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }
        metrics.modDate = Instant.now();

        // Metrics class is self contained with db operations e.g. metrics.persist
        return Panache.withTransaction(metrics::persist)
                .replaceWith(Response.ok(metrics).status(CREATED)::build);
    }


    /**
     * Create a HTTP response from an exception.
     *
     * Response Example:
     *
     * <pre>
     * HTTP/1.1 422 Unprocessable Entity
     * Content-Length: 111
     * Content-Type: application/json
     *
     * {
     *     "code": 422,
     *     "error": "Fruit name was not set on request.",
     *     "exceptionType": "jakarta.ws.rs.WebApplicationException"
     * }
     * </pre>
     */
    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            Throwable throwable = exception;

            int code = 500;
            if (throwable instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            // This is a Mutiny exception and it happens, for example, when we try to insert a new
            // fruit but the name is already in the database
            if (throwable instanceof CompositeException) {
                throwable = ((CompositeException) throwable).getCause();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", throwable.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", throwable.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }
}
