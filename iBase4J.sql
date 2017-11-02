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
CREATE DATABASE IF NOT EXISTS `ibase4j` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ibase4j`;

-- 导出  表 ibase4j.qrtz_cron_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.qrtz_cron_triggers 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;

-- 导出  表 ibase4j.qrtz_fired_triggers 结构
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

-- 正在导出表  ibase4j.qrtz_fired_triggers 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;

-- 导出  表 ibase4j.qrtz_job_details 结构
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

-- 正在导出表  ibase4j.qrtz_job_details 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;

-- 导出  表 ibase4j.qrtz_locks 结构
CREATE TABLE IF NOT EXISTS `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.qrtz_locks 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
INSERT INTO `qrtz_locks` (`SCHED_NAME`, `LOCK_NAME`) VALUES
	('iBase4J-Scheduler', 'STATE_ACCESS'),
	('iBase4J-Scheduler', 'TRIGGER_ACCESS');
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;

-- 导出  表 ibase4j.qrtz_paused_trigger_grps 结构
CREATE TABLE IF NOT EXISTS `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.qrtz_paused_trigger_grps 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;

-- 导出  表 ibase4j.qrtz_scheduler_state 结构
CREATE TABLE IF NOT EXISTS `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.qrtz_scheduler_state 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
INSERT INTO `qrtz_scheduler_state` (`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES
	('iBase4J-Scheduler', 'ShenHuaJie-PC1495967340844', 1495971226462, 20000);
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;

-- 导出  表 ibase4j.qrtz_simple_triggers 结构
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

-- 正在导出表  ibase4j.qrtz_simple_triggers 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;

-- 导出  表 ibase4j.qrtz_simprop_triggers 结构
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

-- 正在导出表  ibase4j.qrtz_simprop_triggers 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;

-- 导出  表 ibase4j.qrtz_triggers 结构
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

-- 正在导出表  ibase4j.qrtz_triggers 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_article 结构
CREATE TABLE IF NOT EXISTS `sys_article` (
  `id_` bigint(20) NOT NULL,
  `type_` varchar(2) DEFAULT NULL COMMENT '类型',
  `author_` varchar(16) DEFAULT NULL COMMENT '作者',
  `title_` varchar(128) DEFAULT NULL COMMENT '标题',
  `content_` longtext COMMENT '内容',
  `out_url` varchar(512) DEFAULT NULL COMMENT '外部链接',
  `seo_keyword` varchar(64) DEFAULT NULL COMMENT 'seo关键字',
  `seo_description` varchar(256) DEFAULT NULL COMMENT 'seo描述',
  `is_top` tinyint(1) DEFAULT NULL COMMENT '是否置顶',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';

-- 正在导出表  ibase4j.sys_article 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_article` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_dept 结构
CREATE TABLE IF NOT EXISTS `sys_dept` (
  `id_` bigint(20) NOT NULL COMMENT '部门编号',
  `unit_id` bigint(20) NOT NULL COMMENT '隶属单位',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门编号',
  `sort_no` int(3) DEFAULT NULL COMMENT '排序号',
  `leaf_` int(1) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

-- 正在导出表  ibase4j.sys_dept 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` (`id_`, `unit_id`, `dept_name`, `parent_id`, `sort_no`, `leaf_`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 1, 'iBase4J', 0, 1, 0, 1, 'qw', 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:06'),
	(2, 1, '市场部', 1, 1, 1, 1, 't', 0, '2016-06-28 18:04:06', 0, '2016-06-28 18:04:06'),
	(825363166504628224, 1, '技术部', 1, 2, NULL, NULL, NULL, 1, '2017-01-28 23:21:28', 1, '2017-02-21 15:11:35');
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_dic 结构
CREATE TABLE IF NOT EXISTS `sys_dic` (
  `id_` bigint(20) NOT NULL,
  `type_` varchar(50) NOT NULL,
  `code_` varchar(50) DEFAULT NULL,
  `code_text` varchar(100) DEFAULT NULL,
  `parent_type` varchar(50) DEFAULT NULL,
  `parent_code` varchar(50) DEFAULT NULL,
  `sort_no` int(2) DEFAULT NULL,
  `editable_` tinyint(1) NOT NULL DEFAULT '1',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(500) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `type__code_` (`type_`,`code_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典明细表';

-- 正在导出表  ibase4j.sys_dic 的数据：~43 rows (大约)
/*!40000 ALTER TABLE `sys_dic` DISABLE KEYS */;
INSERT INTO `sys_dic` (`id_`, `type_`, `code_`, `code_text`, `parent_type`, `parent_code`, `sort_no`, `editable_`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 'SEX', '0', '未知', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:13'),
	(2, 'SEX', '1', '男', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:12'),
	(3, 'SEX', '2', '女', NULL, NULL, 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:11'),
	(4, 'LOCKED', '0', '激活', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:11'),
	(5, 'LOCKED', '1', '锁定', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:10'),
	(6, 'ROLETYPE', '1', '业务角色', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:09'),
	(7, 'ROLETYPE', '2', '管理角色', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:09'),
	(8, 'ROLETYPE', '3', '系统内置角色', NULL, NULL, 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:08'),
	(9, 'LEAF', '0', '树枝节点', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:07'),
	(10, 'LEAF', '1', '叶子节点', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:07'),
	(11, 'EDITABLE', '0', '只读', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:06'),
	(12, 'EDITABLE', '1', '可编辑', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:06'),
	(13, 'ENABLE', '0', '禁用', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:05'),
	(14, 'ENABLE', '1', '启用', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:04'),
	(15, 'AUTHORIZELEVEL', '1', '访问权限', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:03'),
	(16, 'AUTHORIZELEVEL', '2', '管理权限', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:02'),
	(17, 'MENUTYPE', '1', '系统菜单', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:03'),
	(18, 'MENUTYPE', '2', '业务菜单', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:01'),
	(19, 'USERTYPE', '1', '经办员', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:50'),
	(20, 'USERTYPE', '2', '管理员', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:48'),
	(21, 'USERTYPE', '3', '系统内置用户', NULL, NULL, 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:47'),
	(22, 'EXPAND', '0', '收缩', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:47'),
	(23, 'EXPAND', '1', '展开', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:46'),
	(24, 'CRUD', 'add', '新增', NULL, NULL, 1, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:56'),
	(25, 'CRUD', 'read', '查询', NULL, NULL, 2, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:58'),
	(26, 'CRUD', 'update', '修改', NULL, NULL, 3, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:59'),
	(27, 'CRUD', 'delete', '删除', NULL, NULL, 4, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:03:59'),
	(28, 'CRUD', 'open', '打开', NULL, NULL, 5, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:00'),
	(29, 'CRUD', 'close', '关闭', NULL, NULL, 6, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:01'),
	(30, 'CRUD', 'run', '执行', NULL, NULL, 7, 0, 1, NULL, 1, '2016-06-28 18:04:06', 1, '2016-06-28 18:04:01'),
	(31, 'NEWSTYPE', '1', '国内新闻', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(32, 'NEWSTYPE', '2', '国际新闻', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(33, 'NEWSTYPE', '3', '社会新闻', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(34, 'NEWSTYPE', '4', '体育新闻', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(35, 'NEWSTYPE', '5', '娱乐新闻', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(36, 'NEWSTYPE', '6', '科技新闻', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(37, 'NEWSTYPE', '7', '行业新闻', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(38, 'NEWSTYPE', '8', '其他新闻', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(39, 'NOTICETYPE', '1', '会议通知', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(40, 'NOTICETYPE', '2', '活动公告', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(41, 'NOTICETYPE', '3', '社会公告', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(42, 'NOTICETYPE', '4', '内部公告', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46'),
	(43, 'NOTICETYPE', '5', '其他公告', NULL, NULL, 1, 1, 1, NULL, 0, '2017-02-02 12:52:46', 0, '2017-02-02 12:52:46');
/*!40000 ALTER TABLE `sys_dic` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_email 结构
CREATE TABLE IF NOT EXISTS `sys_email` (
  `id_` bigint(20) NOT NULL COMMENT '邮件编号',
  `email_name` varchar(128) NOT NULL COMMENT '邮件名称',
  `sender_` varchar(32) NOT NULL COMMENT '使用发送',
  `email_title` varchar(256) NOT NULL COMMENT '发送标题',
  `email_content` text NOT NULL COMMENT '发送内容',
  `remark_` varchar(500) DEFAULT NULL,
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
CREATE TABLE IF NOT EXISTS `sys_email_config` (
  `id_` bigint(20) NOT NULL COMMENT '邮件配置编号',
  `smtp_host` varchar(32) NOT NULL COMMENT 'SMTP服务器',
  `smtp_port` varchar(8) NOT NULL COMMENT 'SMTP服务器端口',
  `send_method` varchar(16) NOT NULL COMMENT '发送方式',
  `sender_name` varchar(64) NOT NULL COMMENT '名称',
  `sender_account` varchar(32) NOT NULL COMMENT '发邮件邮箱账号',
  `sender_password` varchar(32) NOT NULL COMMENT '发邮件邮箱密码',
  `remark_` varchar(500) DEFAULT NULL,
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
CREATE TABLE IF NOT EXISTS `sys_email_template` (
  `id_` bigint(20) NOT NULL COMMENT '邮件模版编号',
  `email_name` varchar(128) NOT NULL COMMENT '邮件名称',
  `email_account` varchar(32) DEFAULT NULL COMMENT '发送邮件帐号',
  `sort_` int(5) DEFAULT NULL COMMENT '排序号',
  `title_` varchar(512) DEFAULT NULL COMMENT '标题模版',
  `template_` text COMMENT '内容模板',
  `remark_` varchar(500) DEFAULT NULL,
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
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.sys_event 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_event` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id_` bigint(20) NOT NULL COMMENT '菜单编号',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` smallint(2) DEFAULT '2' COMMENT '菜单类型(0:CURD;1:系统菜单;2:业务菜单;)',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单编号',
  `iconcls_` varchar(50) DEFAULT NULL COMMENT '节点图标CSS类名',
  `request_` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `expand_` tinyint(1) NOT NULL DEFAULT '0' COMMENT '展开状态(1:展开;0:收缩)',
  `sort_no` int(2) DEFAULT NULL COMMENT '排序号',
  `is_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `permission_` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `enable_` tinyint(1) DEFAULT '1',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

-- 正在导出表  ibase4j.sys_menu 的数据：~36 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`id_`, `menu_name`, `menu_type`, `parent_id`, `iconcls_`, `request_`, `expand_`, `sort_no`, `is_show`, `permission_`, `remark_`, `enable_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, '主控面板', 1, 0, 'icon-home4', '/main/welcome.html', 0, 1, 1, 'main.index', NULL, 1, 1, '2016-06-20 09:16:56', 2, '2017-03-18 17:09:08'),
	(2, '系统管理', 1, 0, 'glyphicon glyphicon-cog', '#', 0, 1, 1, 'sys', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 08:19:19'),
	(3, '基础管理', 1, 2, NULL, '#', 0, 1, 1, 'sys.base', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-20 09:16:56'),
	(4, '用户管理', 1, 3, NULL, '/main/admin/user.html', 0, 1, 1, 'sys.base.user', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-03-07 17:45:37'),
	(5, '部门管理', 1, 3, NULL, '/main/admin/department.html', 0, 2, 1, 'sys.base.dept', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-02-02 21:46:00'),
	(6, '单位管理', 1, 3, NULL, '/main/admin/unit.html', 0, 2, 1, 'sys.base.unit', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-03-07 17:45:51'),
	(7, '角色管理', 1, 3, NULL, '/main/admin/role.html', 0, 4, 1, 'sys.base.role', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-02-02 21:46:01'),
	(8, '菜单管理', 1, 3, NULL, '/main/admin/menu.html', 0, 5, 1, 'sys.base.menu', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-02-02 21:46:02'),
	(9, '会话管理', 1, 3, NULL, '/main/admin/session.html', 0, 6, 0, 'sys.base.session', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-02-02 21:46:03'),
	(10, '字典管理', 1, 3, NULL, '/main/admin/dic.html', 0, 7, 1, 'sys.base.dic', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-02-02 21:46:04'),
	(11, '参数管理', 1, 3, NULL, '/main/admin/param.html', 0, 8, 1, 'sys.base.param', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-02-02 21:46:05'),
	(12, '操作日志', 1, 3, NULL, '/main/admin/systemLog.html', 0, 9, 1, 'sys.base.event', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-20 09:16:56'),
	(13, '权限管理', 1, 2, NULL, '#', 0, 2, 1, '', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-20 09:16:56'),
	(14, '清除缓存', 1, 13, NULL, NULL, 0, 9, 0, 'sys.cache', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 09:39:25'),
	(15, '人员角色', 1, 13, NULL, '/main/admin/personalRole.html', 0, 1, 1, 'sys.permisson.userRole', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 09:39:25'),
	(16, '人员菜单', 1, 13, NULL, '/main/admin/personalMenu.html', 0, 2, 1, 'sys.permisson.userMenu', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 09:39:27'),
	(17, '角色菜单', 1, 13, NULL, '/main/admin/roleMenu.html', 0, 3, 1, 'sys.permisson.roleMenu', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 09:39:33'),
	(18, '人员操作权限', 1, 13, NULL, '/main/admin/personalPermission.html', 0, 4, 1, 'sys.permisson.user', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 09:39:29'),
	(19, '角色操作权限', 1, 13, NULL, '/main/admin/rolePermission.html', 0, 5, 1, 'sys.permisson.role', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 09:39:29'),
	(20, '公共信息管理', 1, 2, NULL, '#', 0, 3, 1, 'sys.cms', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-03-07 17:18:50'),
	(21, '新闻中心', 1, 20, NULL, '/main/cms/news.html', 0, 2, 1, 'sys.cms.news', NULL, 1, 1, '2017-01-29 12:56:57', 1, '2017-03-07 17:19:50'),
	(30, '邮件管理', 1, 2, NULL, '#', 0, 4, 1, 'sys.email', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-20 09:16:56'),
	(31, '邮件管理列表', 1, 30, NULL, '/main/admin/email.html', 0, 1, 1, 'sys.email.list', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-20 09:16:56'),
	(32, '邮件发送设置', 1, 30, NULL, '/main/admin/emailSend.html', 0, 2, 1, 'sys.email.config', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-02-03 14:55:15'),
	(33, '邮件模版设置', 1, 30, NULL, '/main/admin/emailTemplate.html', 0, 3, 1, 'sys.email.template', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-20 09:16:56'),
	(40, '任务调度', 1, 2, NULL, '#', 0, 6, 1, 'sys.task', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-03-08 11:08:49'),
	(41, '调度管理', 1, 40, NULL, '/main/admin/timedTask.html', 0, 1, 1, 'sys.task.scheduled', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-02-02 21:46:32'),
	(42, '调度日志', 1, 40, NULL, '/main/admin/taskLog.html', 0, 2, 1, 'sys.task.log', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-02-02 21:46:33'),
	(51, '消息管理', 1, 0, 'icon-envelop2', '#', 0, 10, 1, 'cms', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-03-07 17:42:37'),
	(53, '系统消息', 1, 51, '', '#', 0, 1, 1, 'cms.sys', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2017-05-28 18:56:03'),
	(54, '通知公告', 1, 53, NULL, '/main/cms/notice.html', 0, 1, 1, 'cms.notice', NULL, 1, 1, '2017-01-29 12:57:58', 2, '2017-03-12 18:55:52'),
	(839042852275175424, '文章管理', 2, 20, NULL, '/main/cms/article.html', 0, 1, 1, 'cms.article', NULL, 1, 1, '2017-03-07 17:19:39', 2, '2017-03-12 18:54:29'),
	(839042997045772288, '反馈管理', 2, 20, NULL, '/main/cms/feedback.html', 0, 3, 1, 'cms.feedback', NULL, 1, 1, '2017-03-07 17:20:14', 2, '2017-03-12 18:55:02'),
	(839046535817805824, '短信管理', 2, 2, NULL, '#', 0, 5, 1, NULL, NULL, 1, 1, '2017-03-07 17:34:18', 1, '2017-03-07 17:34:18'),
	(839046688716963840, '短信管理列表', 2, 839046535817805824, NULL, '/main/msg/msg.html', 0, 1, 1, 'msg.list', NULL, 1, 1, '2017-03-07 17:34:54', 2, '2017-03-14 11:16:29'),
	(839047074626486272, '短信发送设置', 2, 839046535817805824, NULL, '/main/msg/msgConfig.html', 0, 2, 1, 'msg.config', NULL, 1, 1, '2017-03-07 17:36:26', 2, '2017-03-14 11:16:42');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_msg 结构
CREATE TABLE IF NOT EXISTS `sys_msg` (
  `id_` bigint(20) NOT NULL,
  `biz_id` varchar(64) NOT NULL COMMENT '平台编号',
  `type_` varchar(32) NOT NULL COMMENT '类型',
  `phone_` varchar(20) NOT NULL COMMENT '接收短信号码',
  `content_` varchar(256) NOT NULL COMMENT '短信内容',
  `send_state` varchar(1) NOT NULL COMMENT '发送状态',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信';

-- 正在导出表  ibase4j.sys_msg 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_msg` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_msg` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_msg_config 结构
CREATE TABLE IF NOT EXISTS `sys_msg_config` (
  `id_` bigint(20) NOT NULL,
  `sms_plat_url` varchar(128) DEFAULT NULL COMMENT '短信平台地址',
  `sms_plat_account` varchar(32) DEFAULT NULL COMMENT '短信平台帐号',
  `sms_plat_password` varchar(64) DEFAULT NULL COMMENT '短信平台密码',
  `send_phone` varchar(11) DEFAULT NULL COMMENT '发送短信',
  `sender_name` varchar(32) DEFAULT NULL COMMENT '发送短信签名',
  `order_is_send` tinyint(1) DEFAULT NULL COMMENT '客户下订单时是否给商家发短信',
  `pay_is_send` tinyint(1) DEFAULT NULL COMMENT '客户付款时是否给商家发短信',
  `send_goods_is_send` tinyint(1) DEFAULT NULL COMMENT '商家发货时是否给客户发短信',
  `regist_is_send` tinyint(1) DEFAULT NULL COMMENT '用户注册时是否给客户发短信',
  `advice_goods_is_send` tinyint(1) DEFAULT NULL COMMENT '用户付款后是否给客户发收货验证码',
  `enable_` tinyint(1) DEFAULT NULL COMMENT '启用状态',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.sys_msg_config 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_msg_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_msg_config` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_news 结构
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
  `remark_` varchar(500) DEFAULT NULL,
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
  `remark_` varchar(500) DEFAULT NULL,
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
CREATE TABLE IF NOT EXISTS `sys_param` (
  `id_` bigint(20) NOT NULL COMMENT '参数编号',
  `param_key` varchar(50) DEFAULT NULL COMMENT '参数键名',
  `param_value` varchar(100) DEFAULT NULL COMMENT '参数键值',
  `catalog_id` bigint(20) DEFAULT NULL,
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
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
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id_` bigint(20) NOT NULL COMMENT '角色编号',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属部门编号',
  `role_type` int(1) NOT NULL DEFAULT '1' COMMENT '角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- 正在导出表  ibase4j.sys_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id_`, `role_name`, `dept_id`, `role_type`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, '管理员', 1, 1, 1, NULL, 1, '2016-06-20 09:16:56', 1, '2017-01-29 10:11:20');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id_` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission_` varchar(50) NOT NULL COMMENT '权限标识',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `sys_role_menu_key1` (`role_id`,`menu_id`,`permission_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色授权表';

-- 正在导出表  ibase4j.sys_role_menu 的数据：~100 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` (`id_`, `role_id`, `menu_id`, `permission_`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(868778355060875264, 1, 1, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355073458176, 1, 2, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355098624000, 1, 3, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355119595520, 1, 4, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355136372736, 1, 5, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355178315776, 1, 6, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355195092992, 1, 7, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355211870208, 1, 8, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355232841728, 1, 9, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355249618944, 1, 10, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355262201856, 1, 11, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355274784768, 1, 12, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355295756288, 1, 13, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355316727808, 1, 15, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355329310720, 1, 16, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355341893632, 1, 17, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355350282240, 1, 18, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355362865152, 1, 19, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355379642368, 1, 14, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355392225280, 1, 20, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355404808192, 1, 839042852275175424, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355417391104, 1, 21, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355425779712, 1, 839042997045772288, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355442556928, 1, 30, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355455139840, 1, 31, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355467722752, 1, 32, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355484499968, 1, 33, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355501277184, 1, 839046535817805824, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355513860096, 1, 839046688716963840, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355530637312, 1, 839047074626486272, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355539025920, 1, 40, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355555803136, 1, 41, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355568386048, 1, 42, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355589357568, 1, 51, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355597746176, 1, 53, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868778355614523392, 1, 54, 'read', 1, NULL, 1, '2017-05-28 18:37:56', 1, '2017-05-28 18:37:56'),
	(868784518229893120, 1, 4, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518238281728, 1, 4, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518246670336, 1, 4, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518255058944, 1, 5, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518263447552, 1, 5, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518267641856, 1, 5, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518280224768, 1, 6, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518297001984, 1, 6, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518313779200, 1, 6, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518322167808, 1, 7, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518330556416, 1, 7, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518338945024, 1, 7, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518347333632, 1, 8, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518376693760, 1, 8, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518427025408, 1, 8, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518447996928, 1, 9, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518464774144, 1, 9, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518477357056, 1, 9, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518489939968, 1, 10, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518519300096, 1, 10, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518540271616, 1, 10, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518569631744, 1, 11, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518594797568, 1, 11, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518611574784, 1, 11, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518615769088, 1, 15, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518674489344, 1, 16, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518699655168, 1, 17, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518724820992, 1, 18, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518741598208, 1, 19, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518749986816, 1, 14, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518762569728, 1, 839042852275175424, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518783541248, 1, 839042852275175424, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518800318464, 1, 839042852275175424, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518825484288, 1, 21, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518842261504, 1, 21, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518846455808, 1, 21, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518859038720, 1, 839042997045772288, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518871621632, 1, 839042997045772288, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518884204544, 1, 839042997045772288, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518892593152, 1, 31, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518913564672, 1, 31, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518947119104, 1, 31, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518972284928, 1, 32, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784518997450752, 1, 32, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519010033664, 1, 32, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519022616576, 1, 33, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519043588096, 1, 33, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519077142528, 1, 33, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519098114048, 1, 839046688716963840, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519110696960, 1, 839046688716963840, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519140057088, 1, 839046688716963840, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519156834304, 1, 839047074626486272, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519169417216, 1, 839047074626486272, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519190388736, 1, 839047074626486272, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519202971648, 1, 41, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519223943168, 1, 41, 'close', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519261691904, 1, 41, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519295246336, 1, 41, 'open', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519312023552, 1, 41, 'run', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519328800768, 1, 41, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519358160896, 1, 42, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519387521024, 1, 54, 'add', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519408492544, 1, 54, 'delete', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25'),
	(868784519421075456, 1, 54, 'update', 1, NULL, 1, '2017-05-28 19:02:25', 1, '2017-05-28 19:02:25');
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_session 结构
CREATE TABLE IF NOT EXISTS `sys_session` (
  `id_` bigint(20) NOT NULL,
  `session_id` varchar(50) DEFAULT NULL,
  `account_` varchar(50) DEFAULT NULL,
  `ip_` varchar(50) DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(5000) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会话管理';

-- 正在导出表  ibase4j.sys_session 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_session` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_unit 结构
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

-- 正在导出表  ibase4j.sys_unit 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_unit` DISABLE KEYS */;
INSERT INTO `sys_unit` (`id_`, `unit_name`, `principal_`, `phone_`, `address_`, `sort_`, `enable_`, `remark_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 'iBase4J', '经理', '13945678911', '中国', 1, NULL, NULL, '2017-01-12 00:00:00', 1, '2017-01-28 23:51:57', 1);
/*!40000 ALTER TABLE `sys_unit` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id_` bigint(20) NOT NULL,
  `account_` varchar(20) DEFAULT NULL COMMENT '登陆帐户',
  `password_` varchar(50) DEFAULT NULL COMMENT '密码',
  `user_type` varchar(2) DEFAULT '1' COMMENT '用户类型(1普通用户2管理员3系统用户)',
  `user_name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `name_pinyin` varchar(64) DEFAULT NULL COMMENT '姓名拼音',
  `sex_` int(1) NOT NULL DEFAULT '0' COMMENT '性别(0:未知;1:男;2:女)',
  `avatar_` varchar(500) DEFAULT NULL COMMENT '头像',
  `phone_` varchar(50) DEFAULT NULL COMMENT '电话',
  `email_` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `wei_xin` varchar(32) DEFAULT NULL COMMENT '微信',
  `wei_bo` varchar(32) DEFAULT NULL COMMENT '微博',
  `qq_` varchar(32) DEFAULT NULL COMMENT 'QQ',
  `birth_day` date DEFAULT NULL COMMENT '出生日期',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门编号',
  `position_` varchar(64) DEFAULT NULL COMMENT '职位',
  `address_` varchar(256) DEFAULT NULL COMMENT '详细地址',
  `staff_no` varchar(32) DEFAULT NULL COMMENT '工号',
  `enable_` tinyint(1) DEFAULT '1',
  `remark_` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `account` (`account_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理';

-- 正在导出表  ibase4j.sys_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id_`, `account_`, `password_`, `user_type`, `user_name`, `name_pinyin`, `sex_`, `avatar_`, `phone_`, `email_`, `id_card`, `wei_xin`, `wei_bo`, `qq_`, `birth_day`, `dept_id`, `position_`, `address_`, `staff_no`, `enable_`, `remark_`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES
	(1, 'admin', 'i/sV2VpTPy7Y+ppesmkCmM==', '3', '管理员', 'GUANLIYUAN', 0, 'http://118.190.43.148/group1/M00/00/00/dr4rlFjNBguAfJl7AAcOE67NTFk744.png', '15333821711', '12@12', NULL, NULL, NULL, NULL, '2017-01-27', 2, '213', NULL, NULL, 1, '1', '2016-05-06 10:06:52', 1, '2017-03-18 18:03:55', 1),
	(2, 'test', 'i/sV2VpTPy7Y+ppesmkCmM==', '1', 'admin', 'CESHIRENYUAN', 1, 'http://118.190.43.148/group1/M00/00/00/dr4rlFj3H0iATcqFAAv7S9z_iMg689.png', '12345678901', '123@163.com', NULL, NULL, NULL, NULL, '2017-02-01', 825363166504628224, '测试', NULL, NULL, 1, '1', '2016-05-13 16:58:17', 1, '2017-04-19 16:26:49', 2);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_user_menu 结构
CREATE TABLE IF NOT EXISTS `sys_user_menu` (
  `id_` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `permission_` varchar(50) NOT NULL COMMENT '权限标识',
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(5000) DEFAULT NULL,
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
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id_` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `enable_` tinyint(1) NOT NULL DEFAULT '1',
  `remark_` varchar(5000) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_role_id` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- 正在导出表  ibase4j.sys_user_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id_`, `user_id`, `role_id`, `enable_`, `remark_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, 1, 1, 1, NULL, 1, '2016-06-16 15:59:56', 1, '2016-06-16 15:59:56');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

-- 导出  表 ibase4j.sys_user_thirdparty 结构
CREATE TABLE IF NOT EXISTS `sys_user_thirdparty` (
  `id_` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `provider_` varchar(50) NOT NULL COMMENT '第三方类型',
  `open_id` varchar(50) NOT NULL COMMENT '第三方Id',
  `enable_` tinyint(1) DEFAULT NULL,
  `remark_` varchar(5000) DEFAULT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ibase4j.task_fire_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `task_fire_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_fire_log` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
