DROP TABLE IF EXISTS direction;
DROP TABLE IF EXISTS price;

CREATE TABLE price
(
    id serial NOT NULL UNIQUE ,
    description varchar(50) not null ,
    price real NOT NULL default 0.0
);

CREATE TABLE direction
(
    id serial NOT NULL ,
    route_pattern varchar(20) NOT NULL ,
    route_description varchar(50) NOT NULL ,
    price_id integer NOT NULL ,
    foreign key (price_id) references price (id) on delete cascade
)
