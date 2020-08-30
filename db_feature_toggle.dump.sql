-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.21 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for db_feature_toggle
DROP DATABASE IF EXISTS `db_feature_toggle`;
CREATE DATABASE IF NOT EXISTS `db_feature_toggle` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_feature_toggle`;

-- Dumping structure for table db_feature_toggle.customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `id` int NOT NULL,
  `display_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_feature_toggle.customer: ~20 rows (approximately)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`id`, `display_name`) VALUES
	(1, 'Ilya Gusarov'),
	(2, 'Ivan Petrov'),
	(3, 'Bruce Springsteen'),
	(4, 'Freddie Mercury'),
	(5, 'Jimi Hendrix'),
	(6, 'Elvis Presley'),
	(7, 'Janis Joplin'),
	(8, 'Paul McCartney'),
	(9, 'Mick Jagger'),
	(10, 'Chuck Berry'),
	(11, 'David Bowie'),
	(12, 'Patti Smith'),
	(13, 'Kurt Cobain'),
	(14, 'Stevie Nicks'),
	(15, 'Jim Morrison'),
	(16, 'Joni Mitchell'),
	(17, 'Robert Plant'),
	(18, 'Ozzy Osbourne'),
	(19, 'Axl Rose'),
	(20, 'Bob Dylan');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table db_feature_toggle.feature_toggle
DROP TABLE IF EXISTS `feature_toggle`;
CREATE TABLE IF NOT EXISTS `feature_toggle` (
  `id` int NOT NULL,
  `display_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `technical_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `expires_on` timestamp NULL DEFAULT NULL,
  `description` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `inverted` tinyint(1) NOT NULL,
  `archive` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_unique` (`technical_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_feature_toggle.feature_toggle: ~15 rows (approximately)
DELETE FROM `feature_toggle`;
/*!40000 ALTER TABLE `feature_toggle` DISABLE KEYS */;
INSERT INTO `feature_toggle` (`id`, `display_name`, `technical_name`, `expires_on`, `description`, `inverted`, `archive`) VALUES
	(1, 'feature A', 'feature-a', '2020-08-14 20:00:00', 'Feature description', 0, 0),
	(6, 'feature B', 'feature-b', '2020-08-22 13:00:29', 'Feature description', 0, 0),
	(7, 'Feature display name 3', 'feature-c', '2020-08-22 13:00:29', 'Feature description', 0, 1),
	(8, 'Feature display name 2', 'feature-d', '2020-08-22 13:00:29', 'Feature description', 0, 0),
	(9, 'Feature display name 2', 'feature-e', '2020-08-22 13:00:29', 'Feature description', 0, 0),
	(10, 'Feature display name 2', 'feature-f', '2020-08-22 13:00:29', 'Feature description', 0, 0),
	(11, 'Test creation', 'feature-g', '2020-08-04 16:00:00', 'sdsdfs\nsdfsfsd', 0, 0),
	(17, 'Test creation', 'feature-h', '2020-08-04 16:00:00', 'sdsdfs\nsdfsfsd', 0, 0),
	(18, 'Test creation', 'feature-j', '2020-08-04 16:00:00', 'sdsdfs\nsdfsfsd', 1, 0),
	(19, 'Test saving', 'feature-k', '2020-08-04 16:00:00', 'sdsdfs\nsdfsfsd', 0, 0),
	(20, 'Saving 5677', 'feature-l', '2020-08-04 16:00:00', 'sdsdfs\nsdfsfsd', 0, 0),
	(23, 'feature-z', 'feature-z', '2020-07-28 20:00:00', 'test test', 0, 0),
	(26, NULL, 'feature-cd', NULL, NULL, 1, 0),
	(27, NULL, 'feature-m', '2020-08-18 20:00:00', NULL, 0, 1),
	(28, NULL, 'feature-ght', NULL, NULL, 1, 1);
/*!40000 ALTER TABLE `feature_toggle` ENABLE KEYS */;

-- Dumping structure for table db_feature_toggle.feature_toggle_customer
DROP TABLE IF EXISTS `feature_toggle_customer`;
CREATE TABLE IF NOT EXISTS `feature_toggle_customer` (
  `feature_toggle_id` int NOT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`feature_toggle_id`,`customer_id`),
  KEY `FK_feature_toggle_customer_customer` (`customer_id`),
  CONSTRAINT `FK_feature_toggle_customer_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_feature_toggle_customer_feature_toggle` FOREIGN KEY (`feature_toggle_id`) REFERENCES `feature_toggle` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_feature_toggle.feature_toggle_customer: ~13 rows (approximately)
DELETE FROM `feature_toggle_customer`;
/*!40000 ALTER TABLE `feature_toggle_customer` DISABLE KEYS */;
INSERT INTO `feature_toggle_customer` (`feature_toggle_id`, `customer_id`) VALUES
	(1, 1),
	(9, 1),
	(10, 1),
	(23, 1),
	(27, 1),
	(8, 2),
	(23, 2),
	(27, 2),
	(1, 5),
	(6, 7),
	(1, 13),
	(1, 15),
	(1, 19);
/*!40000 ALTER TABLE `feature_toggle_customer` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
