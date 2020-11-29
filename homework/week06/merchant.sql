drop table if exists t_user;
CREATE TABLE `t_user` (
`id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '主键',
`user_name` VARCHAR ( 50 ) NOT NULL DEFAULT '' COMMENT '用户别名',
`real_name` VARCHAR ( 50 ) NOT NULL DEFAULT '' COMMENT '真实姓名',
`phone` VARCHAR ( 20 ) NOT NULL DEFAULT '' COMMENT '手机号',
`modified_by` VARCHAR ( 128 ) DEFAULT NULL COMMENT '修改人',
`modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`created_by` VARCHAR ( 128 ) DEFAULT NULL COMMENT '创建人',
`created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`delete_flag` SMALLINT DEFAULT 0 COMMENT '删除标识，0:正常状态，1:删除状态',
PRIMARY KEY ( `id` ),
KEY `idx_name` ( `user_name` ) USING BTREE COMMENT '用户别名索引',
KEY `idx_phone` ( `phone` ) USING BTREE COMMENT '手机号索引'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '用户表';

drop table if exists t_goods;
CREATE TABLE `t_goods` (
`id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '主键',
`name` VARCHAR ( 100 ) NOT NULL DEFAULT '' COMMENT '商品名称',
`price` decimal(20,3) NOT NULL DEFAULT 0 COMMENT '价格',
`desc` TEXT(65535) NOT NULL DEFAULT '' COMMENT '商品描述',
`stock` BIGINT ( 20 ) NOT NULL DEFAULT 0 COMMENT '库存',
`modified_by` VARCHAR ( 128 ) DEFAULT NULL COMMENT '修改人',
`modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`created_by` VARCHAR ( 128 ) DEFAULT NULL COMMENT '创建人',
`created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`delete_flag` SMALLINT DEFAULT 0 COMMENT '删除标识，0:正常状态，1:删除状态',
PRIMARY KEY ( `id` ),
KEY `idx_name` ( `name` ) USING BTREE COMMENT '商品名称索引',
KEY `idx_price` ( `price` ) USING BTREE COMMENT '价格索引'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '商品表';

drop table if exists t_order;
CREATE TABLE `t_order` (
`id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT COMMENT '主键',
`user_id` BIGINT ( 20 ) NOT NULL COMMENT '用户id',
`goods_id` BIGINT ( 20 ) NOT NULL COMMENT '商品id',
`price` decimal(20,3) NOT NULL DEFAULT 0 COMMENT '商品价格',
`buy_count` BIGINT ( 20 ) NOT NULL DEFAULT 0 COMMENT '购买数量',
`buy_time` datetime NOT NULL '购买时间',
`modified_by` VARCHAR ( 128 ) DEFAULT NULL COMMENT '修改人',
`modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
`created_by` VARCHAR ( 128 ) DEFAULT NULL COMMENT '创建人',
`created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`delete_flag` SMALLINT DEFAULT 0 COMMENT '删除标识，0:正常状态，1:删除状态',
PRIMARY KEY ( `id` ),
KEY `idx_user_id` ( `user_id` ) USING BTREE COMMENT '用户id索引',
KEY `idx_goods_id` ( `goods_id` ) USING BTREE COMMENT '商品id索引',
KEY `idx_buy_time` ( `buy_time` ) USING BTREE COMMENT '购买时间索引'
) ENGINE = INNODB DEFAULT CHARSET = utf8 COLLATE = utf8_bin COMMENT = '订单表';