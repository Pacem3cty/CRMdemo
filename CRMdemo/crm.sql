/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2021-11-30 16:49:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_cus
-- ----------------------------
DROP TABLE IF EXISTS `t_cus`;
CREATE TABLE `t_cus` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '客户编号',
  `cus_num` varchar(20) DEFAULT NULL,
  `cus_name` varchar(20) DEFAULT NULL COMMENT '客户名称',
  `area` varchar(20) DEFAULT NULL COMMENT '所属地区',
  `cus_manager` varchar(20) DEFAULT NULL COMMENT '客户经理',
  `level` varchar(30) DEFAULT NULL COMMENT '客户级别',
  `csr` varchar(30) DEFAULT NULL COMMENT '客户满意度',
  `ccr` varchar(30) DEFAULT NULL COMMENT '客户信用度',
  `cus_address` varchar(255) DEFAULT NULL COMMENT '客户地址',
  `postal_code` varchar(50) DEFAULT NULL COMMENT '邮编',
  `cus_phone` varchar(20) DEFAULT NULL COMMENT '客户电话',
  `cus_fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `website` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户网站',
  `social_credit_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '统一社会信用代码',
  `artificial_person` varchar(20) DEFAULT NULL COMMENT '法人代表',
  `registered_capital` varchar(20) DEFAULT NULL COMMENT '注册资金',
  `annual_turnover` varchar(20) DEFAULT NULL COMMENT '年营业额',
  `account_bank` varchar(50) DEFAULT NULL COMMENT '开户银行',
  `account_num` varchar(50) DEFAULT NULL COMMENT '银行账号',
  `loss_status` int DEFAULT NULL COMMENT '流失状态',
  `is_valid` int DEFAULT NULL COMMENT '有效状态 0 有效 1 无效',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus
-- ----------------------------
INSERT INTO `t_cus` VALUES ('1', 'C202111240000001', '驣殉计算机系统有限公司', '深圳', '测试', '战略合作伙伴', '满意', '良好', '广东深圳', null, '18934879265', null, null, null, null, null, null, null, null, '0', '0', '2021-11-24 20:44:35', null);
INSERT INTO `t_cus` VALUES ('2', 'C202111240000002', '锕锂芸计算有限公司 ', '杭州', '测试', '重要客户', '一般', '良好', '浙江杭州', null, '15008976432', null, null, '91330100799655058B', '马耘', null, null, null, null, '1', '0', '2021-11-24 00:00:00', '2021-11-27 23:45:18');
INSERT INTO `t_cus` VALUES ('3', 'C202111270000003', '魍弌网络有限公司', '', '测试', '', '', null, '', '', '18934879265', '', null, '91330000788831167A', '丁攂', '', '', '', '', null, '0', '2021-11-27 21:20:31', null);

