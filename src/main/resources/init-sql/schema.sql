/*
Date: 2016-06-01 23:44:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_test_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_test_user`;
CREATE TABLE `t_test_user` (
  `id` varchar(19) NOT NULL ,
  `account` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `salary` DECIMAL(8,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_test_user
-- ----------------------------
INSERT INTO `t_test_user`(`id`,`account`,`password`,`name`,`age`,`salary`,`create_time`) VALUES ('1', 'zyb', '123', '钟义彪', 25,2309.2, '2016-06-01 23:41:39');
INSERT INTO `t_test_user`(`id`,`account`,`password`,`name`,`age`,`salary`,`create_time`) VALUES ('2','zy', '123', '钟杨', 25,3353.2, '2016-06-01 23:41:39');
INSERT INTO `t_test_user`(`id`,`account`,`password`,`name`,`age`,`salary`,`create_time`) VALUES ('3', 'lk', '123', '李坤', 25,2309.2, '2016-06-01 23:41:39');
INSERT INTO `t_test_user`(`id`,`account`,`password`,`name`,`age`,`salary`,`create_time`) VALUES ('4','pk', '123', '彭坤', 25,3353.2, '2016-06-01 23:41:39');
INSERT INTO `t_test_user`(`id`,`account`,`password`,`name`,`age`,`salary`,`create_time`) VALUES ('5', 'wmt', '123', '吴明佗', 25,2309.2, '2016-06-01 23:41:39');
INSERT INTO `t_test_user`(`id`,`account`,`password`,`name`,`age`,`salary`,`create_time`) VALUES ('6','sfx', '123', '尚福星', 26,3353.2, '2016-06-01 23:41:39');
INSERT INTO `t_test_user`(`id`,`account`,`password`,`name`,`age`,`salary`,`create_time`) VALUES ('7','lhl', '123', '刘湖龙', 25,3353.2, '2016-06-01 23:41:39');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` VARCHAR(64) NOT NULL,
  `account` VARCHAR(64) DEFAULT NULL,
  `password` VARCHAR(64) DEFAULT NULL,
  `delFlag` VARCHAR(4) DEFAULT NULL,
  `createdDate` TIMESTAMP,
  `createBy` VARCHAR(64) DEFAULT NULL,
  `lastUpdatedDate` TIMESTAMP,
  `lastUpdatedDateBy` VARCHAR(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;




-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user`(`id`,`account`,`password`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('1', 'admin', '123456', 'N','2016-06-01 23:35:17','1','2016-06-01 23:35:17','1');
INSERT INTO `t_user`(`id`,`account`,`password`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('2', 'lance', '123456', 'N','2016-06-01 23:35:17','1','2016-06-02 23:35:38','1');
INSERT INTO `t_user`(`id`,`account`,`password`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('3', 'tony', '123456', 'N','2016-06-01 23:35:17','1','2016-06-02 23:35:38','1');
INSERT INTO `t_user`(`id`,`account`,`password`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('4', 'dave', '123456', 'N','2016-06-01 23:35:17','1','2016-06-02 23:35:38','1');
INSERT INTO `t_user`(`id`,`account`,`password`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('5', 'key', '123456', 'N','2016-06-01 23:35:17','1','2016-06-02 23:35:38','1');
INSERT INTO `t_user`(`id`,`account`,`password`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('6', 'tom', '123456', 'N','2016-06-01 23:35:17','1','2016-06-02 23:35:38','1');
INSERT INTO `t_user`(`id`,`account`,`password`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('7', 'ketty', '123456', 'N','2016-06-01 23:35:17','1','2016-06-02 23:35:38','1');
INSERT INTO `t_user`(`id`,`account`,`password`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('8', 'jeremy', '123456', 'N','2016-06-01 23:35:17','1','2016-06-02 23:35:38','1');




-- ----------------------------
-- Table structure for `t_user_role`
--
-- admin  ADMIN (系统管理员)
-- lance  (GA) 总代理
-- tony   (AGENT) 代理商
-- dave   (AGENT) 代理商
-- key    (VIP) tony会员
-- tom    (VIP) tony会员
-- ketty  (VIP) dave会员
-- jeremy (VIP) dave会员
--
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` VARCHAR(64) NOT NULL,
  `user_id` VARCHAR(64) DEFAULT NULL,
  `role_id` VARCHAR(64) DEFAULT NULL,
  `delFlag` VARCHAR(4) DEFAULT NULL,
  `createdDate` TIMESTAMP,
  `createBy` VARCHAR(64) DEFAULT NULL,
  `lastUpdatedDate` TIMESTAMP,
  `lastUpdatedDateBy` VARCHAR(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;




-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role`(`id`,`user_id`,`role_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('1', '1', '1','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_user_role`(`id`,`user_id`,`role_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('2', '2', '2','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_user_role`(`id`,`user_id`,`role_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('3', '3', '3','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_user_role`(`id`,`user_id`,`role_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('4', '4', '3','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_user_role`(`id`,`user_id`,`role_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('5', '5', '4','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_user_role`(`id`,`user_id`,`role_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('6', '6', '4','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_user_role`(`id`,`user_id`,`role_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('7', '7', '4','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_user_role`(`id`,`user_id`,`role_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('8', '8', '4','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');




-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` VARCHAR(64) NOT NULL,
  `role` VARCHAR(64) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `delFlag` VARCHAR(4) DEFAULT NULL,
  `createdDate` TIMESTAMP,
  `createBy` VARCHAR(64) DEFAULT NULL,
  `lastUpdatedDate` TIMESTAMP,
  `lastUpdatedDateBy` VARCHAR(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;





-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role`(`id`,`role`,`description`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('1', 'ADMIN', '系统管理员','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role`(`id`,`role`,`description`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('2', 'GA', '总代理' ,'N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role`(`id`,`role`,`description`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('3', 'AGENT', '代理商','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role`(`id`,`role`,`description`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('4', 'VIP', '会员','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');




-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` VARCHAR(64) NOT NULL,
  `role_id` VARCHAR(64) DEFAULT NULL,
  `permission_id` VARCHAR(64) DEFAULT NULL,
  `delFlag` VARCHAR(4) DEFAULT NULL,
  `createdDate` TIMESTAMP,
  `createBy` VARCHAR(64) DEFAULT NULL,
  `lastUpdatedDate` TIMESTAMP,
  `lastUpdatedDateBy` VARCHAR(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;




-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission`(`id`,`role_id`,`permission_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('1', '1', '1','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role_permission`(`id`,`role_id`,`permission_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('2', '1', '2','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role_permission`(`id`,`role_id`,`permission_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('3', '1', '3','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role_permission`(`id`,`role_id`,`permission_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('4', '1', '4','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role_permission`(`id`,`role_id`,`permission_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('5', '1', '2','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role_permission`(`id`,`role_id`,`permission_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('6', '1', '3','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role_permission`(`id`,`role_id`,`permission_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('7', '2', '4','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role_permission`(`id`,`role_id`,`permission_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('8', '4', '5','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role_permission`(`id`,`role_id`,`permission_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('9', '4', '2','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_role_permission`(`id`,`role_id`,`permission_id`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('10', '1', '4','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');




-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` VARCHAR(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `permission` varchar(64) DEFAULT NULL,
  `url` varchar(64) DEFAULT NULL,
  `resource_type` VARCHAR(4) DEFAULT NULL COMMENT '1.URL, 2.功能',
  `delFlag` VARCHAR(4) DEFAULT NULL,
  `createdDate` TIMESTAMP,
  `createBy` VARCHAR(64) DEFAULT NULL,
  `lastUpdatedDate` TIMESTAMP,
  `lastUpdatedDateBy` VARCHAR(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;




-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission`(`id`,`name`,`url`,`permission`,`resource_type`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('1', '首页', '/index', null, '1','N', '2016-06-01 23:41:39','1','2016-06-01 23:41:39','1');
INSERT INTO `t_permission`(`id`,`name`,`url`,`permission`,`resource_type`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('2', '用户新增', null, 'user:add', 'N', '1','2016-06-02 09:42:17','1','2016-06-01 23:41:39','1');
INSERT INTO `t_permission`(`id`,`name`,`url`,`permission`,`resource_type`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('3', '用户删除', null, 'user:delete', 'N', '1','2016-06-03 21:42:17','1','2016-06-01 23:41:39','1');
INSERT INTO `t_permission`(`id`,`name`,`url`,`permission`,`resource_type`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('4', '用户更新', null, 'user:update', 'N', '1','2016-06-03 20:38:01','1','2016-06-01 23:41:39','1');
INSERT INTO `t_permission`(`id`,`name`,`url`,`permission`,`resource_type`,`delFlag`,`createdDate`,`createBy`,`lastUpdatedDate`,`lastUpdatedDateBy`) VALUES ('5', '用户查询', null, 'user:view', 'N', '1','2016-06-03 20:38:04','1','2016-06-01 23:41:39','1');

