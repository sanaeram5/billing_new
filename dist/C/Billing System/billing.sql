-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2021 at 03:40 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `billing`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `invoiceno` int(100) DEFAULT NULL,
  `invoicedate` date DEFAULT NULL,
  `articleno` varchar(10) DEFAULT NULL,
  `brandname` varchar(30) DEFAULT NULL,
  `eanno` varchar(30) DEFAULT NULL,
  `sz` varchar(10) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `salespersonusername` varchar(30) DEFAULT NULL,
  `mrp` decimal(10,2) DEFAULT NULL,
  `sellingprice` decimal(10,2) DEFAULT NULL,
  `discount` decimal(5,3) DEFAULT NULL,
  `payment` varchar(10) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `damage`
--

CREATE TABLE `damage` (
  `articleno` varchar(10) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `sz` varchar(10) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `reason` varchar(500) DEFAULT NULL,
  `damagedate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `masters`
--

CREATE TABLE `masters` (
  `gender` varchar(10) DEFAULT NULL,
  `sz` varchar(10) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `gst` int(5) DEFAULT NULL,
  `brandname` varchar(30) DEFAULT NULL,
  `suppliername` varchar(30) DEFAULT NULL,
  `suppliercode` varchar(10) DEFAULT NULL,
  `gstno` varchar(10) DEFAULT NULL,
  `add1` varchar(50) DEFAULT NULL,
  `add2` varchar(50) DEFAULT NULL,
  `contact` varchar(15) DEFAULT NULL,
  `salespersonusername` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `random`
--

CREATE TABLE `random` (
  `nm` varchar(10) DEFAULT NULL,
  `phn` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `salesperson`
--

CREATE TABLE `salesperson` (
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `salesperson`
--

INSERT INTO `salesperson` (`username`, `password`) VALUES
('abc', 'xyz'),
('abc', 'xyz'),
('snaz', 'pass'),
('shazz', 'root');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `articleno` varchar(10) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `sz` varchar(10) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `gst` int(5) DEFAULT NULL,
  `hsncode` varchar(10) DEFAULT NULL,
  `costprice` decimal(10,2) DEFAULT NULL,
  `mrp` decimal(10,2) DEFAULT NULL,
  `sellingprice` decimal(10,2) DEFAULT NULL,
  `discount` decimal(5,2) DEFAULT NULL,
  `suppliername` varchar(30) DEFAULT NULL,
  `brandname` varchar(30) DEFAULT NULL,
  `eancode` varchar(10) DEFAULT NULL,
  `stockdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `stockreturn`
--

CREATE TABLE `stockreturn` (
  `articleno` varchar(10) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `sz` varchar(10) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `reason` varchar(500) DEFAULT NULL,
  `eancode` varchar(20) DEFAULT NULL,
  `returndate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