-- ----------------------------
-- Table structure for t_cus_contact
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_contact`;
CREATE TABLE `t_cus_contact` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '客户交往记录编号',
  `cus_id` int DEFAULT NULL COMMENT '客户编号',
  `contact_time` datetime DEFAULT NULL COMMENT '交往时间',
  `contact_address` varchar(255) DEFAULT NULL COMMENT '交往地址',
  `overview` varchar(255) DEFAULT NULL COMMENT '交往概述',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  `is_valid` int DEFAULT NULL COMMENT '有效状态 0 有效 1 无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_contact
-- ----------------------------
INSERT INTO `t_cus_contact` VALUES ('1', '1', '2021-11-28 00:00:00', '广东广州', '测试概述', '2021-11-28 00:00:00', '2021-11-29 15:41:31', '0');
INSERT INTO `t_cus_contact` VALUES ('2', '1', '2021-11-15 00:00:00', '广东深圳', '测试交往概述', '2021-11-29 15:26:58', null, '0');
INSERT INTO `t_cus_contact` VALUES ('3', '1', '2021-11-03 00:00:00', '测试地址', '测试概述', '2021-11-29 15:41:54', null, '0');
INSERT INTO `t_cus_contact` VALUES ('4', '1', '2021-11-03 00:00:00', '测试地址', '测试概述', '2021-11-29 15:45:18', null, '0');

-- ----------------------------
-- Table structure for t_cus_dev_plan
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_dev_plan`;
CREATE TABLE `t_cus_dev_plan` (
  `id` int NOT NULL,
  `sale_chance_id` int DEFAULT NULL,
  `plan_item` varchar(255) DEFAULT NULL,
  `plan_date` datetime DEFAULT NULL,
  `exe_affect` varchar(255) DEFAULT NULL COMMENT '执行效果',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  `is_valid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sale_chance_id` (`sale_chance_id`),
  CONSTRAINT `t_cus_dev_plan_ibfk_1` FOREIGN KEY (`sale_chance_id`) REFERENCES `t_sale_chance` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_dev_plan
-- ----------------------------
INSERT INTO `t_cus_dev_plan` VALUES ('1', '1', '测试计划项1', '2021-10-27 09:02:05', '测试计划效果1', '2021-10-27 09:02:21', null, '0');
INSERT INTO `t_cus_dev_plan` VALUES ('2', '2', '测试计划项2', '2021-10-27 14:49:43', '测试计划效果2', '2021-10-27 14:49:36', null, '0');
INSERT INTO `t_cus_dev_plan` VALUES ('3', '3', '测试计划项3', '2021-10-27 00:00:00', '测试计划效果3', '2021-10-27 00:00:00', '2021-11-04 15:33:57', '0');
INSERT INTO `t_cus_dev_plan` VALUES ('4', '3', '测试计划项4', '2021-10-28 00:00:00', '测试执行效果4', '2021-10-28 00:00:00', '2021-10-28 16:08:30', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('5', '2', '测试计划项3', '2021-11-03 00:00:00', '测试执行效果3', '2021-11-03 00:00:00', '2021-11-03 10:11:05', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('6', '3', '测试计划项6', '2021-11-04 00:00:00', '测试执行效果6', '2021-11-04 15:36:32', null, '0');
INSERT INTO `t_cus_dev_plan` VALUES ('7', '4', '测试计划项7', '2021-11-04 00:00:00', '测试执行效果7', '2021-11-04 15:42:25', null, '0');

-- ----------------------------
-- Table structure for t_cus_linkman
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_linkman`;
CREATE TABLE `t_cus_linkman` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '客户联系人编号',
  `cus_id` int DEFAULT NULL COMMENT '客户编号',
  `linkman_name` varchar(20) DEFAULT NULL COMMENT '联系人名称',
  `sex` int DEFAULT NULL COMMENT '性别 0 男 | 1 女',
  `position` varchar(50) DEFAULT NULL COMMENT '职位',
  `office_phone` varchar(50) DEFAULT NULL COMMENT '办公电话',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `is_valid` int DEFAULT NULL COMMENT '有效状态',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_linkman
-- ----------------------------
INSERT INTO `t_cus_linkman` VALUES ('1', '1', '测试姓名', '1', '测试性别', '18000000000', null, '0', '2021-11-29 00:00:00', '2021-11-29 14:01:33');
INSERT INTO `t_cus_linkman` VALUES ('8', '1', '测试姓名', '0', '测试职位', '18000000000', null, '0', '2021-11-29 15:48:56', null);

-- ----------------------------
-- Table structure for t_cus_loss
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_loss`;
CREATE TABLE `t_cus_loss` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '客户流失记录编号',
  `cus_id` int DEFAULT NULL COMMENT '客户编号',
  `cus_name` varchar(20) DEFAULT NULL,
  `cus_manager` varchar(20) DEFAULT NULL COMMENT '客户经理',
  `last_order_time` datetime DEFAULT NULL COMMENT '最后下单时间',
  `confirm_loss_time` datetime DEFAULT NULL COMMENT '确认流失时间',
  `state` int DEFAULT NULL COMMENT '流失状态',
  `loss_reason` varchar(255) DEFAULT NULL COMMENT '流失原因',
  `is_valid` int DEFAULT NULL COMMENT '有效状态',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`),
  KEY `cus_id` (`cus_id`),
  CONSTRAINT `cus_id` FOREIGN KEY (`cus_id`) REFERENCES `t_cus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_loss
-- ----------------------------
INSERT INTO `t_cus_loss` VALUES ('1', '1', '驣殉计算机系统有限公司', '测试', '2021-05-08 21:04:46', null, '1', null, '0', '2021-11-26 21:06:09', null);

-- ----------------------------
-- Table structure for t_cus_order
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_order`;
CREATE TABLE `t_cus_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `cus_id` int DEFAULT NULL COMMENT '客户编号',
  `order_num` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `order_date` datetime DEFAULT NULL COMMENT '下单时间',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `state` int DEFAULT NULL COMMENT '状态',
  `is_valid` int DEFAULT NULL COMMENT '有效状态 0 有效 1 无效',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_order
-- ----------------------------
INSERT INTO `t_cus_order` VALUES ('1', '1', 'O202111300000001', '2021-11-30 00:00:00', '广东深圳', '1', '0', '2021-11-30 00:00:00', '2021-11-30 00:00:00');
INSERT INTO `t_cus_order` VALUES ('3', '1', 'O202111300000002', '2021-11-04 00:00:00', '广东广州', '1', '0', '2021-11-30 10:18:22', '2021-11-30 00:00:00');
INSERT INTO `t_cus_order` VALUES ('5', '1', 'O202111300000004', '2021-11-06 00:00:00', '测试地址', '0', '0', '2021-11-30 10:35:01', null);

-- ----------------------------
-- Table structure for t_cus_reprieve
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_reprieve`;
CREATE TABLE `t_cus_reprieve` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '暂缓记录编号',
  `loss_id` int DEFAULT NULL COMMENT '流失编号',
  `measure` varchar(255) DEFAULT NULL COMMENT '暂缓措施',
  `is_valid` int DEFAULT NULL COMMENT '有效状态',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_reprieve
-- ----------------------------

-- ----------------------------
-- Table structure for t_cus_service
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_service`;
CREATE TABLE `t_cus_service` (
  `id` int NOT NULL COMMENT '客户服务编号',
  `service_type` varchar(30) DEFAULT NULL COMMENT '服务类型',
  `overview` varchar(255) DEFAULT NULL COMMENT '概述',
  `customer` varchar(30) DEFAULT NULL COMMENT '客户',
  `state` varchar(20) DEFAULT NULL COMMENT '服务状态',
  `service_request` varchar(255) DEFAULT NULL COMMENT '服务请求',
  `create_person` varchar(100) DEFAULT NULL COMMENT '创建人',
  `assign_person` varchar(100) DEFAULT NULL COMMENT '指派人',
  `assign_date` datetime DEFAULT NULL COMMENT '分配日期',
  `service_process` varchar(255) DEFAULT NULL COMMENT '服务处理',
  `process_person` varchar(20) DEFAULT NULL COMMENT '处理人',
  `process_date` datetime DEFAULT NULL COMMENT '处理时间',
  `process_result` varchar(255) DEFAULT NULL COMMENT '处理结果',
  `csr` varchar(50) DEFAULT NULL COMMENT '客户满意度',
  `is_valid` int DEFAULT NULL COMMENT '有效状态',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_cus_service
-- ----------------------------

-- ----------------------------
-- Table structure for t_module
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module` (
  `id` int NOT NULL,
  `module_name` varchar(255) DEFAULT NULL,
  `module_style` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `parent_opt_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `opt_value` varchar(255) DEFAULT NULL,
  `orders` int NOT NULL,
  `is_valid` int DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_module
-- ----------------------------
INSERT INTO `t_module` VALUES ('1', '营销管理', null, '#', '-1', '010', null, '010', '1', '0', '2021-11-05 15:51:07', null);
INSERT INTO `t_module` VALUES ('2', '营销机会管理', null, '/sales', '1', '010', null, '0101', '2', '0', '2021-11-05 15:53:35', '2021-11-22 11:21:36');
INSERT INTO `t_module` VALUES ('3', '营销机会管理查询', null, '#', '2', '0101', null, '01012', '3', '0', '2021-11-05 15:56:23', null);
INSERT INTO `t_module` VALUES ('4', '营销机会信息添加', null, '/sales/add', '2', '0101', null, '01013', '4', '0', '2021-11-11 11:22:08', '2021-11-22 11:18:37');
INSERT INTO `t_module` VALUES ('5', '营销机会管理修改', null, null, '2', '0101', null, '01014', '5', '0', '2021-11-11 11:23:44', null);
INSERT INTO `t_module` VALUES ('6', '营销机会管理删除', null, null, '2', '0101', null, '01015', '6', '0', '2021-11-11 11:24:01', null);
INSERT INTO `t_module` VALUES ('7', '客户开发计划', null, null, '1', '010', null, '0102', '7', '0', '2021-11-12 14:31:14', null);
INSERT INTO `t_module` VALUES ('8', '开发计划查询', null, null, '7', '0102', null, '01021', '8', '0', '2021-11-12 14:35:32', null);
INSERT INTO `t_module` VALUES ('9', '计划项详情查看', null, null, '7', '0102', null, '01022', '9', '0', '2021-11-12 14:37:27', null);
INSERT INTO `t_module` VALUES ('10', '计划项数据维护', null, null, '7', '0102', null, '01023', '10', '0', '2021-11-12 14:37:47', '2021-11-22 15:37:37');
INSERT INTO `t_module` VALUES ('11', '客户管理', null, null, '-1', '020', null, '020', '11', '0', '2021-11-12 14:39:57', null);
INSERT INTO `t_module` VALUES ('12', '客户信息管理', null, null, '11', '020', null, '0201', '12', '0', '2021-11-15 08:53:32', null);
INSERT INTO `t_module` VALUES ('13', '客户信息查询', null, null, '12', '0201', null, '02011', '13', '0', '2021-11-15 08:54:01', null);
INSERT INTO `t_module` VALUES ('14', '客户信息添加', null, null, '12', '0201', null, '02012', '14', '0', '2021-11-15 08:55:00', null);
INSERT INTO `t_module` VALUES ('15', '客户信息修改', null, null, '12', '0201', null, '02013', '15', '0', '2021-11-15 08:55:19', null);
INSERT INTO `t_module` VALUES ('16', '客户信息删除', null, null, '12', '0201', null, '02014', '16', '0', '2021-11-15 08:57:21', null);
INSERT INTO `t_module` VALUES ('17', '客户流失管理', null, null, '11', '020', null, '0202', '17', '0', '2021-11-15 08:58:00', null);
INSERT INTO `t_module` VALUES ('18', '客户流失信息查询', null, null, '17', '0202', null, '02021', '18', '0', '2021-11-15 08:58:35', null);
INSERT INTO `t_module` VALUES ('19', '客户流失信息添加', null, null, '17', '0202', null, '02022', '19', '0', '2021-11-15 08:59:02', null);
INSERT INTO `t_module` VALUES ('20', '客户流失信息修改', null, null, '17', '0202', null, '02023', '20', '0', '2021-11-15 08:59:22', null);
INSERT INTO `t_module` VALUES ('21', '客户流失信息删除', null, null, '17', '0202', null, '02024', '21', '0', '2021-11-15 08:59:48', null);
INSERT INTO `t_module` VALUES ('23', '测试顶层模块', null, '#', '-1', null, null, '#', '23', '1', '2021-11-22 14:47:05', null);
INSERT INTO `t_module` VALUES ('24', '计划项数据添加', null, '', '7', null, null, '01024', '24', '0', '2021-11-22 15:39:17', null);

-- ----------------------------
-- Table structure for t_order_details
-- ----------------------------
DROP TABLE IF EXISTS `t_order_details`;
CREATE TABLE `t_order_details` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '详情编号',
  `order_id` int DEFAULT NULL COMMENT '订单编号',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `goods_num` int DEFAULT NULL COMMENT '商品数量',
  `unit` varchar(20) DEFAULT NULL COMMENT '商品单位',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `total` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_valid` int DEFAULT NULL COMMENT '有效状态',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_order_details
-- ----------------------------
INSERT INTO `t_order_details` VALUES ('1', '1', '测试商品1', '1', '盒', '199.99', '199.99', null, '0', '2021-11-30 00:00:00', '2021-11-30 15:59:07');
INSERT INTO `t_order_details` VALUES ('2', '1', '测试商品2', '4', '箱', '499.50', '1998.00', null, '0', '2021-11-30 15:46:45', null);
INSERT INTO `t_order_details` VALUES ('3', '1', '测试商品3', '2', '箱', '600.00', '1200.00', null, '0', '2021-11-30 00:00:00', '2021-11-30 16:13:01');
INSERT INTO `t_order_details` VALUES ('4', '1', '测试商品4', '3', '瓶', '399.50', '1198.50', null, '0', '2021-11-30 00:00:00', '2021-11-30 16:17:03');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `module_id` int NOT NULL,
  `acl_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_permission_ibfk_1` (`role_id`),
  KEY `t_permission_ibfk_2` (`module_id`),
  CONSTRAINT `t_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_permission_ibfk_2` FOREIGN KEY (`module_id`) REFERENCES `t_module` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', '1', '1', '010', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('2', '1', '2', '0101', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('3', '1', '3', '01012', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('4', '1', '4', '01013', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('5', '1', '5', '01014', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('6', '1', '6', '01015', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('7', '1', '7', '0102', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('8', '1', '8', '01021', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('9', '1', '9', '01022', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('10', '1', '10', '01023', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('11', '1', '11', '020', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('12', '1', '12', '0201', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('13', '1', '13', '02011', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('14', '1', '14', '02012', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('15', '1', '15', '02013', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('16', '1', '16', '02014', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('17', '1', '17', '0202', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('18', '1', '18', '02021', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('19', '1', '19', '02022', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('20', '1', '20', '02023', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('21', '1', '21', '02024', '2021-11-17 09:11:17', '2021-11-17 09:11:17');
INSERT INTO `t_permission` VALUES ('22', '2', '1', '010', '2021-11-17 09:30:56', '2021-11-17 09:30:56');
INSERT INTO `t_permission` VALUES ('23', '2', '2', '0101', '2021-11-17 09:30:56', '2021-11-17 09:30:56');
INSERT INTO `t_permission` VALUES ('24', '2', '3', '01012', '2021-11-17 09:30:56', '2021-11-17 09:30:56');
INSERT INTO `t_permission` VALUES ('25', '2', '4', '01013', '2021-11-17 09:30:56', '2021-11-17 09:30:56');
INSERT INTO `t_permission` VALUES ('26', '2', '5', '01014', '2021-11-17 09:30:56', '2021-11-17 09:30:56');
INSERT INTO `t_permission` VALUES ('27', '2', '6', '01015', '2021-11-17 09:30:56', '2021-11-17 09:30:56');
INSERT INTO `t_permission` VALUES ('28', '2', '7', '0102', '2021-11-17 09:30:56', '2021-11-17 09:30:56');
INSERT INTO `t_permission` VALUES ('29', '2', '8', '01021', '2021-11-17 09:30:56', '2021-11-17 09:30:56');
INSERT INTO `t_permission` VALUES ('30', '2', '9', '01022', '2021-11-17 09:30:56', '2021-11-17 09:30:56');
INSERT INTO `t_permission` VALUES ('31', '2', '10', '01023', '2021-11-17 09:30:56', '2021-11-17 09:30:56');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  `is_valid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '系统管理员', '所有模块权限', '2021-11-03 00:00:00', '2021-11-22 15:47:52', '0');
INSERT INTO `t_role` VALUES ('2', '销售', '营销管理模块；系统设置模块-修改资料', '2021-11-03 00:00:00', '2021-11-22 15:47:36', '0');
INSERT INTO `t_role` VALUES ('3', '客户经理', '无系统设置模块权限（除修改资料）', '2021-11-03 10:42:01', null, '0');
INSERT INTO `t_role` VALUES ('4', '技术经理', '无系统设置模块权限（除修改资料）', '2021-11-03 10:42:27', null, '0');
INSERT INTO `t_role` VALUES ('5', '人力资源经理', '无系统设置模块权限（除修改资料）', '2021-11-05 00:00:00', '2021-11-05 11:31:28', '0');

-- ----------------------------
-- Table structure for t_sale_chance
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_chance`;
CREATE TABLE `t_sale_chance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `chance_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机会来源',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `probability` int DEFAULT NULL COMMENT '成功几率',
  `overview` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '概要',
  `contract_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `contract_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '描述',
  `create_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `assign_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分配人',
  `assign_date` datetime DEFAULT NULL COMMENT '分配时间',
  `state` int DEFAULT NULL COMMENT '分配状态 0 未分配 1 已分配',
  `dev_result` int DEFAULT NULL COMMENT '开发结果',
  `is_valid` int DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_sale_chance
-- ----------------------------
INSERT INTO `t_sale_chance` VALUES ('1', '官网', '锕锂芸计算有限公司 ', '60', '全球领先的云计算及人工智能科技公司', '马耘', '15008976432', '测试机会描述', '吴芮', '2021-10-21 00:00:00', '吴芮', '2021-11-04 00:00:00', '1', '2', '0', '2021-11-04 15:25:29');
INSERT INTO `t_sale_chance` VALUES ('2', '展会', '驣殉计算机系统有限公司 ', '80', '最大的互联网综合服务提供商之一', '马砉螣', '18934879265', '测试机会描述', '吴㕙', '2021-03-18 00:00:00', '吴芮', '2021-11-04 00:00:00', '1', '1', '0', '2021-11-04 15:25:24');
INSERT INTO `t_sale_chance` VALUES ('3', '展会', '魍弌网络有限公司', '75', '中国领先的互联网技术公司', '丁攂', '17398438954', '测试机会描述', '吴㕙', '2021-10-22 00:00:00', '吴㕙', '2021-11-04 00:00:00', '1', '0', '0', '2021-11-04 11:12:12');
INSERT INTO `t_sale_chance` VALUES ('4', '官网', '枔誏网技术有限公司', '80', '一家服务于中国及全球华人社群的网络媒体公司', '曹椁葳', '18975638638', '测试机会描述', '吴芮', '2021-10-25 00:00:00', '', null, '1', '0', '0', '2021-10-25 16:45:03');
INSERT INTO `t_sale_chance` VALUES ('15', '测试', '测试', '80', '测试概述', '测试', '18000000000', '', '吴芮', '2021-11-04 00:00:00', '', null, '0', '0', '0', '2021-11-05 11:29:48');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `true_name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `is_valid` int DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'Pacem3cty', 'e10adc3949ba59abbe56e057f20f883e', '吴芮', '1556250364@qq.com', '19868586764', '一定要出重拳', '0', '2021-10-09 16:15:20', '2021-11-03 10:03:46');
INSERT INTO `t_user` VALUES ('2', 'Pacem', 'e35cf7b66449df565f93c607d5a81d09', '吴㕙', '1556251364@qq.com', '19868586762', '牙医shakeit', '0', '2021-10-29 00:00:00', '2021-11-03 09:22:21');
INSERT INTO `t_user` VALUES ('3', '测试', 'e35cf7b66449df565f93c607d5a81d09', '测试', '', '18000000000', '测试备注', '0', '2021-11-03 00:00:00', '2021-11-11 09:59:44');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_user_role_ibfk_1` (`user_id`),
  KEY `t_user_role_ibfk_2` (`role_id`),
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1', '2021-11-12 10:48:16', null);
INSERT INTO `t_user_role` VALUES ('2', '2', '2', '2021-11-12 11:17:53', null);
INSERT INTO `t_user_role` VALUES ('3', '3', '3', '2021-11-03 00:00:00', '2021-11-11 09:59:44');
