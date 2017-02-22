-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.10 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.4.0.5143
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 ibase4j 的数据库结构
DROP DATABASE IF EXISTS `ibase4j`;
CREATE DATABASE IF NOT EXISTS `ibase4j` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ibase4j`;

-- 导出  表 ibase4j.qrtz_fired_triggers 结构
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 导出  表 ibase4j.qrtz_job_details 结构
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE IF NOT EXISTS `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 导出  表 ibase4j.qrtz_locks 结构
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE IF NOT EXISTS `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 导出  表 ibase4j.qrtz_paused_trigger_grps 结构
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE IF NOT EXISTS `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 导出  表 ibase4j.qrtz_scheduler_state 结构
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE IF NOT EXISTS `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 导出  表 ibase4j.qrtz_simple_triggers 结构
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 导出  表 ibase4j.qrtz_simprop_triggers 结构
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 导出  表 ibase4j.qrtz_triggers 结构
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 导出  表 ibase4j.qrtz_cron_triggers 结构
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE IF NOT EXISTS `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 导出  表 ibase4j.sys_dept 结构
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `id_` bigint(20) NOT NULL COMMENT '部门编号',
  `unit_id` bigint(20) NOT NULL COMMENT '隶属单位',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门编号',
  `sort_no` int(3) DEFAULT NULL COMMENT '排序号',
  `leaf_` int(1) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

-- 正在导出表  ibase4j.sys_dept 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` (`id_`, `unit_id`, `dept_name`, `parent_id`, `sort_no`, `leaf_`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 1, 'iBase4J', 0, 1, 0, 1, 'qw', 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:06'),
	(2, 1, '市场部', 1, 1, 1, 1, 't', 0, '2016-06-28 18:04:06', 0, '2016-06-28 18:04:06'),
	(825363166504628224, 1, '技术部', 1, 2, NULL, NULL, NULL, 1, '2017-01-28 23:21:28', 1, '2017-01-28 23:50:51');
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_dic 结构
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE IF NOT EXISTS `sys_dic` (
  `id_` bigint(20) NOT NULL,
  `type_` varchar(50) NOT NULL,
  `code_` varchar(50) NOT NULL,
  `code_text` varchar(100) NOT NULL,
  `sort_no` int(2) DEFAULT NULL,
  `editable_` tinyint(1) NOT NULL DEFAULT '1',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(500) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `field_id_code` (`type_`,`code_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典明细表';

