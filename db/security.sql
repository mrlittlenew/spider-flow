DROP TABLE IF EXISTS `sp_user`;
CREATE TABLE `sp_user`  (
                            `id` bigint(20) NOT NULL COMMENT 'id',
                            `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                            `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE INDEX `uni_username`(`username`) USING BTREE COMMENT 'username唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

/* 默认的用户 admin/admin */
INSERT INTO `sp_user` VALUES (2, 'admin', '$2a$10$p/GfWqPSSjAPvga0jSPNzOdvKbpQbfzJ6dV2tFnhwBubvYVc7a08K');

SET FOREIGN_KEY_CHECKS=0;