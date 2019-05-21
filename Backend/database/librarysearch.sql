-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2019 at 01:11 PM
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
('a9eccbe7159df695a15003970aa5a9ad', 'Hj56Tfu4', '2019-05-21 11:04:22');

-- --------------------------------------------------------

--
-- Table structure for table `shelf`
--

CREATE TABLE `shelf` (
  `shelfID` int(11) NOT NULL,
  `Row` int(11) NOT NULL,
  `Col` int(11) NOT NULL,
  `lowestBookNr` int(11) NOT NULL,
  `highestBookNr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `Image` varchar(64) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Email`, `Firstname`, `Lastname`, `Password`, `Image`) VALUES
('Hj56Tfu4', 'test@test.com', 'Test', 'Test', '8006bff45df325736f290a05e4e9bb0093f9ef6fc545a146c1b06f5ef3aaba88', '');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
