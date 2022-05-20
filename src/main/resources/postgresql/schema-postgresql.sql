CREATE SCHEMA ruleta_schema;

create table ruleta_schema.ruletas
(
    id_ruleta          bigint    not null
        primary key,
    esta_abierta       boolean   not null,
    fecha_alta         timestamp not null,
    fecha_modificacion timestamp
);

alter table ruleta_schema.ruletas
    owner to postgres;

