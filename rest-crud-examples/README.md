


```

curl -X 'POST' \
  'http://localhost:8080/pmetrics' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "clientEnv": "client-env-1",
  "serverId": "server-id-1",
  "schemaVersion": 0,
  "data": {
    "client_env": "dotcmscorp_prod",
    "schema_version": 0,
    "server_id": "dotcmscorp_prod-1",
    "mod_date": "2024-01-01 21:56:43+00",
    "workflow_actions": 9,
    "content_types_assigned_schemes": 0,
    "workflow_schemes": 1,
    "is_default_language_not_english": false
  }
}'

```

- [Swagger UI http://localhost:8080/q/swagger-ui/](http://localhost:8080/q/swagger-ui/)
- [Quarkus Dev UI - http://localhost:8080/q/dev-ui/](http://localhost:8080/q/dev-ui/)
# Related Links

- [Quarkus](https://quarkus.io/)
- [Quarkus - Hibernate Reactive](https://quarkus.io/guides/hibernate-reactive)
- [Quarkus - Hibernate Reactive - Panache](https://quarkus.io/guides/hibernate-reactive-panache)
- [Quarkus - Flyway ](https://quarkus.io/guides/flyway)
- [Quarkus - RESTEasy Reactive](https://quarkus.io/guides/resteasy-reactive)
- [Quarkus - RESTEasy Reactive - OpenAPI](https://quarkus.io/guides/resteasy-reactive-openapi)
- [Smallrye Mutiny](https://smallrye.io/smallrye-mutiny/latest/)
- [Quarkus - Using OpenAPI and SwaggerUI](https://quarkus.io/guides/openapi-swaggerui)
- [Quarkus - Database Dev Services](https://quarkus.io/guides/databases-dev-services)
- [Quarkus - Context and Dependency Injection CDI ](https://quarkus.io/guides/cdi-reference) 

# Original Documentation


# Quarkus demo: Hibernate Reactive with Panache and RESTEasy Reactive

This is a minimal CRUD service exposing a couple of endpoints over REST,
with a front-end based on Angular so you can play with it from your browser.

While the code is surprisingly simple, under the hood this is using:
 - RESTEasy Reactive to expose the REST endpoints
 - Hibernate Reactive with Panache to perform the CRUD operations on the database
 - A PostgreSQL database; see below to run one via Docker
 - ArC, the CDI inspired dependency injection tool with zero overhead

## Requirements

To compile and run this demo you will need:

- JDK 11+
- GraalVM

In addition, you will need either a PostgreSQL database, or Docker to run one.

### Configuring GraalVM and JDK 11+

Make sure that both the `GRAALVM_HOME` and `JAVA_HOME` environment variables have
been set, and that a JDK 11+ `java` command is on the path.

See the [Building a Native Executable guide](https://quarkus.io/guides/building-native-image)
for help setting up your environment.

## Building the demo

Launch the Maven build on the checked out sources of this demo:

> ./mvnw package

## Running the demo

### Prepare a PostgreSQL instance

Make sure you have a PostgreSQL instance running. To set up a PostgreSQL database with Docker:

> docker run -it --rm=true --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:13.3

Connection properties for the Agroal datasource are defined in the standard Quarkus configuration file,
`src/main/resources/application.properties`.

### Live coding with Quarkus

The Maven Quarkus plugin provides a development mode that supports
live coding. To try this out:

> ./mvnw quarkus:dev

In this mode you can make changes to the code and have the changes immediately applied, by just refreshing your browser.

    Hot reload works even when modifying your JPA entities.
    Try it! Even the database schema will be updated on the fly.

### Run Quarkus in JVM mode

When you're done iterating in developer mode, you can run the application as a
conventional jar file.

First compile it:

> ./mvnw package

Then run it:

> java -jar ./target/quarkus-app/quarkus-run.jar

    Have a look at how fast it boots.
    Or measure total native memory consumption...

### Run Quarkus as a native application

You can also create a native executable from this application without making any
source code changes. A native executable removes the dependency on the JVM:
everything needed to run the application on the target platform is included in
the executable, allowing the application to run with minimal resource overhead.

Compiling a native executable takes a bit longer, as GraalVM performs additional
steps to remove unnecessary codepaths. Use the  `native` profile to compile a
native executable:

> ./mvnw package -Dnative

After getting a cup of coffee, you'll be able to run this binary directly:

> ./target/hibernate-reactive-panache-quickstart-1.0.0-SNAPSHOT-runner

    Please brace yourself: don't choke on that fresh cup of coffee you just got.
    
    Now observe the time it took to boot, and remember: that time was mostly spent to generate the tables in your database and import the initial data.
    
    Next, maybe you're ready to measure how much memory this service is consuming.

N.B. This implies all dependencies have been compiled to native;
that's a whole lot of stuff: from the bytecode enhancements that Panache
applies to your entities, to the lower level essential components such as the PostgreSQL JDBC driver, the Undertow webserver.

## See the demo in your browser

Navigate to:

<http://localhost:8080/index.html>

Have fun, and join the team of contributors!

## Running the demo in Kubernetes

This section provides extra information for running both the database and the demo on Kubernetes.
As well as running the DB on Kubernetes, a service needs to be exposed for the demo to connect to the DB.

Then, rebuild demo docker image with a system property that points to the DB. 

```bash
-Dquarkus.datasource.reactive.url=vertx-reactive:postgresql://<DB_SERVICE_NAME>/quarkus_test
```
