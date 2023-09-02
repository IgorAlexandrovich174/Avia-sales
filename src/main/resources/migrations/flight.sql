create table flights
(
    id                serial
        constraint flights_id_pk
            primary key,
    departure_airport text,
    arrival_airport   text,
    date              date,
    aircraft_id       integer not null
        constraint flights_aircrafts_id_fk
            references aircrafts
);
