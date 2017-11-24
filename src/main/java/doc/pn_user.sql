
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pn_user
-- ----------------------------
DROP TABLE IF EXISTS `pn_user`;
CREATE TABLE `pn_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL COMMENT '登陆用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '登陆密码',
  `department_id` bigint(20) DEFAULT NULL COMMENT '所属部门',
  `role_id` bigint(20) DEFAULT NULL COMMENT '用户所属角色id',
  `user_nike` varchar(20) DEFAULT NULL COMMENT '昵称',
  `email` varchar(20) DEFAULT NULL COMMENT '电子邮件',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机',
  `sex` varchar(2) DEFAULT '1' COMMENT '性别',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(2) DEFAULT '1' COMMENT '状态 1 正常, 0 停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 增加默认用户
insert into pn_user (user_name,password,department_id,role_id,name) values ('admin','e10adc3949ba59abbe56e057f20f883e',1,1,'管理员');

-- 系统操作日志 --
DROP TABLE IF EXISTS `pn_log`;
CREATE TABLE `pn_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(1000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统操作日志';

-- 系统菜单管理 -- 
DROP TABLE IF EXISTS `pn_menu`;
CREATE TABLE `pn_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(4) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单管理';

-- 用户角色 -- 
DROP TABLE IF EXISTS `pn_role`;
CREATE TABLE `pn_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(2) DEFAULT '1' COMMENT '状态  1:正常, 0:停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

insert into `pn_role` values ('1', '超级用户角色', '拥有最高权限', '1', '2017-11-24 00:00:00', '2017-11-24 00:00:00','1');

--角色与系统菜单关系表 -- 
DROP TABLE IF EXISTS `pn_role_menu`;
CREATE TABLE `pn_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

