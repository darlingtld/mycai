-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: mycai
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.14.04.1

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `username` varchar(45) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES ('admin','songda','ROLE_ADMIN');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

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
  `confirm_bill` text,
  `confirm_ts` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_order`
--

LOCK TABLES `bill_order` WRITE;
/*!40000 ALTER TABLE `bill_order` DISABLE KEYS */;
INSERT INTO `bill_order` VALUES (45,'JasonZhao','o5Irvt2ACfsTy_Y4w-Pwu6qH_o_Y','{\"items\":[{\"productId\":222,\"productName\":\"卷心菜\",\"description\":\"精选卷心菜\",\"amount\":12,\"productPrice\":2,\"picurl\":\"product_images/pic21327408546759493-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:216\"},{\"productId\":225,\"productName\":\"大白菜\",\"description\":\"精选大白菜\",\"amount\":4,\"productPrice\":1,\"picurl\":\"product_images/pic22906982548339067-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:217\"},{\"productId\":229,\"productName\":\"小青菜\",\"description\":\"精选小青菜\",\"amount\":3,\"productPrice\":4,\"picurl\":\"product_images/pic23882521549314606-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:218\"}],\"totalAmount\":19,\"totalPrice\":40}','2015-07-23 13:31:42','2015/07/23 13:31','易安信','jason','15298387073','已配送（未付款）','966654207','{\"items\":[{\"productId\":222,\"productName\":\"卷心菜\",\"description\":\"精选卷心菜\",\"amount\":\"11\",\"productPrice\":2,\"picurl\":\"product_images/pic21327408546759493-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:216\"},{\"productId\":225,\"productName\":\"大白菜\",\"description\":\"精选大白菜\",\"amount\":\"11\",\"productPrice\":1,\"picurl\":\"product_images/pic22906982548339067-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:217\"},{\"productId\":229,\"productName\":\"小青菜\",\"description\":\"精选小青菜\",\"amount\":\"11\",\"productPrice\":4,\"picurl\":\"product_images/pic23882521549314606-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:218\"}],\"totalAmount\":19,\"totalPrice\":\"66.00\"}','2015-07-23 13:32:39'),(46,'灵达','o5Irvt5957jQ4xmdHmDp59epk0UU','{\"items\":[{\"productId\":222,\"productName\":\"卷心菜\",\"description\":\"精选卷心菜\",\"amount\":\"1\",\"productPrice\":2,\"picurl\":\"product_images/pic21327408546759493-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:104\"}],\"totalAmount\":1,\"totalPrice\":2}','2015-07-23 21:39:49','2015/07/24 08:39','家里','唐灵达','13402188638','已配送（已付款）','492826221',NULL,NULL),(47,'songda user','o5Irvt3tty06NYT8W-nJa0gPERvM','{\"items\":[{\"productId\":217,\"productName\":\"五花肉\",\"description\":\"精选五花肉\",\"amount\":\"1\",\"productPrice\":2.68,\"picurl\":\"product_images/pic20401548545833633-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:89\"},{\"productId\":245,\"productName\":\"牛肉\",\"description\":\"精选牛肉\",\"amount\":\"1\",\"productPrice\":2.68,\"picurl\":\"product_images/pic940430987721177-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:90\"},{\"productId\":236,\"productName\":\"整鸭\",\"description\":\"精选整鸭\",\"amount\":\"1\",\"productPrice\":2.68,\"picurl\":\"product_images/pic845625987626372-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:91\"},{\"productId\":222,\"productName\":\"卷心菜\",\"description\":\"精选卷心菜\",\"amount\":5,\"productPrice\":2.68,\"picurl\":\"product_images/pic21327408546759493-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:92\"},{\"productId\":229,\"productName\":\"小青菜\",\"description\":\"精选小青菜\",\"amount\":\"1\",\"productPrice\":2.68,\"picurl\":\"product_images/pic23882521549314606-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:94\"},{\"productId\":225,\"productName\":\"大白菜\",\"description\":\"精选大白菜\",\"amount\":5,\"productPrice\":1,\"picurl\":\"product_images/pic22906982548339067-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:114\"},{\"productId\":294,\"productName\":\"银耳\",\"description\":\"精选银耳\",\"amount\":7,\"productPrice\":999,\"picurl\":\"product_images/pic1214973987995720-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:115\"}],\"totalAmount\":21,\"totalPrice\":7020.76}','2015-07-23 21:52:37','2015/07/23 21:52','我','我','12345678901','已配送（已付款）','906921782','{\"items\":[{\"productId\":217,\"productName\":\"五花肉\",\"description\":\"精选五花肉\",\"amount\":\"2\",\"productPrice\":2.68,\"picurl\":\"product_images/pic20401548545833633-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:89\"},{\"productId\":245,\"productName\":\"牛肉\",\"description\":\"精选牛肉\",\"amount\":\"2\",\"productPrice\":2.68,\"picurl\":\"product_images/pic940430987721177-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:90\"},{\"productId\":236,\"productName\":\"整鸭\",\"description\":\"精选整鸭\",\"amount\":\"2\",\"productPrice\":2.68,\"picurl\":\"product_images/pic845625987626372-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:91\"},{\"productId\":222,\"productName\":\"卷心菜\",\"description\":\"精选卷心菜\",\"amount\":\"2\",\"productPrice\":2.68,\"picurl\":\"product_images/pic21327408546759493-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:92\"},{\"productId\":229,\"productName\":\"小青菜\",\"description\":\"精选小青菜\",\"amount\":\"2\",\"productPrice\":2.68,\"picurl\":\"product_images/pic23882521549314606-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:94\"},{\"productId\":225,\"productName\":\"大白菜\",\"description\":\"精选大白菜\",\"amount\":\"2\",\"productPrice\":1,\"picurl\":\"product_images/pic22906982548339067-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:114\"},{\"productId\":294,\"productName\":\"银耳\",\"description\":\"精选银耳\",\"amount\":\"2\",\"productPrice\":999,\"picurl\":\"product_images/pic1214973987995720-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:115\"}],\"totalAmount\":21,\"totalPrice\":\"13986.00\"}','2015-07-23 21:57:36'),(48,'灵达','o5Irvt5957jQ4xmdHmDp59epk0UU','{\"items\":[{\"productId\":217,\"productName\":\"五花肉\",\"description\":\"精选五花肉\",\"amount\":\"1\",\"productPrice\":999,\"picurl\":\"product_images/pic20401548545833633-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:118\"},{\"productId\":234,\"productName\":\"排骨\",\"description\":\"精选排骨\",\"amount\":2,\"productPrice\":22,\"picurl\":\"product_images/pic829782987610529-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:119\"},{\"productId\":316,\"productName\":\"蹄髈\",\"description\":\"精选蹄髈\",\"amount\":\"1\",\"productPrice\":11,\"picurl\":\"product_images/pic1168644987949391-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:120\"}],\"totalAmount\":4,\"totalPrice\":1054}','2015-07-24 19:56:45','2015/07/25 19:56','家里','唐灵达','13402188638','未配送','733912078',NULL,NULL),(49,'凤权','o5Irvt6r7U71oT6FlnAuLCKqqsvY','{\"items\":[{\"productId\":256,\"productName\":\"空心菜\",\"description\":\"精选空心菜\",\"amount\":5,\"productPrice\":3,\"picurl\":\"product_images/pic30924915556357000-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:228\"},{\"productId\":248,\"productName\":\"猪肉\",\"description\":\"精选猪肉\",\"amount\":8,\"productPrice\":11,\"picurl\":\"product_images/pic946847987727594-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:229\"}],\"totalAmount\":13,\"totalPrice\":103}','2015-07-24 20:03:50','2015/07/25 04:00','骏产货源充足','唐凤','13764301160','未配送','960395494',NULL,NULL),(50,'凤权','o5Irvt6r7U71oT6FlnAuLCKqqsvY','{\"items\":[{\"productId\":276,\"productName\":\"茴香\",\"description\":\"精选茴香\",\"amount\":4,\"productPrice\":18,\"picurl\":\"product_images/pic1080549987861296-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:226\"},{\"productId\":292,\"productName\":\"辣椒\",\"description\":\"精选辣椒\",\"amount\":6,\"productPrice\":999,\"picurl\":\"product_images/pic1166799987947546-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:227\"}],\"totalAmount\":10,\"totalPrice\":6066}','2015-07-24 22:59:13','2015/07/25 22:40','骏产货源充足','唐凤','13764301160','未配送','957057101',NULL,NULL),(51,'灵达','o5Irvt5957jQ4xmdHmDp59epk0UU','{\"items\":[{\"productId\":256,\"productName\":\"空心菜\",\"description\":\"精选空心菜\",\"amount\":2,\"productPrice\":3,\"picurl\":\"product_images/pic30924915556357000-931019923.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:215\"}],\"totalAmount\":2,\"totalPrice\":6}','2015-07-25 14:17:54','2015/07/25 14:17','abc','a','13402188638','未配送','798414300',NULL,NULL);
/*!40000 ALTER TABLE `bill_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procurement`
--

DROP TABLE IF EXISTS `procurement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procurement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `procprice` double DEFAULT NULL,
  `procindex` double DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procurement`
--

LOCK TABLES `procurement` WRITE;
/*!40000 ALTER TABLE `procurement` DISABLE KEYS */;
INSERT INTO `procurement` VALUES (10,282,2,1,'2015-07-25 06:08:58');
/*!40000 ALTER TABLE `procurement` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=317 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (215,'丝瓜','精选丝瓜','SHUCAISHUIGUO','GENJINGLEI',2.5,'斤','product_images/pic649823987430570-931019923.jpg',0,'2015-07-20 12:06:41'),(216,'中翅','精选中翅','QINROUDANLEI','XIANJIROU',20,'斤','product_images/pic653112987433859-931019923.jpg',0,'2015-07-20 11:48:40'),(217,'五花肉','精选五花肉','QINROUDANLEI','YIJIBAITIAO',999,'斤','product_images/pic20401548545833633-931019923.jpg',0,'2015-07-21 13:13:55'),(218,'冬瓜','精选冬瓜','SHUCAISHUIGUO','QIEGUOLEI',1.2,'斤','product_images/pic678064987458811-931019923.jpg',0,'2015-07-20 12:06:41'),(219,'冬笋','精选冬笋','SHUCAISHUIGUO','GENJINGLEI',999,'斤','product_images/pic679647987460394-931019923.jpg',0,'2015-07-21 13:13:55'),(220,'刀豆','精选刀豆','SHUCAISHUIGUO','DOULEI',3,'斤','product_images/pic686662987467409-931019923.jpg',0,'2015-07-20 12:06:41'),(221,'南瓜','精选南瓜','SHUCAISHUIGUO','QIEGUOLEI',2,'斤','product_images/pic691301987472048-931019923.jpg',0,'2015-07-20 12:06:41'),(222,'卷心菜','精选卷心菜','SHUCAISHUIGUO','YECAILEI',2,'斤','product_images/pic21327408546759493-931019923.jpg',0,'2015-07-20 12:06:37'),(223,'四季豆','精选四季豆','SHUCAISHUIGUO','DOULEI',2,'斤','product_images/pic22128990547561075-931019923.jpg',0,'2015-07-20 12:06:37'),(224,'圣女果','精选圣女果','SHUCAISHUIGUO','QIEGUOLEI',999,'斤','product_images/pic22173420547605505-931019923.jpg',0,'2015-07-21 13:13:55'),(225,'大白菜','精选大白菜','SHUCAISHUIGUO','YECAILEI',1,'斤','product_images/pic22906982548339067-931019923.jpg',0,'2015-07-20 12:06:38'),(226,'大葱','精选大葱','SHUCAISHUIGUO','CONGJIANGSUAN',3,'斤','product_images/pic741418987522165-931019923.jpg',0,'2015-07-20 12:06:41'),(227,'姜','精选姜','SHUCAISHUIGUO','CONGJIANGSUAN',5.5,'斤','product_images/pic2300431854641-931019923.jpg',0,'2015-07-20 12:06:38'),(228,'嫩玉米','精选嫩玉米','SHUCAISHUIGUO','GENJINGLEI',999,'斤','product_images/pic23314099548746184-931019923.jpg',0,'2015-07-21 13:13:55'),(229,'小青菜','精选小青菜','SHUCAISHUIGUO','YECAILEI',4,'斤','product_images/pic23882521549314606-931019923.jpg',0,'2015-07-20 12:06:38'),(230,'山药','精选山药','SHUCAISHUIGUO','GENJINGLEI',7,'斤','product_images/pic767262987548009-931019923.jpg',0,'2015-07-20 12:06:41'),(231,'平菇','精选平菇','SHUCAISHUIGUO','JUNLEI',999,'斤','product_images/pic783284987564031-931019923.jpg',0,'2015-07-21 13:13:55'),(232,'慈姑','精选慈姑','SHUCAISHUIGUO','JUNLEI',999,'斤','product_images/pic795017987575764-931019923.jpg',0,'2015-07-21 13:13:55'),(233,'扁豆','精选扁豆','SHUCAISHUIGUO','DOULEI',3.5,'斤','product_images/pic815653987596400-931019923.jpg',0,'2015-07-20 12:06:42'),(234,'排骨','精选排骨','QINROUDANLEI','YIJIBAITIAO',22,'斤','product_images/pic829782987610529-931019923.jpg',0,'2015-07-20 11:48:44'),(235,'整鸡','精选整鸡','QINROUDANLEI','XIANJIROU',8,'斤','product_images/pic845613987626360-931019923.jpg',0,'2015-07-20 11:48:44'),(236,'整鸭','精选整鸭','QINROUDANLEI','YAROU',7.5,'斤','product_images/pic845625987626372-931019923.jpg',0,'2015-07-20 11:48:44'),(237,'杏鲍菇','精选杏鲍菇','SHUCAISHUIGUO','JUNLEI',999,'斤','product_images/pic26691689552123774-931019923.jpg',0,'2015-07-21 13:13:55'),(238,'杭茄','精选杭茄','SHUCAISHUIGUO','QIEGUOLEI',5,'斤','product_images/pic854327987635074-931019923.jpg',0,'2015-07-20 12:06:42'),(239,'枸杞','精选枸杞','SHUCAISHUIGUO','TECAI',999,'斤','product_images/pic849574987630321-931019923.jpg',0,'2015-07-21 13:13:55'),(240,'毛豆','精选毛豆','SHUCAISHUIGUO','DOULEI',2.5,'斤','product_images/pic891851987672598-931019923.jpg',0,'2015-07-20 12:06:42'),(241,'洋葱','精选洋葱','SHUCAISHUIGUO','CONGJIANGSUAN',1.8,'斤','product_images/pic899270987680017-931019923.jpg',0,'2015-07-20 12:06:42'),(242,'海带','精选海带','SHUCAISHUIGUO','GENJINGLEI',999,'斤','product_images/pic892815987673562-931019923.jpg',0,'2015-07-21 13:13:55'),(243,'海带－干','精选海带－干','SHUCAISHUIGUO','TECAI',999,'斤','product_images/pic860043476-31431073-931019923.jpg',0,'2015-07-21 13:13:55'),(244,'滑菇','精选滑菇','SHUCAISHUIGUO','JUNLEI',999,'斤','product_images/pic913174987693921-931019923.jpg',0,'2015-07-21 13:13:55'),(245,'牛肉','精选牛肉','QINROUDANLEI','NIUROU',32,'斤','product_images/pic940430987721177-931019923.jpg',0,'2015-07-20 11:48:45'),(246,'牛腩','精选牛腩','QINROUDANLEI','NIUROU',27,'斤','product_images/pic940654987721401-931019923.jpg',0,'2015-07-20 11:48:46'),(247,'猪大肠','精选猪大肠','QINROUDANLEI','DONGZHUROU',999,'斤','product_images/pic29072643554504728-931019923.jpg',0,'2015-07-21 13:13:55'),(248,'猪肉','精选猪肉','QINROUDANLEI','DONGZHUROU',11,'斤','product_images/pic946847987727594-931019923.jpg',0,'2015-07-20 11:48:46'),(249,'猪肝','精选猪肝','QINROUDANLEI','DONGZHUROU',999,'斤','product_images/pic946867987727614-931019923.jpg',0,'2015-07-21 13:13:55'),(250,'猪蹄','精选猪蹄','QINROUDANLEI','DONGZHUROU',13,'斤','product_images/pic950362987731109-931019923.jpg',0,'2015-07-20 11:48:58'),(251,'猴头菇','精选猴头菇','SHUCAISHUIGUO','JUNLEI',999,'斤','product_images/pic29083463554515548-931019923.jpg',0,'2015-07-21 13:13:55'),(252,'甜玉米','精选甜玉米','SHUCAISHUIGUO','GENJINGLEI',2.5,'斤','product_images/pic29759526555191611-931019923.jpg',0,'2015-07-20 12:06:39'),(253,'番茄','精选番茄','SHUCAISHUIGUO','QIEGUOLEI',2,'斤','product_images/pic965338987746085-931019923.jpg',0,'2015-07-20 12:06:43'),(254,'白萝卜','精选白萝卜','SHUCAISHUIGUO','GENJINGLEI',1.5,'斤','product_images/pic30219804555651889-931019923.jpg',0,'2015-07-20 12:06:39'),(255,'秋葵','精选秋葵','SHUCAISHUIGUO','YECAILEI',3,'斤','product_images/pic1000458987781205-931019923.jpg',0,'2015-07-20 12:06:33'),(256,'空心菜','精选空心菜','SHUCAISHUIGUO','YECAILEI',3,'斤','product_images/pic30924915556357000-931019923.jpg',0,'2015-07-20 12:06:39'),(257,'竹笋','精选竹笋','SHUCAISHUIGUO','GENJINGLEI',999,'斤','product_images/pic1007410987788157-931019923.jpg',0,'2015-07-21 13:13:55'),(258,'糯玉米','精选糯玉米','SHUCAISHUIGUO','GENJINGLEI',2.5,'斤','product_images/pic31684409557116494-931019923.jpg',0,'2015-07-20 12:06:39'),(259,'紫菜','精选紫菜','SHUCAISHUIGUO','YECAILEI',999,'斤','product_images/pic1027089987807836-931019923.jpg',0,'2015-07-21 13:13:55'),(260,'红薯','精选红薯','SHUCAISHUIGUO','QIEGUOLEI',999,'斤','product_images/pic1039181987819928-931019923.jpg',0,'2015-07-21 13:13:55'),(261,'纯瘦肉','精选纯瘦肉','QINROUDANLEI','ERJIBAITIAO',12,'斤','product_images/pic32136722557568807-931019923.jpg',0,'2015-07-20 11:48:48'),(262,'绿豆芽','精选绿豆芽','SHUCAISHUIGUO','GENJINGLEI',999,'斤','product_images/pic32389750557821835-931019923.jpg',0,'2015-07-21 13:13:55'),(263,'羊肉','精选羊肉','QINROUDANLEI','YANGROU',31,'斤','product_images/pic1045055987825802-931019923.jpg',0,'2015-07-20 11:48:48'),(264,'羊蝎子','精选羊蝎子','QINROUDANLEI','YANGROU',999,'斤','product_images/pic32473804557905889-931019923.jpg',0,'2015-07-21 13:13:55'),(265,'胡萝卜','精选胡萝卜','SHUCAISHUIGUO','YECAILEI',1.8,'斤','product_images/pic32776064558208149-931019923.jpg',0,'2015-07-20 12:06:39'),(266,'芋艿','精选芋艿','SHUCAISHUIGUO','GENJINGLEI',3.5,'斤','product_images/pic1069396987850143-931019923.jpg',0,'2015-07-20 12:06:34'),(267,'芥蓝','精选芥蓝','SHUCAISHUIGUO','YECAILEI',999,'斤','product_images/pic1070808987851555-931019923.jpg',0,'2015-07-21 13:13:55'),(268,'芦笋','精选芦笋','SHUCAISHUIGUO','YECAILEI',7.1,'斤','product_images/pic1068325987849072-931019923.jpg',0,'2015-07-20 12:06:34'),(269,'花菜','精选花菜','SHUCAISHUIGUO','YECAILEI',2.5,'斤','product_images/pic1070923987851670-931019923.jpg',0,'2015-07-20 12:06:34'),(270,'芹菜','精选芹菜','SHUCAISHUIGUO','YECAILEI',2.5,'斤','product_images/pic1071171987851918-931019923.jpg',0,'2015-07-20 12:06:35'),(271,'芹菜2','精选芹菜2','SHUCAISHUIGUO','YECAILEI',999,'斤','product_images/pic33206351558638436-931019923.jpg',0,'2015-07-21 13:13:55'),(272,'苋菜','精选苋菜','SHUCAISHUIGUO','YECAILEI',2.5,'斤','product_images/pic1071729987852476-931019923.jpg',0,'2015-07-20 12:06:35'),(273,'苦瓜','精选苦瓜','SHUCAISHUIGUO','QIEGUOLEI',2.5,'斤','product_images/pic1068726987849473-931019923.jpg',0,'2015-07-20 12:06:34'),(274,'茄子','精选茄子','SHUCAISHUIGUO','QIEGUOLEI',4,'斤','product_images/pic1063116987843863-931019923.jpg',0,'2015-07-20 12:06:34'),(275,'茭白','精选茭白','SHUCAISHUIGUO','YECAILEI',3,'斤','product_images/pic1071344987852091-931019923.jpg',0,'2015-07-20 12:06:35'),(276,'茴香','精选茴香','TIAOLIAOQITA','TIAOWEIPIN',18,'斤','product_images/pic1080549987861296-931019923.jpg',0,'2015-07-20 11:48:51'),(277,'茶树菇','精选茶树菇','SHUCAISHUIGUO','JUNLEI',6,'斤','product_images/pic33139596558571681-931019923.jpg',0,'2015-07-20 12:06:39'),(278,'茼蒿','精选茼蒿','SHUCAISHUIGUO','YECAILEI',999,'斤','product_images/pic1075459987856206-931019923.jpg',0,'2015-07-21 13:13:55'),(279,'草菇','精选草菇','SHUCAISHUIGUO','JUNLEI',999,'斤','product_images/pic1075614987856361-931019923.jpg',0,'2015-07-21 13:13:55'),(280,'荠菜','精选荠菜','SHUCAISHUIGUO','YECAILEI',999,'斤','product_images/pic1076348987857095-931019923.jpg',0,'2015-07-21 13:13:55'),(281,'莲藕','精选莲藕','SHUCAISHUIGUO','GENJINGLEI',7,'斤','product_images/pic1079395987860142-931019923.jpg',0,'2015-07-20 12:06:35'),(282,'莴苣','精选莴苣','SHUCAISHUIGUO','YECAILEI',2,'斤','product_images/pic1078703987859450-931019923.jpg',0,'2015-07-25 06:08:58'),(283,'菠菜','精选菠菜','SHUCAISHUIGUO','YECAILEI',5,'斤','product_images/pic1080316987861063-931019923.jpg',0,'2015-07-20 12:06:35'),(284,'蒜','精选蒜','SHUCAISHUIGUO','CONGJIANGSUAN',5,'斤','product_images/pic3394831865585-931019923.jpg',0,'2015-07-20 12:06:40'),(285,'蒜苔','精选蒜苔','SHUCAISHUIGUO','YECAILEI',4,'斤','product_images/pic1085880987866627-931019923.jpg',0,'2015-07-20 12:06:36'),(286,'蘑菇','精选蘑菇','SHUCAISHUIGUO','YECAILEI',10,'斤','product_images/pic1097686987878433-931019923.jpg',0,'2015-07-20 12:06:36'),(287,'蚕豆','精选蚕豆','SHUCAISHUIGUO','DOULEI',999,'斤','product_images/pic1103953987884700-931019923.jpg',0,'2015-07-21 13:13:55'),(288,'西兰花','精选西兰花','SHUCAISHUIGUO','YECAILEI',4.5,'斤','product_images/pic34505984559938069-931019923.jpg',0,'2015-07-20 12:06:40'),(289,'西芹','精选西芹','SHUCAISHUIGUO','YECAILEI',2.5,'斤','product_images/pic1124634987905381-931019923.jpg',0,'2015-07-20 12:06:36'),(290,'西葫芦','精选西葫芦','SHUCAISHUIGUO','QIEGUOLEI',2.5,'斤','product_images/pic34910554560342639-931019923.jpg',0,'2015-07-20 12:06:40'),(291,'豌豆','精选豌豆','SHUCAISHUIGUO','DOULEI',999,'斤','product_images/pic1149306987930053-931019923.jpg',0,'2015-07-21 13:13:55'),(292,'辣椒','精选辣椒','TIAOLIAOQITA','TIAOWEIPIN',999,'斤','product_images/pic1166799987947546-931019923.jpg',0,'2015-07-21 13:13:55'),(293,'金针菇','精选金针菇','SHUCAISHUIGUO','JUNLEI',999,'斤','product_images/pic37085648562517733-931019923.jpg',0,'2015-07-21 13:13:55'),(294,'银耳','精选银耳','SHUCAISHUIGUO','TECAI',999,'斤','product_images/pic1214973987995720-931019923.jpg',0,'2015-07-21 13:13:55'),(295,'长豇豆','精选长豇豆','SHUCAISHUIGUO','DOULEI',3,'斤','product_images/pic37927582563359667-931019923.jpg',0,'2015-07-20 12:06:40'),(296,'青椒','精选青椒','SHUCAISHUIGUO','GENJINGLEI',3,'斤','product_images/pic1227776988008523-931019923.jpg',0,'2015-07-20 12:06:36'),(297,'青菜','精选青菜','SHUCAISHUIGUO','YECAILEI',2,'斤','product_images/pic1234634988015381-931019923.jpg',0,'2015-07-20 12:06:37'),(298,'韭菜','精选韭菜','SHUCAISHUIGUO','YECAILEI',2.5,'斤','product_images/pic1239439988020186-931019923.jpg',0,'2015-07-20 12:06:37'),(299,'韭黄','精选韭黄','SHUCAISHUIGUO','YECAILEI',7,'斤','product_images/pic1246327988027074-931019923.jpg',0,'2015-07-20 12:06:37'),(300,'香菇','精选香菇','SHUCAISHUIGUO','JUNLEI',999,'斤','product_images/pic1252686988033433-931019923.jpg',0,'2015-07-21 13:13:55'),(301,'香菜','精选香菜','SHUCAISHUIGUO','YECAILEI',999,'斤','product_images/pic1252707988033454-931019923.jpg',0,'2015-07-21 13:13:55'),(302,'马兰头','精选马兰头','SHUCAISHUIGUO','YECAILEI',999,'斤','product_images/pic38659376564091461-931019923.jpg',0,'2015-07-21 13:13:55'),(303,'马铃薯','精选马铃薯','SHUCAISHUIGUO','GENJINGLEI',2,'斤','product_images/pic39205048564637133-931019923.jpg',0,'2015-07-20 12:06:40'),(304,'鸡翅','精选鸡翅','QINROUDANLEI','DONGJIROU',999,'斤','product_images/pic1287620988068367-931019923.jpg',0,'2015-07-21 13:13:55'),(305,'鸡腿','精选鸡腿','QINROUDANLEI','DONGJIROU',9,'斤','product_images/pic1288062988068809-931019923.jpg',0,'2015-07-20 11:48:56'),(306,'鸡腿菇','精选鸡腿菇','SHUCAISHUIGUO','JUNLEI',3.5,'斤','product_images/pic39963657565395742-931019923.jpg',0,'2015-07-20 12:06:40'),(307,'鸭腿','精选鸭腿','QINROUDANLEI','YAROU',999,'斤','product_images/pic1288434988069181-931019923.jpg',0,'2015-07-21 13:13:55'),(308,'黄瓜','精选黄瓜','SHUCAISHUIGUO','GENJINGLEI',2.5,'斤','product_images/pic1289880988070627-931019923.jpg',0,'2015-07-20 12:06:37'),(309,'黄豆芽','精选黄豆芽','SHUCAISHUIGUO','YECAILEI',999,'斤','product_images/pic40205563565637648-931019923.jpg',0,'2015-07-21 13:13:55'),(310,'黑木耳','精选黑木耳','SHUCAISHUIGUO','JUNLEI',50,'斤','product_images/pic39922844565354929-931019923.jpg',0,'2015-07-20 12:06:40'),(311,'娃娃菜','精选娃娃菜','SHUCAISHUIGUO','YECAILEI',2,'斤','product_images/pic22892412548324497-931019923.jpg',0,'2015-07-20 12:06:38'),(312,'杭白菜','精选杭白菜','SHUCAISHUIGUO','YECAILEI',2.5,'斤','product_images/pic26418476551850561-931019923.jpg',0,'2015-07-20 12:06:38'),(313,'百合','精选百合','SHUCAISHUIGUO','YECAILEI',5,'斤','product_images/pic961866987742613-931019923.jpg',0,'2015-07-20 12:06:43'),(314,'小葱','精选小葱','SHUCAISHUIGUO','YECAILEI',6,'斤','product_images/pic764482987545229-931019923.jpg',0,'2015-07-20 12:06:41'),(315,'生菜','精选生菜','SHUCAISHUIGUO','YECAILEI',5,'斤','product_images/pic963229987743976-931019923.jpg',0,'2015-07-20 12:06:43'),(316,'蹄髈','精选蹄髈','QINROUDANLEI','YIJIBAITIAO',11,'斤','product_images/pic1168644987949391-931019923.jpg',0,'2015-07-20 12:11:35');
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
  `wechat_id` varchar(255) NOT NULL DEFAULT '0',
  `nickname` varchar(255) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `headimgurl` text,
  `consignee` varchar(45) DEFAULT NULL,
  `consignee_contact` varchar(45) DEFAULT NULL,
  `shop_info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'o5Irvt1IzDfOZ7S3jsWKT_5G07w0',' 滚雪球','DELIVERYMAN','http://wx.qlogo.cn/mmopen/yicVS03KxPsMQSObW8c34aJxiat8k0DMj1txXwvqHrMlGkIYqLZH2XTiaKBjsb3UkOYnxYap7CQc6Y5iakALnz4yhLnNOaK2L25q/0',NULL,NULL,NULL),(9,'o5Irvt6r7U71oT6FlnAuLCKqqsvY','凤权','USER','http://wx.qlogo.cn/mmopen/oGt7IeDr3prIZM8b9MyzLLT3oepKSb98jl2qL7ic3GGtFoE4rAicicutof40wiaRDUWRe7Pky9wLo8mEmp9jLWibBtBZrckCyAxVm/0','唐凤','13764301160','骏产货源充足'),(10,'o5Irvt5957jQ4xmdHmDp59epk0UU','灵达','DELIVERYMAN','http://wx.qlogo.cn/mmopen/0pygn8iaZdEeVBqUntWJB9rzhkKIyKnQFzIqswrYFrhHefEXiaCOhJnBqIicxMRd0IeOHe9ffAtKTvXzOfokp9UhS2BlYXh5PxO/0','a','13402188638','abc'),(11,'o5Irvtw83iWQ7aI0F17_RHCkylQw','小傅','USER','http://wx.qlogo.cn/mmopen/rxXF7qF9c6ECQgDibfKCsgNRdvaibog31ViaUHc88ms8yv4lcBjqXAxUOduIECJ0qicNJqV7OUbSibnu7OPXpiby0x5ibZib0tqwNNiac/0',NULL,NULL,NULL),(13,'o5Irvt01SBZtq6JSDVwvhL-1Apf4','小鱼','USER','http://wx.qlogo.cn/mmopen/rxXF7qF9c6ECQgDibfKCsgPXP6Nbc4HCplyaHDYuAZ5fAMLGBrz9U8KTupEzNHUjDTaujiaU7iaq2R6xsQH1YqicZbqjWfK6XdKO/0',NULL,NULL,NULL);
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

-- Dump completed on 2015-07-25 14:26:47
