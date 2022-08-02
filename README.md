# spring-study
Spring study

## DB : MySQL

## Tables
### tb article 
```mysql
create table tb_article (
	article_no int primary key not null auto_increment,
    title varchar(200) not null,
    content text,
    writer varchar(50) not null,
	regdate timestamp not null default now(),
	viewcnt int default 0
  writerImg varchar(100) default 'user/default-user.png';
  file_cnt int default 0
);
```
### tb reply
```mysql
create table tb_reply (
	reply_no int not null auto_increment,
    article_no int not null default 0,
    reply_text varchar(1000) not null,
    reply_writer varchar(50) not null,
    user_img varchar(100) not null default 'user/default-user.png',
    reg_date timestamp not null default now(),
    update_date timestamp not null default now(),
    primary key (reply_no)
);
```
```mysql
alter table tb_reply add constraint fk_article
foreign key (article_no) references tb_article (article_no);
```
### tb user
```mysql
CREATE TABLE tb_user (
	user_id varchar(50) not null,
    user_pw varchar(100) not null,
    user_name varchar(100) not null,
    user_email varchar(50) not null,
    user_point int not null default 0,
    session_key varchar(50) not null default 'none',
    session_limit timestamp,
    user_img varchar(100) not null default 'user/default-user.png',
    user_join_date timestamp not null default now(),
    user_login_date timestamp not null default now(),
    user_signature varchar(200) not null default 'Hello',
    primary key (user_id)
);
```
### tb article file
```mysql
CREATE TABLE tb_article_file (
	fullname varchar(150) not null,
    article_no int not null,
    reg_date timestamp default now(),
    primary key (fullname)
);
```
```mysql
alter table tb_article_file add constraint fk_article_file
foreign key(article_no) references tb_article(article_no);
```