-- 正在导出表  ibase4j.sys_dic 的数据：~43 rows (大约)
/*!40000 ALTER TABLE `sys_dic` DISABLE KEYS */;
INSERT INTO `sys_dic` (`id_`, `type_`, `code_`, `code_text`, `sort_no`, `editable_`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 'SEX', '0', '未知', 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:13'),
	(2, 'SEX', '1', '男', 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:12'),
	(3, 'SEX', '2', '女', 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:11'),
	(4, 'LOCKED', '0', '激活', 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:11'),
	(5, 'LOCKED', '1', '锁定', 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:10'),
	(6, 'ROLETYPE', '1', '业务角色', 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:09'),
	(7, 'ROLETYPE', '2', '管理角色', 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:09'),
	(8, 'ROLETYPE', '3', '系统内置角色', 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:08'),
	(9, 'LEAF', '0', '树枝节点', 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:07'),
	(10, 'LEAF', '1', '叶子节点', 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:07'),
	(11, 'EDITABLE', '0', '只读', 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:06'),
	(12, 'EDITABLE', '1', '可编辑', 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:06'),
	(13, 'ENABLE', '0', '禁用', 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:05'),
	(14, 'ENABLE', '1', '启用', 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:04'),
	(15, 'AUTHORIZELEVEL', '1', '访问权限', 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:03'),
	(16, 'AUTHORIZELEVEL', '2', '管理权限', 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:02'),
	(17, 'MENUTYPE', '1', '系统菜单', 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:03'),
	(18, 'MENUTYPE', '2', '业务菜单', 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:01'),
	(19, 'USERTYPE', '1', '经办员', 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:50'),
	(20, 'USERTYPE', '2', '管理员', 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:48'),
	(21, 'USERTYPE', '3', '系统内置用户', 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:47'),
	(22, 'EXPAND', '0', '收缩', 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:47'),
	(23, 'EXPAND', '1', '展开', 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:46'),
	(24, 'CRUD', 'add', '新增', 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:56'),
	(25, 'CRUD', 'read', '查询', 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:58'),
	(26, 'CRUD', 'update', '修改', 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:59'),
	(27, 'CRUD', 'delete', '删除', 4, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:59'),
	(28, 'CRUD', 'open', '打开', 5, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:00'),
	(29, 'CRUD', 'close', '关闭', 6, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:01'),
	(30, 'CRUD', 'run', '执行', 7, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:01'),
	(31, 'NEWSTYPE', '1', '国内新闻', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(32, 'NEWSTYPE', '2', '国际新闻', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(33, 'NEWSTYPE', '3', '社会新闻', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(34, 'NEWSTYPE', '4', '体育新闻', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(35, 'NEWSTYPE', '5', '娱乐新闻', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(36, 'NEWSTYPE', '6', '科技新闻', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(37, 'NEWSTYPE', '7', '行业新闻', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(38, 'NEWSTYPE', '8', '其他新闻', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(39, 'NOTICETYPE', '1', '会议通知', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(40, 'NOTICETYPE', '2', '活动公告', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(41, 'NOTICETYPE', '3', '社会公告', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(42, 'NOTICETYPE', '4', '内部公告', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(43, 'NOTICETYPE', '5', '其他公告', 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46');
/*!40000 ALTER TABLE `sys_dic` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_email 结构
DROP TABLE IF EXISTS `sys_email`;
CREATE TABLE IF NOT EXISTS `sys_email` (
  `id_` bigint(20) NOT NULL COMMENT '邮件编号',
  `email_name` varchar(128) NOT NULL COMMENT '邮件名称',
  `sender_` varchar(32) NOT NULL COMMENT '使用发送',
  `email_title` varchar(256) NOT NULL COMMENT '发送标题',
  `email_content` text NOT NULL COMMENT '发送内容',
  `remark_` varchar(1024) DEFAULT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件表';

-- 正在导出表  ibase4j.sys_email 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_email` DISABLE KEYS */;
INSERT INTO `sys_email` (`id_`, `email_name`, `sender_`, `email_title`, `email_content`, `remark_`, `enable_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 'test', 't', 'a', '并蒂芙蓉', NULL, 1, 1, '2017-02-02 16:37:54', 1, '2017-02-02 16:37:54');
/*!40000 ALTER TABLE `sys_email` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_email_config 结构
DROP TABLE IF EXISTS `sys_email_config`;
CREATE TABLE IF NOT EXISTS `sys_email_config` (
  `id_` bigint(20) NOT NULL COMMENT '邮件配置编号',
  `smtp_host` varchar(32) NOT NULL COMMENT 'SMTP服务器',
  `smtp_port` varchar(8) NOT NULL COMMENT 'SMTP服务器端口',
  `send_method` varchar(16) NOT NULL COMMENT '发送方式',
  `sender_name` varchar(64) NOT NULL COMMENT '名称',
  `sender_account` varchar(32) NOT NULL COMMENT '发邮件邮箱账号',
  `sender_password` varchar(32) NOT NULL COMMENT '发邮件邮箱密码',
  `remark_` varchar(1024) DEFAULT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件配置表';

-- 正在导出表  ibase4j.sys_email_config 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_email_config` DISABLE KEYS */;
INSERT INTO `sys_email_config` (`id_`, `smtp_host`, `smtp_port`, `send_method`, `sender_name`, `sender_account`, `sender_password`, `remark_`, `enable_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(828157583909109760, 'smtp.163.com', '101', '0', 'iBase4J', 'iBase4J@163.com', 'BK5sgjz5JOOsFuD4w0mbe7==', NULL, 1, 1, '2017-02-05 16:25:29', 1, '2017-02-05 16:37:50');
/*!40000 ALTER TABLE `sys_email_config` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_email_template 结构
DROP TABLE IF EXISTS `sys_email_template`;
CREATE TABLE IF NOT EXISTS `sys_email_template` (
  `id_` bigint(20) NOT NULL COMMENT '邮件模版编号',
  `email_name` varchar(128) NOT NULL COMMENT '邮件名称',
  `email_account` varchar(32) DEFAULT NULL COMMENT '发送邮件帐号',
  `sort_` int(5) DEFAULT NULL COMMENT '排序号',
  `title_` varchar(512) DEFAULT NULL COMMENT '标题模版',
  `template_` text COMMENT '内容模板',
  `remark_` varchar(1024) DEFAULT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件模版表';

-- 正在导出表  ibase4j.sys_email_template 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_email_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_email_template` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_event 结构
DROP TABLE IF EXISTS `sys_event`;
CREATE TABLE IF NOT EXISTS `sys_event` (
  `id_` bigint(20) NOT NULL,
  `title_` varchar(50) DEFAULT NULL,
  `request_uri` varchar(50) DEFAULT NULL,
  `parameters_` varchar(500) DEFAULT NULL,
  `method_` varchar(20) DEFAULT NULL,
  `client_host` varchar(50) DEFAULT NULL,
  `user_agent` varchar(300) DEFAULT NULL,
  `status_` int(3) DEFAULT NULL,
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` text,
  `create_by` bigint(20) NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- 正在导出表  ibase4j.sys_event 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_event` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_menu 结构
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id_` bigint(20) NOT NULL COMMENT '菜单编号',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` tinyint(1) DEFAULT '2' COMMENT '菜单类型(0:CURD;1:系统菜单;2:业务菜单;)',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单编号',
  `iconcls_` varchar(50) DEFAULT NULL COMMENT '节点图标CSS类名',
  `request_` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `expand_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '展开状态(1:展开;0:收缩)',
  `sort_no` int(2) DEFAULT NULL COMMENT '排序号',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `permission_` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `remark_` varchar(1024) DEFAULT NULL COMMENT '备注',
  `enable_` tinyint(1) DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

-- 导出  表 ibase4j.sys_news 结构
DROP TABLE IF EXISTS `sys_news`;
CREATE TABLE IF NOT EXISTS `sys_news` (
  `id_` bigint(20) NOT NULL COMMENT '新闻编号',
  `news_title` varchar(64) NOT NULL COMMENT '新闻标题',
  `news_type` varchar(8) NOT NULL COMMENT '新闻类型',
  `send_time` datetime NOT NULL COMMENT '发布时间',
  `author_` varchar(32) NOT NULL COMMENT '作者',
  `editor_` varchar(32) NOT NULL COMMENT '编辑',
  `tags_` varchar(128) DEFAULT NULL COMMENT 'Tag标签',
  `keys_` varchar(128) DEFAULT NULL COMMENT '关键字',
  `content_` text COMMENT '内容',
  `reader_times` int(11) NOT NULL DEFAULT '0' COMMENT '阅读次数',
  `status_` varchar(2) NOT NULL DEFAULT '1' COMMENT '发布状态',
  `remark_` varchar(1024) DEFAULT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='新闻表';

-- 正在导出表  ibase4j.sys_news 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_news` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_notice 结构
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE IF NOT EXISTS `sys_notice` (
  `id_` bigint(20) NOT NULL COMMENT '公告编号',
  `notice_title` varchar(128) NOT NULL COMMENT '公告标题',
  `notice_type` varchar(8) NOT NULL COMMENT '公告类型',
  `send_time` datetime DEFAULT NULL COMMENT '发布时间',
  `info_sources` varchar(256) DEFAULT NULL COMMENT '信息来源',
  `sources_url` varchar(2048) DEFAULT NULL COMMENT '来源地址',
  `content_` text COMMENT '内容',
  `reader_times` int(11) NOT NULL DEFAULT '0' COMMENT '阅读次数',
  `status_` varchar(2) NOT NULL DEFAULT '1' COMMENT '发布状态',
  `remark_` varchar(1024) DEFAULT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通知公告表';

-- 正在导出表  ibase4j.sys_notice 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_param 结构
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE IF NOT EXISTS `sys_param` (
  `id_` bigint(20) NOT NULL COMMENT '参数编号',
  `param_key` varchar(50) DEFAULT NULL COMMENT '参数键名',
  `param_value` varchar(100) DEFAULT NULL COMMENT '参数键值',
  `remark_` varchar(1024) DEFAULT NULL COMMENT '备注',
  `enable_` tinyint(1) DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全局参数表';

-- 正在导出表  ibase4j.sys_param 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_param` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_param` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_role 结构
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id_` bigint(20) NOT NULL COMMENT '角色编号',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属部门编号',
  `role_type` int(1) NOT NULL DEFAULT '1' COMMENT '角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(1024) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- 正在导出表  ibase4j.sys_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id_`, `role_name`, `dept_id`, `role_type`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, '管理员', 1, 1, 1, NULL, 1, '2016-06-20 09:16:56', 1, '2017-01-29 10:11:20');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_role_menu 结构
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id_` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission_` varchar(50) NOT NULL COMMENT '权限标识',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(1024) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `sys_role_menu_key1` (`role_id`,`menu_id`,`permission_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色授权表';

-- 导出  表 ibase4j.sys_session 结构
DROP TABLE IF EXISTS `sys_session`;
CREATE TABLE IF NOT EXISTS `sys_session` (
  `id_` bigint(20) NOT NULL,
  `session_id` varchar(50) DEFAULT NULL,
  `account_` varchar(50) DEFAULT NULL,
  `ip_` varchar(50) DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(1024) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会话管理';

-- 导出  表 ibase4j.sys_unit 结构
DROP TABLE IF EXISTS `sys_unit`;
CREATE TABLE IF NOT EXISTS `sys_unit` (
  `id_` bigint(20) NOT NULL,
  `unit_name` varchar(128) NOT NULL COMMENT '单位名称',
  `principal_` varchar(32) DEFAULT NULL COMMENT '负责人',
  `phone_` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `address_` varchar(256) DEFAULT NULL COMMENT '地址',
  `sort_` int(5) DEFAULT NULL COMMENT '排序号',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(1024) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单位表';

-- 正在导出表  ibase4j.sys_unit 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_unit` DISABLE KEYS */;
INSERT INTO `sys_unit` (`id_`, `unit_name`, `principal_`, `phone_`, `address_`, `sort_`, `enable_`, `remark_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 'iBase4J', '经理', '13945678911', '中国', 1, NULL, NULL, '2017-01-12 00:00:00', 1, '2017-01-28 23:51:57', 1);
/*!40000 ALTER TABLE `sys_unit` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_user 结构
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id_` bigint(20) NOT NULL,
  `account_` varchar(20) DEFAULT NULL COMMENT '登陆帐户',
  `password_` varchar(50) DEFAULT NULL COMMENT '密码',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `name_pinyin` varchar(64) DEFAULT NULL COMMENT '姓名拼音',
  `sex_` int(1) NOT NULL DEFAULT '0' COMMENT '性别(0:未知;1:男;2:女)',
  `avatar_` varchar(500) DEFAULT NULL,
  `user_type` int(1) DEFAULT '1' COMMENT '人员类型(1:经办员;2:管理员;3:系统内置人员;)',
  `phone_` varchar(50) DEFAULT NULL COMMENT '电话',
  `email_` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `birth_day` date DEFAULT NULL COMMENT '出生日期',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门编号',
  `position_` varchar(64) DEFAULT NULL COMMENT '职位',
  `address_` varchar(256) DEFAULT NULL COMMENT '详细地址',
  `staff_no` varchar(32) DEFAULT NULL COMMENT '工号',
  `locked_` tinyint(1) DEFAULT '0' COMMENT '锁定标志(1:锁定;0:激活)',
  `enable_` tinyint(1) DEFAULT '1',
  `remark_` varchar(1024) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `account` (`account_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理';

-- 正在导出表  ibase4j.sys_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id_`, `account_`, `password_`, `user_name`, `name_pinyin`, `sex_`, `avatar_`, `user_type`, `phone_`, `email_`, `birth_day`, `dept_id`, `position_`, `address_`, `staff_no`, `locked_`, `enable_`, `remark_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 'admin', 'i/sV2VpTPy7Y+ppesmkCmM==', '管理员', 'GUANLIYUAN', 0, '', 3, '15333821711', '12@12', '2017-01-27', 1, '213', NULL, NULL, 0, 1, '1', '2016-05-06 10:06:52', 1, '2017-01-27 15:45:31', 1),
	(2, 'test', 'i/sV2VpTPy7Y+ppesmkCmM==', 'test', NULL, 0, NULL, 1, '12345678901', NULL, NULL, 1, NULL, NULL, NULL, 0, 1, '1', '2016-05-13 16:58:17', 1, '2016-06-13 14:04:38', 1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_user_menu 结构
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE IF NOT EXISTS `sys_user_menu` (
  `id_` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission_` varchar(50) NOT NULL COMMENT '权限标识',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(1024) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `sys_user_menu_key1` (`user_id`,`menu_id`,`permission_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- 正在导出表  ibase4j.sys_user_menu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_menu` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_user_role 结构
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id_` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(1024) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_role_id` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- 正在导出表  ibase4j.sys_user_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id_`, `user_id`, `role_id`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 1, 1, 1, NULL, 1, '2016-06-16 15:59:56', 1, '2016-06-16 15:59:56');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_user_thirdparty 结构
DROP TABLE IF EXISTS `sys_user_thirdparty`;
CREATE TABLE IF NOT EXISTS `sys_user_thirdparty` (
  `id_` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `provider_` varchar(50) NOT NULL COMMENT '第三方类型',
  `open_id` varchar(50) NOT NULL COMMENT '第三方Id',
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(1024) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_provider__open_id` (`user_id`,`provider_`,`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方用户';

-- 正在导出表  ibase4j.sys_user_thirdparty 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_thirdparty` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_thirdparty` ENABLE KEYS */;

-- 导出  表 ibase4j.task_fire_log 结构
DROP TABLE IF EXISTS `task_fire_log`;
CREATE TABLE IF NOT EXISTS `task_fire_log` (
  `id_` bigint(20) NOT NULL,
  `group_name` varchar(50) NOT NULL,
  `task_name` varchar(50) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `status_` varchar(1) NOT NULL DEFAULT 'I',
  `server_host` varchar(50) DEFAULT NULL COMMENT '服务器名',
  `server_duid` varchar(50) DEFAULT NULL COMMENT '服务器网卡序列号',
  `fire_info` text,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `group_name_task_name_start_time` (`group_name`,`task_name`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志表';