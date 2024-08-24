create database user_db;

use user_db;

create table user_form(
    name varchar(250) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    userType varchar(255) not null,
    primary key (email)
);

create table user_info(
    name varchar(250),
    email varchar(255),
    firstName varchar(250) ,
    lastName varchar(250),
    phone varchar(50),
    address varchar(250)
);

create table task(
    id int not null auto_increment,
    userEmail varchar(255) not null,
    name varchar(250) not null,
    description varchar(250),
    createdDate timestamp not null,
    startDate timestamp not null,
    endDate timestamp not null,
    completed bool not null,
    primary key (id),
    foreign key (userEmail) references user_form(email)
);
