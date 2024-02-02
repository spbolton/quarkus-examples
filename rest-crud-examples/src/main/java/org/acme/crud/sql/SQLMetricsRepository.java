package org.acme.crud.sql;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import java.time.ZoneId;


@ApplicationScoped
public class SQLMetricsRepository {

    @Inject
    io.vertx.mutiny.pgclient.PgPool client;

    public Uni<Long> create(SQLMetricsDto metrics) {
        // Changes to Schema will require updating in here, and equivalent updates in Metrics object
        // schema will require manual modification with every change
        return client.preparedQuery("insert \n"
                + "    into\n"
                + "        metrics_sql\n"
                + "        (id,client_env,server_id,schema_version,data,mod_date) \n"
                + "    values\n"
                + "        (nextval('metrics_sql_seq'),$1,$2,$3,$4,$5 ) RETURNING id").execute(mapToArgTupple(metrics))
                .onItem().transform(pgRowSet -> pgRowSet.iterator().next().getLong("id"));
    }

    // Option to create a mapper service
    private Tuple mapToArgTupple(SQLMetricsDto metrics) {
        // Need to map types like the Instant to OffsetDateTime for backing timestamp with time (timestampz)
        return Tuple.of(metrics.clientEnv(),metrics.serverId(),metrics.schemaVersion(),metrics.data().toString(),
                OffsetDateTime.ofInstant(metrics.modDate(), ZoneId.of("Z")));
    }
}
