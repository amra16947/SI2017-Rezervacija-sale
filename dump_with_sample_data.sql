-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2017 at 01:56 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tim9`
--

-- --------------------------------------------------------

--
-- Table structure for table `access_grant`
--

CREATE TABLE `access_grant` (
  `id` bigint(20) NOT NULL,
  `classroomId` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `classroom`
--

CREATE TABLE `classroom` (
  `id` bigint(20) NOT NULL,
  `key_count` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `seat_count` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `taken_key_count` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `teacher_in_charge_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `classroom`
--

INSERT INTO `classroom` (`id`, `key_count`, `name`, `seat_count`, `status`, `taken_key_count`, `type`, `teacher_in_charge_id`) VALUES
(1, 5, 'S0-01', 15, 0, 0, 0, NULL),
(2, 5, 'S0-02', 15, 0, 0, 0, 4);

-- --------------------------------------------------------

--
-- Table structure for table `classroom_equipment`
--

CREATE TABLE `classroom_equipment` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `classroomId` bigint(20) NOT NULL,
  `equipment_type_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `damage_report`
--

CREATE TABLE `damage_report` (
  `id` bigint(20) NOT NULL,
  `description` varchar(2048) COLLATE utf8_unicode_ci NOT NULL,
  `reported_at` datetime NOT NULL,
  `classroomId` bigint(20) NOT NULL,
  `reported_by_id` bigint(20) DEFAULT NULL,
  `reservation_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `equipment_type`
--

CREATE TABLE `equipment_type` (
  `id` bigint(20) NOT NULL,
  `label` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL,
  `duration` tinyint(4) NOT NULL,
  `reserved_at` datetime NOT NULL,
  `classroomId` bigint(20) NOT NULL,
  `schedule_block_id` bigint(20) DEFAULT NULL,
  `teacher_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `schedule_block`
--

CREATE TABLE `schedule_block` (
  `id` bigint(20) NOT NULL,
  `semester_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `semester`
--

CREATE TABLE `semester` (
  `id` bigint(20) NOT NULL,
  `begins_at` date NOT NULL,
  `ends_at` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `taken_key`
--

CREATE TABLE `taken_key` (
  `id` bigint(20) NOT NULL,
  `returned_at` datetime DEFAULT NULL,
  `taken_at` datetime NOT NULL,
  `reservation_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `password`, `type`, `username`) VALUES
(2, 'Ahmo', 'Popovic', '$2a$10$MSQkaaYBs..uRrJGEPa4vOoUeujY7raIom3QHQxz8a6qWoFAAyqom', 2, 'admin'),
(4, 'Benjamin', 'Ramić', '$2a$10$oZcf.olaHX0vN835Hvzrq.9kSrA9ds/SXlJdcrk9QptoZ.mhQlxKW', 0, 'bramic2'),
(5, 'Ernedin', 'Zajko', '$2a$10$/UVNMW42CJsGVz8wwaKZ9uUzeRcgFzzVOYmvs5/MNW0pC.61umfPe', 2, 'ezajko'),
(6, 'Amina', 'Puce', '$2a$10$XFRJw6MVUwWLwAIci12.COcbaJESThqNGwQdIsrdPzfNwauBovsAa', 2, 'apuce1'),
(7, 'Ajla', 'Saba', '$2a$10$z3ypaTHnjsnNIqles1M5hOZAF2JQNgA4WaIAzTNsdN.2hA4CootnS', 0, 'ajla2'),
(8, 'Operaterka', 'Operaterkova', '$2a$10$NAuuk5MJ/N1s8RIEADtjbuS9vTmPaPUROWGuH7zptdludldMCQrWa', 1, 'minim'),
(9, 'Profa', 'Profić', '$2a$10$Z40uPjkq9j.mZRqOMANS/eeY8.jFt7Hgksi.S8x3z2GDc5RdIAIcG', 0, 'profa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `access_grant`
--
ALTER TABLE `access_grant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmgidkukyqlvwaseb2xtiwhmeu` (`classroomId`),
  ADD KEY `FKpqjg9wpritg2ydrpllif44ufd` (`teacher_id`);

--
-- Indexes for table `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6ate3aw7ls2unp61ui1c32n6s` (`name`),
  ADD KEY `FK9im9xadvw62dhl3hv624ndfdl` (`teacher_in_charge_id`);

--
-- Indexes for table `classroom_equipment`
--
ALTER TABLE `classroom_equipment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk4v0m59tbgafxdnkcmrsxtoqf` (`classroomId`),
  ADD KEY `FKrkygposl49eg9w8wtl6kb290d` (`equipment_type_id`);

--
-- Indexes for table `damage_report`
--
ALTER TABLE `damage_report`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnogmcu5capeih7t4r2ppr1yo1` (`classroomId`),
  ADD KEY `FKt9hdb54qpxdbrhidw82qixwio` (`reported_by_id`),
  ADD KEY `FKq1dqtcqw7ylu1sv84mqcxa0dh` (`reservation_id`);

--
-- Indexes for table `equipment_type`
--
ALTER TABLE `equipment_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ns2mmspyt1f98emv0u6sxfxcg` (`label`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjnxxi52alyljlcodaf614y0v3` (`classroomId`),
  ADD KEY `FKp5xi97dr7s24uvs43qv8q2tdv` (`schedule_block_id`),
  ADD KEY `FKou38qwmwenwft1tafje4iuxsb` (`teacher_id`);

--
-- Indexes for table `schedule_block`
--
ALTER TABLE `schedule_block`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2an5sdl3fwi31iy14d6jnj4gl` (`semester_id`);

--
-- Indexes for table `semester`
--
ALTER TABLE `semester`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `taken_key`
--
ALTER TABLE `taken_key`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_hx6t9ka9mx7h5wbrft56nvolo` (`reservation_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `access_grant`
--
ALTER TABLE `access_grant`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `classroom`
--
ALTER TABLE `classroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `classroom_equipment`
--
ALTER TABLE `classroom_equipment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `damage_report`
--
ALTER TABLE `damage_report`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `equipment_type`
--
ALTER TABLE `equipment_type`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `schedule_block`
--
ALTER TABLE `schedule_block`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `semester`
--
ALTER TABLE `semester`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `taken_key`
--
ALTER TABLE `taken_key`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
