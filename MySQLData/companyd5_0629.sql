CREATE DATABASE  IF NOT EXISTS `companyd5` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `companyd5`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: companyd5
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `games` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `picture` varchar(45) DEFAULT NULL,
  `introduce` varchar(300) DEFAULT NULL,
  `times` varchar(100) DEFAULT NULL,
  `install` varchar(45) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
INSERT INTO `games` VALUES (1,'貪吃蛇','snake.png','經典的益智電子遊戲，玩家操控一條不斷前進的蛇，透過吞食畫面中的食物來增加長度，同時需避免撞擊牆壁或自己的身體','2026-06-26 12:00:00','Y','controller.SnakeGame'),(2,'經典2048','G2048.png','玩家透過滑動螢幕或鍵盤方向鍵，將網格上的數字方塊合併，最終目標是創造出數值為 2048 的方塊','2026-06-26 12:00:00','N','controller.Game2048'),(3,'俄羅斯方塊','Tetris.png','玩家需操作由四個小方塊組成的「四格骨牌」（Tetromino），在垂直區域內旋轉並填滿整行以進行消除，是全球電子遊戲史上最暢銷的經典之作','2026-06-26 12:00:00','N',NULL);
/*!40000 ALTER TABLE `games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `memberid` varchar(45) DEFAULT NULL,
  `gameid` varchar(45) DEFAULT NULL,
  `TopScore` varchar(45) DEFAULT NULL,
  `times` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (2,'D0006','1','10',''),(3,'D0006','1','0','2026-06-28 20:02:13'),(4,'D0006','1','0','2026-06-28 20:02:23'),(5,'D0006','1','0','2026-06-28 20:02:27'),(6,'D0006','1','60','2026-06-28 20:03:04'),(7,'D0006','1','30','2026-06-28 20:03:19'),(8,'admin','1','10','2026-06-29 00:17:33'),(9,'sky123','1','30','2026-06-29 00:38:31'),(10,'admin','1','20','2026-06-29 09:07:28'),(11,'cat123','1','20','2026-06-29 09:09:14'),(12,'admin','1','40','2026-06-29 09:37:01'),(13,'dog','1','10','2026-06-29 10:27:53'),(14,'admin','1','10','2026-06-29 10:30:30');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(45) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `year` int DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `admin` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'admin','1234','凱貓','0955-114-506','cat@cty.com.tw',30,'新北市新店區','Y'),(2,'D0007','456','帥狗','0955-331-887','dog@cty.com.tw',20,'台北市萬華區','N'),(3,'bcd','123','狗狗123','0955','dog@com',30,'台北市00','N'),(5,'bbb','123','skybbb','123','123@bds',12,'Taiwain','N'),(6,'GG123','123','GG','0800','GG@123',12,'123','N'),(7,'adminsky','1234','sky','0922897546','sky@gmail.com',18,'TAIWIN','N'),(9,'cat123','123','cat','0988256985','cat@123',12,'tawain','N'),(10,'dog','123','123','123','123',123,'123','N');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-29 11:44:11
