/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : transporter

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-10-11 20:06:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('4', '/permission/index.shtml', '权限列表');
INSERT INTO `sys_permission` VALUES ('6', '/permission/addPermission.shtml', '权限添加');
INSERT INTO `sys_permission` VALUES ('7', '/permission/deletePermissionById.shtml', '权限删除');
INSERT INTO `sys_permission` VALUES ('8', '/member/list.shtml', '用户列表');
INSERT INTO `sys_permission` VALUES ('9', '/member/online.shtml', '在线用户');
INSERT INTO `sys_permission` VALUES ('10', '/member/changeSessionStatus.shtml', '用户Session踢出');
INSERT INTO `sys_permission` VALUES ('11', '/member/forbidUserById.shtml', '用户激活&禁止');
INSERT INTO `sys_permission` VALUES ('12', '/member/deleteUserById.shtml', '用户删除');
INSERT INTO `sys_permission` VALUES ('13', '/permission/addPermission2Role.shtml', '权限分配');
INSERT INTO `sys_permission` VALUES ('14', '/role/clearRoleByUserIds.shtml', '用户角色分配清空');
INSERT INTO `sys_permission` VALUES ('15', '/role/addRole2User.shtml', '角色分配保存');
INSERT INTO `sys_permission` VALUES ('16', '/role/deleteRoleById.shtml', '角色列表删除');
INSERT INTO `sys_permission` VALUES ('17', '/role/addRole.shtml', '角色列表添加');
INSERT INTO `sys_permission` VALUES ('18', '/role/index.shtml', '角色列表');
INSERT INTO `sys_permission` VALUES ('19', '/permission/allocation.shtml', '权限分配');
INSERT INTO `sys_permission` VALUES ('20', '/role/allocation.shtml', '角色分配');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '888888');
INSERT INTO `sys_role` VALUES ('3', '权限角色', '100003');
INSERT INTO `sys_role` VALUES ('4', '用户中心', '100002');

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) DEFAULT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('4', '8');
INSERT INTO `sys_role_permission` VALUES ('4', '9');
INSERT INTO `sys_role_permission` VALUES ('4', '10');
INSERT INTO `sys_role_permission` VALUES ('4', '11');
INSERT INTO `sys_role_permission` VALUES ('4', '12');
INSERT INTO `sys_role_permission` VALUES ('3', '4');
INSERT INTO `sys_role_permission` VALUES ('3', '6');
INSERT INTO `sys_role_permission` VALUES ('3', '7');
INSERT INTO `sys_role_permission` VALUES ('3', '13');
INSERT INTO `sys_role_permission` VALUES ('3', '14');
INSERT INTO `sys_role_permission` VALUES ('3', '15');
INSERT INTO `sys_role_permission` VALUES ('3', '16');
INSERT INTO `sys_role_permission` VALUES ('3', '17');
INSERT INTO `sys_role_permission` VALUES ('3', '18');
INSERT INTO `sys_role_permission` VALUES ('3', '19');
INSERT INTO `sys_role_permission` VALUES ('3', '20');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '16');
INSERT INTO `sys_role_permission` VALUES ('1', '17');
INSERT INTO `sys_role_permission` VALUES ('1', '18');
INSERT INTO `sys_role_permission` VALUES ('1', '19');
INSERT INTO `sys_role_permission` VALUES ('1', '20');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` bigint(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '管理员', 'admin', '57eb72e6b78a87a12d46a7f5e9315138', '2016-06-16 11:15:33', '2017-10-11 15:02:53', '1');
INSERT INTO `sys_user` VALUES ('11', 'soso', '8446666@qq.com', 'd57ffbe486910dd5b26d0167d034f9ad', '2016-05-26 20:50:54', '2016-06-16 11:24:35', '1');
INSERT INTO `sys_user` VALUES ('12', '8446666', '8446666', '4afdc875a67a55528c224ce088be2ab8', '2016-05-27 22:34:19', '2016-06-15 17:03:16', '1');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) DEFAULT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('12', '4');
INSERT INTO `sys_user_role` VALUES ('11', '3');
INSERT INTO `sys_user_role` VALUES ('11', '4');
INSERT INTO `sys_user_role` VALUES ('1', '1');
