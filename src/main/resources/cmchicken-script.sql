
select * from customer ;

select * from credit_card; 

select * from "order"; 

select * from menu;


drop table credit_card;
drop table "order";
drop table menu;
drop table customer;


-- create schema cmchicken;

 
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
	--is_substitutable   bit default 0
);

create table "order" (
	 id					serial			primary key,
	--id					int	IDENTITY(1,1) primary key,			
	-- IDENTITY only work when you create at DBeaver 
	menu_item			varchar(50)		not null,
	"comment"			text,
	is_favorite			boolean			default false,
	--is_favorite			bit			default 0,
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
('go1',   'fname1', 'lname1', 'password', 0.00, false),
('go2',   'fname2', 'lname2', 'password', 0.00, false),
('go3',   'fname3', 'lname3', 'password', 0.00, false),
('go4',   'fname4', 'lname4', 'password', 0.00, false),
('go5',   'fname5', 'lname5', 'password', 0.00, false),
('go6',   'fname6', 'lname6', 'password', 0.00, false),
('go7',   'fname7', 'lname7', 'password', 0.00, true);


insert into menu
(item_name, "cost", protein, is_substitutable)
values 
('Fried Chicken',			11.99,	'chicken',	false),	
('Garlic Soy Chicken',		13.99,	'chicken',	false),	
('Garlic Spicy Chicken',	13.99,	'chicken',	false),	
('Red Hot Pepper Chicken',	13.99,	'chicken',	false),
('Snow Onion',				15.99,	'chicken',	false),	
('Hot Snow Onion',			15.99,	'chicken',	false),	
('Curry Snow Onion',		15.99,	'chicken',	false),	
('Garlic Soy Tikkudak',		15.99,	'chicken',	false),	
('Red Hot Pepper Tikkudak',	15.99,	'chicken',	false),	
('Curry Tikkudak',			15.99,	'chicken',	false),	
('Soy Garlic Spring Onion', 15.99,	'chicken',	false),
('Lemon Spring Onion',		15.99,	'chicken',	false),
('Bacon Spinach',			10.99,	'chicken',	false),	
('Tikku Soy Sauce Gizzard',	11.99,	'chicken',	false),	
('TtukBoKki',				10.99,	'fishcake',	false),	
('Corn Cheese',				 9.99,	'cheese',	false),	
('French Fries',			 4.99,	'',			false),	
('Coleslaw',				 4.99,	'egg',		false),	
('Fried Calamari',			 9.99,	'calamari',	false),	
('Pop Corn Shimp',			 9.99,	'shrimp',	false),	
('Fried Dumpling',			 5.99,	'pork',		false),	
('Fried Spring roll',		 3.99,	'pork',		false),	
('Tempura Shrimp',			 5.99,	'shrimp',	false),	
('Crab Rangoon',			 3.99,	'shrimp',	false),	
('Cream Cheese Rangoon',	 3.99,	'shrimp',	false),	
('Rice',					 1.00,	'',			false),	
('Chicken Box',			 	 9.99,	'chicken',	true),	
('Soft Drink',			 	 2.99,	'',			true);	




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
(menu_item, "comment", is_favorite, order_date, customer_username )
values
('Red Hot Pepper Chicken',	'comment', true, '05/28/2022', 'go1' ),
('Snow Onion',				'comment', true, '05/28/2022', 'go2' ),
('Hot Snow Onion',			'comment', true, '05/28/2022', 'go3' ),
('Curry Snow Onion',		'comment', true, '05/28/2022', 'go4' ),
('Garlic Soy Tikkudak',		'comment', true, '05/28/2022', 'go5' ),
('Red Hot Pepper Tikkudak', 'comment', true, '05/28/2022', 'go6' ),
('Curry Tikkudak',			'comment', true, '05/28/2022', 'go7' ),
('Soy Garlic Spring Onion',	'comment', true, '05/28/2022', 'go1' ),
('Lemon Spring Onion',		'comment', true, '05/28/2022', 'go2' ),
('Bacon Spinach',			'comment', true, '05/28/2022', 'go3' ),
('Tikku Soy Sauce Gizzard',	'comment', true, '05/28/2022', 'go4' ),
('TtukBoKki',				'comment', true, '05/28/2022', 'go5' ),
( 'Fried Chicken',			'comment', true, '05/28/2022', 'go6' ),
( 'Garlic Soy Chicken',		'comment', true, '05/28/2022', 'go7' ),
( 'Garlic Spicy Chicken',	'comment', true, '05/28/2022', 'go1' ),
( 'Red Hot Pepper Chicken',	'comment', true, '05/28/2022', 'go2' ),
( 'Snow Onion',				'comment', true, '05/28/2022', 'go3' ),
( 'Hot Snow Onion',			'comment', true, '05/28/2022', 'go4' ),
( 'Curry Snow Onion',		'comment', true, '05/28/2022', 'go5' ),
( 'Garlic Soy Tikkudak',	'comment', true, '05/28/2022', 'go6' ),
( 'Red Hot Pepper Tikkudak','comment', true, '05/28/2022', 'go7' ),
( 'Curry Tikkudak',			'comment', true, '05/28/2022', 'go1' ),
( 'Soy Garlic Spring Onion','comment', true, '05/28/2022', 'go2' ),
( 'Lemon Spring Onion',		'comment', true, '05/28/2022', 'go3' ),
( 'Bacon Spinach',	       	'comment', true, '05/28/2022', 'go4' );

/*
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
(menu_item, "comment", is_favorite, order_date, customer_username )
values
('Red Hot Pepper Chicken',	'comment', 1, '05/28/2022', 'go1' ),
('Snow Onion',				'comment', 1, '05/28/2022', 'go2' ),
('Hot Snow Onion',			'comment', 1, '05/28/2022', 'go3' ),
('Curry Snow Onion',		'comment', 1, '05/28/2022', 'go4' ),
('Garlic Soy Tikkudak',		'comment', 1, '05/28/2022', 'go5' ),
('Red Hot Pepper Tikkudak', 'comment', 1, '05/28/2022', 'go6' ),
('Curry Tikkudak',			'comment', 1, '05/28/2022', 'go7' ),
('Soy Garlic Spring Onion',	'comment', 1, '05/28/2022', 'go1' ),
('Lemon Spring Onion',		'comment', 1, '05/28/2022', 'go2' ),
('Bacon Spinach',			'comment', 1, '05/28/2022', 'go3' ),
('Tikku Soy Sauce Gizzard',	'comment', 1, '05/28/2022', 'go4' ),
('TtukBoKki',				'comment', 1, '05/28/2022', 'go5' ),
( 'Fried Chicken',			'comment', 1, '05/28/2022', 'go6' ),
( 'Garlic Soy Chicken',		'comment', 1, '05/28/2022', 'go7' ),
( 'Garlic Spicy Chicken',	'comment', 1, '05/28/2022', 'go1' ),
( 'Red Hot Pepper Chicken',	'comment', 1, '05/28/2022', 'go2' ),
( 'Snow Onion',				'comment', 1, '05/28/2022', 'go3' ),
( 'Hot Snow Onion',			'comment', 1, '05/28/2022', 'go4' ),
( 'Curry Snow Onion',		'comment', 1, '05/28/2022', 'go5' ),
( 'Garlic Soy Tikkudak',	'comment', 1, '05/28/2022', 'go6' ),
( 'Red Hot Pepper Tikkudak','comment', 1, '05/28/2022', 'go7' ),
( 'Curry Tikkudak',			'comment', 1, '05/28/2022', 'go1' ),
( 'Soy Garlic Spring Onion','comment', 1, '05/28/2022', 'go2' ),
( 'Lemon Spring Onion',		'comment', 1, '05/28/2022', 'go3' ),
( 'Bacon Spinach',	       	'comment', 1, '05/28/2022', 'go4' );
*/