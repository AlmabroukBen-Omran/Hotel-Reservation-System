-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2025 at 02:46 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customerID` int(11) NOT NULL,
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `phoneNumber` varchar(32) NOT NULL,
  `QID` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customerID`, `firstname`, `lastname`, `phoneNumber`, `QID`) VALUES
(1, 'Alice', 'Timblewood', '+974 55872209', 'vwsxIQd2Lky/NTyiZWp5Kg=='),
(2, 'Sara', 'Abu Taleb', '+974 77980034', 'Cx/qY40NsAFmK9eg1fcPJw=='),
(3, 'John', 'Smith', '+974 55879012', 'HGlObLaYkYWn3U81FfxDdQ=='),
(4, 'Mohab', 'Ben Omran', '+974 67109287', 'gV1htJydZ+JJEwowajFQSQ=='),
(5, 'Bob', 'Hickory', '+974 33889955', 'AQ/N6eCHr/MiFEuB7MuYsA=='),
(6, 'Robert', 'White', '+974 77409134', 'zESPv0yMkLzO6/tIk9cAMg=='),
(7, 'Hussam', 'Instructor', '+974 12345678', 'gpNwYp0BY9WYhqDukfBSCg==');

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `logID` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `actionType` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`logID`, `username`, `actionType`, `timestamp`) VALUES
(1, '', 'Login Attempt - Failed (User Not Found)', '2025-04-05 05:13:58'),
(2, 'test', 'Login Attempt - Failed (User Not Found)', '2025-04-05 05:14:34'),
(3, 'benomranalmabrouk', 'Login Attempt - Failed (Incorrect Password)', '2025-04-05 05:15:01'),
(4, 'benomranalmabrou', 'Login Attempt - Failed (User Not Found)', '2025-04-05 05:15:25'),
(5, 'benomranalmabrouk', 'Login Successful', '2025-04-05 05:15:42'),
(6, 'benomranalmabrouk', 'Login Successful', '2025-04-05 05:40:43'),
(7, 'benomranalmabrouk', 'Signed out', '2025-04-05 05:41:40'),
(8, 'benomranalmabrouk', 'Login Successful', '2025-04-05 05:42:04'),
(9, 'benomranalmabrouk', 'Registered new user: strongjamal (Role: Receptionist)', '2025-04-05 05:43:40'),
(10, 'benomranalmabrouk', 'Unlocked user account: alhailabdulrahman', '2025-04-05 05:44:15'),
(11, 'benomranalmabrouk', 'Unlocked user account: doesnotexist', '2025-04-05 05:44:32'),
(12, 'benomranalmabrouk', 'Signed out', '2025-04-05 05:44:56'),
(13, 'khalilawwab', 'Login Successful', '2025-04-06 10:43:00'),
(14, 'khalilawwab', 'Checked room availability.', '2025-04-06 10:43:10'),
(15, 'khalilawwab', 'Viewed all customer profiles.', '2025-04-06 10:43:22'),
(16, 'khalilawwab', 'Viewed all reservations.', '2025-04-06 10:43:28'),
(17, 'khalilawwab', 'Signed out.', '2025-04-06 10:43:50'),
(18, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:15:24'),
(19, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:15:24'),
(20, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:15:24'),
(21, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:15:24'),
(22, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:16:01'),
(23, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:16:01'),
(24, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:16:01'),
(25, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:16:01'),
(26, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:16:42'),
(27, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:16:42'),
(28, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:16:42'),
(29, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:16:42'),
(30, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:17:16'),
(31, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:17:16'),
(32, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:17:16'),
(33, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:17:17'),
(34, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:22:25'),
(35, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:22:25'),
(36, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:22:25'),
(37, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:22:25'),
(38, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:24:36'),
(39, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:24:37'),
(40, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:24:37'),
(41, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:24:37'),
(42, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:25:11'),
(43, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:25:11'),
(44, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:25:11'),
(45, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:25:11'),
(46, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:25:37'),
(47, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:25:37'),
(48, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:25:37'),
(49, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:25:37'),
(50, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:26:01'),
(51, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:26:01'),
(52, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:26:01'),
(53, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:26:02'),
(54, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:26:59'),
(55, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:26:59'),
(56, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:26:59'),
(57, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:26:59'),
(58, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:29:28'),
(59, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:29:28'),
(60, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:29:28'),
(61, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:29:28'),
(62, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:30:19'),
(63, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 13:30:19'),
(64, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 13:30:19'),
(65, 'benomranalmabrouk', 'Login Successful', '2025-04-06 13:30:19'),
(66, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 16:00:23'),
(67, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 16:00:24'),
(68, 'benomranalmabrouk', 'Login Attempt - Failed (Incorrect Password)', '2025-04-06 16:00:24'),
(69, 'Security', 'Retrieved failed attempts for user \'benomranalmabrouk\': 1', '2025-04-06 16:00:25'),
(70, 'Security', 'Increased failed attempts for user \'benomranalmabrouk\' to 1', '2025-04-06 16:00:25'),
(71, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 16:01:24'),
(72, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 16:01:24'),
(73, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 16:01:24'),
(74, 'benomranalmabrouk', 'Login Successful', '2025-04-06 16:01:24'),
(75, 'benomranalmabrouk', 'Signed out', '2025-04-06 16:04:47'),
(76, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-06 16:05:01'),
(77, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-06 16:05:01'),
(78, 'khalilawwab', 'Login Attempt - Failed (Incorrect Password)', '2025-04-06 16:05:01'),
(79, 'Security', 'Retrieved failed attempts for user \'khalilawwab\': 1', '2025-04-06 16:05:03'),
(80, 'Security', 'Increased failed attempts for user \'khalilawwab\' to 1', '2025-04-06 16:05:03'),
(81, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-06 16:05:09'),
(82, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-06 16:05:09'),
(83, 'Security', 'Reset failed attempts for user \'khalilawwab\'', '2025-04-06 16:05:09'),
(84, 'khalilawwab', 'Login Successful', '2025-04-06 16:05:09'),
(85, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:05:14'),
(86, 'khalilawwab', 'Viewed all customer profiles.', '2025-04-06 16:05:36'),
(87, 'khalilawwab', 'Opened reservation creation form.', '2025-04-06 16:06:07'),
(88, 'khalilawwab', 'Viewed all customer profiles.', '2025-04-06 16:06:18'),
(89, 'khalilawwab', 'Attempted to create reservation, but some fields were empty.', '2025-04-06 16:06:56'),
(90, 'khalilawwab', 'Attempted to create reservation, but some fields were empty.', '2025-04-06 16:07:02'),
(91, 'khalilawwab', 'Failed to parse customer ID, room number, or deposit amount.', '2025-04-06 16:07:15'),
(92, 'khalilawwab', '\'Created By\' user not found or not a receptionist.', '2025-04-06 16:07:37'),
(93, 'khalilawwab', 'Attempted reservation for non-existent room number: 500', '2025-04-06 16:07:52'),
(94, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:08:51'),
(95, 'khalilawwab', 'Successfully created reservation for customer ID: 4, Room: 100', '2025-04-06 16:09:01'),
(96, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:03'),
(97, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:14'),
(98, 'khalilawwab', 'Successfully loaded 3 reservations from database.', '2025-04-06 16:09:17'),
(99, 'khalilawwab', 'Viewed all reservations.', '2025-04-06 16:09:17'),
(100, 'khalilawwab', 'Successfully deleted reservation with ID: 4', '2025-04-06 16:09:29'),
(101, 'khalilawwab', 'Reservation ID: 4 removed from view.', '2025-04-06 16:09:29'),
(102, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:33'),
(103, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:34'),
(104, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:35'),
(105, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:35'),
(106, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:35'),
(107, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:35'),
(108, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:36'),
(109, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:38'),
(110, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:38'),
(111, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:39'),
(112, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:39'),
(113, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:42'),
(114, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:48'),
(115, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:49'),
(116, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:49'),
(117, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:50'),
(118, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:54'),
(119, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:54'),
(120, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:54'),
(121, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:09:54'),
(122, 'khalilawwab', 'Successfully loaded 2 reservations from database.', '2025-04-06 16:22:10'),
(123, 'khalilawwab', 'Viewed all reservations.', '2025-04-06 16:22:10'),
(124, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-06 16:41:52'),
(125, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-06 16:41:52'),
(126, 'Security', 'Reset failed attempts for user \'khalilawwab\'', '2025-04-06 16:41:52'),
(127, 'khalilawwab', 'Login Successful', '2025-04-06 16:41:52'),
(128, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:41:55'),
(129, 'khalilawwab', 'Successfully loaded 2 reservations from database.', '2025-04-06 16:42:05'),
(130, 'khalilawwab', 'Viewed all reservations.', '2025-04-06 16:42:06'),
(131, 'khalilawwab', 'Successfully loaded 2 reservations from database.', '2025-04-06 16:42:42'),
(132, 'khalilawwab', 'Viewed all reservations.', '2025-04-06 16:42:43'),
(133, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:42:50'),
(134, 'khalilawwab', 'Successfully loaded 2 reservations from database.', '2025-04-06 16:42:51'),
(135, 'khalilawwab', 'Viewed all reservations.', '2025-04-06 16:42:52'),
(136, 'khalilawwab', 'Opened reservation creation form.', '2025-04-06 16:42:54'),
(137, 'khalilawwab', 'Viewed all customer profiles.', '2025-04-06 16:42:58'),
(138, 'khalilawwab', 'Successfully created reservation for customer ID: 6, Room: 100', '2025-04-06 16:43:33'),
(139, 'khalilawwab', 'Successfully loaded 3 reservations from database.', '2025-04-06 16:43:42'),
(140, 'khalilawwab', 'Viewed all reservations.', '2025-04-06 16:43:43'),
(141, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:43:54'),
(142, 'khalilawwab', 'Successfully deleted reservation with ID: 5', '2025-04-06 16:43:57'),
(143, 'khalilawwab', 'Reservation ID: 5 removed from view.', '2025-04-06 16:43:57'),
(144, 'khalilawwab', 'Checked room availability.', '2025-04-06 16:43:58'),
(145, 'khalilawwab', 'Signed out.', '2025-04-06 16:44:13'),
(146, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 16:44:26'),
(147, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 16:44:26'),
(148, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 16:44:26'),
(149, 'benomranalmabrouk', 'Login Successful', '2025-04-06 16:44:26'),
(150, 'benomranalmabrouk', 'Signed out', '2025-04-06 16:44:55'),
(151, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 16:45:36'),
(152, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 16:45:36'),
(153, 'Security', 'Reset failed attempts for user \'alhailabdulrahman\'', '2025-04-06 16:45:36'),
(154, 'alhailabdulrahman', 'Login Successful', '2025-04-06 16:45:36'),
(155, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:45:46'),
(156, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:45:47'),
(157, 'Manager', 'Viewing Feedback Reports.', '2025-04-06 16:46:10'),
(158, 'Manager', 'Loaded 1 Feedback report(s).', '2025-04-06 16:46:10'),
(159, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:50:50'),
(160, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:50:50'),
(161, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:03'),
(162, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:03'),
(163, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:04'),
(164, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:04'),
(165, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:04'),
(166, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:04'),
(167, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:04'),
(168, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:04'),
(169, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:04'),
(170, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:04'),
(171, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:05'),
(172, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:05'),
(173, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:05'),
(174, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:05'),
(175, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:05'),
(176, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:05'),
(177, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:05'),
(178, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:05'),
(179, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:05'),
(180, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:05'),
(181, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:06'),
(182, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:06'),
(183, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:06'),
(184, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:06'),
(185, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:06'),
(186, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:06'),
(187, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:06'),
(188, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:06'),
(189, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:06'),
(190, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:06'),
(191, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:07'),
(192, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:07'),
(193, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:07'),
(194, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:07'),
(195, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:07'),
(196, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:07'),
(197, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:51:07'),
(198, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:51:07'),
(199, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 16:54:00'),
(200, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 16:54:00'),
(201, 'Security', 'Reset failed attempts for user \'alhailabdulrahman\'', '2025-04-06 16:54:00'),
(202, 'alhailabdulrahman', 'Login Successful', '2025-04-06 16:54:00'),
(203, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:54:05'),
(204, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:54:05'),
(205, 'Manager', 'Viewing Feedback Reports.', '2025-04-06 16:54:07'),
(206, 'Manager', 'Loaded 2 Feedback report(s).', '2025-04-06 16:54:07'),
(207, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:54:37'),
(208, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:54:37'),
(209, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:54:37'),
(210, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:54:37'),
(211, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:54:38'),
(212, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:54:38'),
(213, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:54:38'),
(214, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:54:38'),
(215, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 16:54:38'),
(216, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 16:54:38'),
(217, 'alhailabdulrahman', 'Signed out.', '2025-04-06 16:54:44'),
(218, '\' OR 1=1 --', 'Login Attempt - Failed (User Not Found)', '2025-04-06 20:44:24'),
(219, '\' OR 1=1 --', 'Login Attempt - Failed (User Not Found)', '2025-04-06 20:44:49'),
(220, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-06 20:51:28'),
(221, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-06 20:51:28'),
(222, 'Security', 'Reset failed attempts for user \'khalilawwab\'', '2025-04-06 20:51:28'),
(223, 'khalilawwab', 'Login Successful', '2025-04-06 20:51:28'),
(224, 'khalilawwab', 'Opened customer registration form.', '2025-04-06 20:51:32'),
(225, 'khalilawwab', 'Invalid QID entered during registration: 9999999999', '2025-04-06 20:53:11'),
(226, 'khalilawwab', 'Invalid QID entered during registration: 9999999999', '2025-04-06 20:54:40'),
(227, 'khalilawwab', 'Opened reservation creation form.', '2025-04-06 20:55:42'),
(228, 'khalilawwab', 'Attempted to create reservation, but some fields were empty.', '2025-04-06 20:59:00'),
(229, 'khalilawwab', 'Attempted to create reservation, but some fields were empty.', '2025-04-06 20:59:15'),
(230, 'khalilawwab', 'Failed to parse customer ID, room number, or deposit amount.', '2025-04-06 20:59:33'),
(231, 'khalilawwab', 'Failed to parse customer ID, room number, or deposit amount.', '2025-04-06 21:01:05'),
(232, 'khalilawwab', 'Signed out.', '2025-04-06 21:02:20'),
(233, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:02:30'),
(234, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:02:30'),
(235, 'alhailabdulrahman', 'Login Attempt - Failed (Incorrect Password)', '2025-04-06 21:02:30'),
(236, 'Security', 'Retrieved failed attempts for user \'alhailabdulrahman\': 1', '2025-04-06 21:02:31'),
(237, 'Security', 'Increased failed attempts for user \'alhailabdulrahman\' to 1', '2025-04-06 21:02:31'),
(238, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:02:32'),
(239, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:02:32'),
(240, 'alhailabdulrahman', 'Login Attempt - Failed (Incorrect Password)', '2025-04-06 21:02:32'),
(241, 'Security', 'Retrieved failed attempts for user \'alhailabdulrahman\': 2', '2025-04-06 21:02:33'),
(242, 'Security', 'Increased failed attempts for user \'alhailabdulrahman\' to 2', '2025-04-06 21:02:33'),
(243, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:02:33'),
(244, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:02:33'),
(245, 'alhailabdulrahman', 'Login Attempt - Failed (Incorrect Password)', '2025-04-06 21:02:33'),
(246, 'Security', 'Retrieved failed attempts for user \'alhailabdulrahman\': 3', '2025-04-06 21:02:34'),
(247, 'Security', 'Increased failed attempts for user \'alhailabdulrahman\' to 3', '2025-04-06 21:02:34'),
(248, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:02:35'),
(249, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:02:35'),
(250, 'alhailabdulrahman', 'Login Attempt - Failed (Incorrect Password)', '2025-04-06 21:02:35'),
(251, 'Security', 'Retrieved failed attempts for user \'alhailabdulrahman\': 4', '2025-04-06 21:02:36'),
(252, 'Security', 'Increased failed attempts for user \'alhailabdulrahman\' to 4', '2025-04-06 21:02:36'),
(253, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:02:36'),
(254, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:02:36'),
(255, 'alhailabdulrahman', 'Login Attempt - Failed (Incorrect Password)', '2025-04-06 21:02:36'),
(256, 'Security', 'Retrieved failed attempts for user \'alhailabdulrahman\': 5', '2025-04-06 21:02:37'),
(257, 'Security', 'Increased failed attempts for user \'alhailabdulrahman\' to 5', '2025-04-06 21:02:37'),
(258, 'Security', 'Locked account for user \'alhailabdulrahman\'', '2025-04-06 21:02:37'),
(259, 'Security', 'Checked lock status for user \'alhailabdulrahman\': LOCKED', '2025-04-06 21:02:38'),
(260, 'alhailabdulrahman', 'Login Attempt - Failed (Account Locked)', '2025-04-06 21:02:38'),
(261, 'Security', 'Checked lock status for user \'alhailabdulrahman\': LOCKED', '2025-04-06 21:02:39'),
(262, 'alhailabdulrahman', 'Login Attempt - Failed (Account Locked)', '2025-04-06 21:02:39'),
(263, 'Security', 'Checked lock status for user \'alhailabdulrahman\': LOCKED', '2025-04-06 21:03:05'),
(264, 'alhailabdulrahman', 'Login Attempt - Failed (Account Locked)', '2025-04-06 21:03:05'),
(265, 'Security', 'Checked lock status for user \'alhailabdulrahman\': LOCKED', '2025-04-06 21:03:28'),
(266, 'alhailabdulrahman', 'Login Attempt - Failed (Account Locked)', '2025-04-06 21:03:28'),
(267, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:04:00'),
(268, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:04:02'),
(269, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:04:04'),
(270, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:04:05'),
(271, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:04:07'),
(272, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:04:08'),
(273, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:04:10'),
(274, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:04:12'),
(275, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:04:13'),
(276, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:05:53'),
(277, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:05:55'),
(278, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:05:57'),
(279, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:05:59'),
(280, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:06:01'),
(281, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:06:02'),
(282, 'jkvijwbvjw', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:06:06'),
(283, 'Security', 'Checked lock status for user \'alhailabdulrahman\': LOCKED', '2025-04-06 21:07:03'),
(284, 'alhailabdulrahman', 'Login Attempt - Failed (Account Locked)', '2025-04-06 21:07:03'),
(285, 'Security', 'Checked lock status for user \'alhailabdulrahman\': LOCKED', '2025-04-06 21:08:41'),
(286, 'alhailabdulrahman', 'Login Attempt - Failed (Account Locked)', '2025-04-06 21:08:41'),
(287, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 21:10:13'),
(288, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 21:10:13'),
(289, 'benomranalmabrouk', 'Login Attempt - Failed (Incorrect Password)', '2025-04-06 21:10:13'),
(290, 'Security', 'Retrieved failed attempts for user \'benomranalmabrouk\': 1', '2025-04-06 21:10:14'),
(291, 'Security', 'Increased failed attempts for user \'benomranalmabrouk\' to 1', '2025-04-06 21:10:14'),
(292, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 21:10:19'),
(293, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-06 21:10:19'),
(294, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-06 21:10:19'),
(295, 'benomranalmabrouk', 'Login Successful', '2025-04-06 21:10:19'),
(296, 'benomranalmabrouk', 'Unlocked user account: alhailabdulrahman', '2025-04-06 21:10:33'),
(297, 'Security', 'Unlocked account and reset failed attempts for user \'alhailabdulrahman\'', '2025-04-06 21:10:33'),
(298, 'benomranalmabrouk', 'Unlocked user account: viwebibvwbvirw', '2025-04-06 21:10:45'),
(299, 'Security', 'Unlocked account and reset failed attempts for user \'viwebibvwbvirw\'', '2025-04-06 21:10:45'),
(300, 'benomranalmabrouk', 'Signed out', '2025-04-06 21:10:53'),
(301, 'hailabdulrahman', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:11:05'),
(302, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:11:12'),
(303, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-06 21:11:12'),
(304, 'Security', 'Reset failed attempts for user \'alhailabdulrahman\'', '2025-04-06 21:11:12'),
(305, 'alhailabdulrahman', 'Login Successful', '2025-04-06 21:11:12'),
(306, 'Manager', 'Viewing Maintenance Reports.', '2025-04-06 21:11:25'),
(307, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-06 21:11:25'),
(308, 'Manager', 'Viewing Feedback Reports.', '2025-04-06 21:11:27'),
(309, 'Manager', 'Loaded 2 Feedback report(s).', '2025-04-06 21:11:27'),
(310, 'alhailabdulrahman', 'Signed out.', '2025-04-06 21:12:06'),
(311, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:12:11'),
(312, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:12:13'),
(313, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:12:15'),
(314, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:12:16'),
(315, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:12:17'),
(316, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:12:19'),
(317, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:12:20'),
(318, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:02'),
(319, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:05'),
(320, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:10'),
(321, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:13'),
(322, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:15'),
(323, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:15'),
(324, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:15'),
(325, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:16'),
(326, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:16'),
(327, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:16'),
(328, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:17'),
(329, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:17'),
(330, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:17'),
(331, '', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:13:18'),
(332, 'admin\' --', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:15:03'),
(333, 'admin\' --', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:15:24'),
(334, '\' OR IF(1=1, SLEEP(3), 0) --', 'Login Attempt - Failed (User Not Found)', '2025-04-06 21:16:21'),
(335, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:20:37'),
(336, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:20:37'),
(337, 'Security', 'Reset failed attempts for user \'khalilawwab\'', '2025-04-09 22:20:37'),
(338, 'khalilawwab', 'Login Successful', '2025-04-09 22:20:37'),
(339, 'khalilawwab', 'Checked room availability.', '2025-04-09 22:21:02'),
(340, 'khalilawwab', 'Opened customer registration form.', '2025-04-09 22:21:26'),
(341, 'khalilawwab', 'Invalid phone number entered during registration: 10101010', '2025-04-09 22:22:25'),
(342, 'khalilawwab', 'Successfully registered customer: hussam instructor', '2025-04-09 22:22:35'),
(343, 'khalilawwab', 'Viewed all customer profiles.', '2025-04-09 22:22:38'),
(344, 'khalilawwab', 'Opened reservation creation form.', '2025-04-09 22:22:50'),
(345, 'khalilawwab', 'Successfully created reservation for customer ID: 7, Room: 100', '2025-04-09 22:23:31'),
(346, 'khalilawwab', 'Checked room availability.', '2025-04-09 22:23:34'),
(347, 'khalilawwab', 'Successfully loaded 3 reservations from database.', '2025-04-09 22:23:40'),
(348, 'khalilawwab', 'Viewed all reservations.', '2025-04-09 22:23:40'),
(349, 'khalilawwab', 'Successfully deleted reservation with ID: 6', '2025-04-09 22:24:13'),
(350, 'khalilawwab', 'Reservation ID: 6 removed from view.', '2025-04-09 22:24:13'),
(351, 'khalilawwab', 'Checked room availability.', '2025-04-09 22:24:14'),
(352, 'khalilawwab', 'Signed out.', '2025-04-09 22:24:19'),
(353, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:24:56'),
(354, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:24:56'),
(355, 'khalilawwab', 'Login Attempt - Failed (Incorrect Password)', '2025-04-09 22:24:56'),
(356, 'Security', 'Retrieved failed attempts for user \'khalilawwab\': 1', '2025-04-09 22:24:58'),
(357, 'Security', 'Increased failed attempts for user \'khalilawwab\' to 1', '2025-04-09 22:24:58'),
(358, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:25:00'),
(359, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:25:00'),
(360, 'khalilawwab', 'Login Attempt - Failed (Incorrect Password)', '2025-04-09 22:25:00'),
(361, 'Security', 'Retrieved failed attempts for user \'khalilawwab\': 2', '2025-04-09 22:25:02'),
(362, 'Security', 'Increased failed attempts for user \'khalilawwab\' to 2', '2025-04-09 22:25:02'),
(363, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:25:03'),
(364, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:25:03'),
(365, 'khalilawwab', 'Login Attempt - Failed (Incorrect Password)', '2025-04-09 22:25:03'),
(366, 'Security', 'Retrieved failed attempts for user \'khalilawwab\': 3', '2025-04-09 22:25:05'),
(367, 'Security', 'Increased failed attempts for user \'khalilawwab\' to 3', '2025-04-09 22:25:05'),
(368, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:25:17'),
(369, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:25:18'),
(370, 'khalilawwab', 'Login Attempt - Failed (Incorrect Password)', '2025-04-09 22:25:18'),
(371, 'Security', 'Retrieved failed attempts for user \'khalilawwab\': 4', '2025-04-09 22:25:18'),
(372, 'Security', 'Increased failed attempts for user \'khalilawwab\' to 4', '2025-04-09 22:25:18'),
(373, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:25:31'),
(374, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-09 22:25:31'),
(375, 'khalilawwab', 'Login Attempt - Failed (Incorrect Password)', '2025-04-09 22:25:31'),
(376, 'Security', 'Retrieved failed attempts for user \'khalilawwab\': 5', '2025-04-09 22:25:32'),
(377, 'Security', 'Increased failed attempts for user \'khalilawwab\' to 5', '2025-04-09 22:25:32'),
(378, 'Security', 'Locked account for user \'khalilawwab\'', '2025-04-09 22:25:32'),
(379, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-09 22:26:10'),
(380, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-09 22:26:10'),
(381, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-09 22:26:10'),
(382, 'benomranalmabrouk', 'Login Successful', '2025-04-09 22:26:10'),
(383, 'benomranalmabrouk', 'Unlocked user account: khalilawwab', '2025-04-09 22:26:29'),
(384, 'Security', 'Unlocked account and reset failed attempts for user \'khalilawwab\'', '2025-04-09 22:26:29'),
(385, 'benomranalmabrouk', 'Registered new user: hussaminstructor (Role: Manager)', '2025-04-09 22:28:04'),
(386, 'benomranalmabrouk', 'Signed out', '2025-04-09 22:29:13'),
(387, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-09 22:29:23'),
(388, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-09 22:29:23'),
(389, 'alhailabdulrahman', 'Login Attempt - Failed (Incorrect Password)', '2025-04-09 22:29:23'),
(390, 'Security', 'Retrieved failed attempts for user \'alhailabdulrahman\': 1', '2025-04-09 22:29:24'),
(391, 'Security', 'Increased failed attempts for user \'alhailabdulrahman\' to 1', '2025-04-09 22:29:24'),
(392, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-09 22:29:29'),
(393, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-09 22:29:29'),
(394, 'Security', 'Reset failed attempts for user \'alhailabdulrahman\'', '2025-04-09 22:29:29'),
(395, 'alhailabdulrahman', 'Login Successful', '2025-04-09 22:29:29'),
(396, 'Manager', 'Viewing Maintenance Reports.', '2025-04-09 22:29:35'),
(397, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-09 22:29:35'),
(398, 'Manager', 'Viewing Feedback Reports.', '2025-04-09 22:29:48'),
(399, 'Manager', 'Loaded 2 Feedback report(s).', '2025-04-09 22:29:48'),
(400, 'alhailabdulrahman', 'Signed out.', '2025-04-09 22:30:02'),
(401, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:22:55'),
(402, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:22:55'),
(403, 'Security', 'Reset failed attempts for user \'khalilawwab\'', '2025-04-10 15:22:55'),
(404, 'khalilawwab', 'Login Successful', '2025-04-10 15:22:55'),
(405, 'khalilawwab', 'Checked room availability.', '2025-04-10 15:23:10'),
(406, 'khalilawwab', 'Opened customer registration form.', '2025-04-10 15:23:33'),
(407, 'khalilawwab', 'Successfully registered customer: Hussam Instructor', '2025-04-10 15:24:33'),
(408, 'khalilawwab', 'Viewed all customer profiles.', '2025-04-10 15:25:20'),
(409, 'khalilawwab', 'Opened reservation creation form.', '2025-04-10 15:25:43'),
(410, 'khalilawwab', '\'Created By\' user not found or not a receptionist.', '2025-04-10 15:26:56'),
(411, 'khalilawwab', 'Successfully created reservation for customer ID: 7, Room: 100', '2025-04-10 15:27:02'),
(412, 'khalilawwab', 'Checked room availability.', '2025-04-10 15:27:07'),
(413, 'khalilawwab', 'Successfully loaded 3 reservations from database.', '2025-04-10 15:27:16'),
(414, 'khalilawwab', 'Viewed all reservations.', '2025-04-10 15:27:16'),
(415, 'khalilawwab', 'Successfully deleted reservation with ID: 3', '2025-04-10 15:27:39'),
(416, 'khalilawwab', 'Reservation ID: 3 removed from view.', '2025-04-10 15:27:39'),
(417, 'khalilawwab', 'Checked room availability.', '2025-04-10 15:27:43'),
(418, 'khalilawwab', 'Signed out.', '2025-04-10 15:27:57'),
(419, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:33:12'),
(420, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:33:12'),
(421, 'khalilawwab', 'Login Attempt - Failed (Incorrect Password)', '2025-04-10 15:33:12'),
(422, 'Security', 'Retrieved failed attempts for user \'khalilawwab\': 1', '2025-04-10 15:33:13'),
(423, 'Security', 'Increased failed attempts for user \'khalilawwab\' to 1', '2025-04-10 15:33:13'),
(424, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:33:15'),
(425, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:33:15'),
(426, 'khalilawwab', 'Login Attempt - Failed (Incorrect Password)', '2025-04-10 15:33:15'),
(427, 'Security', 'Retrieved failed attempts for user \'khalilawwab\': 2', '2025-04-10 15:33:16'),
(428, 'Security', 'Increased failed attempts for user \'khalilawwab\' to 2', '2025-04-10 15:33:16'),
(429, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:33:17'),
(430, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:33:17'),
(431, 'khalilawwab', 'Login Attempt - Failed (Incorrect Password)', '2025-04-10 15:33:17'),
(432, 'Security', 'Retrieved failed attempts for user \'khalilawwab\': 3', '2025-04-10 15:33:18'),
(433, 'Security', 'Increased failed attempts for user \'khalilawwab\' to 3', '2025-04-10 15:33:18'),
(434, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:33:19'),
(435, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:33:19'),
(436, 'khalilawwab', 'Login Attempt - Failed (Incorrect Password)', '2025-04-10 15:33:19'),
(437, 'Security', 'Retrieved failed attempts for user \'khalilawwab\': 4', '2025-04-10 15:33:20'),
(438, 'Security', 'Increased failed attempts for user \'khalilawwab\' to 4', '2025-04-10 15:33:20'),
(439, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:33:26'),
(440, 'Security', 'Checked lock status for user \'khalilawwab\': UNLOCKED', '2025-04-10 15:33:26'),
(441, 'khalilawwab', 'Login Attempt - Failed (Incorrect Password)', '2025-04-10 15:33:26'),
(442, 'Security', 'Retrieved failed attempts for user \'khalilawwab\': 5', '2025-04-10 15:33:27'),
(443, 'Security', 'Increased failed attempts for user \'khalilawwab\' to 5', '2025-04-10 15:33:27'),
(444, 'Security', 'Locked account for user \'khalilawwab\'', '2025-04-10 15:33:27'),
(445, 'Security', 'Checked lock status for user \'khalilawwab\': LOCKED', '2025-04-10 15:33:29'),
(446, 'khalilawwab', 'Login Attempt - Failed (Account Locked)', '2025-04-10 15:33:29'),
(447, 'Security', 'Checked lock status for user \'khalilawwab\': LOCKED', '2025-04-10 15:33:38'),
(448, 'khalilawwab', 'Login Attempt - Failed (Account Locked)', '2025-04-10 15:33:38'),
(449, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-10 15:34:28'),
(450, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-10 15:34:28'),
(451, 'benomranalmabrouk', 'Login Attempt - Failed (Incorrect Password)', '2025-04-10 15:34:28'),
(452, 'Security', 'Retrieved failed attempts for user \'benomranalmabrouk\': 1', '2025-04-10 15:34:29'),
(453, 'Security', 'Increased failed attempts for user \'benomranalmabrouk\' to 1', '2025-04-10 15:34:29'),
(454, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-10 15:34:35'),
(455, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-10 15:34:35'),
(456, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-10 15:34:35'),
(457, 'benomranalmabrouk', 'Login Successful', '2025-04-10 15:34:35'),
(458, 'benomranalmabrouk', 'Registered new user: teacherhussam (Role: Manager)', '2025-04-10 15:36:32'),
(459, 'benomranalmabrouk', 'Unlocked user account: khalilawwab', '2025-04-10 15:37:22'),
(460, 'Security', 'Unlocked account and reset failed attempts for user \'khalilawwab\'', '2025-04-10 15:37:22'),
(461, 'benomranalmabrouk', 'Signed out', '2025-04-10 15:38:17'),
(462, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-10 15:38:34'),
(463, 'Security', 'Checked lock status for user \'benomranalmabrouk\': UNLOCKED', '2025-04-10 15:38:34'),
(464, 'Security', 'Reset failed attempts for user \'benomranalmabrouk\'', '2025-04-10 15:38:34'),
(465, 'benomranalmabrouk', 'Login Successful', '2025-04-10 15:38:34'),
(466, 'benomranalmabrouk', 'Signed out', '2025-04-10 15:39:10'),
(467, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-10 15:39:24'),
(468, 'Security', 'Checked lock status for user \'alhailabdulrahman\': UNLOCKED', '2025-04-10 15:39:24'),
(469, 'Security', 'Reset failed attempts for user \'alhailabdulrahman\'', '2025-04-10 15:39:24'),
(470, 'alhailabdulrahman', 'Login Successful', '2025-04-10 15:39:24'),
(471, 'Manager', 'Viewing Maintenance Reports.', '2025-04-10 15:39:37'),
(472, 'Manager', 'Loaded 1 Maintenance report(s).', '2025-04-10 15:39:37'),
(473, 'Manager', 'Viewing Feedback Reports.', '2025-04-10 15:41:05'),
(474, 'Manager', 'Loaded 2 Feedback report(s).', '2025-04-10 15:41:05'),
(475, 'Manager', 'Deleted report with ID: 1', '2025-04-10 15:41:22'),
(476, 'alhailabdulrahman', 'Signed out.', '2025-04-10 15:41:32');

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `reportID` int(11) NOT NULL,
  `type` varchar(32) NOT NULL COMMENT 'Report type can either be "Maintenance" or "Feedback"',
  `submittedBy` varchar(32) NOT NULL,
  `creationDateAndTime` datetime NOT NULL DEFAULT current_timestamp(),
  `content` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`reportID`, `type`, `submittedBy`, `creationDateAndTime`, `content`) VALUES
(2, 'Feedback', 'khalilawwab', '2025-04-05 07:23:59', 'Dear Admin Almabrouk Ben Omran,\r\n\r\nWe have received many positive reports due to your swift response to our customers\' internet queries and issues. One customer even offered to buy you a brand-new car for installing more RAM on his laptop, great work!\r\n\r\nTo reward your hard work, dedication, and commitment to upkeeping our great establishment, we will be promoting you to the hotel\'s Chief Technology Officer with a monthly salary of QAR 150,000.\r\n\r\nEnjoy your new yacht!\r\n\r\nBest regards,\r\n\r\nAbdulrahman Alhail.'),
(3, 'Feedback', 'khalilawwab', '2025-04-06 19:49:59', 'Dear Abdulrahman Alhail,\r\n\r\nThe recent VIP customer Hussam Fetyan has decided to rate the hotel system a 9.5/10, The only feedback that we were given is that he did not like the hotel\'s theme.\r\n\r\nI hope you would consider renovating as the VIP Hussam Fetyan is a valuable customer.\r\n\r\nRegards,\r\nAwwab Khalil\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservationID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `roomNumber` int(11) NOT NULL,
  `checkInDate` date NOT NULL,
  `checkOutDate` date NOT NULL,
  `deposit` float NOT NULL,
  `paymentRequired` int(11) NOT NULL COMMENT 'Either QAR 800 or QAR 1500 dpending on whether the room number belongs to a single room or suite.',
  `createdBy` varchar(32) NOT NULL COMMENT 'The receptionist user that created the room reservation'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservationID`, `customerID`, `roomNumber`, `checkInDate`, `checkOutDate`, `deposit`, `paymentRequired`, `createdBy`) VALUES
(1, 2, 103, '2025-03-30', '2025-04-01', 100, 1500, 'khalilawwab'),
(2, 3, 104, '2025-03-30', '2025-03-31', 428, 800, 'khalilawwab');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `roomNumber` int(11) NOT NULL,
  `type` varchar(32) NOT NULL COMMENT 'Room type can only be a Suite or Single Room.',
  `isReserved` tinyint(1) NOT NULL DEFAULT 0,
  `rate` float NOT NULL COMMENT 'Rate is per night.\r\nSuite = QAR 1500\r\nSingle Room = QAR 800'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`roomNumber`, `type`, `isReserved`, `rate`) VALUES
(100, 'Suite', 0, 1500),
(101, 'Single Room', 1, 800),
(102, 'Suite', 0, 1500),
(103, 'Suite', 1, 1500),
(104, 'Single Room', 1, 800),
(105, 'Suite', 1, 1500),
(106, 'Suite', 1, 1500),
(107, 'Suite', 0, 1500),
(108, 'Single Room', 0, 800);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(105) NOT NULL COMMENT 'Format: SALT:HASH',
  `role` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  `phoneNumber` varchar(32) NOT NULL,
  `failedAttempts` tinyint(3) UNSIGNED NOT NULL DEFAULT 0,
  `isLocked` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`firstname`, `lastname`, `username`, `password`, `role`, `email`, `phoneNumber`, `failedAttempts`, `isLocked`) VALUES
('Abdulrahman', 'Al-Hail', 'alhailabdulrahman', '0c8c4e07db38443be9be68a2f2a014e3185e4697:84f47c3c6bbcb6000f9cd012d2c46c30b76a0e22b99ab60beec30aae64b0c74f', 'Manager', 'alhailabdulrahman@gmail.com', '+974 33033018', 0, 0),
('Almabrouk', 'Ben-Omran', 'benomranalmabrouk', '0f56b9cf1e51679308592baa11a5cb456d2181d0:efc91183d89a1ed5abaee6bb39e9ceaff9171d73c070abf9c55ef99f8d4c054e', 'Admin', 'benomranalmabrouk@gmail.com', '+974 33354771', 0, 0),
('Awwab', 'Khalil', 'khalilawwab', '1d48247837c86a862b255fbf9197fc5bc6173832:9f7574fe524a69df4a51fb56f35fbdb3dbe2119225cb2518d37a1f4f6fa73352', 'Receptionist', 'khalilawwab@gmail.com', '+974 51000897', 0, 0),
('Jamal', 'Strong', 'strongjamal', 'a0bf63c2e42889a4d12fef441ecc8376587572cb:d738226d579eeb3af4b948ece839f0a09131ea85f176b332d706257a6abbabdd', 'Receptionist', 'strongjamal@hotel.com', '+974 55667788', 0, 0),
('Hussam', 'Teacher', 'teacherhussam', '6ba8f7bcbe934e5a29105abb1602f1f22c9306b3:26069926ab3d08cd67270293b341e60827d9a30526de46c68f445ec3ecdac8f7', 'Manager', 'hussam.teacher@example.com', '+974 77890065', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customerID`),
  ADD UNIQUE KEY `QID` (`QID`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`logID`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`reportID`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservationID`),
  ADD UNIQUE KEY `customerID` (`customerID`),
  ADD UNIQUE KEY `roomNumber` (`roomNumber`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`roomNumber`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `logID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=477;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `reportID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
