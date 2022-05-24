create schema cmchicken

create table customer(
"username" varchar(25) primary key,
fname varchar(20) not null,
lname varchar(20) not null,
"password" varchar(32) not null,
balance numeric(12,2) not null,
is_admin boolean default false
);

create table menu(
item_name varchar(50) primary key,
"cost" numeric (12,2) not null,
protein varchar(20) not null,
is_substitutable boolean default false
);

create table "order"(
id serial primary key,
menu_item varchar(50) not null,
"comment" text,
is_favorite boolean default false,
"order_date" varchar(15) ,
customer_username varchar(25) not null
);




