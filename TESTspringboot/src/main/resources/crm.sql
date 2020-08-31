/*
 Navicat Premium Data Transfer

 Source Server         : hjf
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : java0608

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 31/08/2020 13:55:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `cusId` int(11) NOT NULL AUTO_INCREMENT,
  `cusName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `empid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`cusId`) USING BTREE,
  INDEX `cus_emp_key`(`empid`) USING BTREE,
  CONSTRAINT `cus_emp_key` FOREIGN KEY (`empid`) REFERENCES `employees` (`empid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '王语焉', '北京市东城区景山前街4号', '王语焉', '15501064177', '313654879@qq.com', 21);
INSERT INTO `customer` VALUES (2, '程灵素', '北京市东城区天安门广场西侧', '程灵素', '18515420777', 'mijobs@126.com', 3);
INSERT INTO `customer` VALUES (4, '霍青桐', '北京市东城区五四大街1号', '霍青桐', '18518306111', 'mai961@qq.com', 6);
INSERT INTO `customer` VALUES (5, '木婉清', '北京市东城区国子监街13号', '木婉清', '18687017987', 'esas@yeah.net', 16);
INSERT INTO `customer` VALUES (6, '花不迟', '北京市西城区二环中路德胜门', '花不迟', '18669105239', 'mike@toncom.cn', 4);
INSERT INTO `customer` VALUES (8, '齐无真', '上海市人民大道201号', '齐无真', '18555552858', '1152451081@qq.com', 12315);
INSERT INTO `customer` VALUES (9, '叶蓁', '上海市虹桥路1286号', '叶蓁', '18555559752', 'mikelaoma@163.com', 13);
INSERT INTO `customer` VALUES (10, '鱼幼薇', '广东省深圳特区东湖公园内', '鱼幼薇', '15555558254', '568968384@qq.com', 17);
INSERT INTO `customer` VALUES (11, '周承钰', '天津市河西区越秀路与平江道交口文化中心', '周承钰', '15555554485', '1152837939@qq.com', 17);
INSERT INTO `customer` VALUES (12, '颜淡', '天津市古文化街天后宫内', '颜淡', '15555527011', '306200379@qq.com', 22);
INSERT INTO `customer` VALUES (13, '歆瑶', '河北省石家庄市东大街4号', '歆瑶', '15555595322', '1051645043@qq.com', 12316);
INSERT INTO `customer` VALUES (14, '唐蕊', '山西省太原市文庙巷3号', '唐蕊', '18687000686', 'miker669@163.com', 12318);
INSERT INTO `customer` VALUES (15, '尹妃瑄', '辽宁省沈阳市沈阳路171号', '尹妃瑄', '18680068630', '28962756@qq.com ', 10);
INSERT INTO `customer` VALUES (19, '李四', '提示', '李四', '213212412312', '1@aa', 7);
INSERT INTO `customer` VALUES (21, '王珞丹', '上海', '王珞丹', '13453413432', '1@aa', 12320);
INSERT INTO `customer` VALUES (23, 'll', 'qwdqw', 'qwdq', '123123', '1@aa', 6);
INSERT INTO `customer` VALUES (24, 'sauhdhsa', 'wqqwd', 'dsadas', '21321312', '1@aa', 12330);

-- ----------------------------
-- Table structure for customershare
-- ----------------------------
DROP TABLE IF EXISTS `customershare`;
CREATE TABLE `customershare`  (
  `shareId` int(11) NOT NULL AUTO_INCREMENT,
  `cusId` int(11) NULL DEFAULT NULL,
  `empId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`shareId`) USING BTREE,
  INDEX `share_cus_key`(`cusId`) USING BTREE,
  INDEX `share_emp_key`(`empId`) USING BTREE,
  CONSTRAINT `share_cus_key` FOREIGN KEY (`cusId`) REFERENCES `customer` (`cusId`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `share_emp_key` FOREIGN KEY (`empId`) REFERENCES `employees` (`empid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customershare
-- ----------------------------
INSERT INTO `customershare` VALUES (1, 2, 21);
INSERT INTO `customershare` VALUES (4, 1, 4);
INSERT INTO `customershare` VALUES (5, 12, 4);
INSERT INTO `customershare` VALUES (6, 4, 4);
INSERT INTO `customershare` VALUES (8, 15, 12317);
INSERT INTO `customershare` VALUES (10, 5, NULL);
INSERT INTO `customershare` VALUES (11, 1, 12330);
INSERT INTO `customershare` VALUES (12, 24, 13);

-- ----------------------------
-- Table structure for customervisit
-- ----------------------------
DROP TABLE IF EXISTS `customervisit`;
CREATE TABLE `customervisit`  (
  `visitId` int(11) NOT NULL AUTO_INCREMENT,
  `cusId` int(11) NULL DEFAULT NULL,
  `empId` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`visitId`) USING BTREE,
  INDEX `visit_cus_key`(`cusId`) USING BTREE,
  INDEX `visit_emp_key`(`empId`) USING BTREE,
  CONSTRAINT `visit_cus_key` FOREIGN KEY (`cusId`) REFERENCES `customer` (`cusId`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `visit_emp_key` FOREIGN KEY (`empId`) REFERENCES `employees` (`empid`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customervisit
-- ----------------------------
INSERT INTO `customervisit` VALUES (1, 5, 8, '日常拜访', '2020-08-21 19:08:00');
INSERT INTO `customervisit` VALUES (2, 8, 12315, '日常拜访', '2020-08-21 19:08:00');
INSERT INTO `customervisit` VALUES (4, 1, 3, '日常拜访', '2020-08-24 11:10:00');
INSERT INTO `customervisit` VALUES (6, 21, 12320, '日常拜访', '2020-08-25 19:04:00');
INSERT INTO `customervisit` VALUES (8, 5, NULL, '日常拜访', '2020-08-26 10:27:00');
INSERT INTO `customervisit` VALUES (9, 1, 12330, '日常拜访', '2020-08-26 15:44:00');
INSERT INTO `customervisit` VALUES (10, 24, 12330, '日常拜访', '2020-08-26 15:45:00');

-- ----------------------------
-- Table structure for employees
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees`  (
  `empid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleid` int(20) NOT NULL,
  PRIMARY KEY (`empid`) USING BTREE,
  INDEX `emp_role_key`(`roleid`) USING BTREE,
  CONSTRAINT `emp_role_key` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12331 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES (1, '秦梦瑶', 'qmy', '13899990728', '秦梦瑶', '1234@123.com', 2);
INSERT INTO `employees` VALUES (2, 'admin', '123', '13899910728', 'admin', 'hjfaykx@gmail.com', 1);
INSERT INTO `employees` VALUES (3, '师妃喧', '123', '13261308888', '师妃喧', 'lw38373238dangji@163.com', 3);
INSERT INTO `employees` VALUES (4, '允寒夜', 'yhy', '15627327777', '允寒夜', 'oko7190962ta@163.com', 3);
INSERT INTO `employees` VALUES (5, '樱雪婷', 'yxt', '13288888039', '樱雪婷', 'zd0746263yuem@163.com', 3);
INSERT INTO `employees` VALUES (6, '月韩依', 'yhy', '13260163336', '月韩依', 'oll6907573taoy@163.com', 3);
INSERT INTO `employees` VALUES (7, '雯欣雨', 'wxy', '18688669451', '雯欣雨', 'skxs55899248che@163.com', 3);
INSERT INTO `employees` VALUES (8, '可一琳', 'kyl', '1123123123', '可一琳', 'sojc9545341shansi@163.com', 3);
INSERT INTO `employees` VALUES (9, '韩语惠', 'hyh', '13121475444', '韩语惠', 'y53677311luboxio@163.com', 3);
INSERT INTO `employees` VALUES (10, '叶允栗', 'yyx', '13240944567', '叶允栗', 'bxpq3831507yishao@163.com', 3);
INSERT INTO `employees` VALUES (11, '安雨痕', 'ayh', '13145205838', '安雨痕', 'dnkw45915878ken@163.com', 3);
INSERT INTO `employees` VALUES (12, '欧蓝逞', 'olc', '13145206448', '欧蓝逞', 'hoaf1755657huang3@163.com', 3);
INSERT INTO `employees` VALUES (13, '柯掩澈', 'kyc', '13211115238', '柯掩澈', 'laki78002765sush@163.com', 3);
INSERT INTO `employees` VALUES (14, '谢钟绣', 'xzx', '15611117475', '谢钟绣', 'bgos7217171te@163.com', 3);
INSERT INTO `employees` VALUES (15, '安雨沫', 'aym', '13211116957', '安雨沫', 'hr95214827liaodao@163.com', 3);
INSERT INTO `employees` VALUES (16, '安若素', 'ars', '13211110534', '安若素', 'xinxiangshicheng29@163.com', 3);
INSERT INTO `employees` VALUES (17, '安恋熙', 'alq', '13145207461', '安恋熙', 'zhangxindeailian@163.com', 3);
INSERT INTO `employees` VALUES (18, '凡筱若', 'fsr', '13160752222\r\n', '凡筱若', 'zhangxindeailian1@163.com', 3);
INSERT INTO `employees` VALUES (19, '斯凡', 'sf', '18677145111', '斯凡', 'zhangxindeailian2@163.com', 3);
INSERT INTO `employees` VALUES (20, '于霏', 'yf', '18677154141', '于霏', 'zhangxindeailian3@163.com', 3);
INSERT INTO `employees` VALUES (21, '安知晓', 'ayx', '18697948585', '安知晓', 'zhangxindeailian4@163.com', 3);
INSERT INTO `employees` VALUES (22, '安念莲', 'anl', '18648959393', '安念莲', 'cynthia_017@163.com', 3);
INSERT INTO `employees` VALUES (12314, '安瑾凉', 'aql', '18676838859\r\n', '安瑾凉', '150939468@qq.com', 3);
INSERT INTO `employees` VALUES (12315, '杨语菲', 'yyf', '18585999999\r\n', '杨语菲', 'miikii2008@gmail.com', 3);
INSERT INTO `employees` VALUES (12316, '杨兮诺', 'yxn', '18581829996', '杨兮诺', 'shilibleach@yahoo.cn', 3);
INSERT INTO `employees` VALUES (12317, '夏璃沫', 'xlm', '13163288808', '夏璃沫', '503609793@qq.com', 3);
INSERT INTO `employees` VALUES (12318, '叶梓潼', 'yzt', '13048322225\r\n', '叶梓潼', 'mijaewbv@kmvyhaxe.com', 3);
INSERT INTO `employees` VALUES (12319, '2', '2', '2', '2', '2@2', 2);
INSERT INTO `employees` VALUES (12320, '3', '3', '3', '3', '3@3', 3);
INSERT INTO `employees` VALUES (12327, '1', '1', '1', '1', '1@1', 1);
INSERT INTO `employees` VALUES (12328, '7', '11', '1', '1', '1@aa', 6);
INSERT INTO `employees` VALUES (12330, '9', '123', '9', '9', '9@9', 3);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `permissionId` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `purl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ismenu` int(11) NULL DEFAULT NULL,
  `parentId` int(11) NULL DEFAULT NULL,
  `pinfo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`permissionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (2, '员工查询', '', NULL, NULL, 'emp:list');
INSERT INTO `permission` VALUES (3, '员工增加', NULL, NULL, NULL, 'emp:add');
INSERT INTO `permission` VALUES (4, '员工删除', NULL, NULL, NULL, 'emp:del');
INSERT INTO `permission` VALUES (5, '员工修改', NULL, NULL, NULL, 'emp:upd');
INSERT INTO `permission` VALUES (6, '客户查询', NULL, NULL, NULL, 'cus:list');
INSERT INTO `permission` VALUES (7, '客户添加', NULL, NULL, NULL, 'cus:add');
INSERT INTO `permission` VALUES (8, '客户删除', NULL, NULL, NULL, 'cus:del');
INSERT INTO `permission` VALUES (9, '客户修改', NULL, NULL, NULL, 'cus:upd');
INSERT INTO `permission` VALUES (10, '拜访查询', NULL, NULL, NULL, 'cusv:list');
INSERT INTO `permission` VALUES (11, '拜访增加', NULL, NULL, NULL, 'cusv:add');
INSERT INTO `permission` VALUES (12, '拜访删除', NULL, NULL, NULL, 'cusv:del');
INSERT INTO `permission` VALUES (13, '拜访修改', NULL, NULL, NULL, 'cusv:upd');
INSERT INTO `permission` VALUES (14, '分享查询', NULL, NULL, NULL, 'cuss:list');
INSERT INTO `permission` VALUES (15, '分享增加', NULL, NULL, NULL, 'cuss:add');
INSERT INTO `permission` VALUES (16, '分享删除', NULL, NULL, NULL, 'cuss:del');
INSERT INTO `permission` VALUES (17, '分享修改', NULL, NULL, NULL, 'cuss:upd');
INSERT INTO `permission` VALUES (18, '角色查询', NULL, NULL, NULL, 'role:list');
INSERT INTO `permission` VALUES (19, '角色增加', NULL, NULL, NULL, 'role:add');
INSERT INTO `permission` VALUES (20, '角色删除', NULL, NULL, NULL, 'role:del');
INSERT INTO `permission` VALUES (21, '角色修改', NULL, NULL, NULL, 'role:upd');
INSERT INTO `permission` VALUES (22, '权限查询', NULL, NULL, NULL, 'perm:list');
INSERT INTO `permission` VALUES (23, '权限增加', NULL, NULL, NULL, 'perm:add');
INSERT INTO `permission` VALUES (24, '权限删除', NULL, NULL, NULL, 'perm:del');
INSERT INTO `permission` VALUES (25, '权限修改', NULL, NULL, NULL, 'perm:upd');
INSERT INTO `permission` VALUES (26, '角色权限查询', NULL, NULL, NULL, 'rp:list');
INSERT INTO `permission` VALUES (27, '角色权限增加', NULL, NULL, NULL, 'rp:add');
INSERT INTO `permission` VALUES (28, '角色权限删除', NULL, NULL, NULL, 'rp:del');
INSERT INTO `permission` VALUES (29, '角色权限修改', NULL, NULL, NULL, 'rp:upd');
INSERT INTO `permission` VALUES (30, '客户转移', NULL, NULL, NULL, 'cus:move');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleInfo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 'admin');
INSERT INTO `role` VALUES (2, '经理', 'manager');
INSERT INTO `role` VALUES (3, '员工', 'emp');
INSERT INTO `role` VALUES (6, '大堂', 'manager');
INSERT INTO `role` VALUES (7, '大堂2', 'manager');

-- ----------------------------
-- Table structure for rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `rolepermission`;
CREATE TABLE `rolepermission`  (
  `rpId` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NULL DEFAULT NULL,
  `permissionId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`rpId`) USING BTREE,
  INDEX `rp_perm_key`(`permissionId`) USING BTREE,
  INDEX `rp_role_key`(`roleId`) USING BTREE,
  CONSTRAINT `rp_perm_key` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`permissionId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rp_role_key` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rolepermission
-- ----------------------------
INSERT INTO `rolepermission` VALUES (1, 1, 2);
INSERT INTO `rolepermission` VALUES (2, 1, 3);
INSERT INTO `rolepermission` VALUES (3, 1, 4);
INSERT INTO `rolepermission` VALUES (7, 1, 5);
INSERT INTO `rolepermission` VALUES (8, 1, 6);
INSERT INTO `rolepermission` VALUES (9, 1, 7);
INSERT INTO `rolepermission` VALUES (10, 1, 8);
INSERT INTO `rolepermission` VALUES (11, 1, 9);
INSERT INTO `rolepermission` VALUES (12, 1, 10);
INSERT INTO `rolepermission` VALUES (13, 1, 11);
INSERT INTO `rolepermission` VALUES (14, 1, 12);
INSERT INTO `rolepermission` VALUES (15, 1, 13);
INSERT INTO `rolepermission` VALUES (16, 1, 14);
INSERT INTO `rolepermission` VALUES (17, 1, 15);
INSERT INTO `rolepermission` VALUES (18, 1, 16);
INSERT INTO `rolepermission` VALUES (19, 1, 17);
INSERT INTO `rolepermission` VALUES (20, 1, 18);
INSERT INTO `rolepermission` VALUES (21, 1, 19);
INSERT INTO `rolepermission` VALUES (22, 1, 20);
INSERT INTO `rolepermission` VALUES (23, 1, 21);
INSERT INTO `rolepermission` VALUES (24, 1, 22);
INSERT INTO `rolepermission` VALUES (25, 1, 23);
INSERT INTO `rolepermission` VALUES (26, 1, 24);
INSERT INTO `rolepermission` VALUES (27, 1, 25);
INSERT INTO `rolepermission` VALUES (28, 1, 26);
INSERT INTO `rolepermission` VALUES (29, 1, 27);
INSERT INTO `rolepermission` VALUES (30, 1, 28);
INSERT INTO `rolepermission` VALUES (31, 1, 29);
INSERT INTO `rolepermission` VALUES (33, 2, 2);
INSERT INTO `rolepermission` VALUES (34, 2, 3);
INSERT INTO `rolepermission` VALUES (35, 2, 4);
INSERT INTO `rolepermission` VALUES (36, 2, 5);
INSERT INTO `rolepermission` VALUES (37, 2, 6);
INSERT INTO `rolepermission` VALUES (38, 2, 30);
INSERT INTO `rolepermission` VALUES (39, 2, 10);
INSERT INTO `rolepermission` VALUES (40, 2, 14);
INSERT INTO `rolepermission` VALUES (41, 3, 2);
INSERT INTO `rolepermission` VALUES (42, 3, 5);
INSERT INTO `rolepermission` VALUES (43, 3, 6);
INSERT INTO `rolepermission` VALUES (44, 3, 7);
INSERT INTO `rolepermission` VALUES (45, 3, 8);
INSERT INTO `rolepermission` VALUES (46, 3, 9);
INSERT INTO `rolepermission` VALUES (47, 3, 10);
INSERT INTO `rolepermission` VALUES (48, 3, 11);
INSERT INTO `rolepermission` VALUES (49, 3, 12);
INSERT INTO `rolepermission` VALUES (50, 3, 13);
INSERT INTO `rolepermission` VALUES (51, 3, 14);
INSERT INTO `rolepermission` VALUES (52, 3, 15);
INSERT INTO `rolepermission` VALUES (56, 1, 30);
INSERT INTO `rolepermission` VALUES (57, 3, 30);
INSERT INTO `rolepermission` VALUES (67, 7, 19);

SET FOREIGN_KEY_CHECKS = 1;
