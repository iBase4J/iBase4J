
INSERT INTO `sys_menu` (`id_`, `menu_name`, `menu_type`, `parent_id`, `iconcls_`, `request_`, `expand_`, `sort_no`, `is_show`, `permission_`, `remark_`, `enable_`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
	(1, '系统管理', 1, 0, 'glyphicon glyphicon-cog', '#', 0, 1, 1, 'sys', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 08:19:19'),
	(2, '用户管理', 1, 1, 'glyphicon glyphicon-user', 'main.sys.user.list', 0, 1, 1, 'sys.base.user', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 08:38:35'),
	(3, '部门管理', 1, 1, 'glyphicon glyphicon-flag', 'main.sys.dept.list', 0, 2, 1, 'sys.base.dept', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-28 18:07:43'),
	(4, '菜单管理', 1, 1, 'glyphicon glyphicon-list', 'main.sys.menu.list', 0, 3, 1, 'sys.base.menu', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-28 18:07:45'),
	(5, '角色管理', 1, 1, 'glyphicon glyphicon-tags', 'main.sys.role.list', 0, 4, 1, 'sys.base.role', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-28 18:07:47'),
	(6, '会话管理', 1, 1, 'glyphicon glyphicon-earphone', 'main.sys.session.list', 0, 6, 1, 'sys.base.session', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-28 18:07:49'),
	(7, '字典管理', 1, 1, 'glyphicon glyphicon-book', 'main.sys.dic.list', 0, 7, 1, 'sys.base.dic', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-28 18:07:50'),
	(8, '参数管理', 1, 1, 'glyphicon glyphicon-wrench', 'main.sys.param.list', 0, 8, 1, 'sys.base.param', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-28 18:07:52'),
	(9, '调度中心', 1, 0, 'glyphicon glyphicon-fire', '#', 0, 2, 1, 'sys.task', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-30 14:23:57'),
	(12, '调度管理', 1, 9, 'glyphicon glyphicon-random', 'main.task.scheduled.list', 0, 3, 1, 'sys.task.scheduled', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-30 14:24:02'),
	(13, '调度日志', 1, 9, 'glyphicon glyphicon-file', 'main.task.log.list', 0, 4, 1, 'sys.task.log', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-28 18:08:48'),
	(14, '清除缓存', 1, 1, NULL, NULL, 0, 9, 0, 'sys.sys.cache', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 09:39:25'),
	(15, '用户权限', 1, 1, NULL, NULL, 0, 10, 0, 'sys.permisson.roleMenu', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 09:39:27'),
	(16, '用户角色', 1, 1, NULL, NULL, 0, 11, 0, 'sys.permisson.useRole', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 09:39:29'),
	(17, '角色权限', 1, 1, NULL, NULL, 0, 12, 0, 'sys.permisson.userMenu', NULL, 1, 1, '2016-06-20 09:16:56', 1, '2016-06-29 09:39:33');
