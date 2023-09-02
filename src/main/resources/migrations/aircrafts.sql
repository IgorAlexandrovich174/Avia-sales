
create table aircrafts
(
    model       text              not null
        constraint aircrafts_pk
            unique,
    seats_count integer default 0 not null,
    id          serial
        constraint aircrafts_pk2
            primary key
);


