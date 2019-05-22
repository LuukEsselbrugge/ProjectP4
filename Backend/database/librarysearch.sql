-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2019 at 05:41 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `librarysearch`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookshelf`
--

CREATE TABLE `bookshelf` (
  `shelfID` int(2) NOT NULL,
  `Mac` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookshelf`
--

INSERT INTO `bookshelf` (`shelfID`, `Mac`) VALUES
(1, 'abcd'),
(2, 'abcd'),
(3, 'abcd'),
(4, 'abcd'),
(5, 'abcd'),
(6, 'abcd'),
(7, 'abcd'),
(8, 'abcd');

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE `sessions` (
  `SessionID` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `UserID` varchar(8) COLLATE utf8mb4_bin NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`SessionID`, `UserID`, `Date`) VALUES
('a9eccbe7159df695a15003970aa5a9ad', 'Hj56Tfu4', '2019-05-21 11:04:22'),
('10477d41e89a78fac060cda0fbdd7346', 'Hj56Tfu4', '2019-05-21 12:16:07'),
('d8d7abc6adc91e3eff85bd77df9d95c4', 'ADminADM', '2019-05-22 14:25:46');

-- --------------------------------------------------------

--
-- Table structure for table `shelf`
--

CREATE TABLE `shelf` (
  `shelfID` int(5) NOT NULL,
  `bookshelf` int(2) NOT NULL,
  `Row` int(2) NOT NULL,
  `Col` int(2) NOT NULL,
  `lowestBookNr` int(5) NOT NULL,
  `highestBookNr` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shelf`
--

INSERT INTO `shelf` (`shelfID`, `bookshelf`, `Row`, `Col`, `lowestBookNr`, `highestBookNr`) VALUES
(1, 1, 1, 1, 1, 10),
(2, 1, 2, 1, 11, 20),
(3, 1, 3, 1, 21, 30),
(4, 1, 4, 1, 31, 40),
(5, 1, 5, 1, 41, 50),
(6, 1, 1, 2, 51, 60),
(7, 1, 2, 2, 61, 70),
(8, 1, 3, 2, 71, 80),
(9, 2, 1, 1, 200, 210),
(10, 4, 2, 1, 211, 220),
(11, 2, 3, 1, 221, 230),
(12, 2, 4, 1, 231, 240),
(13, 2, 5, 1, 241, 255),
(14, 5, 1, 2, 256, 280),
(15, 3, 1, 1, 301, 310),
(16, 3, 2, 1, 311, 320),
(17, 3, 3, 1, 321, 330),
(18, 3, 4, 1, 331, 340),
(19, 3, 5, 1, 341, 350),
(20, 3, 1, 2, 351, 360),
(21, 3, 2, 2, 361, 370),
(22, 3, 3, 2, 371, 380),
(23, 3, 4, 2, 381, 390),
(24, 3, 5, 2, 391, 400),
(25, 3, 1, 3, 401, 410),
(26, 3, 2, 3, 411, 420),
(27, 3, 3, 3, 421, 430),
(28, 3, 4, 3, 431, 440),
(29, 3, 5, 3, 441, 450),
(30, 3, 1, 4, 451, 460),
(31, 3, 2, 4, 461, 470),
(32, 3, 3, 4, 471, 480),
(33, 3, 4, 4, 481, 490),
(34, 3, 5, 4, 491, 500),
(35, 3, 1, 5, 501, 510),
(36, 3, 2, 5, 511, 520),
(37, 3, 3, 5, 521, 530),
(38, 3, 4, 5, 531, 540),
(39, 3, 5, 5, 541, 550);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` varchar(8) COLLATE utf8mb4_bin NOT NULL,
  `Email` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `Firstname` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `Lastname` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `Password` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `Image` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `Admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Email`, `Firstname`, `Lastname`, `Password`, `Image`, `Admin`) VALUES
('2fb7f6fd', '', 'Gebruiker', '', '', '', 0),
('ADminADM', 'admin@admin.com', 'Admin', 'Admin', '48d72c13fc7e5e725df863060f41efca7a54e3a3768a8c6234d29d7a93ef2176', '', 1),
('Hj56Tfu4', 'test@test.com', 'Test', 'Test', '8006bff45df325736f290a05e4e9bb0093f9ef6fc545a146c1b06f5ef3aaba88', '', 0),
('f7238edc', '', 'Gebruiker', '', '', '', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `shelf`
--
ALTER TABLE `shelf`
  ADD PRIMARY KEY (`shelfID`),
  ADD UNIQUE KEY `shelfID` (`shelfID`),
  ADD UNIQUE KEY `shelfID_2` (`shelfID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD UNIQUE KEY `UserID` (`UserID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `shelf`
--
ALTER TABLE `shelf`
  MODIFY `shelfID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
