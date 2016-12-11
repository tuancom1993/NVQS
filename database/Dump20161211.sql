CREATE DATABASE  IF NOT EXISTS `nghiavuquansu` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `nghiavuquansu`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: nghiavuquansu
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.9-MariaDB

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
-- Table structure for table `capdaotao`
--

DROP TABLE IF EXISTS `capdaotao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capdaotao` (
  `idcapdaotao` int(11) NOT NULL,
  `mota` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `in` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idcapdaotao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capdaotao`
--

LOCK TABLES `capdaotao` WRITE;
/*!40000 ALTER TABLE `capdaotao` DISABLE KEYS */;
INSERT INTO `capdaotao` VALUES (2,'Lớp 12','12'),(3,'Đại Học','ĐH'),(4,'Cao Đẳng','CĐ'),(5,'Trung Cấp','TC'),(6,'Khác','Khác');
/*!40000 ALTER TABLE `capdaotao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `congdan`
--

DROP TABLE IF EXISTS `congdan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `congdan` (
  `idcongdan` int(11) NOT NULL AUTO_INCREMENT,
  `hoten` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ngaysinh` date NOT NULL,
  `todanpho` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `phuong` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `trinhdohocvan` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `idcapdaotao` int(11) NOT NULL,
  `nganhhoc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `nienkhoa` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `diachi` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idlydo` int(11) NOT NULL,
  `idphanloailydo` int(11) DEFAULT NULL,
  `hotencha` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hotenme` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phanloaisuckhoe` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tinhtrangsuckhoe` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `ghichu` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `lydocuthe` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `ghichucanhan` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tentruong` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `congdancol1` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idcongdan`),
  KEY `fk_congdan_lydo` (`idlydo`),
  KEY `fk_congdan_capdaotao` (`idcapdaotao`),
  CONSTRAINT `fk_congdan_capdaotao` FOREIGN KEY (`idcapdaotao`) REFERENCES `capdaotao` (`idcapdaotao`),
  CONSTRAINT `fk_congdan_lydo` FOREIGN KEY (`idlydo`) REFERENCES `lydo` (`idlydo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `congdan`
--

LOCK TABLES `congdan` WRITE;
/*!40000 ALTER TABLE `congdan` DISABLE KEYS */;
INSERT INTO `congdan` VALUES (1,'Vũ Xuân Tuấn Anh','1993-10-21',NULL,NULL,'12/12',2,NULL,'Kĩ sư ','201 đống đa',1,-1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'Vũ Xuân Tuấn Anh','1993-01-21','18','Thạch THang','12/12',6,'CNTT','2011-2016','201 Đống Đa',14,1,'Vũ Xuân Bảo','Vũ Thị Hiền',NULL,NULL,'Biên Chuế','Lý Do Cụ THể','Cá Nhân','Bách Khoa',NULL),(3,'Trần ăn A','1993-12-23','1','12sdsd','fasgsd',3,'fdg','gdfgrd','fsdfs',14,2,'sđs','sdfa','fsdfsd','dfs','dsdf','fsdfs','dfgd','ffds',NULL),(4,'Trần ăn A','1993-12-23','1','12sdsd','fasgsd',3,'fdg','gdfgrd','fsdfs',14,2,'sđs','sdfa','fsdfsd','dfs','dsdf','fsdfs','dfgd','ffds',NULL),(5,'dsdfsd','2016-12-09','','Thạch Thang','',2,'','','fsdfsd',14,1,'sdfds','fsdfsd','','','','','','',NULL);
/*!40000 ALTER TABLE `congdan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loainghiavu`
--

DROP TABLE IF EXISTS `loainghiavu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loainghiavu` (
  `idloainghiavu` int(11) NOT NULL AUTO_INCREMENT,
  `mota` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idloainghiavu`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loainghiavu`
--

LOCK TABLES `loainghiavu` WRITE;
/*!40000 ALTER TABLE `loainghiavu` DISABLE KEYS */;
INSERT INTO `loainghiavu` VALUES (1,'Khác'),(2,'Đề nghị tạm hoãn gọi nhập ngũ'),(3,'Đề nghị miễn gọi nhập ngũ'),(4,'Đề nghị không gọi nhập ngũ, chưa gọi nhập ngũ'),(5,'Đề nghị chưa gọi nhập ngũ'),(6,'Đủ điều kiện gọi khám sức khỏe');
/*!40000 ALTER TABLE `loainghiavu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lydo`
--

DROP TABLE IF EXISTS `lydo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lydo` (
  `idlydo` int(11) NOT NULL AUTO_INCREMENT,
  `idloainghiavu` int(11) NOT NULL,
  `mota` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `danhsach` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idlydo`),
  KEY `fk_lydo_loainghiavu` (`idloainghiavu`),
  CONSTRAINT `fk_lydo_loainghiavu` FOREIGN KEY (`idloainghiavu`) REFERENCES `loainghiavu` (`idloainghiavu`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lydo`
--

LOCK TABLES `lydo` WRITE;
/*!40000 ALTER TABLE `lydo` DISABLE KEYS */;
INSERT INTO `lydo` VALUES (1,1,'Khác',''),(2,2,'Chưa đủ sức khỏe phục vụ tại ngũ theo kết luận của Hội đồng khám sức khỏe','1'),(3,2,'Là lao động duy nhất phải trực tiếp nuôi người khác trong gia đình không còn sức lao động hoặc chưa đến tuổi lao động','2'),(4,2,'Một con của bệnh binh, người nhiễm chất độc da cam suy giảm khả năng lao động từ 61 - 80%','3'),(5,2,'Có anh, chị hoặc em ruột là HSQ-BS đang phục vụ tại ngũ; HSQ chiến sĩ thực hiện nghĩa vụ tham gia Công an nhân dân','4'),(6,2,'Người thuộc diện di dân, giãn dân trong 3 năm đầu đến các xã đặc biệt khó khắn theo dự án phát triển kinh té - xã hôi','5'),(7,2,'Cán bộ, công chức, viên chức, TNXP được điều động đến công tác, làm việc ở vùng có điều kiện kinh tế - xã hội đặc biệt khó khăn theo quy định của pháp luật','6'),(8,2,'Đang học tại cơ sở giáo dục phổ thông, đang được đào tạo trình độ đại học chính quy, cao đẳng chính quy','7'),(9,3,'Con liệt sĩ, con Thương binh hạng 1','8'),(10,3,'Một anh hoặc một em trai của liệt sĩ','9'),(11,3,'Một con của thương binh hạng 2, một con của bệnh binh từ 81% trở lên, một con của người nhiễm chất độc da cam từ 81% trở lên','10'),(12,3,'Người làm công tác cơ yếu không phải là quân nhân, công an nhân dân','11'),(13,3,'Cán bộ, công chức, viên chức, thanh niên xong phong được điều động đến công tác, làm việc ở vùng có điều kiện KT-XH đặc biệt khó khăn từ 24 tháng trở lên','12'),(14,4,'Vi phạm tiêu chuẩn chính trị theo Thông tư 50/TTLT-BQP-BCA','13'),(15,5,'Chưa đủ tiêu chuẩn nhập ngũ, trình độ văn hóa thấp','14'),(16,5,'Đang thực hiện nghĩa vụ Dân quân - Tự vệ','15'),(17,6,'Có mặt tại địa phương','16'),(18,6,'(Đề nghị Hội Đồng NVQS xem xét chưa gọi khám sức khỏe đợt này','16A'),(19,6,'(Thực hiện nghĩa vụ tham gia Công an nhân dân)','17');
/*!40000 ALTER TABLE `lydo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phanloailydo`
--

DROP TABLE IF EXISTS `phanloailydo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phanloailydo` (
  `idphanloailydo` int(11) NOT NULL AUTO_INCREMENT,
  `mota` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idlydo` int(11) NOT NULL,
  PRIMARY KEY (`idphanloailydo`),
  KEY `fk_phanloailydo_lydo` (`idlydo`),
  CONSTRAINT `fk_phanloailydo_lydo` FOREIGN KEY (`idlydo`) REFERENCES `lydo` (`idlydo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phanloailydo`
--

LOCK TABLES `phanloailydo` WRITE;
/*!40000 ALTER TABLE `phanloailydo` DISABLE KEYS */;
INSERT INTO `phanloailydo` VALUES (1,'Đề nghị không gọi nhập ngũ vào quân đội:',14),(2,'Đề nghị chưa gọi nhập ngũ vào quân đội:',14),(3,'Công dân từ 18 đến hết 25 tuổi',17),(4,'Công dân được đạo tạo trình độ CĐ, ĐH đã được tạm hoãn gọi nhập ngũ đến hết 27 tuổi',17),(5,'Công dân từ 18 đến hết 25 tuổi',18),(6,'Công dân được đạo tạo trình độ CĐ, ĐH đã được tạm hoãn gọi nhập ngũ đến hết 27 tuổi',18);
/*!40000 ALTER TABLE `phanloailydo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen`
--

DROP TABLE IF EXISTS `quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quyen` (
  `idquyen` int(11) NOT NULL AUTO_INCREMENT,
  `mota` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idquyen`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen`
--

LOCK TABLES `quyen` WRITE;
/*!40000 ALTER TABLE `quyen` DISABLE KEYS */;
INSERT INTO `quyen` VALUES (1,'Admin'),(2,'User');
/*!40000 ALTER TABLE `quyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `quyen` int(11) NOT NULL,
  `hoten` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `fk_user_quyen` (`quyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','admin',1,'Vũ Xuân Tuấn Anh');
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

-- Dump completed on 2016-12-11  9:20:43
