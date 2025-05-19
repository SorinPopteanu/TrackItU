CREATE TABLE IF NOT EXISTS `rooms` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `room_code` varchar(5) UNIQUE NOT NULL,
    `name` varchar(50),
    `created_at` datetime NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` datetime DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `room_professor` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `room_id` int NOT NULL,
    `professor_id` varchar(5) NOT NULL,
    `created_at` datetime NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` datetime DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,

    FOREIGN KEY (room_id) REFERENCES rooms(id)
);

CREATE TABLE IF NOT EXISTS `bookings` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `room_id` varchar(5) NOT NULL,
    `customer_id` int NOT NULL,
    `date` datetime NOT NULL,
    `start_time` datetime NOT NULL,
    `end_time` datetime NOT NULL,
    `status` varchar(20) NOT NULL,
    `created_at` datetime NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` datetime DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,

    FOREIGN KEY (room_id) REFERENCES rooms(id)
);