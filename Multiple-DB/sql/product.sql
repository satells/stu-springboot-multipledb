drop table if exists product;


create table product(
	id serial not null primary key,
	name varchar(20),
	description varchar(100),
	price decimal(8,3)

)