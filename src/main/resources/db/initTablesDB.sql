DROP TABLE IF EXISTS direction;
DROP TABLE IF EXISTS price;

CREATE TABLE price
(
    id serial NOT NULL UNIQUE ,
    description varchar(255) not null ,
    price real NOT NULL default 0.0,
    zone_number integer
);

CREATE TABLE direction
(
    id serial NOT NULL UNIQUE ,
    route_pattern varchar(255) NOT NULL UNIQUE ,
    route_description varchar(255) NOT NULL ,
    price_id integer NOT NULL ,
    zone_number integer,
    foreign key (price_id) references price (id) on delete cascade
)
