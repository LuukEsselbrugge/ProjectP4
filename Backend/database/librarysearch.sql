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

CREATE TABLE `Bookshelf` (
  `shelfID` int(2) NOT NULL,
  `Mac` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookshelf`
--

INSERT INTO `Bookshelf` (`shelfID`, `Mac`) VALUES
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

CREATE TABLE `Sessions` (
  `SessionID` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `UserID` varchar(8) COLLATE utf8mb4_bin NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `sessions`
--

INSERT INTO `Sessions` (`SessionID`, `UserID`, `Date`) VALUES
('a9eccbe7159df695a15003970aa5a9ad', 'Hj56Tfu4', '2019-05-21 11:04:22'),
('10477d41e89a78fac060cda0fbdd7346', 'Hj56Tfu4', '2019-05-21 12:16:07'),
('d8d7abc6adc91e3eff85bd77df9d95c4', 'ADminADM', '2019-05-22 14:25:46');

-- --------------------------------------------------------

--
-- Table structure for table `shelf`
--

CREATE TABLE `Shelf` (
  `shelfID` int(5) NOT NULL,
  `bookshelf` int(2) NOT NULL,
  `Row` int(2) NOT NULL,
  `Col` int(2) NOT NULL,
  `lowestBookNr` VARCHAR(15) NOT NULL,
  `highestBookNr` VARCHAR(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shelf`
--

INSERT INTO `Shelf` (`bookshelf`, `Row`, `Col`, `lowestBookNr`, `highestBookNr`) VALUES
(1, 1, 5, '003.0 baar', '035.0 nede'),
(1, 2, 5, '048.0 fond', '135.0 mead'),
(1, 3, 5, '153.2 kenn', '213.0 blok'),
(1, 4, 5, '214.3 banc', '222.0 bijb'),
(1, 5, 5, '222.0 offr', '257.2 refo'),
(1, 1, 4, '300.1 mark', '300.6 hand'),
(1, 2, 4, '300.6 onde', '300.6 veal'),
(1, 3, 4, '300.6 verh', '300.6 verh'),
(1, 4, 4, '300.6 verh', '303.4 jage'),
(1, 5, 4, '303.4 wijsm', '305.6 inte'),
(1, 1, 3, '305.8 holz', '307.0 mapp'),
(1, 2, 3, '307.3 kron', '317.5 gaal'),
(1, 3, 3, '317.6 belo', '329.0 klos'),
(1, 4, 3, '329.0 noor', '332.0 hubb'),
(1, 5, 3, '332.0 marr', '340.6 heiz'),
(1, 1, 2, '340.6 kraj', '340.9 buun'),
(1, 2, 2, '340.9 case', '340.9 pinc'),
(1, 3, 2, '340.9 rau', '341.6 bosw'),
(1, 4, 2, '341.6 evan', '345.0 broe'),
(1, 5, 2, '345.0 ewijk', '345.1 prin'),
(1, 1, 1, '345.1 soln', '345.7 onro'),
(1, 2, 1, '345.7 poll', '346.2 bela'),
(1, 3, 1, '346.2 bela', '351.3 luijk'),
(1, 4, 1, '351.3 meer', '354.0 hull'),
(1, 5, 1, '354.0 jage', '358.0 isaa'),
(2, 1, 1, '360.0 sluy', '360.6 heez'),
(2, 1, 2, '360.6 hull', '360.6 welt'),
(2, 1, 3, '360.8 cran', '360.9 vela'),
(2, 1, 4, '361.0 blak', '362.0 bel'),
(2, 1, 5, '362.0 berk', '362.0 dors'),
(2, 2, 1, '362.0 drur', '362.0 heez'),
(2, 2, 2, '362.0 hilt', '362.0 mult'),
(2, 2, 3, '362.0 nibe', '362.0 waal'),
(2, 2, 4, '362.0 wett', '362.0 wytz'),
(2, 2, 5, '362.1 wieb', '363.0 epe'),
(2, 3, 1, '363.0 exte', '364.0 chib'),
(2, 3, 2, '364.0 cbib', '366.0 chas'),
(2, 3, 3, '366.0 daws', '366.1 balo'),
(2, 3, 4, '366.1 berg', '366.1 cano'),
(2, 3, 5, '366.1 cial', '366.1 grit'),
(2, 4, 1, '366.1 hamm', '366.1 lind'),
(2, 4, 2, '366.1 loo', '366.1 pell'),
(2, 4, 3, '366.1 pijl', '366.1 webe'),
(2, 4, 4, '366.1 wegg', '366.2 laud'),
(2, 4, 5, '366.2 laud', '366.3 hoog'),
(2, 5, 1, '366.3 jans', '366.4 blom'),
(2, 5, 2, '366.4 bodd', '366.4 cove'),
(2, 5, 3, '366.4 crea', '366.4 dam'),
(2, 5, 4, '366.5 dam', '366.4 gels'),
(2, 5, 5, '366.4 geor', '366.4 grit');
-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `Users` (
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

INSERT INTO `Users` (`UserID`, `Email`, `Firstname`, `Lastname`, `Password`, `Image`, `Admin`) VALUES
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
ALTER TABLE `Shelf`
  ADD PRIMARY KEY (`shelfID`),
  ADD UNIQUE KEY `shelfID` (`shelfID`),
  ADD UNIQUE KEY `shelfID_2` (`shelfID`);

--
-- Indexes for table `users`
--
ALTER TABLE `Users`
  ADD UNIQUE KEY `UserID` (`UserID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `shelf`
--
ALTER TABLE `Shelf`
  MODIFY `shelfID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
