-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2016 at 12:53 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uniproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `grocerylist2`
--

CREATE TABLE `grocerylist2` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `per` varchar(5) NOT NULL,
  `comment` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grocerylist2`
--

INSERT INTO `grocerylist2` (`id`, `name`, `price`, `per`, `comment`) VALUES
(1, 'oranges', 1, 'kg', 'healthy'),
(2, 'tea', 1, 'pack', 'nice tea'),
(3, 'coffee', 1, 'kg', 'coffee'),
(4, 'Asparagus', 1, 'kg', 'good price'),
(5, 'Broccoli', 2, 'kg', 'premium quality'),
(6, 'Carrots', 5, 'kg', 'good taste'),
(7, 'Cauliflower', 3, 'kg', 'good price'),
(8, 'Celery', 2, 'kg', 'premium quality'),
(9, 'Corn', 1, 'kg', 'good taste'),
(10, 'Cucumbers', 2, 'kg', 'good price'),
(11, 'Lettuce', 3, 'kg', 'premium quality'),
(12, 'Mushrooms', 4, 'kg', 'good taste'),
(13, 'Onions', 1, 'kg', 'good price'),
(14, 'Peppers', 2, 'kg', 'premium quality'),
(15, 'Potatoes', 4, 'kg', 'good taste'),
(16, 'Spinach', 2, 'kg', 'good price'),
(17, 'Squash', 5, 'kg', 'premium quality'),
(18, 'Zucchini', 1, 'kg', 'good taste'),
(19, 'Tomatoes', 2, 'kg', 'good price'),
(20, 'Breakfasts', 5, 'pack', 'premium quality'),
(21, 'Burritos', 1, 'pack', 'good taste'),
(22, 'Fish sticks', 4, 'pack', 'good price'),
(23, 'Fries / Tater tots', 2, 'pack', 'premium quality'),
(24, 'Ice cream / Sorbet', 6, 'pack', 'good taste'),
(25, 'Juice concentrate', 2, 'pack', 'good price'),
(26, 'Pizza', 1, 'pack', 'premium quality'),
(27, 'Pizza Rolls', 2, 'pack', 'good taste'),
(28, 'Popsicles', 3, 'pack', 'good price'),
(29, 'TV dinners', 6, 'pack', 'premium quality'),
(30, 'Vegetables', 1, 'pack', 'good taste'),
(31, 'Beer', 2, 'pack', 'good price'),
(32, 'Club soda / Tonic', 4, 'pack', 'premium quality'),
(33, 'Champagne', 5, 'bottl', 'good taste'),
(34, 'Gin', 1, 'bottl', 'good price'),
(35, 'Juice', 2, 'bottl', 'premium quality'),
(36, 'Mixers', 5, 'bottl', 'good taste'),
(37, 'Red wine / White wine', 5, 'bottl', 'good price'),
(38, 'Rum', 5, 'bottl', 'premium quality'),
(39, 'Sak?', 5, 'bottl', 'good taste'),
(40, 'Soda pop', 2, 'bottl', 'good price'),
(41, 'Sports drink', 2, 'bottl', 'premium quality'),
(42, 'Whiskey', 10, 'bottl', 'good taste'),
(43, 'Vodka', 10, 'bottl', 'good price'),
(44, 'Antiperspirant / Deodorant', 5, 'pack', 'premium quality'),
(45, 'Bath soap', 4, 'pack', 'good taste'),
(46, 'Cosmetics', 10, 'pack', 'good price'),
(47, 'Cotton swabs', 1, 'pack', 'premium quality'),
(48, 'Facial cleanser', 2, 'pack', 'good taste'),
(49, 'Facial tissue', 3, 'pack', 'good price'),
(50, 'Feminine products', 4, 'pack', 'premium quality'),
(51, 'Floss', 5, 'pack', 'good taste'),
(52, 'Hair gel', 1, 'pack', 'good price'),
(53, 'Lip balm', 2, 'pack', 'premium quality'),
(54, 'Moisturizing lotion', 3, 'pack', 'good taste'),
(55, 'Mouthwash', 4, 'pack', 'good price'),
(56, 'Razors', 5, 'pack', 'premium quality'),
(57, 'Shampoo', 1, 'pack', 'good taste'),
(58, 'Sunblock', 5, 'pack', 'good price'),
(59, 'Toilet paper', 1, 'pack', 'premium quality'),
(60, 'Toothpaste', 2, 'pack', 'good taste'),
(61, 'Vitamins / Supplements', 4, 'pack', 'good price'),
(62, 'Shaving cream', 1, 'pack', 'premium quality'),
(63, 'Conditioner', 5, 'pack', 'good taste'),
(64, ' Supplements', 1, 'pack', 'good price'),
(65, 'Hand soap', 2, 'pack', 'premium quality'),
(66, ' Air freshener', 3, 'pack', 'good taste'),
(67, 'Bathroom cleaner', 4, 'pack', 'good price'),
(68, 'Bleach / Detergent', 1, 'pack', 'premium quality'),
(69, ' Garbage bags', 6, 'pack', 'good taste'),
(70, 'Glass cleaner', 1, 'pack', 'good price'),
(71, 'Mop head', 2, 'pack', 'premium quality'),
(72, 'Sponges', 4, 'pack', 'good taste'),
(73, 'Vacuum bags', 1, 'pack', 'good price');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `grocerylist2`
--
ALTER TABLE `grocerylist2`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `grocerylist2`
--
ALTER TABLE `grocerylist2`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
