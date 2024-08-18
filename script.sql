create database user_db;

use user_db;

create table user_form(
    name varchar(250),
    email varchar(255),
    password varchar(255),
    userType varchar(255)
);

create table user_info(
    name varchar(250),
    email varchar(255),
    firstName varchar(250),
    lastName varchar(250),
    phone varchar(50),
    address varchar(250)
);
