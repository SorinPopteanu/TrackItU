CREATE TABLE IF NOT EXISTS`customer` (
    `customer_id` int AUTO_INCREMENT PRIMARY KEY,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `created_at` datetime NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` datetime DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `accounts` (
    `customer_id` int NOT NULL,
    `account_number` int AUTO_INCREMENT PRIMARY KEY,
    `account_type` varchar(100) NOT NULL,
    `status` varchar(15) NOT NULL,
    `created_at` datetime NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` datetime DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);