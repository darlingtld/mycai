-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: mycai
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.12.04.1

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
  `userid` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `wechat_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bill` text COLLATE utf8_unicode_ci,
  `order_ts` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `delivery_ts` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `shop_info` text COLLATE utf8_unicode_ci,
  `consignee` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `consignee_contact` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_order`
--

LOCK TABLES `bill_order` WRITE;
/*!40000 ALTER TABLE `bill_order` DISABLE KEYS */;
INSERT INTO `bill_order` VALUES (1,'lingda',NULL,'{\"items\":[{\"productId\":5,\"productName\":\"青菜\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:25\"}],\"totalAmount\":2,\"totalPrice\":5}','2015/05/24 19:04','2015/05/20 16:28','asdf','asdf','asdf','配送中'),(2,'lingda',NULL,'{\"items\":[{\"productId\":5,\"productName\":\"青菜\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:25\"}],\"totalAmount\":2,\"totalPrice\":5}','2015-05-24 14:49:13','2015/05/20 17:01','re','erwt','wert','未配送'),(3,'lingda',NULL,'{\"items\":[{\"productId\":1,\"productName\":\"青菜\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:25\"}],\"totalAmount\":2,\"totalPrice\":5}','2015-05-24 14:49:13','2015/05/20 17:02','啦啦','猪猪','13611844337','未配送'),(4,'lingda',NULL,'{\"items\":[{\"productId\":1,\"productName\":\"青菜\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:51\"},{\"productId\":2,\"productName\":\"猪肉\",\"amount\":2,\"productPrice\":12,\"picurl\":\"zhurou.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:52\"}],\"totalAmount\":4,\"totalPrice\":29}','2015-05-24 14:49:13','2015/06/27 19:08','不急','ako','阿里山','已配送（已付款）'),(5,'lingda',NULL,'{\"items\":[{\"productId\":2,\"productName\":\"猪肉\",\"amount\":19,\"productPrice\":12,\"picurl\":\"zhurou.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:95\"}],\"totalAmount\":19,\"totalPrice\":228}','2015/05/26 20:04','2015/05/22 10:33',NULL,NULL,NULL,'已配送（已付款）'),(6,'lingda',NULL,'{\"items\":[{\"productId\":3,\"productName\":\"大米\",\"amount\":4,\"productPrice\":50,\"picurl\":\"dami.jpg\",\"productUnit\":\"袋\"}],\"totalAmount\":4,\"totalPrice\":200}','2015-05-24 14:49:13','2015/05/22 10:33',NULL,NULL,NULL,'未配送'),(7,'lingda',NULL,'{\"items\":[{\"productId\":3,\"productName\":\"大米\",\"amount\":\"1\",\"productPrice\":50,\"picurl\":\"dami.jpg\",\"productUnit\":\"袋\",\"$$hashKey\":\"object:31\"}],\"totalAmount\":1,\"totalPrice\":50}','2015-05-24 14:49:13','2015/05/26 19:07','','','','已配送（未付款）'),(8,'lingda',NULL,'{\"items\":[{\"productId\":7,\"productName\":\"青菜\",\"amount\":\"1\",\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:53\"},{\"productId\":10,\"productName\":\"青菜\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\"},{\"productId\":5,\"productName\":\"青菜\",\"amount\":\"1\",\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\"}],\"totalAmount\":4,\"totalPrice\":10}','2015-05-24 14:49:13','2015/05/22 10:33',NULL,NULL,NULL,'未配送'),(9,'lingda',NULL,'{\"items\":[{\"productId\":5,\"productName\":\"青菜\",\"amount\":\"1\",\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:89\"}],\"totalAmount\":1,\"totalPrice\":2.5}','2015-05-24 14:49:13','','','','','未配送'),(10,'lingda',NULL,'{\"items\":[{\"productId\":5,\"productName\":\"青菜\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:14\"},{\"productId\":7,\"productName\":\"青菜\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:15\"}],\"totalAmount\":4,\"totalPrice\":10}','2015-05-24 14:49:13','','','','','未配送'),(11,'lingda',NULL,'{\"items\":[{\"productId\":7,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:31\"}],\"totalAmount\":2,\"totalPrice\":5}','2015-05-24 14:49:13','2015/05/23 12:11','倒计时','随你说吧','睡觉就是你','未配送'),(12,'lingda',NULL,'{\"items\":[{\"productId\":5,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":1,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:31\"},{\"productId\":3,\"productName\":\"大米\",\"description\":\"新疆大米\",\"amount\":3,\"productPrice\":50,\"picurl\":\"dami.jpg\",\"productUnit\":\"袋\",\"$$hashKey\":\"object:37\"},{\"productId\":6,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":\"1\",\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:69\"},{\"productId\":1,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:70\"}],\"totalAmount\":7,\"totalPrice\":160}','2015-05-24 14:49:13','2015/05/23 14:18','','','','未配送'),(36,'lingda',NULL,'{\"items\":[{\"productId\":7,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":4,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:31\"}],\"totalAmount\":4,\"totalPrice\":10}','2015/05/24 19:01','2015/05/24 16:46','asdf','adsf','adf','已配送（未付款）'),(37,NULL,'123456','{\"items\":[{\"productId\":7,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:57\"},{\"productId\":2,\"productName\":\"猪肉\",\"description\":\"一般的猪肉\",\"amount\":3,\"productPrice\":12,\"picurl\":\"zhurou.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:58\"}],\"totalAmount\":5,\"totalPrice\":41}','2015-05-24 19:29:56','2015/05/25 10:29','kfc','唐灵达','13402188638','未配送'),(38,NULL,'123456','{\"items\":[{\"productId\":5,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:31\"}],\"totalAmount\":2,\"totalPrice\":5}','2015-05-24 19:31:21','2015/05/24 19:31','mcdonald','aaa','123','未配送'),(39,NULL,'123456','{\"items\":[{\"productId\":1,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:53\"}],\"totalAmount\":2,\"totalPrice\":5}','2015-05-24 19:34:44','2015/10/24 19:34','asd','adsf','asdf','未配送'),(40,NULL,'oR_l8s42hoj5k9ox0M3hrh3BGh5A','{\"items\":[{\"productId\":1,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:58\"},{\"productId\":2,\"productName\":\"猪肉\",\"description\":\"一般的猪肉\",\"amount\":3,\"productPrice\":12,\"picurl\":\"zhurou.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:59\"}],\"totalAmount\":5,\"totalPrice\":41}','2015-05-24 19:42:26','2015/08/24 19:51','猪猪的老巢','大笨猪','13611844337','未配送'),(41,NULL,'oR_l8sy8QtxQ_Fs1B22Yz2UeFMLI','{\"items\":[{\"productId\":7,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:96\"}],\"totalAmount\":2,\"totalPrice\":5}','2015-05-24 20:22:28','2015/05/24 20:41','o','D','0','未配送'),(42,NULL,'123','{\"items\":[{\"productId\":7,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:31\"}],\"totalAmount\":2,\"totalPrice\":5}','2015-05-24 22:21:13','2015/05/24 22:21','234','234','234','未配送'),(43,NULL,'123','{\"items\":[{\"productId\":7,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:31\"}],\"totalAmount\":2,\"totalPrice\":5}','2015-05-24 22:23:21','2015/05/24 22:23','00','00','00','未配送'),(44,NULL,'123','{\"items\":[{\"productId\":5,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:32\"}],\"totalAmount\":2,\"totalPrice\":5}','2015-05-24 22:23:46','2015/05/24 22:23',';',';',';','未配送'),(45,NULL,'123','{\"items\":[{\"productId\":7,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":\"1\",\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:31\"}],\"totalAmount\":1,\"totalPrice\":2.5}','2015-05-24 22:24:14','2015/05/24 22:24',';',';',';','未配送'),(46,NULL,'123','{\"items\":[{\"productId\":7,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:31\"}],\"totalAmount\":2,\"totalPrice\":5}','2015-05-24 22:25:48','2015/05/24 22:25','asdf','asdf','asdf','未配送'),(47,NULL,NULL,'{\"items\":[{\"productId\":5,\"productName\":\"青菜\",\"description\":\"好吃的青菜，便宜的，多买更便宜(10元/5斤)\",\"amount\":2,\"productPrice\":2.5,\"picurl\":\"qingcai.jpg\",\"productUnit\":\"斤\",\"$$hashKey\":\"object:31\"}],\"totalAmount\":2,\"totalPrice\":5}','2015-05-25 19:46:10','2015/05/25 19:46','123','123','123','未配送');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'青菜','好吃的青菜，便宜的，多买更便宜(10元/5斤)','SHUCAISHUIGUO','YECAILEI',2.5,'斤','qingcai.jpg',0,'2015-05-20 04:52:11'),(2,'猪肉','一般的猪肉','QINROUDANLEI','YIJIBAITIAO',12,'斤','zhurou.jpg',0,'2015-05-19 07:51:15'),(3,'大米','新疆大米','MIMIANLIANGYOU','DAMI',50,'袋','dami.jpg',0,'2015-05-19 07:51:15'),(5,'青菜','好吃的青菜，便宜的，多买更便宜(10元/5斤)','SHUCAISHUIGUO','YECAILEI',2.5,'斤','qingcai.jpg',1,'2015-05-22 06:51:57'),(6,'青菜','好吃的青菜，便宜的，多买更便宜(10元/5斤)','SHUCAISHUIGUO','YECAILEI',2.5,'斤','qingcai.jpg',0,'2015-05-20 04:52:11'),(7,'青菜','好吃的青菜，便宜的，多买更便宜(10元/5斤)','SHUCAISHUIGUO','YECAILEI',2.5,'斤','qingcai.jpg',1,'2015-05-22 06:51:57'),(8,'青菜','好吃的青菜，便宜的，多买更便宜(10元/5斤)','SHUCAISHUIGUO','YECAILEI',2.5,'斤','qingcai.jpg',0,'2015-05-20 04:52:11'),(9,'青菜','好吃的青菜，便宜的，多买更便宜(10元/5斤)','SHUCAISHUIGUO','YECAILEI',2.5,'斤','qingcai.jpg',0,'2015-05-20 04:52:11'),(10,'青菜','好吃的青菜，便宜的','SHUCAISHUIGUO','YECAILEI',2.5,'斤','qingcai.jpg',0,'2015-05-25 11:51:24');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-25 19:52:22
