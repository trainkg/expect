CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL COMMENT '系统用户',
  `activation_time` datetime DEFAULT NULL COMMENT '帐户激活时间',
  `application_time` date DEFAULT NULL COMMENT '系统时间',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `first_login_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `login_name` varchar(50) NOT NULL,
  `login_times` int(11) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `source_id` varchar(32) DEFAULT NULL,
  `spell` varchar(255) DEFAULT NULL COMMENT '名称拼写',
  `user_status` int(11) NOT NULL,
  `user_type` int(11) NOT NULL,
  `insert_by` bigint(19) DEFAULT NULL,
  `update_by` bigint(19) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_id` (`id`) USING BTREE,
  KEY `Index_login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_group_role` (
  `list_id` int(11) NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `insert_by` bigint(19) DEFAULT NULL,
  `update_by` bigint(19) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`list_id`),
  KEY `FK_GROUP_ROLE__GROUP_ID` (`group_id`),
  CONSTRAINT `FK_GROUP_ROLE__GROUP_ID` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`List_id`),
  CONSTRAINT `FK_GROUP_ROLE__ROLE_ID` FOREIGN KEY (`list_id`) REFERENCES `t_role` (`list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_user_group` (
  `list_id` int(11) NOT NULL,
  `user_id` varchar(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `insert_by` bigint(19) DEFAULT NULL,
  `update_by` bigint(19) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`list_id`),
  KEY `FK_USER_GROUP__USER_ID` (`user_id`),
  KEY `FK_USER_GROUP__GROUP_ID` (`group_id`),
  CONSTRAINT `FK_USER_GROUP__GROUP_ID` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`List_id`),
  CONSTRAINT `FK_USER_GROUP__USER_ID` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


