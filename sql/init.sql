create table bill
(
	id int auto_increment
		primary key,
	name varchar(50) not null comment '事项',
	type int not null comment '种类	',
	money float not null comment '金额',
	balance int default '0' not null,
	member int not null comment '经手成员
	',
	recordtime datetime not null comment '经手时间',
	comment varchar(200) null comment '事项说明'
)
comment '账单' engine=InnoDB
;

create table billtag
(
	id int auto_increment
		primary key,
	name varchar(50) not null,
	comment varchar(200) null,
	constraint billtag_name_uindex
		unique (name)
)
comment '标签' engine=InnoDB
;

create table billtype
(
	id int auto_increment
		primary key,
	name varchar(50) not null,
	comment varchar(200) null,
	constraint billtype_name_uindex
		unique (name)
)
comment '账单类型' engine=InnoDB
;

create table billwithtags
(
	bill int not null,
	tag int not null
)
engine=InnoDB
;

create table familymember
(
	id int auto_increment
		primary key,
	account varchar(50) not null comment '帐号',
	name varchar(50) not null comment '姓名',
	password varchar(100) not null comment '密码',
	mobile varchar(20) null comment '手机号码',
	email varchar(100) null comment '邮箱',
	qq varchar(20) null comment 'QQ号码',
	creator varchar(50) default 'washmore' null,
	creatAt datetime null,
	updater varchar(50) default 'washmore' null,
	updateAt datetime null,
	image varchar(200) default '@/assets/touxiang.png' null,
	openId varchar(100) null,
	constraint FamilyMember_account_uindex
	unique (account),
	constraint familymember_openId_uindex
	unique (openId)
)
	comment '家庭成员' engine=InnoDB
;



INSERT INTO family.billtype (id, name, comment) VALUES (1, '衣', NULL);
INSERT INTO family.billtype (id, name, comment) VALUES (2, '食', NULL);
INSERT INTO family.billtype (id, name, comment) VALUES (3, '住', NULL);
INSERT INTO family.billtype (id, name, comment) VALUES (4, '行', NULL);

INSERT INTO family.familymember (id, account, name, password, mobile, email, qq, creator, creatAt, updater, updateAt, image)
VALUES (1, 'admin', '密码123456', '*6BB4837EB74329105EE4568DDA7DC67ED2CA2AD9', NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'https://avatars2.githubusercontent.com/u/13030698?s=460&v=4');
