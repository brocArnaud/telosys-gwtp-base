--Postgresql 9.6.1

CREATE USER test WITH SUPERUSER PASSWORD 'test';
create database test owner test;

insert into team (id,name) values (1,'C2K');
insert into team (id,name) values (2,'4F');

insert into player (id,name,team) values (1,'mako',1);
insert into player (id,name,team) values (2,'kiar',1);
insert into player (id,name,team) values (3,'momo',1);
insert into player (id,name,team) values (4,'boba',2);
insert into player (id,name,team) values (5,'rourouh',2);
insert into player (id,name,team) values (6,'garoump',2);

select setval('hibernate_sequence', 10, true);