CREATE TABLE IF NOT EXISTS `funding_sources` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(50) NOT NULL,
    `created_at` datetime NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` datetime DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `asset_types` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(50) NOT NULL UNIQUE,
    `unit_of_measure` varchar(10),
    `management_type` varchar(10) NOT NULL,
    `created_at` datetime NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` datetime DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS `assets` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `inventory_number` varchar(20) UNIQUE NOT NULL,
    `asset_type_id` int NOT NULL,
    `funding_source_id` int,
    `acquisition_date` datetime NOT NULL,
    `price` decimal(10, 2) NOT NULL,
    `room_code` varchar(5),
    `status` varchar(20) NOT NULL,
    `created_at` datetime NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` datetime DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,

    FOREIGN KEY (funding_source_id) REFERENCES funding_sources(id),
    FOREIGN KEY (asset_type_id) REFERENCES asset_types(id)
    )