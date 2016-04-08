-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.11-enterprise-commercial-advanced-log - MySQL Enterprise Server - Advanced Edition (Commercial)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4998
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 ibase4j 的数据库结构
CREATE DATABASE IF NOT EXISTS `ibase4j` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ibase4j`;


-- 导出  表 ibase4j.sys_dept 结构
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级部门编号',
  `enable` int(1) DEFAULT NULL COMMENT '启用状态',
  `sortno` int(3) DEFAULT NULL COMMENT '排序号',
  `leaf` int(1) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

-- 正在导出表  ibase4j.sys_dept 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_field 结构
CREATE TABLE IF NOT EXISTS `sys_field` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `field` varchar(30) DEFAULT NULL,
  `field_desc` varchar(50) DEFAULT NULL,
  `editable` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`field`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='代码表';

-- 正在导出表  ibase4j.sys_field 的数据：~10 rows (大约)
/*!40000 ALTER TABLE `sys_field` DISABLE KEYS */;
INSERT INTO `sys_field` (`id`, `field`, `field_desc`, `editable`) VALUES
	(1, 'SEX', '性别', 0),
	(2, 'LOCKED', '锁定', 0),
	(3, 'ROLETYPE', '角色类型', 0),
	(4, 'LEAF', '节点类型', 0),
	(5, 'EDITABLE', '编辑模式', 0),
	(6, 'ENABLE', '启用状态', 0),
	(7, 'AUTHORIZELEVEL', '权限级别', 0),
	(8, 'MENUTYPE', '菜单类型', 0),
	(9, 'USERTYPE', '人员类型', 0),
	(10, 'EXPAND', '展开状态', 0);
/*!40000 ALTER TABLE `sys_field` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_field_code 结构
CREATE TABLE IF NOT EXISTS `sys_field_code` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `field_id` int(20) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `code_desc` varchar(50) DEFAULT NULL,
  `enable` int(1) NOT NULL DEFAULT '1',
  `sortno` int(2) DEFAULT NULL,
  `editable` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `field_id_code` (`field_id`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.sys_field_code 的数据：~23 rows (大约)
/*!40000 ALTER TABLE `sys_field_code` DISABLE KEYS */;
INSERT INTO `sys_field_code` (`id`, `field_id`, `code`, `code_desc`, `enable`, `sortno`, `editable`) VALUES
	(1, 1, '0', '未知', 1, 1, 0),
	(2, 1, '1', '男', 1, 2, 0),
	(3, 1, '2', '女', 1, 3, 0),
	(4, 2, '0', '激活', 1, 1, 0),
	(5, 2, '1', '锁定', 1, 2, 0),
	(6, 3, '1', '业务角色', 1, 1, 0),
	(7, 3, '2', '管理角色', 1, 2, 0),
	(8, 3, '3', '系统内置角色', 1, 3, 0),
	(9, 4, '0', '树枝节点', 1, 1, 0),
	(10, 4, '1', '叶子节点', 1, 2, 0),
	(11, 5, '0', '只读', 1, 1, 0),
	(12, 5, '1', '可编辑', 1, 2, 0),
	(13, 6, '0', '禁用', 1, 1, 0),
	(14, 6, '1', '启用', 1, 2, 0),
	(15, 7, '1', '访问权限', 1, 1, 0),
	(16, 7, '2', '管理权限', 1, 2, 0),
	(17, 8, '1', '系统菜单', 1, 1, 0),
	(18, 8, '2', '业务菜单', 1, 2, 0),
	(19, 9, '1', '经办员', 1, 1, 0),
	(20, 9, '2', '管理员', 1, 2, 0),
	(21, 9, '3', '系统内置用户', 1, 3, 0),
	(22, 10, '0', '收缩', 1, 1, 0),
	(23, 10, '1', '展开', 1, 2, 0);
/*!40000 ALTER TABLE `sys_field_code` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` int(1) DEFAULT '0' COMMENT '菜单类型(1:系统菜单;0:业务菜单)',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级菜单编号',
  `iconcls` varchar(50) DEFAULT NULL COMMENT '节点图标CSS类名',
  `request` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `expand` int(1) NOT NULL DEFAULT '0' COMMENT '展开状态(1:展开;0:收缩)',
  `sortno` int(2) DEFAULT NULL COMMENT '排序号',
  `leaf` int(1) NOT NULL DEFAULT '0' COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

-- 正在导出表  ibase4j.sys_menu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_param 结构
CREATE TABLE IF NOT EXISTS `sys_param` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '参数编号',
  `param_key` varchar(50) DEFAULT NULL COMMENT '参数键名',
  `param_value` varchar(100) DEFAULT NULL COMMENT '参数键值',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全局参数表';

-- 正在导出表  ibase4j.sys_param 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_param` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_param` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `dept_id` int(20) DEFAULT NULL COMMENT '所属部门编号',
  `role_type` int(1) NOT NULL DEFAULT '1' COMMENT '角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)',
  `enable` int(1) NOT NULL DEFAULT '1',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- 正在导出表  ibase4j.sys_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT NULL,
  `menu_id` int(20) DEFAULT NULL,
  `authorize` int(1) NOT NULL DEFAULT '1' COMMENT '权限级别(1:访问权限;2:管理权限)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id_menu_id` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色授权表';

-- 正在导出表  ibase4j.sys_role_menu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL COMMENT '登陆帐户',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `sex` int(1) NOT NULL DEFAULT '0' COMMENT '性别(0:未知;1:男;2:女)',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `avatar` varchar(50) DEFAULT NULL,
  `user_type` int(1) DEFAULT '1' COMMENT '人员类型(1:经办员;2:管理员;3:系统内置人员;)',
  `dept_id` int(20) DEFAULT '1' COMMENT '部门编号',
  `locked` int(1) DEFAULT '0' COMMENT '锁定标志(1:锁定;0:激活)',
  `usable` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.sys_user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `account`, `password`, `sex`, `user_name`, `avatar`, `user_type`, `dept_id`, `locked`, `usable`) VALUES
	(2, 'admin', 'Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE=', 0, NULL, NULL, 3, 1, 0, 1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_user_menu 结构
CREATE TABLE IF NOT EXISTS `sys_user_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `menu_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_menu_id` (`user_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- 正在导出表  ibase4j.sys_user_menu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_menu` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` int(20) NOT NULL,
  `user_id` int(20) DEFAULT NULL,
  `role_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_role_id` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- 正在导出表  ibase4j.sys_user_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
