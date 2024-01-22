create table metrics_sql
(
    schema_version integer default 0,
    id             bigint not null,
    client_env     varchar(255) not null,
    server_id      varchar(255) not null,
    data           jsonb,
    mod_date       timestamptz,
    primary key (id)
);

create sequence metrics_sql_seq start with 1 increment by 50;
