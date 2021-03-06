-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: freetalk
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `administrator_id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`administrator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ban_time`
--

DROP TABLE IF EXISTS `ban_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ban_time` (
  `ban_item_id` int NOT NULL AUTO_INCREMENT,
  `begin` datetime(6) DEFAULT NULL,
  `end` datetime(6) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`ban_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ban_time`
--

LOCK TABLES `ban_time` WRITE;
/*!40000 ALTER TABLE `ban_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `ban_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_like_table`
--

DROP TABLE IF EXISTS `comment_like_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_like_table` (
  `comments_like_item_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `comment_id` int DEFAULT NULL,
  PRIMARY KEY (`comments_like_item_id`),
  KEY `comment_like_comment_id` (`comment_id`),
  KEY `comment_like_user_id` (`user_id`),
  CONSTRAINT `comment_like_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`comment_id`),
  CONSTRAINT `comment_like_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_like_table`
--

LOCK TABLES `comment_like_table` WRITE;
/*!40000 ALTER TABLE `comment_like_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_like_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_star_table`
--

DROP TABLE IF EXISTS `comment_star_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_star_table` (
  `comment_star_item_id` int NOT NULL AUTO_INCREMENT,
  `comment_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`comment_star_item_id`),
  KEY `comment_star_comment_id` (`comment_id`),
  KEY `comment_star_user_id` (`user_id`),
  CONSTRAINT `comment_star_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`comment_id`),
  CONSTRAINT `comment_star_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_star_table`
--

LOCK TABLES `comment_star_table` WRITE;
/*!40000 ALTER TABLE `comment_star_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_star_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `comment_content` mediumtext,
  `reply_id` int DEFAULT NULL,
  `likes` int DEFAULT NULL,
  `stars` int DEFAULT NULL,
  `post_time` datetime DEFAULT NULL,
  `reply_number` int DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `topic_id` (`topic_id`),
  KEY `comment_user_id` (`user_id`),
  CONSTRAINT `comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `topic_id` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follows`
--

DROP TABLE IF EXISTS `follows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follows` (
  `follow_row_id` int NOT NULL AUTO_INCREMENT,
  `follower_id` int DEFAULT NULL,
  `followed_id` int DEFAULT NULL,
  PRIMARY KEY (`follow_row_id`),
  KEY `follower_id` (`follower_id`),
  KEY `followed_id` (`followed_id`),
  CONSTRAINT `followed_id` FOREIGN KEY (`followed_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `follower_id` FOREIGN KEY (`follower_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follows`
--

LOCK TABLES `follows` WRITE;
/*!40000 ALTER TABLE `follows` DISABLE KEYS */;
/*!40000 ALTER TABLE `follows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `private_message`
--

DROP TABLE IF EXISTS `private_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `private_message` (
  `message_id` int NOT NULL AUTO_INCREMENT,
  `send_user_id` int DEFAULT NULL,
  `accept_user_id` int DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `message_text` text,
  `is_read` int DEFAULT '0',
  PRIMARY KEY (`message_id`),
  KEY `send_user_id` (`send_user_id`),
  KEY `accept_user_id` (`accept_user_id`),
  CONSTRAINT `accept_user_id` FOREIGN KEY (`accept_user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `send_user_id` FOREIGN KEY (`send_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `private_message`
--

LOCK TABLES `private_message` WRITE;
/*!40000 ALTER TABLE `private_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `private_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qr_table`
--

DROP TABLE IF EXISTS `qr_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qr_table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `state` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qr_table`
--

LOCK TABLES `qr_table` WRITE;
/*!40000 ALTER TABLE `qr_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `qr_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratings` (
  `user_id` int DEFAULT NULL,
  `topic_id` int DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `rating_id` int NOT NULL AUTO_INCREMENT,
  `post_time` mediumtext,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_info`
--

DROP TABLE IF EXISTS `topic_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_info` (
  `info_id` int NOT NULL AUTO_INCREMENT,
  `topic_id` int DEFAULT NULL,
  `likes` int DEFAULT NULL,
  `stars` int DEFAULT NULL,
  `comments` int DEFAULT NULL,
  `views` int DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `hot` double DEFAULT NULL,
  PRIMARY KEY (`info_id`),
  KEY `info_topic_id` (`topic_id`),
  CONSTRAINT `info_topic_id` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_info`
--

LOCK TABLES `topic_info` WRITE;
/*!40000 ALTER TABLE `topic_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_like_table`
--

DROP TABLE IF EXISTS `topic_like_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_like_table` (
  `topic_like_item_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `topic_id` int DEFAULT NULL,
  PRIMARY KEY (`topic_like_item_id`),
  KEY `topic_like_user_id` (`user_id`),
  KEY `topic_like_topic_id` (`topic_id`),
  CONSTRAINT `topic_like_topic_id` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  CONSTRAINT `topic_like_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_like_table`
--

LOCK TABLES `topic_like_table` WRITE;
/*!40000 ALTER TABLE `topic_like_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic_like_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_star_table`
--

DROP TABLE IF EXISTS `topic_star_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic_star_table` (
  `topic_star_item_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `topic_id` int DEFAULT NULL,
  PRIMARY KEY (`topic_star_item_id`),
  KEY `topic_star_topic_id` (`topic_id`),
  KEY `topic_star_user_id` (`user_id`),
  CONSTRAINT `topic_star_topic_id` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  CONSTRAINT `topic_star_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_star_table`
--

LOCK TABLES `topic_star_table` WRITE;
/*!40000 ALTER TABLE `topic_star_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic_star_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topics` (
  `topic_id` int NOT NULL AUTO_INCREMENT,
  `post_time` datetime DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `topic_description` mediumtext,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `image` mediumtext,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-18 17:19:39
