create table tickets
(
    id                   char(13) not null
        constraint tickets_pk
            primary key,
    seat                 varchar(4),
    passengers_last_name text,
    flight_id            integer
        constraint tickets_flights_id_fk
            references flights,
    constraint tickets_pk2
        unique (flight_id, seat)
);