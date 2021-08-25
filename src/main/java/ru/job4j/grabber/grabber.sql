create table post(
     id serial primary key,
     name text ,
     text text ,
     link text unique ,
     created timestamp
);

select *from post;

drop table post;