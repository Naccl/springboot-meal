/*
 Navicat Premium Data Transfer

 Source Server         : My
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : springboot_meal

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 05/06/2020 03:36:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for diningcar
-- ----------------------------
DROP TABLE IF EXISTS `diningcar`;
CREATE TABLE `diningcar`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `food_id` int(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKqjhgrcx3ky7jsae7654f8wg33`(`food_id`) USING BTREE,
  INDEX `FKdun5fk0t289o6lar9vohif1cc`(`user_id`) USING BTREE,
  CONSTRAINT `FKdun5fk0t289o6lar9vohif1cc` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKqjhgrcx3ky7jsae7654f8wg33` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 87 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diningcar
-- ----------------------------
INSERT INTO `diningcar` VALUES (1, 7, 3);
INSERT INTO `diningcar` VALUES (2, 1, 4);
INSERT INTO `diningcar` VALUES (3, 2, 4);
INSERT INTO `diningcar` VALUES (4, 3, 4);
INSERT INTO `diningcar` VALUES (5, 38, 3);
INSERT INTO `diningcar` VALUES (6, 40, 3);
INSERT INTO `diningcar` VALUES (7, 10, 3);
INSERT INTO `diningcar` VALUES (8, 11, 3);
INSERT INTO `diningcar` VALUES (9, 2, 2);
INSERT INTO `diningcar` VALUES (10, 11, 2);
INSERT INTO `diningcar` VALUES (11, 21, 2);

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `comment` int(0) NULL DEFAULT NULL,
  `feature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hits` int(0) NULL DEFAULT NULL,
  `material` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` int(0) NOT NULL,
  `type_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK5uq6egy0y1jhbme9r7yfi0r7n`(`type_id`) USING BTREE,
  CONSTRAINT `FK5uq6egy0y1jhbme9r7yfi0r7n` FOREIGN KEY (`type_id`) REFERENCES `foodtype` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES (1, 0, '暂无', 1, '主料：菠菜300克，鸡蛋3个，盐、料酒、葱末、姜末、味精、香油各适量。', '菠菜炒鸡蛋', '/images/jiachang/01.jpg', 9, 3);
INSERT INTO `food` VALUES (2, -1, '工艺：炒    口味：清香味', 0, '主料：韭菜4两（约160克），大鸡蛋3只。\r\n', '韭菜炒鸡蛋', '/images/jiachang/02.jpg', 8, 3);
INSERT INTO `food` VALUES (3, -1, '特色：色泽红亮、酸甜微辣，脆爽可口。', 0, '主料：娃娃菜200克。', '渝味辣白菜', '/images/jiachang/03.jpg', 6, 1);
INSERT INTO `food` VALUES (4, -1, '特色：腰花鲜嫩，造形美观，味道醇厚，滑润不腻。', 0, '主料：猪腰子200克。辅料： 冬笋片、水发木耳各50克；酱油10克、精盐3克、味精1克、绍酒20克、湿粉15克。', '爆炒腰花', '/images/jiachang/04.jpg', 12, 1);
INSERT INTO `food` VALUES (5, 0, '暂无', 0, '主料：韩国辣白菜1碗，洗米水一大碗（约500毫升），中型土豆1个，小型洋葱1个，黄豆芽1把，金针菇1把，豆腐1块，五花肉1小块（牛肉亦可）。', '韩国泡菜汤', '/images/jiachang/05.jpg', 16, 1);
INSERT INTO `food` VALUES (6, -1, '特色：色泽淡稚悦目，味似蟹肉，鲜香馥郁。\r\n辅料：熟火腿3片（25克）， 熟笋6片（60克），水发大香菇3朵，姜片2.5克、葱结1个\r\n', 0, '主料：桂鱼一条（约重750克）。', '清蒸桂鱼', '/images/jiachang/06.jpg', 25, 1);
INSERT INTO `food` VALUES (7, -1, '口味：酸辣味。', 0, '主料：大白菜 1/4棵（约400g）。', '酸辣白菜', '/images/jiachang/07.jpg', 14, 1);
INSERT INTO `food` VALUES (8, -1, '特色：特点：色泽银红，酸甜辣香。功效：润肠通便。', 0, '主料：卷心菜750 克\r\n辅料：葱花3 克，干辣椒节4 克，花椒2 克。\r\n', '醋溜白菜', '/images/jiachang/08.jpg', 14, 1);
INSERT INTO `food` VALUES (9, -1, '暂无', 0, '主料：猪肉、鸡蛋、木耳、黄瓜\r\n辅料：葱姜、盐、酱油、料酒、香油。', '木须肉', '/images/jiachang/09.jpg', 8, 1);
INSERT INTO `food` VALUES (10, -1, '特色：鲜嫩可口。功效：强壮身体，改善体质。', 0, '暂无主料：嫩豆腐600 克，肉末150 克，酱油10 克，细盐3 克，味精3 克，黄酒5克，姜末5 克，葱花3 克，胡椒粉1 克，鲜汤300 克。', '肉末豆腐', '/images/jiachang/10.jpg', 7, 1);
INSERT INTO `food` VALUES (11, 18, '特色：清香爽口，一清二白。', 0, '暂无', '小葱拌豆腐', '/images/liangcai/1.jpg', 22, 2);
INSERT INTO `food` VALUES (12, -1, '暂无', 0, '主料：凤爪1000克，野山椒50克。\r\n辅料：泡菜酸水一些，芹菜、花椒、味精、鸡精、盐、胡椒少许。\r\n主料：凤爪1000克，野山椒50克。\r\n辅料：泡菜酸水一些，芹菜、花椒、味精、鸡精、盐、胡椒少许。', '泡椒鸡爪', '/images/liangcai/2.jpg', 12, 2);
INSERT INTO `food` VALUES (13, -1, '暂无', 0, '主料：鸡爪，花椒,盐（最好当然是专门的泡菜盐，如果没有也没关系,用一般的盐也可以), 八角,桂皮,少许糖,少许白酒。', '泡椒凤爪', '/images/liangcai/3.jpg', 12, 2);
INSERT INTO `food` VALUES (14, -1, '特色：海带是一种海洋蔬菜，含碘、藻胶酸和甘露醇等，可防治甲状腺肿大、克汀病、软骨病、佝偻病。现代药理学研究表明，吃海带可增加单核巨噬细胞活性，增强机体免疫力和抗辐射', 0, '主料：海带300克、蒜茸、香油、醋、味精等。', '凉拌海带丝', '/images/liangcai/4.jpg', 8, 2);
INSERT INTO `food` VALUES (15, 0, '特色：爽、脆可口。', 0, '主料：花生米\r\n辅料：生菜、香干。', '老醋花生米', '/images/liangcai/5.jpg', 14, 2);
INSERT INTO `food` VALUES (16, -1, '暂无', 0, '主料：黄瓜3根 （切成条长5―6厘米、宽1―1.5厘米）\r\n辅料：辣椒少许，大蒜1瓣，色拉油、盐、白醋、糖、味精、麻油少许。\r\n', '凉拌黄瓜', '/images/liangcai/6.jpg', 6, 2);
INSERT INTO `food` VALUES (17, -1, '特色：新疆的一道吃肉、抓饭用的菜，喜欢大口吃肉、大口喝酒的朋友，我推荐给你！', 0, '主料：辣椒、洋葱、西红柿。醋、酱油、辣椒油、味精。', '老虎菜', '/images/liangcai/7.jpg', 7, 2);
INSERT INTO `food` VALUES (18, -1, '特色：透明、味鲜。', 0, '主料：猪肉片500克，盐、味精、葱段、姜块(拍楹)花椒粒、大料瓣知适量。\r\n', '肉皮冻', '/images/liangcai/8.jpg', 9, 2);
INSERT INTO `food` VALUES (19, -1, '特色：爽口解腻、开胃下酒。', 0, '主料：莲藕500克、酱油15克、精盐6克、味精2克、葱花3克、姜丝3克、蒜片3克。', '凉拌藕片', '/images/liangcai/9.jpg', 12, 2);
INSERT INTO `food` VALUES (20, 15, '暂无', 1, '主料：猪臀肉500克。 大蒜50克、上等酱油50克、红油10克、盐2克、冷汤50克、红糖10克、香料3克、味精1克。', '蒜泥白肉', '/images/liangcai/10.jpg', 20, 2);
INSERT INTO `food` VALUES (21, -1, '暂无', 0, '粉条,葱末,姜末,蒜泥等', '四川酸辣粉', '/images/zhushi/1.jpg', 28, 3);
INSERT INTO `food` VALUES (22, -1, '香啊\r\n', 0, '蛋一个，炒饭6两', '蛋包饭', '/images/zhushi/2.jpg', 6, 3);
INSERT INTO `food` VALUES (23, -1, '暂无', 0, '鸡一只，白米,姜，香兰叶，辣椒等', '海南鸡饭', '/images/zhushi/3.jpg', 22, 3);
INSERT INTO `food` VALUES (24, -1, '口味：清香味。', 1, '稻米100克，松花蛋50克', '皮蛋粥', '/images/zhushi/4.jpg', 5, 3);
INSERT INTO `food` VALUES (25, 0, '口味：甜味', 0, '赤小豆20克，稻米100克', '红豆粥', '/images/zhushi/5.jpg', 5, 3);
INSERT INTO `food` VALUES (26, -1, '饼表面软，饼底脆脆的，口感香甜', 0, '玉米面粉200克 面粉100克 甜玉米粒一碗 酵母粉5克(1小勺) 白糖适量 鸡蛋。', '玉米饼', '/images/zhushi/6.jpg', 16, 3);
INSERT INTO `food` VALUES (27, -1, '暂无', 0, '面粉.鸡蛋,菠菜,酱油,盐和水', '猫耳朵', '/images/zhushi/7.jpg', 5, 3);
INSERT INTO `food` VALUES (28, -1, '\r\n工艺：煮    \r\n口味：咸鲜味', 0, '\r\n烙饼(标准粉)200克\r\n。辅料：黄花菜（干）50克,木耳(水发)50克,粉丝50克,青蒜10克,香菜10克,羊肉（熟）100克.\r\n', '羊肉泡馍', '/images/zhushi/8.jpg', 5, 3);
INSERT INTO `food` VALUES (29, -1, '暂无', 0, '米饭250克，火腿30克，鸡蛋1个，豌豆20克，黄瓜、虾各25克，油30克，味精1克，葱花5克，精盐3克。', '扬州炒饭', '/images/zhushi/9.jpg', 7, 3);
INSERT INTO `food` VALUES (30, 10, '暂无', 0, '糯米粉100克、澄粉25克\r\n辅料：油35毫升、豆沙50克、水150毫升\r\n糯米粉100克、澄粉25克\r\n辅料：油35毫升、豆沙50克、水150毫升\r\n糯米粉100克、澄粉25克\r\n辅料：油35毫升.', '驴打滚', '/images/zhushi/10.jpg', 12, 3);
INSERT INTO `food` VALUES (31, -1, '暂无', 1, '主料：甘蔗200克，马蹄150克，红枣50克，红糖50克，桂圆肉10克\r\n', '甘蔗马蹄糖水', '/images/yinpin/01.jpg', 8, 4);
INSERT INTO `food` VALUES (32, -1, '暂无', 0, '主料：吉利丁片9片、红腰豆1瓶、淡奶油130g、白砂糖80g、鲜牛奶400g、蛋黄2个、抹茶粉4g。', '抹茶慕司', '/images/yinpin/02.jpg', 8, 4);
INSERT INTO `food` VALUES (33, -1, '特色：苹果有安眠养神、补中益气、消食化积之特长。瘦身苹果牛奶饮让你首先感觉到的是苹果的甜香，然后是牛奶的浓郁、香滑。临睡前喝一碗，不但可以帮助睡眠，也不会长胖。', 4, '主料：苹果250g,牛奶200g。', '瘦身苹果牛奶饮', '/images/yinpin/03.jpg', 11, 4);
INSERT INTO `food` VALUES (34, 9, '特色：利用西瓜本身的糖分使西米露变得甜美可口，一口入嘴，只说得出“好吃”两个字，水果的芬芳甜美中带有西米的软韧，最后吞下去后，还会觉得唇齿留香。', 0, '主料：西米250g,西瓜200g', 'QQ西瓜西米露', '/images/yinpin/04.jpg', 11, 4);
INSERT INTO `food` VALUES (35, -1, '特色：牛奶是早餐必不可少的一份子，无论是西式还是中式早餐，都别忘记喝牛奶。提高钙的摄取可以减少脂肪的沉积，而钙的最好来源正是牛奶。所以，就让早晨从一杯甜美可口的草莓牛奶开始吧', 0, '主料：草莓口味奶拌 20g,牛奶 1杯\r\n辅料：大杏仁 适量', '杏仁草莓奶拌早餐', '/images/yinpin/05.jpg', 12, 4);
INSERT INTO `food` VALUES (36, -1, '暂无', 0, '主料：腐竹约100克，鹌鹑蛋8只，雪耳约30克，冰糖约350克，清水6杯。', '腐竹鹑蛋糖水', '/images/yinpin/06.jpg', 9, 4);
INSERT INTO `food` VALUES (37, -1, '特色：奶香浓郁，茶味清香。', 0, '主料：红茶、红豆汤、面粉 黄油、白糖、鸡蛋黄 炼乳、牛奶.', '红豆相思茶', '/images/yinpin/07.jpg', 5, 4);
INSERT INTO `food` VALUES (38, 0, '暂无', 0, '主料：鲜牛奶、ＱＱ糖、苹果（你爱吃那种水果就用那种，但最好跟ＱＱ糖口味一致，我觉得这样会比较好', '布丁', '/images/yinpin/08.jpg', 6, 4);
INSERT INTO `food` VALUES (39, -1, '暂无', 0, '主料：薰衣草适量，紫罗兰适量，椰丝适量\r\n辅料：罗拔臣纯鱼胶粉一盒（50克），开水两碗，花型冰格（要奈高温100度以上），不锈钢容器（大碗2个）。', '极度诱惑的果冻', '/images/yinpin/09.jpg', 8, 4);
INSERT INTO `food` VALUES (40, -1, '暂无', 0, '主料：鲜姜一大块，牛奶一袋，白糖，捣蒜缸', '姜撞奶', '/images/yinpin/10.jpg', 17, 4);

-- ----------------------------
-- Table structure for foodtype
-- ----------------------------
DROP TABLE IF EXISTS `foodtype`;
CREATE TABLE `foodtype`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of foodtype
-- ----------------------------
INSERT INTO `foodtype` VALUES (1, '家常');
INSERT INTO `foodtype` VALUES (2, '凉菜');
INSERT INTO `foodtype` VALUES (3, '主食');
INSERT INTO `foodtype` VALUES (4, '饮品');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ident` int(0) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '0', 1, '123', '0', 'admin');
INSERT INTO `user` VALUES (2, '北京市朝阳区', 0, '123', '123456789', 'user1');
INSERT INTO `user` VALUES (3, '北京市朝阳区', 0, '123', '123456789', 'user2');
INSERT INTO `user` VALUES (4, '北京市朝阳区', 0, '123', '123456789', 'user3');

SET FOREIGN_KEY_CHECKS = 1;
