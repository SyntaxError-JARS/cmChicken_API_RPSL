-----------------------------------------
-------------C(reate)RUD-----------------

create schema cmchicken;




create table customer (
	username 	varchar(25) 	primary key,
	fname 		varchar(20) 	not null,
	lname		varchar(20) 	not null,
	"password"	varchar(32) 	not null,
	balance		numeric(12,2) 	not null,
	-- is_admin	boolean 		default false
	is_admin bit default 0
);


create table menu (
	item_name			varchar(50) 	primary key,
	"cost"				numeric(12,2)	not null,
	protein				varchar(20)		not null,
	is_substitutable 	boolean 		default false
	-- is_substitutable   bit default 0
);

create table "order" (
	-- id					serial			primary key,
	id					int	IDENTITY(1,1) primary key,
	-- IDENTITY only work when you create at DBeaver
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
--ON DELETE CASCADE
;

alter table "order"
add constraint fk_menu
foreign key(menu_item) references menu(item_name)
--ON DELETE CASCADE
;



insert into customer
(username, fname, lname, "password", balance, is_admin)
values
('go1',   'fname1', 'lname1', 'password', 0.00, 0),
('go2',   'fname2', 'lname2', 'password', 0.00, 0),
('go3',   'fname3', 'lname3', 'password', 0.00, 0),
('go4',   'fname4', 'lname4', 'password', 0.00, 0),
('go5',   'fname5', 'lname5', 'password', 0.00, 0),
('go6',   'fname6', 'lname6', 'password', 0.00, 0),
('go7',   'fname7', 'lname7', 'password', 0.00, 1);


insert into menu
(item_name, "cost", protein, is_substitutable)
values
('Fried Chicken',			11.99,	'chicken',	0),
('Garlic Soy Chicken',		13.99,	'chicken',	0),
('Garlic Spicy Chicken',	13.99,	'chicken',	0),
('Red Hot Pepper Chicken',	13.99,	'chicken',	0),
('Snow Onion',				15.99,	'chicken',	0),
('Hot Snow Onion',			15.99,	'chicken',	0),
('Curry Snow Onion',		15.99,	'chicken',	0),
('Garlic Soy Tikkudak',		15.99,	'chicken',	0),
('Red Hot Pepper Tikkudak',	15.99,	'chicken',	0),
('Curry Tikkudak',			15.99,	'chicken',	0),
('Soy Garlic Spring Onion', 15.99,	'chicken',	0),
('Lemon Spring Onion',		15.99,	'chicken',	0),
('Bacon Spinach',			10.99,	'chicken',	0),
('Tikku Soy Sauce Gizzard',	11.99,	'chicken',	0),
('TtukBoKki',				10.99,	'fishcake',	0),
('Corn Cheese',				 9.99,	'cheese',	0),
('French Fries',			 4.99,	'',			0),
('Coleslaw',				 4.99,	'egg',		0),
('Fried Calamari',			 9.99,	'calamari',	0),
('Pop Corn Shimp',			 9.99,	'shrimp',	0),
('Fried Dumpling',			 5.99,	'pork',		0),
('Fried Spring roll',		 3.99,	'pork',		0),
('Tempura Shrimp',			 5.99,	'shrimp',	0),
('Crab Rangoon',			 3.99,	'shrimp',	0),
('Cream Cheese Rangoon',	 3.99,	'shrimp',	0),
('Rice',					 1.00,	'',			0),
('Chicken Box',			 	 9.99,	'chicken',	1),
('Soft Drink',			 	 2.99,	'',			1);

insert into credit_card
(cc_number, cc_name, cvv, exp_date, zip, "limit", customer_username )
values
('110022220001','visa', 	789, '01/20/2030', 66223, 3000.00,  'go1' ),
('220022220001','visa', 	789, '01/20/2030', 66223, 3000.00,  'go2' ),
('330022220001','master', 	789, '01/20/2030', 66223, 3000.00,  'go3' ),
('440022220001','Amex', 	789, '01/20/2030', 66223, 3000.00,  'go4' ),
('110022220002','visa', 	789, '01/20/2030', 66223, 3000.00,  'go1' ),
('220022220002','visa', 	789, '01/20/2030', 66223, 3000.00,  'go2' ),
('330022220002','master', 	789, '01/20/2030', 66223, 3000.00,  'go3' ),
('440022220002','Amex', 	789, '01/20/2030', 66223, 3000.00,  'go4' ),
('550022220001','visa', 	789, '01/20/2030', 66223, 3000.00,  'go5' ),
('660022220001','visa', 	789, '01/20/2030', 66223, 3000.00,  'go6' ),
('770022220001','master', 	789, '01/20/2030', 66223, 3000.00,  'go7' );


insert into "order"
(id, menu_item, "comment", is_favorite, order_date, customer_username )
values
(1,		'Red Hot Pepper Chicken',	'comment', 1, '05/28/2022', 'go1' ),
(2,		'Snow Onion',				'comment', 1, '05/28/2022', 'go2' ),
(3,		'Hot Snow Onion',			'comment', 1, '05/28/2022', 'go3' ),
(4,		'Curry Snow Onion',			'comment', 1, '05/28/2022', 'go4' ),
(5,		'Garlic Soy Tikkudak',		'comment', 1, '05/28/2022', 'go5' ),
(6,		'Red Hot Pepper Tikkudak', 	'comment', 1, '05/28/2022', 'go6' ),
(7,		'Curry Tikkudak',			'comment', 1, '05/28/2022', 'go7' ),
(8,		'Soy Garlic Spring Onion',	'comment', 1, '05/28/2022', 'go1' ),
(9,		'Lemon Spring Onion',		'comment', 1, '05/28/2022', 'go2' ),
(10,	'Bacon Spinach',			'comment', 1, '05/28/2022', 'go3' ),
(11, 	'Tikku Soy Sauce Gizzard',	'comment', 1, '05/28/2022', 'go4' ),
(12, 	'TtukBoKki',				'comment', 1, '05/28/2022', 'go5' ),
(13,    'Fried Chicken',			'comment', 1, '05/28/2022', 'go6' ),
(14,    'Garlic Soy Chicken',		'comment', 1, '05/28/2022', 'go7' ),
(15,    'Garlic Spicy Chicken',	   	'comment', 1, '05/28/2022', 'go1' ),
(16,    'Red Hot Pepper Chicken',	'comment', 1, '05/28/2022', 'go2' ),
(17,    'Snow Onion',				'comment', 1, '05/28/2022', 'go3' ),
(18,    'Hot Snow Onion',			'comment', 1, '05/28/2022', 'go4' ),
(19,    'Curry Snow Onion',		   	'comment', 1, '05/28/2022', 'go5' ),
(20,    'Garlic Soy Tikkudak',		'comment', 1, '05/28/2022', 'go6' ),
(21,    'Red Hot Pepper Tikkudak',	'comment', 1, '05/28/2022', 'go7' ),
(22,    'Curry Tikkudak',			'comment', 1, '05/28/2022', 'go1' ),
(23,    'Soy Garlic Spring Onion', 	'comment', 1, '05/28/2022', 'go2' ),
(24,    'Lemon Spring Onion',		'comment', 1, '05/28/2022', 'go3' ),
(25,    'Bacon Spinach',	       	'comment', 1, '05/28/2022', 'go4' );

-----------------------------------------
-------------CRUD(elete)-----------------

drop table customer;
drop table credit_card;
drop table "order";
drop table menu;


drop schema cmchicken;

