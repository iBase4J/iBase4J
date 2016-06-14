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
DROP DATABASE IF EXISTS `ibase4j`;
CREATE DATABASE IF NOT EXISTS `ibase4j` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ibase4j`;


-- 导出  表 ibase4j.sys_catalog 结构
DROP TABLE IF EXISTS `sys_catalog`;
CREATE TABLE IF NOT EXISTS `sys_catalog` (
  `id_` int(20) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `cascade_id` varchar(255) NOT NULL COMMENT '节点语义ID',
  `root_key` varchar(255) NOT NULL COMMENT '科目标识键',
  `root_name` varchar(255) NOT NULL COMMENT '科目名称',
  `name_` varchar(255) NOT NULL COMMENT '分类名称',
  `hotkey_` varchar(255) DEFAULT NULL COMMENT '热键',
  `parent_id` varchar(64) NOT NULL COMMENT '父节点流水号',
  `is_leaf_` varchar(255) NOT NULL DEFAULT '1' COMMENT '是否叶子节点',
  `is_auto_expand` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否自动展开',
  `icon_name` varchar(255) DEFAULT NULL COMMENT '图标文件名称',
  `sort_no` int(10) DEFAULT NULL COMMENT '排序号',
  `enable_` int(1) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` int(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `sys_catalog_ukey` (`cascade_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='分类表';

-- 正在导出表  ibase4j.sys_catalog 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `sys_catalog` DISABLE KEYS */;
INSERT INTO `sys_catalog` (`id_`, `cascade_id`, `root_key`, `root_name`, `name_`, `hotkey_`, `parent_id`, `is_leaf_`, `is_auto_expand`, `icon_name`, `sort_no`, `enable_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, '0.001', 'PARAM_TYPE', '参数分类科目', '参数分类', NULL, '0', '0', '0', 'book.png', 0, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL),
	(2, '0.002', 'DIC_TYPE', '词典分类科目', '数据字典分类', NULL, '0', '0', '0', 'book.png', 2, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL),
	(3, '0.001.001', 'PARAM_TYPE', '参数分类科目', '业务参数', NULL, '1', '1', '0', 'user20.png', 2, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL),
	(4, '0.001.002', 'PARAM_TYPE', '参数分类科目', '系统参数', NULL, '1', '0', '1', 'folder22.png', 1, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL),
	(5, '0.002.001', 'DIC_TYPE', '词典分类科目', '系统管理', NULL, '2', '0', '1', 'folder22.png', 2, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL),
	(6, '0.002.002', 'DIC_TYPE', '词典分类科目', '全局通用', NULL, '2', '1', '0', 'folder24.png', 3, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL),
	(7, '0.002.006', 'DIC_TYPE', '词典分类科目', '平台配置', NULL, '2', '1', '0', 'folder2.png', 1, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL),
	(8, '0.001.002.001', 'PARAM_TYPE', '参数分类科目', '验证码', NULL, '4', '1', '0', 'ok3.png', 2, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL),
	(9, '0.001.002.002', 'PARAM_TYPE', '参数分类科目', '界面显示', NULL, '4', '1', '0', 'icon59.png', 1, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL),
	(10, '0.001.002.003', 'PARAM_TYPE', '参数分类科目', '其它', NULL, '4', '1', '0', 'icon150.png', 9, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL),
	(11, '0.001.002.004', 'PARAM_TYPE', '参数分类科目', '导航与菜单', NULL, '4', '1', '0', 'icon152.png', 3, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL),
	(12, '0.002.001.001', 'DIC_TYPE', '词典分类科目', '工作流', NULL, '5', '1', '0', 'folder6.png', 1, 1, '2016-06-13 14:07:29', NULL, '2016-06-13 14:07:29', NULL);
/*!40000 ALTER TABLE `sys_catalog` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_dept 结构
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `id_` int(20) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级部门编号',
  `enable_` int(1) DEFAULT NULL COMMENT '启用状态',
  `sort_no` int(3) DEFAULT NULL COMMENT '排序号',
  `leaf_` int(1) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` int(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='部门';

-- 正在导出表  ibase4j.sys_dept 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` (`id_`, `dept_name`, `parent_id`, `enable_`, `sort_no`, `leaf_`, `remark_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 'iBase4J', 0, 1, 1, 0, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_dic 结构
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE IF NOT EXISTS `sys_dic` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `index_id` int(20) DEFAULT NULL,
  `code_` varchar(50) DEFAULT NULL,
  `code_text` varchar(100) DEFAULT NULL,
  `enable_` int(1) NOT NULL DEFAULT '1',
  `sort_no` int(2) DEFAULT NULL,
  `editable_` int(1) NOT NULL DEFAULT '1',
  `remark_` varchar(500) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` int(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `field_id_code` (`index_id`,`code_`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.sys_dic 的数据：~27 rows (大约)
/*!40000 ALTER TABLE `sys_dic` DISABLE KEYS */;
INSERT INTO `sys_dic` (`id_`, `index_id`, `code_`, `code_text`, `enable_`, `sort_no`, `editable_`, `remark_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 1, '0', '未知', 1, 1, 0, '1', NULL, NULL, NULL, NULL),
	(2, 1, '1', '男', 1, 2, 0, '1', NULL, NULL, NULL, NULL),
	(3, 1, '2', '女', 1, 3, 0, '1', NULL, NULL, NULL, NULL),
	(4, 2, '0', '激活', 1, 1, 0, '1', NULL, NULL, NULL, NULL),
	(5, 2, '1', '锁定', 1, 2, 0, '1', NULL, NULL, NULL, NULL),
	(6, 3, '1', '业务角色', 1, 1, 0, '1', NULL, NULL, NULL, NULL),
	(7, 3, '2', '管理角色', 1, 2, 0, '1', NULL, NULL, NULL, NULL),
	(8, 3, '3', '系统内置角色', 1, 3, 0, '1', NULL, NULL, NULL, NULL),
	(9, 4, '0', '树枝节点', 1, 1, 0, '1', NULL, NULL, NULL, NULL),
	(10, 4, '1', '叶子节点', 1, 2, 0, '1', NULL, NULL, NULL, NULL),
	(11, 5, '0', '只读', 1, 1, 0, '1', NULL, NULL, NULL, NULL),
	(12, 5, '1', '可编辑', 1, 2, 0, '1', NULL, NULL, NULL, NULL),
	(13, 6, '0', '禁用', 1, 1, 0, '1', NULL, NULL, NULL, NULL),
	(14, 6, '1', '启用', 1, 2, 0, '1', NULL, NULL, NULL, NULL),
	(15, 7, '1', '访问权限', 1, 1, 0, '1', NULL, NULL, NULL, NULL),
	(16, 7, '2', '管理权限', 1, 2, 0, '1', NULL, NULL, NULL, NULL),
	(17, 8, '1', '系统菜单', 1, 1, 0, '1', NULL, NULL, NULL, NULL),
	(18, 8, '2', '业务菜单', 1, 2, 0, '1', NULL, NULL, NULL, NULL),
	(19, 9, '1', '经办员', 1, 1, 0, '1', NULL, NULL, NULL, NULL),
	(20, 9, '2', '管理员', 1, 2, 0, '1', NULL, NULL, NULL, NULL),
	(21, 9, '3', '系统内置用户', 1, 3, 0, '1', NULL, NULL, NULL, NULL),
	(22, 10, '0', '收缩', 1, 1, 0, '1', NULL, NULL, NULL, NULL),
	(23, 10, '1', '展开', 1, 2, 0, '1', NULL, NULL, NULL, NULL),
	(24, 11, 'add', '新增', 1, 1, 0, NULL, NULL, NULL, NULL, NULL),
	(25, 11, 'read', '查询', 1, 2, 0, NULL, NULL, NULL, NULL, NULL),
	(26, 11, 'update', '修改', 1, 3, 0, NULL, NULL, NULL, NULL, NULL),
	(27, 11, 'delete', '删除', 1, 4, 0, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `sys_dic` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_dic_index 结构
DROP TABLE IF EXISTS `sys_dic_index`;
CREATE TABLE IF NOT EXISTS `sys_dic_index` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `catalog_id` int(20) NOT NULL DEFAULT '0',
  `key_` varchar(50) DEFAULT NULL,
  `name_` varchar(200) DEFAULT NULL,
  `remark_` varchar(1000) DEFAULT NULL,
  `enable_` int(1) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` int(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `code` (`key_`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='代码表';

-- 正在导出表  ibase4j.sys_dic_index 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `sys_dic_index` DISABLE KEYS */;
INSERT INTO `sys_dic_index` (`id_`, `catalog_id`, `key_`, `name_`, `remark_`, `enable_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 6, 'SEX', '性别', NULL, 1, NULL, NULL, NULL, NULL),
	(2, 6, 'LOCKED', '锁定', NULL, 1, NULL, NULL, NULL, NULL),
	(3, 5, 'ROLETYPE', '角色类型', NULL, 1, NULL, NULL, NULL, NULL),
	(4, 6, 'LEAF', '节点类型', NULL, 1, NULL, NULL, NULL, NULL),
	(5, 5, 'EDITABLE', '编辑模式', NULL, 1, NULL, NULL, NULL, NULL),
	(6, 5, 'ENABLE', '启用状态', NULL, 1, NULL, NULL, NULL, NULL),
	(7, 5, 'AUTHORIZELEVEL', '权限级别', NULL, 1, NULL, NULL, NULL, NULL),
	(8, 5, 'MENUTYPE', '菜单类型', NULL, 1, NULL, NULL, NULL, NULL),
	(9, 5, 'USERTYPE', '人员类型', NULL, 1, NULL, NULL, NULL, NULL),
	(10, 6, 'EXPAND', '展开状态', NULL, 1, NULL, NULL, NULL, NULL),
	(11, 5, 'CRUD', '操作类型', NULL, 1, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `sys_dic_index` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_menu 结构
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id_` int(20) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` int(1) DEFAULT '0' COMMENT '菜单类型(1:系统菜单;0:业务菜单)',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级菜单编号',
  `iconcls_` varchar(50) DEFAULT NULL COMMENT '节点图标CSS类名',
  `request_` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `expand_` int(1) NOT NULL DEFAULT '0' COMMENT '展开状态(1:展开;0:收缩)',
  `sort_no` int(2) DEFAULT NULL COMMENT '排序号',
  `leaf_` int(1) NOT NULL DEFAULT '0' COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `state_` varchar(50) NOT NULL COMMENT '路由状态',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `enable_` int(1) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` int(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='菜单';

-- 正在导出表  ibase4j.sys_menu 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`id_`, `menu_name`, `menu_type`, `parent_id`, `iconcls_`, `request_`, `expand_`, `sort_no`, `leaf_`, `state_`, `remark_`, `enable_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, '系统管理', 1, 0, 'glyphicon glyphicon-cog', '#', 0, 1, 0, '**.sys.**', NULL, 1, NULL, NULL, NULL, NULL),
	(2, '用户管理', 1, 1, 'glyphicon glyphicon-user', 'main.sys.user.list', 0, 1, 1, '**.sys.user.**', NULL, 1, NULL, NULL, NULL, NULL),
	(3, '部门管理', 1, 1, 'glyphicon glyphicon-flag', 'main.sys.dept.list', 0, 2, 1, '**.sys.dept.**', NULL, 1, NULL, NULL, NULL, NULL),
	(4, '菜单管理', 1, 1, 'glyphicon glyphicon-list-alt', 'main.sys.menu.list', 0, 3, 1, '**.sys.menu.**', NULL, 1, NULL, NULL, NULL, NULL),
	(5, '角色管理', 1, 1, 'glyphicon glyphicon-tags', 'main.sys.role.list', 0, 4, 1, '**.sys.role.**', NULL, 1, NULL, NULL, NULL, NULL),
	(6, '链接管理', 1, 1, 'glyphicon glyphicon-link', 'main.sys.permission.list', 0, 5, 1, '**.sys.permission.**', NULL, 1, NULL, NULL, NULL, NULL),
	(7, '会话管理', 1, 1, 'glyphicon glyphicon-earphone', 'main.sys.session.list', 0, 6, 1, '**.sys.session.**', NULL, 1, NULL, NULL, NULL, NULL),
	(8, '字典管理', 1, 1, 'glyphicon glyphicon-book', 'main.sys.dic.list', 0, 7, 1, '**.sys.dic.**', NULL, 1, NULL, NULL, NULL, NULL),
	(9, '参数管理', 1, 1, 'glyphicon glyphicon-wrench', 'main.sys.param.list', 0, 8, 1, '**.sys.param.**', NULL, 1, NULL, NULL, NULL, NULL),
	(10, '调度管理', 1, 0, 'glyphicon glyphicon-fire', '#', 0, 2, 0, '**.task.**', NULL, 1, NULL, NULL, NULL, NULL),
	(11, '任务组管理', 1, 10, 'glyphicon glyphicon-equalizer', 'main.task.group.list', 0, 1, 1, '**.task.group.**', NULL, 1, NULL, NULL, NULL, NULL),
	(12, '任务管理', 1, 10, 'glyphicon glyphicon-bookmark', 'main.task.scheduler.list', 0, 2, 1, '**.task.scheduler.**', NULL, 1, NULL, NULL, NULL, NULL),
	(13, '调度管理', 1, 10, 'glyphicon glyphicon-random', 'main.task.scheduled.list', 0, 3, 1, '**.task.scheduled.**', NULL, 1, NULL, NULL, NULL, NULL),
	(14, '调度日志', 1, 10, 'glyphicon glyphicon-duplicate', 'main.task.log.list', 0, 4, 1, '**.task.log.**', NULL, 1, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_param 结构
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE IF NOT EXISTS `sys_param` (
  `id_` int(20) NOT NULL AUTO_INCREMENT COMMENT '参数编号',
  `param_key` varchar(50) DEFAULT NULL COMMENT '参数键名',
  `param_value` varchar(100) DEFAULT NULL COMMENT '参数键值',
  `catalog_id` int(20) DEFAULT NULL,
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `enable_` int(1) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` int(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全局参数表';

-- 正在导出表  ibase4j.sys_param 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_param` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_param` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_permission 结构
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `permission_url` varchar(50) DEFAULT NULL,
  `permission_name` varchar(50) DEFAULT NULL,
  `enable_` int(1) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` int(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `permit_url` (`permission_url`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='权限';

-- 正在导出表  ibase4j.sys_permission 的数据：~21 rows (大约)
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` (`id_`, `permission_url`, `permission_name`, `enable_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, '/user/read/current', '获取当前用户', 1, NULL, NULL, NULL, NULL),
	(2, '/user/update', '修改用户信息', 1, NULL, NULL, NULL, NULL),
	(3, '/user/update/password', '修改用户密码', 1, NULL, NULL, NULL, NULL),
	(4, '/user/read/list', '获取用户列表', 1, NULL, NULL, NULL, NULL),
	(5, '/user/read/detail', '获取用户详情', 1, NULL, NULL, NULL, NULL),
	(6, '/session/read/list', '获取会话列表', 1, NULL, NULL, NULL, NULL),
	(7, '/session/delete', '删除会话', 1, NULL, NULL, NULL, NULL),
	(8, '/dept/read/list', '获取部门列表', 1, NULL, NULL, NULL, NULL),
	(9, '/menu/read/list', '获取菜单列表', 1, NULL, NULL, NULL, NULL),
	(10, '/role/read/list', '获取角色列表', 1, NULL, NULL, NULL, NULL),
	(11, '/permission/read/list', '获取链接列表', 1, NULL, NULL, NULL, NULL),
	(12, '/task/read/groups', '获取任务组列表', 1, NULL, NULL, NULL, NULL),
	(13, '/task/read/schedulers', '获取任务列表', 1, NULL, NULL, NULL, NULL),
	(14, '/scheduled/read/tasks', '获取调度列表', 1, NULL, NULL, NULL, NULL),
	(15, '/scheduled/read/log', '获取调度日志列表', 1, NULL, NULL, NULL, NULL),
	(16, '/scheduled/run/task', '执行任务', 1, NULL, NULL, NULL, NULL),
	(17, '/scheduled/open/task', '打开任务', 1, NULL, NULL, NULL, NULL),
	(18, '/scheduled/close/task', '关闭任务', 1, NULL, NULL, NULL, NULL),
	(19, '/param/read/list', '获取参数列表', 1, NULL, NULL, NULL, NULL),
	(20, '/dicIndex/read/list', '获取字典列表', 1, NULL, NULL, NULL, NULL),
	(21, '/dic/read/list', '获取字典列表', 1, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_role 结构
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id_` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `dept_id` int(20) DEFAULT NULL COMMENT '所属部门编号',
  `role_type` int(1) NOT NULL DEFAULT '1' COMMENT '角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)',
  `enable_` int(1) NOT NULL DEFAULT '1',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` int(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- 正在导出表  ibase4j.sys_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id_`, `role_name`, `dept_id`, `role_type`, `enable_`, `remark_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, '管理员', 1, 1, 1, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_role_menu 结构
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT NULL,
  `menu_id` int(20) DEFAULT NULL,
  `operate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `operator_id` int(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `role_id_menu_id` (`role_id`,`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='角色授权表';

-- 正在导出表  ibase4j.sys_role_menu 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`id_`, `role_id`, `menu_id`, `operate_time`, `operator_id`) VALUES
	(1, 1, 1, '2016-05-10 08:43:39', 1),
	(2, 1, 2, '2016-05-10 08:44:02', 1),
	(3, 1, 3, '2016-05-10 08:44:17', 1),
	(4, 1, 4, '2016-05-10 08:44:23', 1),
	(5, 1, 5, '2016-05-17 17:30:22', 1),
	(6, 1, 6, '2016-05-17 17:30:35', 1),
	(7, 1, 7, '2016-05-17 17:30:48', 1),
	(8, 1, 8, '2016-05-17 17:30:57', 1),
	(9, 1, 9, '2016-05-17 17:31:18', 1),
	(10, 1, 10, '2016-05-17 17:31:18', 1),
	(11, 1, 11, '2016-05-17 17:31:18', 1),
	(12, 1, 12, '2016-05-17 17:31:18', 1),
	(13, 1, 13, '2016-05-17 17:31:18', 1),
	(14, 1, 14, '2016-05-17 17:31:18', 1);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_role_permission 结构
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE IF NOT EXISTS `sys_role_permission` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `permit_id_permit_type` (`permission_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='角色操作权限';

-- 正在导出表  ibase4j.sys_role_permission 的数据：~21 rows (大约)
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` (`id_`, `role_id`, `permission_id`) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 1, 3),
	(4, 1, 4),
	(5, 1, 5),
	(6, 1, 6),
	(7, 1, 7),
	(8, 1, 8),
	(9, 1, 9),
	(10, 1, 10),
	(11, 1, 11),
	(12, 1, 12),
	(13, 1, 13),
	(14, 1, 14),
	(15, 1, 15),
	(16, 1, 16),
	(17, 1, 17),
	(18, 1, 18),
	(19, 1, 19),
	(20, 1, 20),
	(21, 1, 21);
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_session 结构
DROP TABLE IF EXISTS `sys_session`;
CREATE TABLE IF NOT EXISTS `sys_session` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(50) DEFAULT NULL,
  `account_` varchar(50) DEFAULT NULL,
  `ip_` varchar(50) DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='会话管理';

-- 正在导出表  ibase4j.sys_session 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_session` DISABLE KEYS */;
INSERT INTO `sys_session` (`id_`, `session_id`, `account_`, `ip_`, `start_time`) VALUES
	(13, '881374c3-557a-4290-b241-9685e58d44f2', 'admin', '192.168.1.18', '2016-06-13 18:54:02');
/*!40000 ALTER TABLE `sys_session` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_user 结构
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `account_` varchar(20) DEFAULT NULL COMMENT '登陆帐户',
  `password_` varchar(50) DEFAULT NULL COMMENT '密码',
  `phone_` varchar(50) DEFAULT NULL COMMENT '电话',
  `sex_` int(1) NOT NULL DEFAULT '0' COMMENT '性别(0:未知;1:男;2:女)',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `avatar_` varchar(500) DEFAULT NULL,
  `user_type` int(1) DEFAULT '1' COMMENT '人员类型(1:经办员;2:管理员;3:系统内置人员;)',
  `dept_id` int(20) DEFAULT '1' COMMENT '部门编号',
  `locked_` int(1) DEFAULT '0' COMMENT '锁定标志(1:锁定;0:激活)',
  `enable_` int(1) DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` int(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `account` (`account_`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.sys_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id_`, `account_`, `password_`, `phone_`, `sex_`, `user_name`, `avatar_`, `user_type`, `dept_id`, `locked_`, `enable_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 'admin', 'i/sV2VpTPy7Y+ppesmkCmM==', '15333821711', 0, 'admin', 'res/img/favicon.jpg', 3, 1, 0, 1, '2016-05-06 10:06:52', NULL, '2016-06-13 14:04:38', NULL),
	(2, 'test', 'i/sV2VpTPy7Y+ppesmkCmM==', '12345678901', 0, 'test', NULL, 1, 1, 0, 1, '2016-05-13 16:58:17', NULL, '2016-06-13 14:04:38', NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_user_menu 结构
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE IF NOT EXISTS `sys_user_menu` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `menu_id` int(20) DEFAULT NULL,
  `operate_time` timestamp NULL DEFAULT NULL,
  `operator_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_menu_id` (`user_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- 正在导出表  ibase4j.sys_user_menu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_menu` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_user_permission 结构
DROP TABLE IF EXISTS `sys_user_permission`;
CREATE TABLE IF NOT EXISTS `sys_user_permission` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `permit_id_permit_type` (`permission_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户操作权限';

-- 正在导出表  ibase4j.sys_user_permission 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_permission` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_user_role 结构
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `role_id` int(20) DEFAULT NULL,
  `operate_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `operator_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_role_id` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- 正在导出表  ibase4j.sys_user_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id_`, `user_id`, `role_id`, `operate_time`, `operator_id`) VALUES
	(1, 1, 1, '2016-05-10 08:45:51', 1);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;


-- 导出  表 ibase4j.sys_user_thirdparty 结构
DROP TABLE IF EXISTS `sys_user_thirdparty`;
CREATE TABLE IF NOT EXISTS `sys_user_thirdparty` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `provider_` varchar(50) NOT NULL COMMENT '第三方类型',
  `open_id` varchar(50) NOT NULL COMMENT '第三方Id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_provider__open_id` (`user_id`,`provider_`,`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方用户';

-- 正在导出表  ibase4j.sys_user_thirdparty 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_thirdparty` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_thirdparty` ENABLE KEYS */;


-- 导出  表 ibase4j.task_fire_log 结构
DROP TABLE IF EXISTS `task_fire_log`;
CREATE TABLE IF NOT EXISTS `task_fire_log` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  `task_name` varchar(50) NOT NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT NULL,
  `status` varchar(1) NOT NULL DEFAULT 'I',
  `fire_info` text,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `group_name_task_name_start_time` (`group_name`,`task_name`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.task_fire_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `task_fire_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_fire_log` ENABLE KEYS */;


-- 导出  表 ibase4j.task_group 结构
DROP TABLE IF EXISTS `task_group`;
CREATE TABLE IF NOT EXISTS `task_group` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  `group_desc` varchar(50) NOT NULL,
  `enable_` int(1) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` int(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `group_name` (`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.task_group 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `task_group` DISABLE KEYS */;
INSERT INTO `task_group` (`id_`, `group_name`, `group_desc`, `enable_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 'sys.CoreTaskProvider', '系统管理', 1, '2016-05-27 14:56:51', 1, '2016-06-13 14:05:11', 1);
/*!40000 ALTER TABLE `task_group` ENABLE KEYS */;


-- 导出  表 ibase4j.task_scheduler 结构
DROP TABLE IF EXISTS `task_scheduler`;
CREATE TABLE IF NOT EXISTS `task_scheduler` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `group_id` int(20) NOT NULL,
  `task_name` varchar(50) NOT NULL,
  `task_type` varchar(50) NOT NULL,
  `task_desc` varchar(50) DEFAULT NULL,
  `task_cron` varchar(50) NOT NULL,
  `task_previous_fire_time` timestamp NULL DEFAULT NULL,
  `task_next_fire_time` timestamp NULL DEFAULT NULL,
  `contact_email` varchar(500) DEFAULT NULL COMMENT '多个邮箱用,分割',
  `enable_` int(1) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` int(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `group_id_task_name` (`group_id`,`task_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.task_scheduler 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `task_scheduler` DISABLE KEYS */;
INSERT INTO `task_scheduler` (`id_`, `group_id`, `task_name`, `task_type`, `task_desc`, `task_cron`, `task_previous_fire_time`, `task_next_fire_time`, `contact_email`, `enable_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 1, 'flushMessage', 'dubbo', '清理缓存国际化信息', '0 0/30 * * * ?', '2016-06-12 19:00:00', '2016-06-12 19:30:00', 'iBase4J@126.com', 1, '2016-06-13 14:05:30', NULL, '2016-06-14 10:57:27', NULL);
/*!40000 ALTER TABLE `task_scheduler` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
