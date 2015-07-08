-- MySQL dump 10.13  Distrib 5.6.25, for Win64 (x86_64)
--
-- Host: localhost    Database: mycai
-- ------------------------------------------------------
-- Server version	5.6.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill_order`
--

DROP TABLE IF EXISTS `bill_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `wechat_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `bill` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `order_ts` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `delivery_ts` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_info` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `consignee_contact` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `confirm_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_order`
--

LOCK TABLES `bill_order` WRITE;
/*!40000 ALTER TABLE `bill_order` DISABLE KEYS */;
INSERT INTO `bill_order` VALUES (1,NULL,'o5Irvt5957jQ4xmdHmDp59epk0UU','{\"items\":[{\"productId\":152,\"productName\":\"蕹菜\",\"description\":\"一级蕹菜\",\"amount\":2,\"productPrice\":3.68,\"picurl\":\"images/pic1092995-931019923.jpg\",\"productUnit\":\"千克\",\"$$hashKey\":\"object:65\"}],\"totalAmount\":2,\"totalPrice\":7.36}','2015-07-01 21:41:13','2015/07/01 21:40','话','龙大','1358879','未配送','777152522'),(2,'哈哈哈哈哈喽','o5Irvt5957jQ4xmdHmDp59epk0UU','{\"items\":[{\"productId\":211,\"productName\":\"长白萝卜\",\"description\":\"一级长白萝卜\",\"amount\":\"1\",\"productPrice\":1.22,\"picurl\":\"images/pic1170351165-931019923.jpg\",\"productUnit\":\"千克\",\"$$hashKey\":\"object:129\"}],\"totalAmount\":1,\"totalPrice\":1.22}','2015-07-01 21:51:08','2015/07/01 21:50','睡觉睡觉睡觉','达达','睡觉妈妈','未配送','694092271'),(3,'çµè¾¾','o5Irvt5957jQ4xmdHmDp59epk0UU','{\"items\":[{\"productId\":204,\"productName\":\"胡萝卜\",\"description\":\"一级胡萝卜\",\"amount\":\"1\",\"productPrice\":2.38,\"picurl\":\"images/pic32776064-931019923.jpg\",\"productUnit\":\"千克\",\"$$hashKey\":\"object:131\"}],\"totalAmount\":1,\"totalPrice\":2.38}','2015-07-01 21:55:48','2015/07/01 21:55','大家睡觉睡觉','就是你说呢','.34694994','未配送','088673585'),(4,'灵达','o5Irvt5957jQ4xmdHmDp59epk0UU','{\"items\":[{\"productId\":211,\"productName\":\"长白萝卜\",\"description\":\"一级长白萝卜\",\"amount\":2,\"productPrice\":1.22,\"picurl\":\"images/pic1170351165-931019923.jpg\",\"productUnit\":\"千克\",\"$$hashKey\":\"object:65\"}],\"totalAmount\":2,\"totalPrice\":2.44}','2015-07-01 22:26:21','2015/07/01 22:26','就是计算机','不是你说呢','8788466','未配送','075234063'),(5,'灵达','o5Irvt5957jQ4xmdHmDp59epk0UU','{\"items\":[{\"productId\":152,\"productName\":\"蕹菜\",\"description\":\"一级蕹菜\",\"amount\":\"1\",\"productPrice\":3.68,\"picurl\":\"images/pic1092995-931019923.jpg\",\"productUnit\":\"千克\",\"$$hashKey\":\"object:135\"}],\"totalAmount\":1,\"totalPrice\":3.68}','2015-07-01 22:32:44','2015/07/01 22:32','安安','去！','还是计算机','未配送','523002905'),(6,'灵达','o5Irvt5957jQ4xmdHmDp59epk0UU','{\"items\":[{\"productId\":185,\"productName\":\"番茄\",\"description\":\"一级番茄\",\"amount\":2,\"productPrice\":3.5,\"picurl\":\"images/pic965338-931019923.jpg\",\"productUnit\":\"千克\",\"$$hashKey\":\"object:562\"}],\"totalAmount\":2,\"totalPrice\":7}','2015-07-03 06:54:36','2015/07/03 06:54','yt','nb','bb','未配送','907960295'),(7,'灵达','o5Irvt5957jQ4xmdHmDp59epk0UU','{\"items\":[{\"productId\":185,\"productName\":\"番茄\",\"description\":\"一级番茄\",\"amount\":2,\"productPrice\":3.5,\"picurl\":\"images/pic965338-931019923.jpg\",\"productUnit\":\"千克\",\"$$hashKey\":\"object:562\"}],\"totalAmount\":2,\"totalPrice\":7}','2015-07-03 06:54:36','2015/07/03 06:54','yt','nb','bb','未配送','134021881'),(8,'小傅','o5Irvtw83iWQ7aI0F17_RHCkylQw','{\"items\":[{\"productId\":217,\"productName\":\"五花肉\",\"description\":\"精选五花肉\",\"amount\":\"1\",\"productPrice\":2.68,\"picurl\":\"product_images/pic20401548545833633-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:109\"},{\"productId\":234,\"productName\":\"排骨\",\"description\":\"精选排骨\",\"amount\":\"1\",\"productPrice\":2.68,\"picurl\":\"product_images/pic829782987610529-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:110\"}],\"totalAmount\":2,\"totalPrice\":5.36}','2015-07-05 12:08:00','2015/07/07 12:06','宾馆','小时','长阳路','未配送','234123452'),(10,'lingda','o5Irvt5957jQ4xmdHmDp59epk0UU','{\"items\":[{\"productId\":265,\"productName\":\"胡萝卜\",\"description\":\"精选胡萝卜\",\"amount\":2,\"productPrice\":2.68,\"picurl\":\"product_images/pic32776064558208149-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:92\"},{\"productId\":225,\"productName\":\"大白菜\",\"description\":\"精选大白菜\",\"amount\":2,\"productPrice\":2.68,\"picurl\":\"product_images/pic22906982548339067-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:93\"}],\"totalAmount\":4,\"totalPrice\":10.72}','2015-07-08 11:44:35','2015/07/08 11:44','ADSF','ADF','ADF','未配送','452258700');
/*!40000 ALTER TABLE `bill_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `unit` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `picurl` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `onsale` int(1) DEFAULT '0',
  `data_change_last_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (215,'丝瓜','精选丝瓜','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic649823987430570-931019923.jpg',0,'2015-07-05 00:40:34'),(216,'中翅','精选中翅','QINROUDANLEI','XIANJIROU',2.68,'斤','product_images/pic653112987433859-931019923.jpg',0,'2015-07-05 00:45:12'),(217,'五花肉','精选五花肉','QINROUDANLEI','YIJIBAITIAO',2.68,'斤','product_images/pic20401548545833633-931019923.jpg',0,'2015-07-05 00:45:13'),(218,'冬瓜','精选冬瓜','SHUCAISHUIGUO','QIEGUOLEI',2.68,'斤','product_images/pic678064987458811-931019923.jpg',0,'2015-07-05 00:45:13'),(219,'冬笋','精选冬笋','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic679647987460394-931019923.jpg',0,'2015-07-05 00:45:13'),(220,'刀豆','精选刀豆','SHUCAISHUIGUO','DOULEI',2.68,'斤','product_images/pic686662987467409-931019923.jpg',0,'2015-07-05 00:45:13'),(221,'南瓜','精选南瓜','SHUCAISHUIGUO','QIEGUOLEI',2.68,'斤','product_images/pic691301987472048-931019923.jpg',0,'2015-07-05 00:45:13'),(222,'卷心菜','精选卷心菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic21327408546759493-931019923.jpg',0,'2015-07-04 01:46:34'),(223,'四季豆','精选四季豆','SHUCAISHUIGUO','DOULEI',2.68,'斤','product_images/pic22128990547561075-931019923.jpg',0,'2015-07-05 00:45:13'),(224,'圣女果','精选圣女果','SHUCAISHUIGUO','QIEGUOLEI',2.68,'斤','product_images/pic22173420547605505-931019923.jpg',0,'2015-07-05 00:45:13'),(225,'大白菜','精选大白菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic22906982548339067-931019923.jpg',0,'2015-07-04 01:46:34'),(226,'大葱','精选大葱','SHUCAISHUIGUO','CONGJIANGSUAN',2.68,'斤','product_images/pic741418987522165-931019923.jpg',0,'2015-07-05 00:45:40'),(227,'姜','精选姜','SHUCAISHUIGUO','CONGJIANGSUAN',2.68,'斤','product_images/pic2300431854641-931019923.jpg',0,'2015-07-05 00:46:03'),(228,'嫩玉米','精选嫩玉米','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic23314099548746184-931019923.jpg',0,'2015-07-05 00:46:19'),(229,'小青菜','精选小青菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic23882521549314606-931019923.jpg',0,'2015-07-04 01:46:35'),(230,'山药','精选山药','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic767262987548009-931019923.jpg',0,'2015-07-05 01:19:37'),(231,'平菇','精选平菇','SHUCAISHUIGUO','JUNLEI',2.68,'斤','product_images/pic783284987564031-931019923.jpg',0,'2015-07-05 00:46:38'),(232,'慈姑','精选慈姑','SHUCAISHUIGUO','JUNLEI',2.68,'斤','product_images/pic795017987575764-931019923.jpg',0,'2015-07-05 00:46:56'),(233,'扁豆','精选扁豆','SHUCAISHUIGUO','DOULEI',2.68,'斤','product_images/pic815653987596400-931019923.jpg',0,'2015-07-05 00:47:14'),(234,'排骨','精选排骨','QINROUDANLEI','YIJIBAITIAO',2.68,'斤','product_images/pic829782987610529-931019923.jpg',0,'2015-07-05 00:47:56'),(235,'整鸡','精选整鸡','QINROUDANLEI','XIANJIROU',2.68,'斤','product_images/pic845613987626360-931019923.jpg',0,'2015-07-05 00:48:13'),(236,'整鸭','精选整鸭','QINROUDANLEI','YAROU',2.68,'斤','product_images/pic845625987626372-931019923.jpg',0,'2015-07-05 00:48:25'),(237,'杏鲍菇','精选杏鲍菇','SHUCAISHUIGUO','JUNLEI',2.68,'斤','product_images/pic26691689552123774-931019923.jpg',0,'2015-07-05 00:48:44'),(238,'杭茄','精选杭茄','SHUCAISHUIGUO','QIEGUOLEI',2.68,'斤','product_images/pic854327987635074-931019923.jpg',0,'2015-07-05 00:52:17'),(239,'枸杞','精选枸杞','SHUCAISHUIGUO','TECAI',2.68,'斤','product_images/pic849574987630321-931019923.jpg',0,'2015-07-05 00:54:01'),(240,'毛豆','精选毛豆','SHUCAISHUIGUO','DOULEI',2.68,'斤','product_images/pic891851987672598-931019923.jpg',0,'2015-07-05 00:55:08'),(241,'洋葱','精选洋葱','SHUCAISHUIGUO','CONGJIANGSUAN',2.68,'斤','product_images/pic899270987680017-931019923.jpg',0,'2015-07-05 00:56:18'),(242,'海带','精选海带','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic892815987673562-931019923.jpg',0,'2015-07-05 00:57:47'),(243,'海带－干','精选海带－干','SHUCAISHUIGUO','TECAI',2.68,'斤','product_images/pic860043476-31431073-931019923.jpg',0,'2015-07-05 00:58:39'),(244,'滑菇','精选滑菇','SHUCAISHUIGUO','JUNLEI',2.68,'斤','product_images/pic913174987693921-931019923.jpg',0,'2015-07-05 01:02:24'),(245,'牛肉','精选牛肉','QINROUDANLEI','NIUROU',2.68,'斤','product_images/pic940430987721177-931019923.jpg',0,'2015-07-05 01:03:38'),(246,'牛腩','精选牛腩','QINROUDANLEI','NIUROU',2.68,'斤','product_images/pic940654987721401-931019923.jpg',0,'2015-07-05 01:04:54'),(247,'猪大肠','精选猪大肠','QINROUDANLEI','DONGZHUROU',2.68,'斤','product_images/pic29072643554504728-931019923.jpg',0,'2015-07-05 01:06:13'),(248,'猪肉','精选猪肉','QINROUDANLEI','DONGZHUROU',2.68,'斤','product_images/pic946847987727594-931019923.jpg',0,'2015-07-05 01:07:19'),(249,'猪肝','精选猪肝','QINROUDANLEI','DONGZHUROU',2.68,'斤','product_images/pic946867987727614-931019923.jpg',0,'2015-07-05 01:08:30'),(250,'猪蹄','精选猪蹄','QINROUDANLEI','DONGZHUROU',2.68,'斤','product_images/pic950362987731109-931019923.jpg',0,'2015-07-05 01:09:31'),(251,'猴头菇','精选猴头菇','SHUCAISHUIGUO','JUNLEI',2.68,'斤','product_images/pic29083463554515548-931019923.jpg',0,'2015-07-05 01:10:34'),(252,'甜玉米','精选甜玉米','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic29759526555191611-931019923.jpg',0,'2015-07-05 01:11:47'),(253,'番茄','精选番茄','SHUCAISHUIGUO','QIEGUOLEI',2.68,'斤','product_images/pic965338987746085-931019923.jpg',0,'2015-07-05 01:13:48'),(254,'白萝卜','精选白萝卜','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic30219804555651889-931019923.jpg',0,'2015-07-05 01:14:20'),(255,'秋葵','精选秋葵','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1000458987781205-931019923.jpg',0,'2015-07-04 01:46:37'),(256,'空心菜','精选空心菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic30924915556357000-931019923.jpg',0,'2015-07-04 01:46:37'),(257,'竹笋','精选竹笋','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic1007410987788157-931019923.jpg',0,'2015-07-05 01:22:35'),(258,'糯玉米','精选糯玉米','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic31684409557116494-931019923.jpg',0,'2015-07-05 01:22:23'),(259,'紫菜','精选紫菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1027089987807836-931019923.jpg',0,'2015-07-04 01:46:37'),(260,'红薯','精选红薯','SHUCAISHUIGUO','QIEGUOLEI',2.68,'斤','product_images/pic1039181987819928-931019923.jpg',0,'2015-07-05 01:18:10'),(261,'纯瘦肉','精选纯瘦肉','QINROUDANLEI','ERJIBAITIAO',2.68,'斤','product_images/pic32136722557568807-931019923.jpg',0,'2015-07-05 01:15:00'),(262,'绿豆芽','精选绿豆芽','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic32389750557821835-931019923.jpg',0,'2015-07-05 01:21:48'),(263,'羊肉','精选羊肉','QINROUDANLEI','YANGROU',2.68,'斤','product_images/pic1045055987825802-931019923.jpg',0,'2015-07-05 01:15:54'),(264,'羊蝎子','精选羊蝎子','QINROUDANLEI','YECAILEI',2.68,'斤','product_images/pic32473804557905889-931019923.jpg',0,'2015-07-05 00:45:13'),(265,'胡萝卜','精选胡萝卜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic32776064558208149-931019923.jpg',0,'2015-07-04 01:46:37'),(266,'芋艿','精选芋艿','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic1069396987850143-931019923.jpg',0,'2015-07-05 01:15:40'),(267,'芥蓝','精选芥蓝','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1070808987851555-931019923.jpg',0,'2015-07-04 01:46:37'),(268,'芦笋','精选芦笋','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1068325987849072-931019923.jpg',0,'2015-07-04 01:46:38'),(269,'花菜','精选花菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1070923987851670-931019923.jpg',0,'2015-07-04 01:46:38'),(270,'芹菜','精选芹菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1071171987851918-931019923.jpg',0,'2015-07-04 01:46:38'),(271,'芹菜2','精选芹菜2','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic33206351558638436-931019923.jpg',0,'2015-07-04 01:46:38'),(272,'苋菜','精选苋菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1071729987852476-931019923.jpg',0,'2015-07-04 01:46:38'),(273,'苦瓜','精选苦瓜','SHUCAISHUIGUO','QIEGUOLEI',2.68,'斤','product_images/pic1068726987849473-931019923.jpg',0,'2015-07-05 01:16:34'),(274,'茄子','精选茄子','SHUCAISHUIGUO','QIEGUOLEI',2.68,'斤','product_images/pic1063116987843863-931019923.jpg',0,'2015-07-05 01:18:21'),(275,'茭白','精选茭白','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1071344987852091-931019923.jpg',0,'2015-07-04 01:46:38'),(276,'茴香','精选茴香','TIAOLIAOQITA','TIAOWEIPIN',2.68,'斤','product_images/pic1080549987861296-931019923.jpg',0,'2015-07-05 01:17:57'),(277,'茶树菇','精选茶树菇','SHUCAISHUIGUO','JUNLEI',2.68,'斤','product_images/pic33139596558571681-931019923.jpg',0,'2015-07-05 01:21:00'),(278,'茼蒿','精选茼蒿','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1075459987856206-931019923.jpg',0,'2015-07-04 01:46:38'),(279,'草菇','精选草菇','SHUCAISHUIGUO','JUNLEI',2.68,'斤','product_images/pic1075614987856361-931019923.jpg',0,'2015-07-05 01:16:45'),(280,'荠菜','精选荠菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1076348987857095-931019923.jpg',0,'2015-07-04 01:46:38'),(281,'莲藕','精选莲藕','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic1079395987860142-931019923.jpg',0,'2015-07-05 01:19:59'),(282,'莴苣','精选莴苣','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1078703987859450-931019923.jpg',0,'2015-07-04 01:46:39'),(283,'菠菜','精选菠菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1080316987861063-931019923.jpg',0,'2015-07-04 01:46:39'),(284,'蒜','精选蒜','SHUCAISHUIGUO','CONGJIANGSUAN',2.68,'斤','product_images/pic3394831865585-931019923.jpg',0,'2015-07-05 01:16:19'),(285,'蒜苔','精选蒜苔','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1085880987866627-931019923.jpg',0,'2015-07-04 01:46:39'),(286,'蘑菇','精选蘑菇','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1097686987878433-931019923.jpg',0,'2015-07-04 01:46:39'),(287,'蚕豆','精选蚕豆','SHUCAISHUIGUO','DOULEI',2.68,'斤','product_images/pic1103953987884700-931019923.jpg',0,'2015-07-05 01:20:15'),(288,'西兰花','精选西兰花','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic34505984559938069-931019923.jpg',0,'2015-07-04 01:46:39'),(289,'西芹','精选西芹','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1124634987905381-931019923.jpg',0,'2015-07-04 01:46:39'),(290,'西葫芦','精选西葫芦','SHUCAISHUIGUO','QIEGUOLEI',2.68,'斤','product_images/pic34910554560342639-931019923.jpg',0,'2015-07-05 01:21:33'),(291,'豌豆','精选豌豆','SHUCAISHUIGUO','DOULEI',2.68,'斤','product_images/pic1149306987930053-931019923.jpg',0,'2015-07-05 01:18:58'),(292,'辣椒','精选辣椒','TIAOLIAOQITA','TIAOWEIPIN',2.68,'斤','product_images/pic1166799987947546-931019923.jpg',0,'2015-07-05 01:22:10'),(293,'金针菇','精选金针菇','SHUCAISHUIGUO','JUNLEI',2.68,'斤','product_images/pic37085648562517733-931019923.jpg',0,'2015-07-05 01:20:48'),(294,'银耳','精选银耳','SHUCAISHUIGUO','TECAI',2.68,'斤','product_images/pic1214973987995720-931019923.jpg',0,'2015-07-05 01:20:36'),(295,'长豇豆','精选长豇豆','SHUCAISHUIGUO','DOULEI',2.68,'斤','product_images/pic37927582563359667-931019923.jpg',0,'2015-07-05 01:16:55'),(296,'青椒','精选青椒','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic1227776988008523-931019923.jpg',0,'2015-07-05 01:17:12'),(297,'青菜','精选青菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1234634988015381-931019923.jpg',0,'2015-07-04 01:46:40'),(298,'韭菜','精选韭菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1239439988020186-931019923.jpg',0,'2015-07-04 01:46:40'),(299,'韭黄','精选韭黄','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1246327988027074-931019923.jpg',0,'2015-07-04 01:46:40'),(300,'香菇','精选香菇','SHUCAISHUIGUO','JUNLEI',2.68,'斤','product_images/pic1252686988033433-931019923.jpg',0,'2015-07-05 01:17:36'),(301,'香菜','精选香菜','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic1252707988033454-931019923.jpg',0,'2015-07-04 01:46:40'),(302,'马兰头','精选马兰头','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic38659376564091461-931019923.jpg',0,'2015-07-04 01:46:40'),(303,'马铃薯','精选马铃薯','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic39205048564637133-931019923.jpg',0,'2015-07-05 01:18:38'),(304,'鸡翅','精选鸡翅','QINROUDANLEI','DONGJIROU',2.68,'斤','product_images/pic1287620988068367-931019923.jpg',0,'2015-07-05 00:45:13'),(305,'鸡腿','精选鸡腿','QINROUDANLEI','DONGJIROU',2.68,'斤','product_images/pic1288062988068809-931019923.jpg',0,'2015-07-05 00:45:13'),(306,'鸡腿菇','精选鸡腿菇','SHUCAISHUIGUO','JUNLEI',2.68,'斤','product_images/pic39963657565395742-931019923.jpg',0,'2015-07-05 01:17:24'),(307,'鸭腿','精选鸭腿','QINROUDANLEI','YAROU',2.68,'斤','product_images/pic1288434988069181-931019923.jpg',0,'2015-07-05 00:45:13'),(308,'黄瓜','精选黄瓜','SHUCAISHUIGUO','GENJINGLEI',2.68,'斤','product_images/pic1289880988070627-931019923.jpg',0,'2015-07-05 01:15:22'),(309,'黄豆芽','精选黄豆芽','SHUCAISHUIGUO','YECAILEI',2.68,'斤','product_images/pic40205563565637648-931019923.jpg',0,'2015-07-04 01:46:41'),(310,'黑木耳','精选黑木耳','SHUCAISHUIGUO','JUNLEI',2.68,'斤','product_images/pic39922844565354929-931019923.jpg',0,'2015-07-05 00:47:26');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `wechat_id` varchar(255) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `headimgurl` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'灵达','o5Irvt5957jQ4xmdHmDp59epk0UU','admin',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-08 17:54:20
