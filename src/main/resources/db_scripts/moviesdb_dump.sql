-- MySQL dump 10.13  Distrib 5.1.66, for Win64 (unknown)
--
-- Host: localhost    Database: moviesdb
-- ------------------------------------------------------
-- Server version	5.1.66-community

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
-- Table structure for table `tgenre`
--

DROP TABLE IF EXISTS `tgenre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tgenre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `genre_name` (`genre_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tgenre`
--

LOCK TABLES `tgenre` WRITE;
/*!40000 ALTER TABLE `tgenre` DISABLE KEYS */;
INSERT INTO `tgenre` VALUES (5,'Action'),(1,'Comedy'),(3,'Documentary'),(6,'Drama'),(2,'Horror'),(7,'Musical'),(4,'Romance');
/*!40000 ALTER TABLE `tgenre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmovieattributes`
--

DROP TABLE IF EXISTS `tmovieattributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmovieattributes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attr_name` varchar(255) DEFAULT NULL,
  `attr_value` varchar(255) DEFAULT NULL,
  `m_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `m_id` (`m_id`),
  CONSTRAINT `tmovieattributes_ibfk_1` FOREIGN KEY (`m_id`) REFERENCES `tmovies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmovieattributes`
--

LOCK TABLES `tmovieattributes` WRITE;
/*!40000 ALTER TABLE `tmovieattributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tmovieattributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmovies`
--

DROP TABLE IF EXISTS `tmovies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmovies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `release_year` int(4) DEFAULT '0',
  `status` varchar(255) DEFAULT NULL,
  `mtype` varchar(255) DEFAULT NULL,
  `genre_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmovies`
--

LOCK TABLES `tmovies` WRITE;
/*!40000 ALTER TABLE `tmovies` DISABLE KEYS */;
INSERT INTO `tmovies` VALUES (1,'Man of Steel',2013,'Upcoming','Movie',5),(2,'Fast & Furious 6',2013,'Now Showing','Movie',5),(3,'The Internship',2013,'Now Showing','Movie',1),(4,'The Shawshank Redemption',1994,'Complete','Movie',6),(5,'The Dark Knight',2008,'Complete','Movie',5),(6,'The Lord of the Rings: The Return of the King',2003,'Complete','Movie',6),(7,'Inception',2010,'Complete','Movie',5),(8,'The Matrix',1999,'Complete','Movie',5),(9,'Game of Thrones',2011,'Ongoing','TV',6),(10,'Friends',1994,'Complete','TV',1),(11,'How I Met Your Mother',2005,'Ongoing','TV',1),(12,'Despicable Me 2',2013,'Now Showing','Movie',1),(14,'Spirited Away',2001,'Complete','Anime',6),(15,'The  Wolverine',2013,'Upcoming','Movie',5),(16,'Fullmetal Alchemist Brotherhood',2009,'Complete','Anime',5),(19,'New Movie',2010,'Complete','Movie',2);
/*!40000 ALTER TABLE `tmovies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-09-15 15:13:54
