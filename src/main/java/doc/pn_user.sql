
SET FOREIGN_KEY_CHECKS=0;

-- --------------创建数据库------------------------
DROP DATABASE IF EXISTS pn;
CREATE DATABASE pn;

USE pn;

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
  `status` int DEFAULT 1 COMMENT '状态 1 正常, 0 停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 增加默认用户
INSERT INTO `pn_user` VALUES (10001, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, 10001, '管理员', '123555', '123456789', '1', 10001, '2017-12-4 15:12:06', '2017-12-4 15:12:09', 1);
INSERT INTO `pn_user` VALUES (10002, 'quifar', 'e10adc3949ba59abbe56e057f20f883e', 10001, 10001, 'quifar', '314', '158', '1', 10001, '2017-12-4 15:12:48', '2017-12-4 15:12:48', 1);

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
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(4) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int DEFAULT 1 COMMENT '状态  1:正常, 0:停用',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单管理';

INSERT INTO `pn_menu` VALUES (10001, 0, '系统管理', '', 1, 'icon-text', 1, '2017-11-28 12:00:00', '2017-11-28 12:00:00',1);
INSERT INTO `pn_menu` VALUES (10002, 10001, '菜单设置', '/menu/list', 1, 'icon-text', 2, '2017-11-28 12:00:00', '2017-11-28 12:00:00',1);
INSERT INTO `pn_menu` VALUES (10003, 10001, '角色设置', '/role/list', 1, 'icon-text', 3, '2017-11-28 12:00:00', '2017-11-28 12:00:00',1);
INSERT INTO `pn_menu` VALUES (10004, 10001, '用户管理', 'user/list', 1, 'icon-text', 4, '2017-11-29 15:00:03', '2017-11-29 15:00:05',1);
INSERT INTO `pn_menu` VALUES (10005, 10001, '系统日志', 'log/list', 1, 'icon-text', 4, '2017-11-29 15:00:03', '2017-11-29 15:00:05',1);
INSERT INTO `pn_menu` VALUES (10006, 0, '商品管理', '', 1, 'icon-text', 1, '2017-11-28 12:00:00', '2017-11-28 12:00:00',1);
INSERT INTO `pn_menu` VALUES (10007, 10006, '商品列表', 'goods/list', 1, 'icon-text', 2, '2017-11-29 15:00:03', '2017-11-29 15:00:05',1);
INSERT INTO `pn_menu` VALUES (10008, 0, '订单管理', '', 1, 'icon-text', 1, '2017-11-28 12:00:00', '2017-11-28 12:00:00',1);
INSERT INTO `pn_menu` VALUES (10009, 10008, '订单列表', 'goods/list', 1, 'icon-text', 2, '2017-11-29 15:00:03', '2017-11-29 15:00:05',1);

-- 用户角色 -- 
DROP TABLE IF EXISTS `pn_role`;
CREATE TABLE `pn_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_id` bigint(20) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int DEFAULT 1 COMMENT '状态  1:正常, 0:停用',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';

INSERT INTO `pn_role` VALUES (10001, '超级用户角色', '拥有最高权限', 10001, '2017-11-28 12:00:00', '2017-11-28 12:00:00',1);


-- 角色与系统菜单关系表 -- 
DROP TABLE IF EXISTS `pn_role_menu`;
CREATE TABLE `pn_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

INSERT INTO `pn_role_menu` VALUES (10001, 10001, 10001);
INSERT INTO `pn_role_menu` VALUES (10002, 10001, 10002);
INSERT INTO `pn_role_menu` VALUES (10003, 10001, 10003);
INSERT INTO `pn_role_menu` VALUES (10004, 10001, 10004);
INSERT INTO `pn_role_menu` VALUES (10005, 10001, 10005);
INSERT INTO `pn_role_menu` VALUES (10006, 10001, 10006);
INSERT INTO `pn_role_menu` VALUES (10007, 10001, 10007);
INSERT INTO `pn_role_menu` VALUES (10008, 10001, 10008);
INSERT INTO `pn_role_menu` VALUES (10009, 10001, 10009);

-- 商品信息表 --
DROP TABLE IF EXISTS `pn_goods`;
CREATE TABLE `pn_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_num` varchar(40) DEFAULT NULL COMMENT '商品编码',
  `goods_name` varchar(80) DEFAULT NULL COMMENT '商品名称',
  `sale_price` decimal(20,2) DEFAULT '0.0000' COMMENT '销售价格',
  `discount_price` decimal(20,2) DEFAULT '0.0000' COMMENT '折后价格',
  `stock` bigint(20) DEFAULT '0' COMMENT '库存',
  `sale_count` bigint(20) DEFAULT '0' COMMENT '销量',
  `status` int DEFAULT 1 COMMENT '状态  1:正常, 0:下架',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';


-- 订单信息表 --
DROP TABLE IF EXISTS `pn_order`;
CREATE TABLE `pn_order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单编码',
  `order_sid` varchar(40) NOT NULL COMMENT '订单号',
  `goods_num` varchar(40) DEFAULT NULL COMMENT '商品编码',
  `goods_name` varchar(80) DEFAULT NULL COMMENT '商品名称',
  `price` decimal(20,2) DEFAULT '0.0000' COMMENT '拍下价格',
  `pay_price` decimal(20,2) DEFAULT '0.0000' COMMENT '支付价格',
  `amount` bigint(20) DEFAULT '0' COMMENT '商品数量',
  `buyer_name` varchar(80) DEFAULT NULL COMMENT '收货人',
  `buyer_address` varchar(80) DEFAULT NULL COMMENT '收货地址',
  `buyer_phone` varchar(80) DEFAULT NULL COMMENT '手机',
  `create_id` bigint(20) DEFAULT NULL COMMENT '订单创建账号ID',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单最后修改时间',
  `status` int(11) DEFAULT '0' COMMENT '订单状态 0:未付款，1：已付款，2：已发货，3：已完成，4：取消订单',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';
