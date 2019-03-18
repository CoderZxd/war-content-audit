-- ----------------------------
-- Table structure for code_app
-- ----------------------------
DROP TABLE IF EXISTS `code_app`;
CREATE TABLE `code_app` (
  `id` smallint(6) NOT NULL COMMENT '【主键】',
  `app_id` varchar(64) NOT NULL COMMENT 'App 代码，如：201901010001',
  `app_name` varchar(64) NOT NULL COMMENT '产品名称',
  `platform_id` smallint(6) DEFAULT NULL COMMENT '平台id，关联code_platform 表',
  `callback_url` varchar(1024) DEFAULT NULL COMMENT '回调地址',
  `policy_template_id` smallint(6) DEFAULT NULL COMMENT '策略模板id',
  `description` varchar(256) DEFAULT NULL COMMENT '产品描述',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for code_platform
-- ----------------------------
DROP TABLE IF EXISTS `code_platform`;
CREATE TABLE `code_platform` (
  `id` smallint(6) NOT NULL COMMENT '【主键】',
  `name` varchar(512) DEFAULT NULL COMMENT '客户名称',
  `domain` varchar(128) DEFAULT NULL COMMENT '域名，暂时无用',
  `sha_key` varchar(64) DEFAULT NULL COMMENT 'Hmac 算法key',
  `public_filter_id` varchar(1024) DEFAULT NULL COMMENT '公共词库id，逗号分隔，例如：1,2,3',
  `callback_url` varchar(1024) DEFAULT NULL COMMENT 'callback_url',
  `created` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for data_record_2019××××
-- 如果创建表格提示:"Invalid default value for 'updated'",去掉sql_mode中的NO_ZERO_IN_DATE,NO_ZERO_DATE即可。
-- 请执行以下语句:
-- show variables like 'sql_mode';
-- set session sql_mode='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
-- ----------------------------
DROP TABLE IF EXISTS `data_record_20190318`;
CREATE TABLE `data_record_20190318` (
  `data_id` bigint(20) NOT NULL COMMENT '【主键】全局唯一数据标识。',
  `id` varchar(128) NOT NULL COMMENT '用户发布的数据标识',
  `uid` varchar(128) NOT NULL COMMENT '用户id',
  `ip` varchar(64) DEFAULT NULL COMMENT '用户ip',
  `device` varchar(128) DEFAULT NULL COMMENT '用户设备号',
  `publish_date` timestamp NULL DEFAULT NULL COMMENT '用户内容发布时间',
  `app_id` smallint(6) NOT NULL COMMENT '产品id，区分不同产品接入方',
  `digest` varchar(32) NOT NULL COMMENT '消息md5 摘要',
  `updated` timestamp NOT NULL DEFAULT '2019-03-18 06:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '审核状态更新时间',
  `created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '入库时间',
  `version` varchar(6) NOT NULL COMMENT 'Api 接入版本号',
  `sys_status` tinyint(4) NOT NULL COMMENT '机器策略审核结果',
  `status` tinyint(4) NOT NULL COMMENT '最终（包含人工）审核结果',
  `sys_policy` varchar(64) NOT NULL COMMENT '机器策略名称',
  `operator` int(11) NOT NULL COMMENT '审核人员id',
  `data_type` tinyint(4) NOT NULL COMMENT '1-文本；2-图片',
  `msg_type` tinyint(4) NOT NULL COMMENT '消息类型，支持自定义',
  `nick_name` varchar(128) DEFAULT NULL COMMENT '用户昵称',
  `user_level` bigint(20) DEFAULT NULL COMMENT '用户等级',
  `extension` varchar(128) DEFAULT NULL COMMENT '为区别唯一数据，额外的辅助数据',
  PRIMARY KEY (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for data_resource
-- ----------------------------
DROP TABLE IF EXISTS `data_resource`;
CREATE TABLE `data_resource` (
  `data_id` bigint(20) NOT NULL COMMENT '【主键】全局唯一数据标识。',
  `order` tinyint(4) NOT NULL COMMENT '【主键】与data_id 联合主键',
  `type` tinyint(128) NOT NULL COMMENT '类型：1-text;2-image',
  `content` text NOT NULL COMMENT '用户发布内容',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '入库时间',
  PRIMARY KEY (`data_id`,`order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;