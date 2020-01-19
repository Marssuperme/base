/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.29 : Database - test_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test_system`;

/*Table structure for table `t_file` */

DROP TABLE IF EXISTS `t_file`;

CREATE TABLE `t_file` (
  `file_id` varchar(50) NOT NULL COMMENT '附件ID',
  `file_original_name` varchar(100) DEFAULT NULL COMMENT '原名称',
  `file_name` varchar(100) DEFAULT NULL COMMENT '虚拟名称',
  `file_path` varchar(200) DEFAULT NULL COMMENT '路径',
  `file_size` varchar(50) DEFAULT NULL COMMENT '大小',
  `file_type` varchar(50) DEFAULT NULL COMMENT '类型',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间（自动生成）',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_file` */

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `role_id` varchar(50) NOT NULL COMMENT '角色ID',
  `role_name` varchar(50) DEFAULT NULL COMMENT '名称',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间（自动生成）',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`role_id`,`role_name`,`remark`,`create_time`) values 
('66e3a882-5c0a-11e9-ae4c-00ff04ab37fe','超级管理员','超级管理员','2019-04-11 11:32:15'),
('8ccdfce0-5c0a-11e9-ae4c-00ff04ab37fe','管理员','管理员','2019-04-11 11:33:19'),
('9507c6df-5c0a-11e9-ae4c-00ff04ab37fe','学生','学生','2019-04-11 11:33:33'),
('9a772403-5c0a-11e9-ae4c-00ff04ab37fe','教师','教师','2019-04-11 11:33:42');

/*Table structure for table `t_role_function` */

DROP TABLE IF EXISTS `t_role_function`;

CREATE TABLE `t_role_function` (
  `role_function_id` varchar(50) NOT NULL COMMENT '角色功能ID',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `function_id` varchar(50) DEFAULT NULL COMMENT '功能ID',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间（自动生成）',
  PRIMARY KEY (`role_function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_function` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` varchar(50) NOT NULL COMMENT '用户ID',
  `user_account` varchar(30) DEFAULT NULL COMMENT '用户账号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `user_password` varchar(100) DEFAULT NULL COMMENT '密码',
  `user_gender` int(11) DEFAULT '0' COMMENT '性别：0-男（默认），1-女',
  `user_email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `state` int(11) DEFAULT '0' COMMENT '0-可用（默认），1-禁用',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间（自动生成）',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`user_id`,`user_account`,`user_name`,`user_password`,`user_gender`,`user_email`,`user_phone`,`role_id`,`state`,`remark`,`create_time`) values 
('98804cc0-5b66-11e9-90e2-00ff04ab37fe','admin','管理员','123',0,'admin@qq.com','15707670000','1',0,'测试','2019-04-11 11:07:07');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
