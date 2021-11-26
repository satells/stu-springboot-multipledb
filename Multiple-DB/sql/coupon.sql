drop table if exists coupon;

create table coupon(
	id 		serial not null primary key,
	code 		varchar(20) unique,
	discount 	decimal(8,3),
	ext_date 	varchar(100)
	
	
)