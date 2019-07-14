/*
 Navicat MySQL Data Transfer

 Source Server         : ddddd
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : aries

 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 11/13/2018 08:18:46 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_org`
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` bigint(22) NOT NULL COMMENT '组织id',
  `name` varchar(200) NOT NULL COMMENT '组织名称',
  `parent_id` varchar(100) DEFAULT '0' COMMENT '父id',
  `org_id` varchar(100) DEFAULT '0' COMMENT '菜单编号 (父id+自己的设置id)',
  `status` tinyint(4) DEFAULT '1' COMMENT '组织状态1表示正常使用，0表示停用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `sort` varchar(100) DEFAULT NULL COMMENT '预留字段（排序使用）',
  `level` varchar(4) DEFAULT NULL COMMENT 'level 10 总公司 11中心 12部门 13室 20 分公司 21 分公司部门',
  `principal` varchar(50) DEFAULT NULL COMMENT '负责人id（可能后期需要放到中间表）',
  `telphone` varchar(50) DEFAULT NULL COMMENT '电话',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `wx_dep_id` int(32) DEFAULT NULL COMMENT '企业微信部门id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;



-- ----------------------------
--  Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(22) NOT NULL COMMENT '菜单id',
  `name` varchar(255) NOT NULL COMMENT '菜单名称',
  `parent_id` varchar(22) DEFAULT '0' COMMENT '父菜单编号',
  `permission_id` varchar(100) DEFAULT NULL COMMENT '菜单编号',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限标志',
  `remarks` varchar(255) DEFAULT NULL COMMENT '菜单描述',
  `status` tinyint(4) DEFAULT '0' COMMENT '菜单状态',
  `type` tinyint(4) DEFAULT NULL COMMENT '菜单类型',
  `icon` varchar(500) DEFAULT NULL COMMENT '按钮路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;



-- ----------------------------
--  Table structure for `sys_pwd_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_pwd_config`;
CREATE TABLE `sys_pwd_config` (
  `id` bigint(22) DEFAULT NULL COMMENT '主键id',
  `length` int(2) DEFAULT '0' COMMENT '长度0-20位',
  `is_contained_upper_en` tinyint(1) DEFAULT '0' COMMENT '是否包含大小字母',
  `is_contained_lower_en` tinyint(1) DEFAULT '0' COMMENT '是否包含小写字母',
  `is_contained_number` tinyint(1) DEFAULT '0' COMMENT '是否包含数字',
  `is_contained_special_char` tinyint(1) DEFAULT '0' COMMENT '是否包含特性字符',
  `is_contained_user_id` tinyint(1) DEFAULT '0' COMMENT '是否包含用户名',
  `force_update_days` int(5) DEFAULT '0' COMMENT '强制更新的天数',
  `tolerate_num` int(2) DEFAULT '0' COMMENT '容忍密码错误次数',
  `sms_validate_everytime` tinyint(1) DEFAULT '0' COMMENT '每次登陆需短信验证',
  `sms_valicate_mac_change` tinyint(1) DEFAULT '0' COMMENT 'mac地址改变，短信验证',
  `sms_validate_force_days` int(5) DEFAULT '0' COMMENT '强制短信验证天数',
  `create_by` varchar(99) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(99) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `sys_pwd_config`
-- ----------------------------
BEGIN;
INSERT INTO `sys_pwd_config` VALUES ('1', '0', '0', '0', '0', '0', '0', '0', '30', '0', '0', '1800', null, null, 'XA00277', '2018-01-19 10:33:38');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(22) NOT NULL COMMENT '角色id',
  `role_id` varchar(255) DEFAULT NULL COMMENT '角色编号',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `type` tinyint(2) DEFAULT '1' COMMENT '1普通用户2管理员角色3任务分配',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态0 停用1 启用2 锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('866873637563596800', 'ROLE_ADMIN', '管理员', '2', null, '1', '2017-05-23 14:07:38', null, '2017-11-22 17:07:47', 'jack'), ('866873740957384704', 'ROLE_USER', '普通用户', null, null, '1', '2017-05-23 14:08:08', '866873637563596800', null, null), ('866915542917787648', 'ROLE_ROOT', 'Root用户', null, null, '1', '2017-05-23 15:14:52', '866873842975440896', null, null), ('895170954940162049', 'ROLE_HR', '人事管理员', '0', 'string', '0', null, null, '2017-10-11 14:44:01', 'jack'), ('919752283312664577', 'ROLE_HRMANAGER', '人事行政部总经理', '1', '人事行政部总经理', '1', '2017-10-16 10:30:07', 'XA00244', '2017-10-16 10:30:07', 'XA00244'), ('919752481141207042', 'ROLE_HRRELATIONSHIP', '人事行政部员工关系', '1', '人事行政部员工关系', '1', '2017-10-16 10:30:54', 'XA00244', '2017-10-16 10:30:54', 'XA00244'), ('919752497636077570', 'ROLE_CHAIRMAN', '董事长', '1', '董事长', '1', '2017-10-16 10:30:58', 'XA00244', null, null), ('920205940164415489', 'ROLE_HRRECRUITMENT', '人事行政部招聘专员', '1', '人事行政部招聘专员', '1', '2017-10-17 16:32:47', 'XA00244', '2017-10-17 16:32:47', 'XA00244'), ('923384596579794946', 'ROLE_ADMINSTRATOR', '行政部专员', '1', '行政部专员', '1', '2017-10-26 11:03:38', 'XA00244', '2017-10-26 11:03:38', 'XA00244');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_group`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_group`;
CREATE TABLE `sys_role_group` (
  `id` bigint(22) NOT NULL COMMENT '角色组id',
  `role_group_id` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色组id',
  `role_group_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色组名称',
  `role_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色编号',
  `remarks` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) DEFAULT '1' COMMENT '1启用0停用2锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时候',
  `update_by` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(22) NOT NULL COMMENT '表id',
  `role_id` varchar(100) NOT NULL COMMENT '角色编号',
  `permission_id` varchar(100) NOT NULL COMMENT '权限编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(22) NOT NULL COMMENT '用户编号',
  `user_id` varchar(50) NOT NULL COMMENT '用户名',
  `realname` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(80) NOT NULL COMMENT '密码，需加密存储',
  `type` tinyint(3) DEFAULT '0' COMMENT '类型',
  `mobile` varchar(30) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) DEFAULT '1' COMMENT '0停用1启用2锁定',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `USERNAME_IDX` (`user_id`),
  KEY `REALNAME_IDX` (`realname`),
  KEY `idx_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户';


-- ----------------------------
--  Table structure for `sys_user_login_records`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_records`;
CREATE TABLE `sys_user_login_records` (
  `id` bigint(20) NOT NULL COMMENT '"登录一览表id"',
  `user_id` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户id',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录ip',
  `login_mac` varchar(60) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录mac',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `sys_user_org`;
CREATE TABLE `sys_user_org` (
  `id` bigint(22) DEFAULT NULL COMMENT '表id',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户编号',
  `org_id` varchar(100) DEFAULT NULL COMMENT '组织编号',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(22) NOT NULL COMMENT 'id主键',
  `user_id` varchar(55) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户编号',
  `role_id` varchar(55) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色编号',
  `remarks` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) DEFAULT '1' COMMENT '0停用1启用2锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(55) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(55) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;


