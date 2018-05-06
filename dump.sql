-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.18-0ubuntu0.16.04.1 - (Ubuntu)
-- Server OS:                    Linux
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for tim9
CREATE DATABASE IF NOT EXISTS `tim9` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci */;
USE `tim9`;

-- Dumping structure for table tim9.access_grant
CREATE TABLE IF NOT EXISTS `access_grant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `classroomId` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmgidkukyqlvwaseb2xtiwhmeu` (`classroomId`),
  KEY `FKpqjg9wpritg2ydrpllif44ufd` (`teacher_id`),
  CONSTRAINT `FKmgidkukyqlvwaseb2xtiwhmeu` FOREIGN KEY (`classroomId`) REFERENCES `classroom` (`id`),
  CONSTRAINT `FKpqjg9wpritg2ydrpllif44ufd` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Data exporting was unselected.
-- Dumping structure for table tim9.classroom
CREATE TABLE IF NOT EXISTS `classroom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key_count` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `seat_count` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `taken_key_count` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `teacher_in_charge_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6ate3aw7ls2unp61ui1c32n6s` (`name`),
  KEY `FK9im9xadvw62dhl3hv624ndfdl` (`teacher_in_charge_id`),
  CONSTRAINT `FK9im9xadvw62dhl3hv624ndfdl` FOREIGN KEY (`teacher_in_charge_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Data exporting was unselected.
-- Dumping structure for table tim9.classroom_equipment
CREATE TABLE IF NOT EXISTS `classroom_equipment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL,
  `classroomId` bigint(20) NOT NULL,
  `equipment_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk4v0m59tbgafxdnkcmrsxtoqf` (`classroomId`),
  KEY `FKrkygposl49eg9w8wtl6kb290d` (`equipment_type_id`),
  CONSTRAINT `FKk4v0m59tbgafxdnkcmrsxtoqf` FOREIGN KEY (`classroomId`) REFERENCES `classroom` (`id`),
  CONSTRAINT `FKrkygposl49eg9w8wtl6kb290d` FOREIGN KEY (`equipment_type_id`) REFERENCES `equipment_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Data exporting was unselected.
-- Dumping structure for table tim9.damage_report
CREATE TABLE IF NOT EXISTS `damage_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(2048) COLLATE utf8mb4_croatian_ci NOT NULL,
  `reported_at` datetime NOT NULL,
  `classroomId` bigint(20) NOT NULL,
  `reported_by_id` bigint(20) DEFAULT NULL,
  `reservation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnogmcu5capeih7t4r2ppr1yo1` (`classroomId`),
  KEY `FKt9hdb54qpxdbrhidw82qixwio` (`reported_by_id`),
  KEY `FKq1dqtcqw7ylu1sv84mqcxa0dh` (`reservation_id`),
  CONSTRAINT `FKnogmcu5capeih7t4r2ppr1yo1` FOREIGN KEY (`classroomId`) REFERENCES `classroom` (`id`),
  CONSTRAINT `FKq1dqtcqw7ylu1sv84mqcxa0dh` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`),
  CONSTRAINT `FKt9hdb54qpxdbrhidw82qixwio` FOREIGN KEY (`reported_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Data exporting was unselected.
-- Dumping structure for table tim9.equipment_type
CREATE TABLE IF NOT EXISTS `equipment_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ns2mmspyt1f98emv0u6sxfxcg` (`label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Data exporting was unselected.
-- Dumping structure for table tim9.reservation
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `duration` tinyint(4) NOT NULL,
  `reserved_at` datetime NOT NULL,
  `classroomId` bigint(20) NOT NULL,
  `schedule_block_id` bigint(20) DEFAULT NULL,
  `teacher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjnxxi52alyljlcodaf614y0v3` (`classroomId`),
  KEY `FKp5xi97dr7s24uvs43qv8q2tdv` (`schedule_block_id`),
  KEY `FKou38qwmwenwft1tafje4iuxsb` (`teacher_id`),
  CONSTRAINT `FKjnxxi52alyljlcodaf614y0v3` FOREIGN KEY (`classroomId`) REFERENCES `classroom` (`id`),
  CONSTRAINT `FKou38qwmwenwft1tafje4iuxsb` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKp5xi97dr7s24uvs43qv8q2tdv` FOREIGN KEY (`schedule_block_id`) REFERENCES `schedule_block` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Data exporting was unselected.
-- Dumping structure for table tim9.schedule_block
CREATE TABLE IF NOT EXISTS `schedule_block` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `semester_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2an5sdl3fwi31iy14d6jnj4gl` (`semester_id`),
  CONSTRAINT `FK2an5sdl3fwi31iy14d6jnj4gl` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Data exporting was unselected.
-- Dumping structure for table tim9.semester
CREATE TABLE IF NOT EXISTS `semester` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `begins_at` date NOT NULL,
  `ends_at` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Data exporting was unselected.
-- Dumping structure for table tim9.taken_key
CREATE TABLE IF NOT EXISTS `taken_key` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `returned_at` datetime DEFAULT NULL,
  `taken_at` datetime NOT NULL,
  `reservation_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hx6t9ka9mx7h5wbrft56nvolo` (`reservation_id`),
  CONSTRAINT `FK9mj4s1q8jwcvwvhc20vemluht` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Data exporting was unselected.
-- Dumping structure for table tim9.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  `type` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
