-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: hinet_vcpe
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `dhcp`
--

DROP TABLE IF EXISTS `dhcp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dhcp` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DNS1` varchar(32) DEFAULT NULL,
  `DNS2` varchar(32) DEFAULT  NULL,
  `SUBNET` varchar(32) DEFAULT NULL,
  `NETMASK` varchar(32) DEFAULT NULL,
  `START_IP` varchar(32) DEFAULT NULL,
  `END_IP` varchar(32) DEFAULT NULL,
  `DEFAULT_GATEWAY` varchar(32) DEFAULT NULL,
  `USER_ID` varchar(30) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dhcp`
--

LOCK TABLES `dhcp` WRITE;
/*!40000 ALTER TABLE `dhcp` DISABLE KEYS */;
INSERT INTO `dhcp` VALUES (1,'211.192.132.141','211.192.132.151','211.192.132.161','211.192.132.171','211.192.132.181','211.192.132.191','211.192.132.201','aaa','2017-02-07 16:51:34');
/*!40000 ALTER TABLE `dhcp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ipsec`
--

DROP TABLE IF EXISTS `ipsec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ipsec` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IPSEC_NAME` varchar(45) DEFAULT NULL,
  `IP` varchar(32) DEFAULT NULL,
  `GATEWAY` varchar(32) DEFAULT NULL,
  `ALLOW_SUBNET` varchar(32) DEFAULT NULL,
  `SITE_TO_SITE_IP` varchar(32) DEFAULT NULL,
  `PRE_SHARED_KEY` varchar(45) DEFAULT NULL,
  `GATEWAY_INTERFACE` varchar(45) DEFAULT NULL,
  `USER_ID` varchar(30) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ipsec`
--

LOCK TABLES `ipsec` WRITE;
/*!40000 ALTER TABLE `ipsec` DISABLE KEYS */;
INSERT INTO `ipsec` VALUES (1,'default_IPsec_1','192.168.22.2','192.168.22.3','192.168.22.4','192.168.22.5','key1','2','aaa','2017-02-07 16:51:35'),(2,'default_IPsec_2','200.111.88.2','200.111.88.3','200.111.88.4','200.111.88.5','key2','3','aaa','2017-02-07 16:51:35');
/*!40000 ALTER TABLE `ipsec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lan`
--

DROP TABLE IF EXISTS `lan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IP` varchar(32) DEFAULT NULL,
  `SUBNET_MASK` varchar(32) DEFAULT NULL,
  `PORT_MUTI_ID` varchar(45) DEFAULT NULL,
  `USER_ID` varchar(30) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lan`
--

LOCK TABLES `lan` WRITE;
/*!40000 ALTER TABLE `lan` DISABLE KEYS */;
INSERT INTO `lan` VALUES (1,'192.168.35.1','255.255.255.0','1,2','aaa','2017-02-07 16:51:35');
/*!40000 ALTER TABLE `lan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `port`
--

DROP TABLE IF EXISTS `port`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `port` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PORT_NAME` varchar(45) DEFAULT NULL,
  `PORT_NO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `port`
--

LOCK TABLES `port` WRITE;
/*!40000 ALTER TABLE `port` DISABLE KEYS */;
INSERT INTO `port` VALUES (1,'table1',55184),(2,'table2',55185),(3,'table3',55186),(4,'table4',55187),(5,'table5',55188),(6,'table6',55189);
/*!40000 ALTER TABLE `port` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `USER_ID` varchar(30) NOT NULL,
  `USER_NAME` varchar(80) NOT NULL,
  `PWD` varchar(45) NOT NULL,
  `ADDRESS` varchar(200) NOT NULL,
  `PHONE` varchar(45) NOT NULL,
  `CONFIG` varchar(90) NOT NULL,
  `ACTIVE` varchar(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES ('aaa','Aaron','aaa','台北市中正區中山北路一段16號2樓','0988458847','1','Y'),('bbb','Bobis','bbb','新北市新莊區中原東路14巷39號3樓','0956778779','2','N'),('ccc','Charlie','ccc','新北市五股區凌雲路一段58號4樓','0979741156','3','N');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wan`
--

DROP TABLE IF EXISTS `wan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wan` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `WAN_NAME` varchar(45) DEFAULT NULL,
  `PORT_ID` int(11) DEFAULT NULL,
  `WAN_IP` varchar(32) DEFAULT NULL,
  `DEFAULT_GATEWAY` varchar(32) DEFAULT NULL,
  `USER_ID` varchar(30) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wan`
--

LOCK TABLES `wan` WRITE;
/*!40000 ALTER TABLE `wan` DISABLE KEYS */;
INSERT INTO `wan` VALUES (1,'default_wan1',3,'192.168.0.3','192.168.0.2','aaa','2017-02-07 16:51:35'),(2,'default_wan2',6,'168.172.30.1','168.172.30.8','aaa','2017-02-07 16:51:35');
/*!40000 ALTER TABLE `wan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-07 19:02:39
