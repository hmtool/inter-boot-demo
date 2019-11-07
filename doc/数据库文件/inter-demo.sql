/*
Navicat MariaDB Data Transfer

Source Server         : 文件服务器
Source Server Version : 100207
Source Host           : 192.168.1.200:3333
Source Database       : inter-micro-demo

Target Server Type    : MariaDB
Target Server Version : 100207
File Encoding         : 65001

Date: 2018-03-12 16:28:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_fun
-- ----------------------------
DROP TABLE IF EXISTS `sys_fun`;
CREATE TABLE `sys_fun` (
  `FUNID` varchar(50) NOT NULL COMMENT '功能代号',
  `ORDERVAL` int(11) NOT NULL COMMENT '排序',
  `LAYID` int(11) NOT NULL COMMENT '当前层',
  `FUNDESC` varchar(120) NOT NULL COMMENT '功能名称',
  `FUNPATH` varchar(120) DEFAULT '' COMMENT '对应页面路径',
  `PARENTID` varchar(50) DEFAULT '' COMMENT '父功能代号',
  `BOTFLAG` char(1) NOT NULL DEFAULT '1' COMMENT '是否是底层权限[0=否/1=是(default)]',
  `USEFLAG` char(1) NOT NULL DEFAULT '1' COMMENT '是否在使用[0=否/1=是(default)]',
  `DISPLAYFUN` char(1) DEFAULT '1' COMMENT '是否展示【0：否/1：是】',
  `OPERATE_USER` varchar(32) DEFAULT '' COMMENT '操作者',
  `OPERATE_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '操作时间',
  `ATTACHID` varchar(32) DEFAULT '',
  PRIMARY KEY (`FUNID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_fun
-- ----------------------------
INSERT INTO `sys_fun` VALUES ('ADDSYSFUN', '1', '3', '添加权限', '', 'FUNMANAGER', '0', '1', '0', '1', '2018-03-06 18:41:21', '');
INSERT INTO `sys_fun` VALUES ('ADDSYSROLE', '2', '3', '添加角色', '', 'ROLEMANAGE', '0', '1', '0', '1', '2018-03-05 19:07:54', '');
INSERT INTO `sys_fun` VALUES ('ADDSYSUSER', '1', '3', '添加用户', '', 'SYSUSERMANAGE', '0', '1', '0', '1', '2018-03-05 19:15:52', '');
INSERT INTO `sys_fun` VALUES ('CONFIGSYSROLE', '5', '3', '配置角色', '', 'ROLEMANAGE', '0', '1', '0', '1', '2018-03-05 19:09:43', '');
INSERT INTO `sys_fun` VALUES ('CONFIGSYSUSERROLE', '6', '3', '确认用户角色', '', 'SYSUSERMANAGE', '0', '1', '0', '1', '2018-03-05 19:18:39', '');
INSERT INTO `sys_fun` VALUES ('DELSYSFUN', '3', '3', '删除权限', '', 'FUNMANAGER', '0', '1', '0', '1', '2017-11-22 10:58:40', '');
INSERT INTO `sys_fun` VALUES ('DELSYSROLE', '3', '3', '删除角色', '', 'ROLEMANAGE', '0', '1', '0', '1', '2018-03-05 19:08:22', '');
INSERT INTO `sys_fun` VALUES ('DELSYSUSER', '3', '3', '删除用户', '', 'SYSUSERMANAGE', '0', '1', '0', '1', '2017-11-22 10:55:56', '');
INSERT INTO `sys_fun` VALUES ('FUNMANAGER', '98', '2', '权限管理', '/user/sysfun/index.html', 'USERMANAGE', '1', '1', '0', '1', '2017-11-22 10:56:20', '');
INSERT INTO `sys_fun` VALUES ('LOCKSYSUSER', '5', '3', '锁定系统用户', '', 'SYSUSERMANAGE', '0', '1', '0', '1', '2018-03-05 19:13:57', '');
INSERT INTO `sys_fun` VALUES ('MODMESSAGEQRY', '1', '3', '新增消息权限', '', 'MESSAGEQRYMANAGE', '0', '1', '0', '1', '2017-08-13 10:58:00', '');
INSERT INTO `sys_fun` VALUES ('MODSYSFUN', '2', '3', '修改权限', '', 'FUNMANAGER', '0', '1', '0', '1', '2017-11-22 10:58:55', '');
INSERT INTO `sys_fun` VALUES ('MODSYSPRORULE', '2', '3', '修改产品规则', '', 'SYSPRORULE', '0', '1', '0', '1', '2017-08-13 10:35:01', '');
INSERT INTO `sys_fun` VALUES ('MODSYSROLE', '3', '3', '修改角色', '', 'ROLEMANAGE', '0', '1', '0', '1', '2018-03-05 19:07:29', '');
INSERT INTO `sys_fun` VALUES ('MODSYSUSER', '2', '3', '修改用户', '', 'SYSUSERMANAGE', '0', '1', '0', '1', '2018-03-05 19:15:27', '');
INSERT INTO `sys_fun` VALUES ('QRYSYSFUN', '6', '3', '查询权限', '', 'FUNMANAGER', '0', '1', '1', '1', '2017-11-22 10:58:26', '');
INSERT INTO `sys_fun` VALUES ('QRYSYSROLE', '1', '3', '查询角色', '', 'ROLEMANAGE', '0', '1', '1', '1', '2018-03-05 19:06:58', '');
INSERT INTO `sys_fun` VALUES ('QRYSYSUSER', '4', '3', '查询用户', '', 'SYSUSERMANAGE', '0', '1', '1', '1', '2018-03-05 19:11:27', '');
INSERT INTO `sys_fun` VALUES ('RESETPWD', '7', '3', '重置密码', '', 'SYSUSERMANAGE', '0', '1', '0', '1', '2018-03-05 19:12:04', '');
INSERT INTO `sys_fun` VALUES ('ROLEMANAGE', '99', '2', '角色管理', '/user/sysrole/index.html', 'USERMANAGE', '1', '1', '0', '1', '2017-11-22 10:56:08', '');
INSERT INTO `sys_fun` VALUES ('SYSUSERMANAGE', '100', '2', '系统用户管理', '/user/sysuser/index.html', 'USERMANAGE', '1', '1', '0', '1', '2017-11-22 10:53:55', '');
INSERT INTO `sys_fun` VALUES ('USERMANAGE', '100', '1', '系统管理', '', 'TOP', '1', '0', '1', '1', '2017-11-22 10:55:23', '');

-- ----------------------------
-- Table structure for sys_fun_exclude
-- ----------------------------
DROP TABLE IF EXISTS `sys_fun_exclude`;
CREATE TABLE `sys_fun_exclude` (
  `TYPE` varchar(200) NOT NULL,
  `URL` varchar(255) NOT NULL,
  `OPERATE_TIME` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `OPERATE_USER` varchar(32) DEFAULT '',
  PRIMARY KEY (`TYPE`,`URL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_fun_exclude
-- ----------------------------
INSERT INTO `sys_fun_exclude` VALUES ('exclude_vist_url', '/gb/gbdict', '2018-03-09 10:08:36', null);
INSERT INTO `sys_fun_exclude` VALUES ('exclude_vist_url', '/sy/sysuserrole/queryUserFunForLogin', '2018-03-05 15:41:17', null);
INSERT INTO `sys_fun_exclude` VALUES ('not_login_vist_url', '/configuration', '2018-03-05 17:22:54', null);
INSERT INTO `sys_fun_exclude` VALUES ('not_login_vist_url', '/error', '2018-03-05 16:37:41', null);
INSERT INTO `sys_fun_exclude` VALUES ('not_login_vist_url', '/swagger-resources', '2018-03-05 17:13:33', null);
INSERT INTO `sys_fun_exclude` VALUES ('not_login_vist_url', '/sy/sysfunvist/reloadVistUrl', '2018-03-05 17:57:23', null);
INSERT INTO `sys_fun_exclude` VALUES ('not_login_vist_url', '/sy/sysuser/loginUsePwd', '2018-03-05 15:33:08', null);
INSERT INTO `sys_fun_exclude` VALUES ('not_login_vist_url', '/v2/api-docs', '2018-03-05 17:18:03', null);

-- ----------------------------
-- Table structure for sys_fun_include
-- ----------------------------
DROP TABLE IF EXISTS `sys_fun_include`;
CREATE TABLE `sys_fun_include` (
  `ID` varchar(32) NOT NULL,
  `URL` varchar(255) NOT NULL,
  `FUNID` varchar(50) NOT NULL,
  `OPERATE_TIME` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `OPERATE_USER` varchar(32) DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_fun_include
-- ----------------------------
INSERT INTO `sys_fun_include` VALUES ('970594871919697920', '/sy/sysfun/queryFunByPage', 'QRYSYSFUN', '2018-03-05 17:40:24', '1');
INSERT INTO `sys_fun_include` VALUES ('970594873001828352', '/sy/sysfun/queryFunTree', 'QRYSYSFUN', '2018-03-05 17:40:24', '1');
INSERT INTO `sys_fun_include` VALUES ('970594874381754368', '/sy/sysfun/queryFun', 'QRYSYSFUN', '2018-03-05 17:40:25', '1');
INSERT INTO `sys_fun_include` VALUES ('970595399361814528', '/sy/sysfun/updateFun', 'MODSYSFUN', '2018-03-05 17:42:30', '1');
INSERT INTO `sys_fun_include` VALUES ('970595400607522816', '/sy/sysfunvist/getVistUrl', 'MODSYSFUN', '2018-03-05 17:42:30', '1');
INSERT INTO `sys_fun_include` VALUES ('970595554068717568', '/sy/sysfun/deleteFun', 'DELSYSFUN', '2018-03-05 17:43:07', '1');
INSERT INTO `sys_fun_include` VALUES ('970616662109143040', '/sy/sysrole/queryRoleByPage', 'QRYSYSROLE', '2018-03-05 19:06:59', '1');
INSERT INTO `sys_fun_include` VALUES ('970616663400988672', '/sy/sysrole/queryRole', 'QRYSYSROLE', '2018-03-05 19:07:00', '1');
INSERT INTO `sys_fun_include` VALUES ('970616790383542272', '/sy/sysrole/updateRole', 'MODSYSROLE', '2018-03-05 19:07:30', '1');
INSERT INTO `sys_fun_include` VALUES ('970616895249530880', '/sy/sysrole/saveRole', 'ADDSYSROLE', '2018-03-05 19:07:55', '1');
INSERT INTO `sys_fun_include` VALUES ('970617013872836608', '/sy/sysrole/deleteRole', 'DELSYSROLE', '2018-03-05 19:08:23', '1');
INSERT INTO `sys_fun_include` VALUES ('970617352600633344', '/sy/sysrolefun/queryRoleFunTree', 'CONFIGSYSROLE', '2018-03-05 19:09:44', '1');
INSERT INTO `sys_fun_include` VALUES ('970617353846341632', '/sy/sysrolefun/setRoleFun', 'CONFIGSYSROLE', '2018-03-05 19:09:44', '1');
INSERT INTO `sys_fun_include` VALUES ('970617790595022848', '/sy/sysuser/queryUserByPage', 'QRYSYSUSER', '2018-03-05 19:11:28', '1');
INSERT INTO `sys_fun_include` VALUES ('970617941812264960', '/sy/sysuser/resetPassword', 'RESETPWD', '2018-03-05 19:12:04', '1');
INSERT INTO `sys_fun_include` VALUES ('970618415999303680', '/sy/sysuser/lockUser', 'LOCKSYSUSER', '2018-03-05 19:13:58', '1');
INSERT INTO `sys_fun_include` VALUES ('970618793625075712', '/sy/sysuser/updateUser', 'MODSYSUSER', '2018-03-05 19:15:28', '1');
INSERT INTO `sys_fun_include` VALUES ('970618901192196096', '/sy/sysuser/saveUser', 'ADDSYSUSER', '2018-03-05 19:15:53', '1');
INSERT INTO `sys_fun_include` VALUES ('970619599178907648', '/sy/sysrole/setRoleFun', 'CONFIGSYSUSERROLE', '2018-03-05 19:18:40', '1');
INSERT INTO `sys_fun_include` VALUES ('970619600491724800', '/sy/sysrolefun/queryRoleFunTree', 'CONFIGSYSUSERROLE', '2018-03-05 19:18:40', '1');
INSERT INTO `sys_fun_include` VALUES ('970619601745821696', '/sy/sysuserrole/queryUserRoleCheck', 'CONFIGSYSUSERROLE', '2018-03-05 19:18:40', '1');
INSERT INTO `sys_fun_include` VALUES ('970972600062091264', '/sy/sysfun/saveFun', 'ADDSYSFUN', '2018-03-06 18:41:22', '1');
INSERT INTO `sys_fun_include` VALUES ('970972601303605248', '/sy/sysfun/queryFunTree', 'ADDSYSFUN', '2018-03-06 18:41:22', '1');

-- ----------------------------
-- Table structure for sys_fun_include_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_fun_include_record`;
CREATE TABLE `sys_fun_include_record` (
  `ID` varchar(32) NOT NULL,
  `SEQNO` varchar(32) NOT NULL,
  `URL` varchar(255) NOT NULL,
  `FUNID` varchar(50) NOT NULL,
  `OPERATE_TIME` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `OPERATE_USER` varchar(32) DEFAULT '',
  `OPERATE_STATUS` char(1) DEFAULT '',
  PRIMARY KEY (`ID`,`SEQNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_fun_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_fun_record`;
CREATE TABLE `sys_fun_record` (
  `FUNID` varchar(50) NOT NULL COMMENT '功能代号',
  `SEQNO` varchar(32) NOT NULL COMMENT '排序',
  `ORDERVAL` int(11) NOT NULL COMMENT '排序',
  `LAYID` int(11) NOT NULL COMMENT '当前层',
  `FUNDESC` varchar(120) NOT NULL COMMENT '功能名称',
  `FUNPATH` varchar(120) DEFAULT '' COMMENT '对应页面路径',
  `PARENTID` varchar(50) DEFAULT '' COMMENT '父功能代号',
  `BOTFLAG` char(1) NOT NULL DEFAULT '1' COMMENT '是否是底层权限[0=否/1=是(default)]',
  `USEFLAG` char(1) NOT NULL DEFAULT '1' COMMENT '是否在使用[0=否/1=是(default)]',
  `DISPLAYFUN` char(1) DEFAULT '1' COMMENT '是否展示【0：否/1：是】',
  `OPERATE_STATUS` char(1) NOT NULL DEFAULT '0' COMMENT '操作状态（1-新增，2-修改，3-删除）',
  `OPERATE_USER` varchar(32) DEFAULT '' COMMENT '操作者',
  `OPERATE_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '操作时间',
  `ATTACHID` varchar(32) DEFAULT '',
  PRIMARY KEY (`FUNID`,`SEQNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单历史表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLEID` varchar(20) NOT NULL COMMENT '角色代号',
  `ROLEDESC` varchar(120) NOT NULL COMMENT '角色名称',
  `ROLEREMARK` varchar(200) DEFAULT '' COMMENT '角色描述',
  `MARKFLAG` char(1) NOT NULL DEFAULT '1' COMMENT '审核标记[1 有效 0 无效]',
  `OPERATE_USER` varchar(32) DEFAULT '' COMMENT '操作者',
  `OPERATE_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '操作时间',
  PRIMARY KEY (`ROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色基本信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('SUPER', '超级管理员', null, '0', '1', '2017-11-22 10:55:21');

-- ----------------------------
-- Table structure for sys_role_fun
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_fun`;
CREATE TABLE `sys_role_fun` (
  `ROLEID` varchar(32) NOT NULL COMMENT '角色ID',
  `FUNID` varchar(50) NOT NULL COMMENT '权限ID',
  `OPERATE_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '操作时间',
  `OPERATE_USER` varchar(32) NOT NULL DEFAULT '1' COMMENT '操作者',
  PRIMARY KEY (`ROLEID`,`FUNID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色权限表';

-- ----------------------------
-- Records of sys_role_fun
-- ----------------------------
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'ADDSYSFUN', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'ADDSYSROLE', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'ADDSYSUSER', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'CONFIGSYSROLE', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'CONFIGSYSUSERROLE', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'DELSYSFUN', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'DELSYSROLE', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'DELSYSUSER', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'FUNMANAGER', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'LOCKSYSUSER', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'MODSYSFUN', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'MODSYSROLE', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'MODSYSUSER', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'QRYSYSFUN', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'QRYSYSROLE', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'QRYSYSUSER', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'RESETPWD', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'ROLEMANAGE', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'SYSUSERMANAGE', '2018-03-08 15:17:18', '1');
INSERT INTO `sys_role_fun` VALUES ('SUPER', 'USERMANAGE', '2018-03-08 15:17:18', '1');

-- ----------------------------
-- Table structure for sys_role_fun_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_fun_record`;
CREATE TABLE `sys_role_fun_record` (
  `ROLEID` varchar(32) NOT NULL COMMENT '角色ID',
  `FUNID` varchar(50) NOT NULL COMMENT '权限ID',
  `SEQNO` varchar(32) NOT NULL COMMENT '历史序列号',
  `OPERATE_STATUS` char(1) NOT NULL DEFAULT '0' COMMENT '操作状态（1-新增，2-修改，3-删除）',
  `OPERATE_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '操作时间',
  `OPERATE_USER` varchar(32) NOT NULL DEFAULT '1' COMMENT '操作者',
  PRIMARY KEY (`ROLEID`,`FUNID`,`SEQNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色权限表';

-- ----------------------------
-- Table structure for sys_role_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_record`;
CREATE TABLE `sys_role_record` (
  `ROLEID` varchar(20) NOT NULL COMMENT '角色代号',
  `SEQNO` varchar(32) NOT NULL COMMENT '历史序列值',
  `ROLEDESC` varchar(120) NOT NULL COMMENT '角色名称',
  `ROLEREMARK` varchar(200) DEFAULT '' COMMENT '角色描述',
  `MARKFLAG` char(1) NOT NULL DEFAULT '1' COMMENT '审核标记[1 有效 0 无效]',
  `OPERATE_STATUS` char(1) NOT NULL DEFAULT '0' COMMENT '操作状态（1-新增，2-修改，3-删除）',
  `OPERATE_USER` varchar(32) DEFAULT '' COMMENT '操作者',
  `OPERATE_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '操作时间',
  PRIMARY KEY (`ROLEID`,`SEQNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色基本信息历史表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USERID` varchar(32) NOT NULL COMMENT '用户唯一编号（建议从100000000开始）',
  `USERNAME` varchar(120) DEFAULT '' COMMENT '用户名称',
  `PASSWORD` varchar(50) DEFAULT '' COMMENT '密码',
  `CHGPWDFLAG` char(1) NOT NULL DEFAULT '1' COMMENT '用户登陆时是否要求修改密码[0=否/1=是(default)／9=用户锁定]',
  `VALIDDAY` int(11) NOT NULL DEFAULT 30 COMMENT '密码有效天数',
  `WARNINGDAY` int(11) NOT NULL DEFAULT 7 COMMENT '密码到期前警告天数',
  `EMAIL` varchar(120) DEFAULT '' COMMENT 'Email',
  `TEL` varchar(30) DEFAULT '' COMMENT '电话',
  `MOBILEPHONE` varchar(30) NOT NULL COMMENT '移动电话',
  `ADDRESS` varchar(180) DEFAULT '' COMMENT '地址',
  `ENTRANTDATE` timestamp NULL DEFAULT current_timestamp() COMMENT '注册日期',
  `AVAILABLE` char(1) NOT NULL DEFAULT '1' COMMENT '用户账号是否有效[1=有效(default)/0=无效]',
  `OPERATE_USER` varchar(32) DEFAULT '' COMMENT '操作者',
  `OPERATE_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '操作时间',
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理人用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '系统管理员', '511b56a0c2c74ab89f58f84bbf4f19f5', '1', '30', '7', '4708461864@qq.com', '08136210030', '19983066208', '`122', null, '1', '1', '2018-03-05 19:19:16');
-- ----------------------------
-- Table structure for sys_user_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_record`;
CREATE TABLE `sys_user_record` (
  `USERID` varchar(32) NOT NULL COMMENT '用户唯一编号',
  `SEQNO` varchar(32) NOT NULL COMMENT '历史序列号',
  `USERNAME` varchar(120) DEFAULT '' COMMENT '用户名称',
  `PASSWORD` varchar(50) DEFAULT '' COMMENT '密码',
  `CHGPWDFLAG` char(1) NOT NULL DEFAULT '1' COMMENT '用户登陆时是否要求修改密码[0=否/1=是(default)／9=用户锁定]',
  `VALIDDAY` int(11) NOT NULL DEFAULT 30 COMMENT '密码有效天数',
  `WARNINGDAY` int(11) NOT NULL DEFAULT 7 COMMENT '密码到期前警告天数',
  `EMAIL` varchar(120) DEFAULT '' COMMENT 'Email',
  `TEL` varchar(30) DEFAULT '' COMMENT '电话',
  `MOBILEPHONE` varchar(30) NOT NULL COMMENT '移动电话',
  `ADDRESS` varchar(180) DEFAULT '' COMMENT '地址',
  `ENTRANTDATE` timestamp NULL DEFAULT current_timestamp() COMMENT '注册日期',
  `AVAILABLE` char(1) NOT NULL DEFAULT '1' COMMENT '用户账号是否有效[1=有效(default)/0=无效]',
  `OPERATE_STATUS` char(1) NOT NULL DEFAULT '0' COMMENT '操作状态（1-新增，2-修改，3-删除）',
  `OPERATE_USER` varchar(32) DEFAULT '' COMMENT '操作者',
  `OPERATE_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '操作时间',
  PRIMARY KEY (`USERID`,`SEQNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理人用户历史表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `USERID` varchar(32) NOT NULL COMMENT '用户ID',
  `ROLEID` varchar(20) NOT NULL COMMENT '角色ID',
  `OPERATE_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '操作时间',
  `OPERATE_USER` varchar(32) NOT NULL DEFAULT '1' COMMENT '操作者',
  PRIMARY KEY (`USERID`,`ROLEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', 'SUPER', '2017-08-11 17:03:21', '1');

-- ----------------------------
-- Table structure for sys_user_role_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_record`;
CREATE TABLE `sys_user_role_record` (
  `USERID` varchar(32) NOT NULL COMMENT '用户ID',
  `ROLEID` varchar(20) NOT NULL COMMENT '角色ID',
  `SEQNO` varchar(32) NOT NULL COMMENT '历史序列值',
  `OPERATE_STATUS` char(1) NOT NULL DEFAULT '0' COMMENT '操作状态（1-新增，2-修改，3-删除）',
  `OPERATE_TIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '操作时间',
  `OPERATE_USER` varchar(32) NOT NULL DEFAULT '1' COMMENT '操作者',
  PRIMARY KEY (`USERID`,`ROLEID`,`SEQNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';