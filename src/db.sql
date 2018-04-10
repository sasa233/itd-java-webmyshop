drop database if exists myshop;
create database myshop default character set utf8;
use myshop;
drop table if exists product;
drop table if exists users;

create table product
(
   id                  int not null auto_increment,
   name                varchar(20),
   price               decimal(8,2),
   pic                 varchar(100),
   remark              longtext,
   date                timestamp default CURRENT_TIMESTAMP,
   primary key (id)
);

create table users
(
   id                  int not null auto_increment,
   name                varchar(20),
   pass 			   varchar(20),
   primary key (id)
);
/* 插入数据 */
insert into users (name,pass) values ('admin', '***123');

/* 商品测试用例 */
insert into product (name,price,remark) values ('圣得西服',3000.00,'这里是简单介绍');
insert into product (name,price,remark) values ('衫衫西服',3000.00,'这里是简单介绍');
insert into product (name,price,remark) values ('Iphone6',6000.00,'这里是简单介绍');

select * from product;  
select * from users ; 
/* (page - 1)*size  */
/* select * from product where name like '%电脑%' limit 1, 2 */