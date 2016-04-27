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
-- Table structure for table `grocerylist1`
--

CREATE TABLE `grocerylist1` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `per` varchar(11) NOT NULL,
  `comment` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grocerylist1`
--

INSERT INTO `grocerylist1` (`id`, `name`, `price`, `per`, `comment`) VALUES
(1, 'green apple', 1, 'kg', 'they are very tasty'),
(2, 'red apple', 1, 'kg', 'cheaper than in other markets'),
(3, 'milk', 1, 'litre', 'good quality'),
(4, 'chocolate', 2, 'pack', 'high quality'),
(5, 'Bagles', 2, 'pack', 'very tasty'),
(6, 'Baguette/French Bread', 3, 'pack', 'good price'),
(7, 'Bread', 2, 'pack', 'good taste'),
(8, 'Cereal M?sli style', 3, 'pack', 'very tasty'),
(9, 'Cocoa Rice Cereal', 2, 'pack', 'good price'),
(10, 'Corn Flakes', 3, 'pack', 'good taste'),
(11, 'Croissants', 2, 'pack', 'very tasty'),
(12, 'Granola Bars regular', 3, 'pack', 'good price'),
(13, 'Honey Crunch Oats', 2, 'pack', 'good taste'),
(14, 'Hot Dog Buns', 4, 'pack', 'very tasty'),
(15, 'Oat Meal', 5, 'pack', 'good price'),
(16, 'White Bread', 1, 'pack', 'good taste'),
(17, 'Breakfast Sausage', 2, 'pack', 'very tasty'),
(18, 'Chicken Breasts', 5, 'pack', 'good price'),
(19, 'Chicken Nuggets', 2, 'pack', 'good taste'),
(20, 'Chicken Tenderloins', 1, 'pack', 'very tasty'),
(21, 'Fish Flounder', 5, 'pack', 'good price'),
(22, 'Fish Mahi Mahi ', 2, 'pack', 'good taste'),
(23, 'Fish Tilapia ', 3, 'pack', 'very tasty'),
(24, 'French Fries ', 2, 'pack', 'good price'),
(25, 'Ground Beef ', 4, 'pack', 'good taste'),
(26, 'Ground Turkey ', 1, 'pack', 'very tasty'),
(27, 'Ice Cream premium', 1, 'pack', 'good price'),
(28, 'Ice Cream simple', 3, 'pack', 'good taste'),
(29, 'Pizza', 4, 'pack', 'very tasty'),
(30, 'Veggies ', 5, 'pack', 'good price'),
(31, 'Waffles ', 1, 'pack', 'good taste'),
(32, 'Aluminum Foil', 2, 'pack', 'very tasty'),
(33, 'Paper Towles', 3, 'pack', 'good price'),
(34, 'Plastic Wrap', 5, 'pack', 'good taste'),
(35, 'Apples Gala bag', 3, 'pack', 'very tasty'),
(36, 'Carrots whole', 7, 'pack', 'good price'),
(37, 'Grapes red', 5, 'kg', 'good taste'),
(38, 'Lemons', 4, 'kg', 'very tasty'),
(39, 'Lettuce iceberg', 3, 'kg', 'good price'),
(40, 'Mushrooms whole', 1, 'kg', 'good taste'),
(41, 'Onions yellow', 3, 'kg', 'very tasty'),
(42, 'Oranges', 4, 'kg', 'good price'),
(43, 'Pears', 6, 'kg', 'good taste'),
(44, 'Potatoes gold (Yukon)', 7, 'kg', 'very tasty'),
(45, 'Potatoes red', 1, 'kg', 'good price'),
(46, 'Potatoes Russet', 2, 'kg', 'good taste'),
(47, 'Tomatoes', 3, 'kg', 'very tasty'),
(48, 'Bacon', 4, 'pack', 'good price'),
(49, 'Beef Stew Meat', 5, 'pack', 'good taste'),
(50, 'Brats 4-6 pack', 6, 'pack', 'very tasty'),
(51, 'Butter real unsalted/salted', 1, 'pack', 'good price'),
(52, 'Cheese Singles (imitation)', 2, 'pack', 'good taste'),
(53, 'Cheese block (regular)', 1, 'pack', 'very tasty'),
(54, 'Cheese Boursin', 6, 'pack', 'good price'),
(55, 'Cheese Brie', 5, 'pack', 'good taste'),
(56, 'Cheese sliced deli', 4, 'pack', 'very tasty'),
(57, 'Cheese wedge (real deli)', 1, 'pack', 'good price'),
(58, 'Chocolate Chip Cookie Dough', 2, 'pack', 'good taste'),
(59, 'Cold Cuts ham or chicken', 4, 'pack', 'very tasty'),
(60, 'Cold Cuts turkey variety', 3, 'pack', 'good price'),
(61, 'Cream, heavy whipping', 2, 'pack', 'good taste'),
(62, 'Eggs, cage free', 1, 'pack', 'very tasty'),
(63, 'Eggs, regular', 2, 'pack', 'good price'),
(64, 'Ground Beef (chuck), fresh', 3, 'pack', 'good taste'),
(65, 'Ground Beef (sirloin), fresh', 4, 'pack', 'very tasty'),
(66, 'Ground Turkey, fresh', 1, 'pack', 'good price'),
(67, 'Ham, half', 2, 'pack', 'good taste'),
(68, 'Kielbasa', 3, 'pack', 'very tasty'),
(69, 'Margarine/Spread', 4, 'pack', 'good price'),
(70, 'Pancetta, diced', 1, 'pack', 'good taste'),
(71, 'Pizza Dough', 3, 'pack', 'very tasty'),
(72, 'Pork Tenderloin, small', 1, 'pack', 'good price'),
(73, 'Ribs, Baby Back ', 2, 'pack', 'good taste'),
(74, 'Ribs, Spare Back ', 3, 'pack', 'very tasty'),
(75, 'Salami, sliced', 4, 'pack', 'good price'),
(76, 'Salami, whole', 5, 'pack', 'good taste'),
(77, 'Sour Cream', 1, 'pack', 'very tasty'),
(78, 'Beer, bottle, pilsener, import', 2, 'pack', 'good price'),
(79, 'Beer, can', 4, 'pack', 'good taste'),
(80, 'Coffee', 5, 'pack', 'very tasty'),
(81, 'Milk', 6, 'pack', 'good price'),
(82, 'Milk, organic', 1, 'pack', 'good taste'),
(83, 'Orange Juice', 2, 'pack', 'very tasty'),
(84, 'Soda, bottle', 3, 'pack', 'good price'),
(85, 'Soda, bottle ', 1, 'pack', 'good taste'),
(86, 'Soda, can', 6, 'pack', 'very tasty'),
(87, 'Tea, green, bags', 2, 'pack', 'good price'),
(88, 'Water regular', 3, 'pack', 'good taste'),
(89, 'Water Sparkling', 4, 'pack', 'very tasty'),
(90, 'Wine Cabernet', 1, 'pack', 'good price'),
(91, 'Wine Chardonnay', 5, 'pack', 'good taste'),
(92, 'Wine White Zinfandel', 3, 'pack', 'very tasty');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `grocerylist1`
--
ALTER TABLE `grocerylist1`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `grocerylist1`
--
ALTER TABLE `grocerylist1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
