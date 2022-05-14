-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 14, 2022 at 08:28 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `egsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `interrupts`
--

CREATE TABLE `interrupts` (
  `interruptID` int(11) NOT NULL,
  `interruptArea` varchar(100) NOT NULL,
  `interruptStartTime` varchar(100) NOT NULL,
  `interruptEndTime` varchar(100) NOT NULL,
  `interruptHours` varchar(100) NOT NULL,
  `interruptMessage` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `interrupts`
--

INSERT INTO `interrupts` (`interruptID`, `interruptArea`, `interruptStartTime`, `interruptEndTime`, `interruptHours`, `interruptMessage`) VALUES
(4, 'Kalutara', '10:30 a.m', '13:30 p.m', '3 hours', 'Due to less amount of water'),
(5, 'Matara', '08:30 a.m', '05:30 p.m', '7 hours', 'Due to an essencial work in your area'),
(13, 'Polonnaruwa', '08:00a.m', '02:00 p.m', '6 hours', 'Due to less amount of deisel'),
(22, 'Galle', '10:00 a.m', '13:00 p.m', '2 Hours', 'Due to less amount of diesel');

-- --------------------------------------------------------

--
-- Table structure for table `logins`
--

CREATE TABLE `logins` (
  `log_uname` varchar(50) NOT NULL,
  `log_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `logins`
--

INSERT INTO `logins` (`log_uname`, `log_password`) VALUES
('paramee', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `interrupts`
--
ALTER TABLE `interrupts`
  ADD PRIMARY KEY (`interruptID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `interrupts`
--
ALTER TABLE `interrupts`
  MODIFY `interruptID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
