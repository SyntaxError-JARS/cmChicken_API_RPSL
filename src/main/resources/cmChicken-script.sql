-----------------------------------------
-------------C(reate)RUD-----------------

create table customer (
	username 	varchar(25) 	primary key,
	fname 		varchar(20) 	not null,
	lname		varchar(20) 	not null,
	"password"	varchar(32) 	not null,
	balance		numeric(12,2) 	not null,
	is_admin	boolean 		default false
	-- is_admin bit default 0
);


create table menu (
	item_name			varchar(50) 	primary key,
	"cost"				numeric(12,2)	not null,
	protein				varchar(20)		not null,
	is_substitutable 	boolean 		default false
	-- is_substitutable   bit default 0
);

create table "order" (
	id					serial			primary key,
	menu_item			varchar(50)		not null,
	"comment"			text,
	is_favorite			boolean			default false,
	-- is_favorite			bit			default 0,
	order_date			varchar(15)		not null,           -- defalut current date
	customer_username	varchar(25)
);

create table credit_card (
	cc_number			varchar(16)		primary key,
	cc_name			    varchar(20)		not null,
	cvv					int 			not null,
	exp_date			varchar(15)		not null,
	zip					int 			not null,
	"limit"			    numeric(15,2) 	not null,
	customer_username	varchar(25)		not null
);




-----------------------------------------
-------------CR(ead)UD-----------------

select * from customer ;

select * from credit_card;

select * from "order";

select * from menu;





-----------------------------------------
-------------CRU(pdate)D-----------------

alter table credit_card
add constraint fk_cm_customer
foreign key(customer_username) references customer(username)
--ON DELETE CASCADE
;


alter table "order"
add constraint fk_customer
foreign key(customer_username) references customer(username)
;

alter table "order"
add constraint fk_menu
foreign key(menu_item) references menu(item_name)
;



