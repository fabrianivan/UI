-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 08, 2016 at 01:34 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kasir_restoran`
--

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `no_pembelian` int(5) NOT NULL,
  `barang` varchar(25) NOT NULL,
  `jumlah` int(20) NOT NULL,
  `satuan` int(20) NOT NULL,
  `harga` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `code` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `price` int(12) NOT NULL,
  `stock` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`code`, `name`, `price`, `stock`) VALUES
('1', 'Nasi Goreng', 19000, 300),
('2', 'Ayam Bakar', 19000, 330);

-- --------------------------------------------------------

--
-- Table structure for table `order_menu`
--

CREATE TABLE `order_menu` (
  `id` int(100) NOT NULL,
  `id_user` int(10) NOT NULL,
  `menu` varchar(20) NOT NULL,
  `price` int(12) NOT NULL,
  `quantity` int(6) NOT NULL,
  `total` int(11) NOT NULL,
  `completed` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_menu`
--

INSERT INTO `order_menu` (`id`, `id_user`, `menu`, `price`, `quantity`, `total`, `completed`) VALUES
(1, 1, 'Nasi Goreng', 19000, 5, 95000, 0),
(2, 1, 'Ayam Bakar', 19000, 5, 95000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `fullname` varchar(25) NOT NULL,
  `gender` varchar(5) NOT NULL,
  `address` varchar(40) NOT NULL,
  `phone` int(15) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `fullname`, `gender`, `address`, `phone`, `username`, `password`) VALUES
(1, 'Deren', 'Male', 'Jalan Maram', 9200090, 'deren', 'ldkpadk'),
(2, 'Kendi', 'Male', 'Jalan S Parman 1', 90931131, 'kendi.unyu', 'kokpfkad'),
(3, 'lmmqljlj', 'Male', 'i0i00', 909093, '1212121', '009021'),
(4, 'rqrr', 'rwrw', '5', 6, '7', '8'),
(5, 'dwjlfwkw', 'lkk9', '9', 9, '9', '9'),
(6, '1313', '1331', '3131', 1313, '133131', '1331');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`no_pembelian`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `order_menu`
--
ALTER TABLE `order_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `fullname` (`fullname`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
