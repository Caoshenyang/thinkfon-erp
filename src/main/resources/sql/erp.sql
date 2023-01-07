/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 42.192.40.128:3306
 Source Schema         : erp

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 07/01/2023 14:56:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '权限码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `acl_module_id` bigint NOT NULL DEFAULT 0 COMMENT '权限所在的权限模块id',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '请求的url, 可以填正则表达式',
  `type` tinyint NOT NULL DEFAULT 3 COMMENT '类型【1 菜单 2 按钮 3 其他】',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 【1 正常 0 冻结】',
  `sort` int NOT NULL DEFAULT 0 COMMENT '权限在当前模块下的顺序，由小到大',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `creator` bigint NOT NULL COMMENT '创建人',
  `operator` bigint NOT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_acl_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl_module`;
CREATE TABLE `sys_acl_module`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '权限模块名称',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '上级权限模块id',
  `level` int NOT NULL DEFAULT 1 COMMENT '权限模块层级',
  `sort` int NOT NULL DEFAULT 0 COMMENT '权限模块在当前层级下的顺序，由小到大',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 【0 冻结 1 正常】',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `creator` bigint NOT NULL COMMENT '创建人',
  `operator` bigint NOT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限模块表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '部门层级',
  `sort` int NOT NULL DEFAULT 1 COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备注',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父节点ID',
  `creator` bigint NOT NULL COMMENT '创建人',
  `operator` bigint NOT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '权限更新类型【1 部门 2 用户 3 权限模块 4 权限 5 角色 6 角色用户关系 7 角色权限关系】',
  `target_id` bigint NOT NULL COMMENT '基于type指定的对应对象ID，比如用户、角色、权限表的主键',
  `old_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '旧值',
  `new_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '新值',
  `operator` bigint NOT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '当前是否复原过【0 未操作 1 已操作】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名称',
  `role_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色权限字符串',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '角色状态【0 停用 1 正常】1',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备注',
  `creator` bigint NOT NULL COMMENT '创建人',
  `operator` bigint NOT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `acl_id` bigint NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色用户关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dept_id` bigint NOT NULL DEFAULT 0 COMMENT '部门ID',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '密码',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `gender` tinyint(1) NOT NULL DEFAULT 1 COMMENT '性别【1 男 2 女】',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '头像',
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '启用标志【0 禁用 1 启用】',
  `creator` bigint NOT NULL COMMENT '创建人',
  `operator` bigint NOT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记【0 正常 1 删除】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
