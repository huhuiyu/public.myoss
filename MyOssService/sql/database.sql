use information_schema;
drop database if exists MyOssDB;
create database MyOssDB default charset utf8 collate utf8_general_ci;
use MyOssDB;

create table TbConfig
(
  configKey varchar(50) primary key comment '配置键值，主键',
  configValue varchar(2000) comment '配置值',
  lastupdate timestamp comment '最后更新时间'
)comment '系统配置表';

create table TbToken
(
  token varchar(50) primary key comment '令牌值，自然主键',
  lastupdate timestamp comment '令牌环最后更新时间'
)comment 'token追踪表';

create table TbTokenInfo
(
  token varchar(50) comment '令牌',
  infoKey varchar(50) comment '令牌信息key值',
  info varchar(2000) comment '令牌信息值',
  lastupdate timestamp comment '令牌信息最后更新时间',
  constraint pkTbTokenInfo primary key(token,infoKey)
)comment 'token附加信息表';

create table TbAdmin
(
  aid int auto_increment primary key not null comment '主键',
  username varchar(20) unique not null comment '用户名',
  password varchar(50) not null comment '密码',
  pwdsalt varchar(10) not null comment '密码盐',
  enable enum('y','n') default 'y' not null comment '是否启用',
  lastupdate timestamp not null on update now() default now() comment '最后更新时间'
)comment '管理员用户表';

create table TbOssConfig
(
  ocid varchar(100) primary key not null comment '主键',
  endpoint varchar(255) not null comment 'endpoint',
  accessKeyId varchar(255) not null comment 'accessKeyId',
  accessKeySecret varchar(255) not null comment 'accessKeySecret',
  bucketName varchar(255) not null comment 'bucketName',
  expiration bigint not null comment '链接过期时间',
  enable enum('y','n') default 'y' not null comment '是否启用',
  lastupdate timestamp not null on update now() default now() comment '最后更新时间'
)comment 'oss配置信息';

create table TbAdminOssConfig
(
  aid int not null comment '外键，管理员id（TbAdmin.aid）',
  ocid varchar(100) not null comment '外键，oss配置id（TbOssConfig.ocid）',
  lastupdate timestamp not null on update now() default now() comment '最后更新时间',
  constraint pkTbAdminOssConfig primary key(aid,ocid)
)comment 'admin关联oss配置信息';

create table TbOssInfo
(
  oiid int auto_increment primary key not null comment '主键',
  ocid varchar(100) not null comment '外键,配置信息',
  objectName varchar(255) not null comment '文件对象名称',
  filename varchar(255) not null comment '原始文件名称',
  contentType varchar(255) not null comment '文件类型',
  filesize bigint not null comment '文件大小',
  fileinfo varchar(200) not null comment '文件描述',
  lastupdate timestamp on update now() default now() not null comment '最后更新时间'
)comment 'oss文件信息';

/* 系统配置数据 */
/* token过期时间配置，值是分钟数 */
insert into TbConfig(configKey,configValue,lastupdate) values('token.timeout','10080',now());