-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: localhost    Database: oa
-- ------------------------------------------------------
-- Server version	5.5.15

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
-- Current Database: `oa`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `oa` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oa`;

--
-- Table structure for table `core_basicdata`
--

DROP TABLE IF EXISTS `core_basicdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_basicdata` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  `value2` varchar(100) DEFAULT NULL,
  `sequence` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_basicdata`
--

LOCK TABLES `core_basicdata` WRITE;
/*!40000 ALTER TABLE `core_basicdata` DISABLE KEYS */;
INSERT INTO `core_basicdata` VALUES (1,1,'2011-10-08 20:33:30',1,'2011-10-10 18:53:33',NULL,'news','公司新闻','01',0),(2,1,'2011-10-08 20:35:25',1,'2011-10-10 18:53:37',NULL,'news','社会新闻','02',1);
/*!40000 ALTER TABLE `core_basicdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_department`
--

DROP TABLE IF EXISTS `core_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_department` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `orgId` int(10) unsigned DEFAULT NULL,
  `parentId` int(10) unsigned DEFAULT NULL,
  `depaName` varchar(100) DEFAULT NULL,
  `depaDesc` varchar(100) DEFAULT NULL,
  `sequence` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_department`
--

LOCK TABLES `core_department` WRITE;
/*!40000 ALTER TABLE `core_department` DISABLE KEYS */;
INSERT INTO `core_department` VALUES (1,0,'2011-08-20 14:18:02',0,'2011-08-21 12:34:09',NULL,1,0,'战略投资部','战略投资部',0),(2,0,'2011-08-20 14:18:44',0,'2011-08-21 12:34:36',NULL,1,0,'人事行政部','人事行政部',0),(3,0,'2011-08-20 21:23:44',0,'2011-08-21 12:48:48',NULL,11,0,'三炮部队','三炮部队',0),(4,0,'2011-08-20 21:23:54',0,'2011-08-21 12:35:02',NULL,1,0,'财务部','财务部',0),(6,0,'2011-08-20 21:34:41',0,'2011-08-21 12:35:38',NULL,1,4,'财务部','财务部',0),(7,0,'2011-08-20 21:34:48',0,'2011-08-21 12:36:02',NULL,1,4,'会计部','会计部',0),(8,0,'2011-08-20 21:34:54',0,'2011-08-21 12:42:09',NULL,1,0,'市场部','市场部',0),(10,0,'2011-08-20 22:14:41',0,'2011-08-21 12:58:46',NULL,1,0,'信息中心','信息中心',9),(11,0,'2011-08-20 22:14:45',0,'2011-08-21 12:44:27',NULL,1,8,'国内市场部','国内市场部',0),(13,0,'2011-08-20 22:14:57',0,'2011-09-05 22:02:54',NULL,1,8,'海外市场部','海外市场部',0),(14,0,'2011-08-20 22:15:05',0,'2011-08-21 12:45:09',NULL,1,0,'技术部','技术部',0),(15,0,'2011-08-20 22:15:08',0,'2011-08-21 12:59:27',NULL,1,0,'质控部','质控部',8),(16,0,'2011-08-20 22:15:15',0,'2011-08-21 12:46:09',NULL,1,0,'生产部','生产部',0),(17,0,'2011-08-20 22:15:22',0,'2011-08-21 12:46:45',NULL,1,16,'交换机生产线','交换机生产线',0),(18,0,'2011-08-21 12:27:35',0,'2011-08-21 12:49:18',NULL,13,0,'固定电话事业部','固定电话事业部',0),(19,0,'2011-08-21 12:27:41',0,'2011-08-21 12:49:29',NULL,13,0,'移动电话事业部','移动电话事业部',0),(20,0,'2011-08-21 12:27:47',0,'2011-08-21 12:48:58',NULL,12,0,'三炮部队','三炮部队',0),(21,0,'2011-08-21 12:47:12',0,NULL,NULL,1,16,'平板电脑生产线','平板电脑生产线',0);
/*!40000 ALTER TABLE `core_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_job`
--

DROP TABLE IF EXISTS `core_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_job` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `orgId` int(10) unsigned DEFAULT NULL,
  `parentId` int(10) unsigned DEFAULT NULL,
  `jobName` varchar(100) DEFAULT NULL,
  `jobDesc` varchar(100) DEFAULT NULL,
  `sequence` int(10) unsigned DEFAULT '0',
  `isManager` tinyint(3) unsigned DEFAULT '0',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_job`
--

LOCK TABLES `core_job` WRITE;
/*!40000 ALTER TABLE `core_job` DISABLE KEYS */;
INSERT INTO `core_job` VALUES (1,0,'2011-08-25 20:18:59',0,NULL,NULL,1,0,'总经理','总经理',0,0),(2,0,'2011-08-25 20:19:20',0,'2011-08-25 21:03:10',NULL,1,1,'企业管理部经理','企业管理部经理',0,1),(3,0,'2011-08-25 20:19:42',0,'2011-08-25 20:21:07',NULL,1,1,'财务部经理','财务部经理',1,0),(4,0,'2011-08-25 20:19:59',0,'2011-08-25 20:34:39',NULL,1,1,'人事行政部经理','人事行政部经理',2,0),(5,0,'2011-08-25 20:20:14',0,'2011-08-25 20:34:49',NULL,1,1,'技术部经理','技术部经理',3,0),(6,0,'2011-08-25 20:20:25',0,'2011-08-25 20:21:18',NULL,1,1,'市场部经理','市场部经理',4,0),(7,0,'2011-08-25 20:20:44',0,'2011-09-04 22:10:56',NULL,1,1,'生产部经理','生产部经理',5,0),(8,0,'2011-08-25 20:20:54',0,'2011-08-25 20:57:10',NULL,1,1,'信息部经理','信息部经理',6,0),(9,0,'2011-08-25 20:37:35',0,NULL,NULL,1,2,'企业管理部副经理','企业管理部副经理',0,0),(10,0,'2011-08-25 20:42:49',0,'2011-08-25 20:43:08',NULL,1,9,'企业管理部主任','企业管理部主任',0,0),(11,0,'2011-08-25 20:43:31',0,'2011-08-25 20:45:15',NULL,1,10,'项目主管','项目主管',0,0),(12,0,'2011-08-25 20:45:39',0,NULL,NULL,1,10,'成本主管','成本主管',1,0),(13,0,'2011-08-25 20:45:58',0,NULL,NULL,1,8,'信息部主任','信息部主任',0,0),(14,0,'2011-08-25 20:46:09',0,'2011-08-25 20:46:20',NULL,1,13,'硬件工程师','硬件工程师',0,0),(15,0,'2011-08-25 20:46:46',0,'2011-08-25 20:46:51',NULL,1,13,'软件工程师','软件工程师',1,0),(16,0,'2011-08-25 21:01:08',0,NULL,NULL,1,13,'系统工程师','系统工程师',3,0);
/*!40000 ALTER TABLE `core_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_leftmenu`
--

DROP TABLE IF EXISTS `core_leftmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_leftmenu` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `parentId` int(10) unsigned DEFAULT NULL,
  `menuName` varchar(100) DEFAULT NULL,
  `menuUrl` varchar(100) DEFAULT NULL,
  `menuTarget` varchar(100) DEFAULT NULL,
  `sequence` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_leftmenu`
--

LOCK TABLES `core_leftmenu` WRITE;
/*!40000 ALTER TABLE `core_leftmenu` DISABLE KEYS */;
INSERT INTO `core_leftmenu` VALUES (1,0,'2011-09-04 15:00:18',0,NULL,NULL,0,'我的桌面','','_self',0),(2,0,'2011-09-04 15:00:32',0,NULL,NULL,0,'审批中心','','_self',1),(3,0,'2011-09-04 15:00:41',0,NULL,NULL,0,'相关链接','','_self',2),(4,0,'2011-09-04 15:00:51',0,NULL,NULL,0,'我的文档','','_self',3),(5,0,'2011-09-04 15:01:03',0,NULL,NULL,0,'帮助中心','','_self',4),(6,0,'2011-09-04 15:01:12',0,'2011-09-22 18:49:17',NULL,1,'修改资料','updateprofile.jsf','_self',0),(7,0,'2011-09-04 15:01:24',0,'2011-09-29 21:33:38',NULL,1,'修改密码','updatepassword.jsf','_self',1),(8,0,'2011-09-04 15:01:33',0,'2011-09-04 15:01:56',NULL,1,'我的任务','','_self',2),(9,0,'2011-09-04 15:02:37',0,'2011-09-04 15:02:41',NULL,1,'审批设置','','_self',3),(10,0,'2011-09-04 15:03:31',0,NULL,NULL,2,'未完成的任务','','_self',0),(11,0,'2011-09-04 15:03:37',0,NULL,NULL,2,'已完成的任务','','_self',1),(12,0,'2011-09-04 15:03:43',0,NULL,NULL,2,'被授权的任务','','_self',2),(13,0,'2011-09-04 15:03:55',0,NULL,NULL,2,'已授权的任务','','_self',3),(14,0,'2011-09-04 15:04:03',0,NULL,NULL,2,'未完成的审批','','_self',4),(15,0,'2011-09-04 15:04:11',0,NULL,NULL,2,'已完成的审批','','_self',5),(16,0,'2011-09-04 15:04:32',0,NULL,NULL,3,'ERP系统','','_self',0),(17,0,'2011-09-04 15:05:09',0,NULL,NULL,3,'MiniOA下载','http://www.minioa.net/','_self',1),(18,0,'2011-09-08 19:24:06',0,NULL,NULL,0,'HelloWorld','helloworld.jsf','_self',3);
/*!40000 ALTER TABLE `core_leftmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_leftmenu_role_relation`
--

DROP TABLE IF EXISTS `core_leftmenu_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_leftmenu_role_relation` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `menuId` int(10) unsigned DEFAULT NULL,
  `roleId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_leftmenu_role_relation`
--

LOCK TABLES `core_leftmenu_role_relation` WRITE;
/*!40000 ALTER TABLE `core_leftmenu_role_relation` DISABLE KEYS */;
INSERT INTO `core_leftmenu_role_relation` VALUES (1,0,'2011-09-05 19:44:27',1,1),(2,0,'2011-09-05 19:44:27',2,1),(3,0,'2011-09-05 19:44:27',3,1),(6,0,'2011-09-05 19:44:27',6,1),(7,0,'2011-09-05 19:44:27',7,1),(8,0,'2011-09-05 19:44:27',8,1),(9,0,'2011-09-05 19:44:27',9,1),(10,0,'2011-09-05 19:44:27',10,1),(11,0,'2011-09-05 19:44:27',11,1),(12,0,'2011-09-05 19:44:27',12,1),(13,0,'2011-09-05 19:44:27',13,1),(14,0,'2011-09-05 19:44:27',14,1),(15,0,'2011-09-05 19:44:27',15,1),(16,0,'2011-09-05 19:44:27',17,1),(17,0,'2011-09-05 19:44:27',16,1),(18,0,'2011-09-05 20:21:52',4,1),(19,0,'2011-09-05 21:21:14',5,1),(20,0,'2011-09-08 19:24:23',18,1),(21,0,'2011-10-12 19:26:59',1,2),(22,0,'2011-10-12 19:26:59',6,2),(23,0,'2011-10-12 19:26:59',7,2);
/*!40000 ALTER TABLE `core_leftmenu_role_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_news`
--

DROP TABLE IF EXISTS `core_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_news` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `cate` varchar(45) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `content` text,
  `ispicnews` tinyint(1) DEFAULT NULL,
  `status_` int(10) unsigned DEFAULT '0',
  `times` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_news`
--

LOCK TABLES `core_news` WRITE;
/*!40000 ALTER TABLE `core_news` DISABLE KEYS */;
INSERT INTO `core_news` VALUES (3,1,'2011-10-10 20:04:30',1,'2011-10-10 20:26:42','18fbec1d-369d-42ba-83f2-fe2ff18461cd','02','泰军方称缅甸贩毒集团枪杀中国船员','<p class=\"summary\">核心提示：泰国军方称，一伙由缅甸毒枭Nor Kham指挥的贩毒集团被认为是在湄公河枪杀中国船员的幕后黑手。10月5日上午，两艘搭载13名中国船员的商船在湄公河金三角水域遭劫持杀害。中国外交部称，最后确认11人遇难，2人失踪。此前有媒体报道13人全部遇难。</p>\n<p><span class=\"blank12\">&nbsp;</span></p>',0,0,0),(5,1,'2011-10-10 20:44:27',1,'2011-10-10 20:45:12','1cac5b61-4648-4c7c-b675-fe7ed33061a3','01','dfgsdfgsdfg','<p>dddddddsfgsdfgsdfgsdfg</p>',0,0,0),(6,1,'2011-10-10 20:44:50',NULL,NULL,'99afc9e4-1ca0-4c6a-a31d-91f713f96681','02','泰军马英九辛亥百年讲话：辛亥革命是两岸共同资产','<p>凤凰卫视10月10日《台湾新闻》节目播出&ldquo;台湾庆双十节 举行大型军事展演&rdquo;，以下为文字实录：</p>\n<p>安东：为了庆祝辛亥革命一百周年纪念日，台北在今天是有盛大的庆祝活动，其中最受瞩目的兵力展演在今天上午的11点登场了，我们也马上带您到现场去看一看。</p>\n<p>今年参与展演的就包括空中兵力还有地面部队，还有70架直升机、运输机和各式的战机，它们将会以高难度的编队飞行，展现空中战斗力，并且由AT3教 练机在领导人办公室前的天空当中喷出红、蓝、白三色的烟雾，而地面部队则是由宪兵机车连打头阵，率领台湾造的&ldquo;云豹&rdquo;装甲车、&ldquo;天弓&rdquo;防空导弹、&ldquo;雄风&rdquo; 三型超音速攻船导弹、&ldquo;雷霆2000&rdquo;多管火箭和&ldquo;中翔二号&rdquo;遥控侦察机等装备，依序的经过<a href=\"http://baike.tw.ifeng.com/doc/25307\" target=\"_blank\">凯达格兰大道</a>。</p>',0,0,0),(7,1,'2011-10-11 21:11:22',1,'2011-10-11 21:40:59','83c177ba-7082-4f36-8b7e-854823b7d46d','01','缅甸佤邦声明未杀害中国船员','<p>核心提示：缅甸佤邦政府10月10日发表声明，回应泰国媒体&ldquo;中国船员遇袭是缅甸佤邦联合军所为&rdquo;报道。声明强调事件与佤邦联合军无关，称佤邦愿配合中国调查。佤邦建议执勤泰国警察接受中方调查。中国外交部则称泰警方已根据中方要求对现场取证，展开调查。</p>',0,0,0);
/*!40000 ALTER TABLE `core_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_news_attachment`
--

DROP TABLE IF EXISTS `core_news_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_news_attachment` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `filename` varchar(200) DEFAULT NULL,
  `filetype` varchar(45) DEFAULT NULL,
  `filesize` int(10) unsigned DEFAULT '0',
  `oldname` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_news_attachment`
--

LOCK TABLES `core_news_attachment` WRITE;
/*!40000 ALTER TABLE `core_news_attachment` DISABLE KEYS */;
INSERT INTO `core_news_attachment` VALUES (8,1,'2011-10-10 21:48:44',NULL,NULL,'7ee68b0d-a19d-40ab-95f2-22340385e8c0','upload\\news\\39a71884-7771-4e7e-95b6-5c5a890b1ed2.jpg','.jpg',158445,'minioademo.jpg'),(9,1,'2011-10-10 21:49:09',NULL,NULL,'7ee68b0d-a19d-40ab-95f2-22340385e8c0','upload\\news\\e5d5b4a0-26bd-4373-8b57-21a93c658097.jpg','.jpg',45648,'1316760017386.jpg'),(11,1,'2011-10-11 20:51:01',NULL,NULL,'99afc9e4-1ca0-4c6a-a31d-91f713f96681','upload\\news\\766043d3-1299-4849-8a90-ed0d70f00e79.jpg','.jpg',36413,'222.jpg'),(12,1,'2011-10-11 20:59:23',NULL,NULL,'99afc9e4-1ca0-4c6a-a31d-91f713f96681','upload\\news\\e10393e7-773a-4df7-baf3-56a3c0a1c847.jpg','.jpg',45648,'1316760017386.jpg'),(14,1,'2011-10-11 21:11:16',NULL,NULL,'83c177ba-7082-4f36-8b7e-854823b7d46d','upload\\news\\d9afbacb-51d4-4bc6-941c-1e8347491b71.jpg','.jpg',16630,'rdn_4e93629fdf0f3.jpg'),(15,1,'2011-10-11 21:33:30',NULL,NULL,'83c177ba-7082-4f36-8b7e-854823b7d46d','upload\\news\\bca4deff-9e24-43f9-84b2-f29d9561a844.jpg','.jpg',36413,'222.jpg');
/*!40000 ALTER TABLE `core_news_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_op`
--

DROP TABLE IF EXISTS `core_op`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_op` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `opName` varchar(100) DEFAULT NULL,
  `opDesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_op`
--

LOCK TABLES `core_op` WRITE;
/*!40000 ALTER TABLE `core_op` DISABLE KEYS */;
INSERT INTO `core_op` VALUES (1,0,'2011-08-27 21:38:52',0,'2011-08-27 21:39:12',NULL,'100102','单位管理-增删改'),(2,0,'2011-08-27 21:39:06',NULL,NULL,NULL,'100101','单位管理-浏览'),(3,0,'2011-08-27 21:39:28',NULL,NULL,NULL,'100202','部门管理-增删改'),(4,0,'2011-08-27 21:39:36',NULL,NULL,NULL,'100201','部门管理-浏览'),(5,0,'2011-08-27 21:40:10',NULL,NULL,NULL,'100302','岗位管理-增删改'),(6,0,'2011-08-27 21:40:28',NULL,NULL,NULL,'100301','岗位管理-浏览'),(7,0,'2011-08-27 21:40:47',0,'2011-09-18 21:22:49',NULL,'100402','帐号管理-增删改'),(8,0,'2011-08-27 21:40:54',NULL,NULL,NULL,'100401','帐号管理-浏览'),(9,0,'2011-08-27 21:41:06',NULL,NULL,NULL,'100502','角色管理-增删改'),(10,0,'2011-08-27 21:41:14',NULL,NULL,NULL,'100501','角色管理-浏览'),(11,0,'2011-08-27 21:41:44',NULL,NULL,NULL,'100602','权限管理-增删改'),(12,0,'2011-08-27 21:41:50',NULL,NULL,NULL,'100601','权限管理-浏览'),(13,0,'2011-09-08 19:25:01',NULL,NULL,NULL,'helloworld',''),(14,0,'2011-10-12 18:56:51',NULL,NULL,NULL,'100702','顶部菜单管理-增删改'),(15,0,'2011-10-12 18:56:59',NULL,NULL,NULL,'100701','顶部菜单管理-浏览'),(16,0,'2011-10-12 18:57:09',0,'2011-10-12 18:59:16',NULL,'100802','左侧菜单管理-增删改'),(17,0,'2011-10-12 18:57:22',NULL,NULL,NULL,'100801','左侧菜单管理-浏览'),(18,0,'2011-10-12 18:57:46',NULL,NULL,NULL,'100902','基础数据-增删改'),(19,0,'2011-10-12 18:57:53',NULL,NULL,NULL,'100901','基础数据-浏览'),(20,0,'2011-10-12 18:58:24',NULL,NULL,NULL,'101002','日志管理-增删改'),(21,0,'2011-10-12 18:58:31',NULL,NULL,NULL,'101001','日志管理-浏览'),(22,0,'2011-10-12 18:58:52',NULL,NULL,NULL,'101102','新闻管理-增删改'),(23,0,'2011-10-12 18:58:59',NULL,NULL,NULL,'101101','新闻管理-浏览'),(24,0,'2011-10-12 19:10:23',NULL,NULL,NULL,'100603','权限管理-权限设置');
/*!40000 ALTER TABLE `core_op` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_op_role_relation`
--

DROP TABLE IF EXISTS `core_op_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_op_role_relation` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `opId` int(10) unsigned DEFAULT NULL,
  `roleId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_op_role_relation`
--

LOCK TABLES `core_op_role_relation` WRITE;
/*!40000 ALTER TABLE `core_op_role_relation` DISABLE KEYS */;
INSERT INTO `core_op_role_relation` VALUES (3,0,'2011-09-04 18:28:49',3,1),(4,0,'2011-09-04 18:28:49',4,1),(5,0,'2011-09-04 18:28:49',5,1),(6,0,'2011-09-04 18:28:49',6,1),(7,0,'2011-09-04 18:28:49',7,1),(8,0,'2011-09-04 18:28:49',8,1),(9,0,'2011-09-04 18:28:49',9,1),(10,0,'2011-09-04 18:28:49',10,1),(11,0,'2011-09-04 18:28:49',11,1),(12,0,'2011-09-04 18:28:49',12,1),(13,0,'2011-09-05 21:50:39',1,1),(14,0,'2011-09-05 21:50:39',2,1),(15,0,'2011-09-09 07:47:44',13,1),(16,0,'2011-10-12 18:59:31',14,1),(17,0,'2011-10-12 18:59:31',15,1),(18,0,'2011-10-12 18:59:31',17,1),(19,0,'2011-10-12 18:59:31',16,1),(20,0,'2011-10-12 18:59:31',19,1),(21,0,'2011-10-12 18:59:31',18,1),(22,0,'2011-10-12 18:59:31',21,1),(23,0,'2011-10-12 18:59:31',20,1),(24,0,'2011-10-12 18:59:31',23,1),(25,0,'2011-10-12 18:59:31',22,1),(26,0,'2011-10-12 19:24:58',24,1),(27,0,'2011-10-12 19:26:07',2,2),(28,0,'2011-10-12 19:26:07',4,2),(29,0,'2011-10-12 19:26:07',6,2),(30,0,'2011-10-12 19:26:07',8,2),(31,0,'2011-10-12 19:26:07',10,2),(32,0,'2011-10-12 19:26:07',12,2),(33,0,'2011-10-12 19:26:07',19,2),(34,0,'2011-10-12 19:26:07',23,2);
/*!40000 ALTER TABLE `core_op_role_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_org`
--

DROP TABLE IF EXISTS `core_org`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_org` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `orgName` varchar(100) DEFAULT NULL,
  `orgDesc` varchar(100) DEFAULT NULL,
  `sequence` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_org`
--

LOCK TABLES `core_org` WRITE;
/*!40000 ALTER TABLE `core_org` DISABLE KEYS */;
INSERT INTO `core_org` VALUES (1,0,'2011-08-15 20:41:22',0,'2011-09-05 22:02:32',NULL,'西柏网络','西柏网络科技有限公司',0),(11,0,'2011-08-15 21:50:50',0,'2011-08-21 21:29:51',NULL,'中国石油','中国石油股份有限公司',0),(12,0,'2011-08-15 21:50:53',0,'2011-08-21 21:24:29',NULL,'中国石化','中国石化股份有限公司',100),(13,0,'2011-08-16 20:47:59',0,'2011-08-21 21:24:13',NULL,'中国移动','中国移动股份有限公司',99);
/*!40000 ALTER TABLE `core_org` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_pinyin`
--

DROP TABLE IF EXISTS `core_pinyin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_pinyin` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `pinyin` varchar(100) DEFAULT NULL,
  `hanzi` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_pinyin`
--

LOCK TABLES `core_pinyin` WRITE;
/*!40000 ALTER TABLE `core_pinyin` DISABLE KEYS */;
INSERT INTO `core_pinyin` VALUES (2,0,'2011-09-16 19:49:36',0,NULL,NULL,'li','李');
/*!40000 ALTER TABLE `core_pinyin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_role`
--

DROP TABLE IF EXISTS `core_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_role` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `roleName` varchar(100) DEFAULT NULL,
  `roleDesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_role`
--

LOCK TABLES `core_role` WRITE;
/*!40000 ALTER TABLE `core_role` DISABLE KEYS */;
INSERT INTO `core_role` VALUES (1,0,'2011-08-27 21:03:24',0,'2011-08-27 21:03:33',NULL,'admin','管理员'),(2,0,'2011-08-27 21:03:49',0,'2011-09-06 21:16:41',NULL,'user','普通用户'),(3,0,'2011-08-27 21:03:58',NULL,NULL,NULL,'manager','经理');
/*!40000 ALTER TABLE `core_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_role_user_relation`
--

DROP TABLE IF EXISTS `core_role_user_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_role_user_relation` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `roleId` int(10) unsigned DEFAULT NULL,
  `userId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_role_user_relation`
--

LOCK TABLES `core_role_user_relation` WRITE;
/*!40000 ALTER TABLE `core_role_user_relation` DISABLE KEYS */;
INSERT INTO `core_role_user_relation` VALUES (2,0,'2011-09-04 07:44:41',1,1),(7,1,'2011-09-06 21:16:46',2,2),(8,1,'2011-10-12 19:27:14',2,4);
/*!40000 ALTER TABLE `core_role_user_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_topmenu`
--

DROP TABLE IF EXISTS `core_topmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_topmenu` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `parentId` int(10) unsigned DEFAULT NULL,
  `menuName` varchar(100) DEFAULT NULL,
  `menuUrl` varchar(100) DEFAULT NULL,
  `menuTarget` varchar(100) DEFAULT NULL,
  `sequence` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_topmenu`
--

LOCK TABLES `core_topmenu` WRITE;
/*!40000 ALTER TABLE `core_topmenu` DISABLE KEYS */;
INSERT INTO `core_topmenu` VALUES (1,0,'2011-08-28 10:50:53',0,NULL,NULL,0,'首页','index.jsf','_self',1),(2,0,'2011-08-28 10:54:12',0,NULL,NULL,0,'新闻中心','','_self',2),(3,0,'2011-08-28 10:54:42',0,NULL,NULL,0,'部门网站','','_self',3),(4,0,'2011-08-28 10:54:50',0,NULL,NULL,0,'内部培训','','_self',4),(5,0,'2011-08-28 10:54:58',0,NULL,NULL,0,'企业邮箱','','_self',5),(6,0,'2011-08-28 10:55:14',0,'2011-08-28 10:55:33',NULL,0,'下载中心','','_self',6),(7,0,'2011-08-28 10:55:26',0,NULL,NULL,0,'系统管理','','_self',99),(8,0,'2011-08-28 10:55:51',0,NULL,NULL,2,'通知通告','','_self',1),(9,0,'2011-08-28 10:56:02',0,'2011-08-28 10:56:21',NULL,2,'公司新闻','','_self',2),(10,0,'2011-08-28 10:56:10',0,'2011-08-28 10:56:33',NULL,2,'集团新闻','','_self',3),(11,0,'2011-08-28 10:56:43',0,'2011-08-28 10:56:55',NULL,2,'行业新闻','','_self',4),(12,0,'2011-08-28 10:57:05',0,NULL,NULL,2,'社会中心','','_self',5),(13,0,'2011-08-28 10:57:24',0,NULL,NULL,3,'人事部','','_self',1),(14,0,'2011-08-28 10:57:30',0,NULL,NULL,3,'财务部','','_self',2),(15,0,'2011-08-28 10:57:38',0,NULL,NULL,3,'技术部','','_self',3),(16,0,'2011-08-28 10:57:46',0,NULL,NULL,3,'市场部','','_self',4),(17,0,'2011-08-28 10:58:08',0,NULL,NULL,4,'新员工入职培训','','_self',1),(18,0,'2011-08-28 10:58:23',0,NULL,NULL,4,'Excel培训视频教程','','_self',2),(19,0,'2011-08-28 10:58:30',0,NULL,NULL,4,'Word培训视频教程','','_self',3),(20,0,'2011-08-28 10:58:45',0,NULL,NULL,5,'帐号设置','','_self',1),(21,0,'2011-08-28 10:58:55',0,NULL,NULL,5,'收件箱','','_self',2),(22,0,'2011-08-28 10:59:02',0,NULL,NULL,5,'发件箱','','_self',3),(23,0,'2011-08-28 10:59:11',0,NULL,NULL,5,'草稿箱','','_self',4),(24,0,'2011-08-28 10:59:20',0,NULL,NULL,5,'垃圾箱','','_self',5),(25,0,'2011-08-28 10:59:40',0,NULL,NULL,6,'工具软件','','_self',1),(26,0,'2011-08-28 10:59:47',0,NULL,NULL,6,'规章制度','','_self',2),(27,0,'2011-08-28 10:59:55',0,NULL,NULL,6,'申请表单','','_self',3),(28,0,'2011-08-29 20:21:40',0,NULL,NULL,7,'单位管理','org.jsf','_self',1),(29,0,'2011-08-29 20:22:14',0,NULL,NULL,7,'部门管理','department.jsf','_self',2),(30,0,'2011-08-29 20:22:33',0,'2011-09-03 14:49:59',NULL,7,'岗位管理','job.jsf','_self',3),(31,0,'2011-08-29 20:22:52',0,NULL,NULL,7,'帐号管理','user.jsf','_self',4),(32,0,'2011-08-29 20:23:12',0,NULL,NULL,7,'角色管理','role.jsf','_self',5),(33,0,'2011-08-29 20:23:34',0,NULL,NULL,7,'权限管理','op.jsf','_self',6),(34,0,'2011-08-29 20:24:05',0,NULL,NULL,7,'顶部菜单管理','topmenuadmin.jsf','_self',7),(35,0,'2011-08-29 20:24:25',0,NULL,NULL,7,'左侧菜单管理','leftmenuadmin.jsf','_self',8),(36,0,'2011-08-29 20:24:51',0,'2011-10-08 20:24:26',NULL,7,'基础数据','basicdata.jsf','_self',9),(37,0,'2011-08-29 20:25:05',0,NULL,NULL,7,'日志管理','','_self',10),(38,0,'2011-08-29 20:25:16',0,'2011-10-10 19:44:02',NULL,7,'新闻管理','news.jsf','_self',11),(39,0,'2011-08-29 20:25:34',0,NULL,NULL,7,'流程管理','','_self',12),(40,0,'2011-08-29 20:30:10',0,NULL,NULL,39,'流程定义','','_self',1),(41,0,'2011-08-29 20:30:26',0,NULL,NULL,39,'节点定义','','_self',2),(42,0,'2011-08-29 20:30:47',0,NULL,NULL,39,'审批代理','','_self',3);
/*!40000 ALTER TABLE `core_topmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_topmenu_role_relation`
--

DROP TABLE IF EXISTS `core_topmenu_role_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_topmenu_role_relation` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `menuId` int(10) unsigned DEFAULT NULL,
  `roleId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_topmenu_role_relation`
--

LOCK TABLES `core_topmenu_role_relation` WRITE;
/*!40000 ALTER TABLE `core_topmenu_role_relation` DISABLE KEYS */;
INSERT INTO `core_topmenu_role_relation` VALUES (1,0,'2011-09-04 22:02:38',0,2),(46,0,'2011-09-04 22:08:49',2,3),(47,0,'2011-09-04 22:08:49',3,3),(48,0,'2011-09-04 22:08:49',4,3),(49,0,'2011-09-04 22:08:49',5,3),(50,0,'2011-09-04 22:08:49',6,3),(51,0,'2011-09-04 22:08:49',7,3),(52,0,'2011-09-04 22:08:49',8,3),(53,0,'2011-09-04 22:08:49',9,3),(54,0,'2011-09-04 22:08:49',10,3),(55,0,'2011-09-04 22:08:49',11,3),(56,0,'2011-09-04 22:08:49',12,3),(57,0,'2011-09-04 22:08:49',13,3),(58,0,'2011-09-04 22:08:49',14,3),(59,0,'2011-09-04 22:08:49',15,3),(60,0,'2011-09-04 22:08:49',17,3),(61,0,'2011-09-04 22:08:49',16,3),(62,0,'2011-09-04 22:08:49',19,3),(63,0,'2011-09-04 22:08:49',18,3),(64,0,'2011-09-04 22:08:49',21,3),(65,0,'2011-09-04 22:08:49',20,3),(66,0,'2011-09-04 22:08:49',23,3),(67,0,'2011-09-04 22:08:49',22,3),(68,0,'2011-09-04 22:08:49',25,3),(69,0,'2011-09-04 22:08:49',24,3),(70,0,'2011-09-04 22:08:49',27,3),(71,0,'2011-09-04 22:08:49',26,3),(72,0,'2011-09-04 22:08:49',29,3),(73,0,'2011-09-04 22:08:49',28,3),(74,0,'2011-09-04 22:08:49',31,3),(75,0,'2011-09-04 22:08:49',30,3),(76,0,'2011-09-04 22:08:49',34,3),(77,0,'2011-09-04 22:08:49',35,3),(78,0,'2011-09-04 22:08:49',32,3),(79,0,'2011-09-04 22:08:49',33,3),(80,0,'2011-09-04 22:08:49',38,3),(81,0,'2011-09-04 22:08:49',39,3),(82,0,'2011-09-04 22:08:49',36,3),(83,0,'2011-09-04 22:08:49',37,3),(84,0,'2011-09-04 22:08:49',42,3),(85,0,'2011-09-04 22:08:49',40,3),(86,0,'2011-09-04 22:08:49',41,3),(87,0,'2011-09-05 19:40:37',1,1),(88,0,'2011-09-05 19:40:37',2,1),(89,0,'2011-09-05 19:40:37',3,1),(90,0,'2011-09-05 19:40:37',4,1),(91,0,'2011-09-05 19:40:37',5,1),(92,0,'2011-09-05 19:40:37',6,1),(93,0,'2011-09-05 19:40:37',7,1),(94,0,'2011-09-05 19:40:37',8,1),(95,0,'2011-09-05 19:40:37',9,1),(96,0,'2011-09-05 19:40:37',10,1),(97,0,'2011-09-05 19:40:37',11,1),(98,0,'2011-09-05 19:40:37',12,1),(99,0,'2011-09-05 19:40:37',13,1),(100,0,'2011-09-05 19:40:37',14,1),(101,0,'2011-09-05 19:40:37',15,1),(102,0,'2011-09-05 19:40:37',17,1),(103,0,'2011-09-05 19:40:37',16,1),(104,0,'2011-09-05 19:40:37',19,1),(105,0,'2011-09-05 19:40:37',18,1),(106,0,'2011-09-05 19:40:37',21,1),(107,0,'2011-09-05 19:40:37',20,1),(108,0,'2011-09-05 19:40:37',23,1),(109,0,'2011-09-05 19:40:37',22,1),(110,0,'2011-09-05 19:40:37',25,1),(111,0,'2011-09-05 19:40:37',24,1),(112,0,'2011-09-05 19:40:37',27,1),(113,0,'2011-09-05 19:40:37',26,1),(114,0,'2011-09-05 19:40:37',29,1),(115,0,'2011-09-05 19:40:37',28,1),(116,0,'2011-09-05 19:40:37',31,1),(117,0,'2011-09-05 19:40:37',30,1),(118,0,'2011-09-05 19:40:37',34,1),(119,0,'2011-09-05 19:40:37',35,1),(120,0,'2011-09-05 19:40:37',32,1),(121,0,'2011-09-05 19:40:37',33,1),(122,0,'2011-09-05 19:40:37',38,1),(124,0,'2011-09-05 19:40:37',36,1),(125,0,'2011-09-05 19:40:37',37,1),(126,0,'2011-10-12 19:26:41',1,2),(127,0,'2011-10-12 19:26:41',7,2),(128,0,'2011-10-12 19:26:41',29,2),(129,0,'2011-10-12 19:26:41',28,2),(130,0,'2011-10-12 19:26:41',31,2),(131,0,'2011-10-12 19:26:41',30,2),(132,0,'2011-10-12 19:26:41',32,2),(133,0,'2011-10-12 19:26:41',33,2),(134,0,'2011-10-12 19:26:41',38,2),(135,0,'2011-10-12 19:26:41',36,2);
/*!40000 ALTER TABLE `core_topmenu_role_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `core_user`
--

DROP TABLE IF EXISTS `core_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_user` (
  `ID_` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `CID_` int(10) unsigned DEFAULT NULL,
  `CDATE_` datetime DEFAULT NULL,
  `MID_` int(10) unsigned DEFAULT NULL,
  `MDATE_` datetime DEFAULT NULL,
  `UUID_` varchar(45) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `depaId` int(10) unsigned DEFAULT NULL,
  `jobId` int(10) unsigned DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(300) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `mobilePhone` varchar(100) DEFAULT NULL,
  `gender` tinyint(3) unsigned DEFAULT '0',
  `displayName` varchar(100) DEFAULT NULL,
  `isLock` tinyint(3) unsigned DEFAULT '0',
  `jobId2` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `core_user`
--

LOCK TABLES `core_user` WRITE;
/*!40000 ALTER TABLE `core_user` DISABLE KEYS */;
INSERT INTO `core_user` VALUES (1,0,'2011-08-23 21:43:02',0,'2011-10-08 21:20:56',NULL,'admin',2,0,'914261631@qq.com','e10adc3949ba59abbe56e057f20f883e','','13589310033',1,'管理员1',0,0),(2,0,'2011-08-24 21:18:36',0,'2011-09-08 19:19:33',NULL,'subadmin',14,6,'914261631@qq.com','e10adc3949ba59abbe56e057f20f883e','123456','13589310033',1,'管理员',1,3),(3,0,'2011-09-09 09:21:13',NULL,NULL,NULL,'Kate',2,0,'kate@gmail.com','e10adc3949ba59abbe56e057f20f883e','','',0,'Kate',NULL,0),(4,0,'2011-09-09 09:21:23',NULL,NULL,NULL,'Tom',2,0,'tom@gmail.com','e10adc3949ba59abbe56e057f20f883e','','',0,'Tom',NULL,0),(5,0,'2011-09-09 09:21:37',0,'2011-09-29 20:14:48',NULL,'Yahoo',2,0,'yahoo@gmail.com','e10adc3949ba59abbe56e057f20f883e','','',0,'Yahoo',NULL,0),(6,0,'2011-09-09 09:21:49',NULL,NULL,NULL,'Google',2,0,'google@gmail.com','e10adc3949ba59abbe56e057f20f883e','','',0,'Google',NULL,0),(7,0,'2011-09-09 09:22:02',NULL,NULL,NULL,'Baidu',2,0,'baidu@gmail.com','e10adc3949ba59abbe56e057f20f883e','','',0,'Baidu',NULL,0),(8,0,'2011-09-09 09:22:14',NULL,NULL,NULL,'163',2,0,'163@gmail.com','e10adc3949ba59abbe56e057f20f883e','','',0,'163',NULL,0),(9,0,'2011-09-09 09:22:27',NULL,NULL,NULL,'Lucy',2,0,'lucy@gmail.com','e10adc3949ba59abbe56e057f20f883e','','',1,'Lucy',NULL,0),(10,0,'2011-09-09 09:22:51',NULL,NULL,NULL,'Ben',2,0,'ben@gmail.com','e10adc3949ba59abbe56e057f20f883e','','',1,'Ben',NULL,0),(11,0,'2011-09-09 09:22:59',NULL,NULL,NULL,'Li',2,0,'li@gmail.com','e10adc3949ba59abbe56e057f20f883e','','',1,'Li',NULL,0);
/*!40000 ALTER TABLE `core_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-10-12 19:30:13
