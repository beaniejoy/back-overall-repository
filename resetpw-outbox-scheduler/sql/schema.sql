DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `reset_pw_key`;

CREATE TABLE `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `user_name` varchar(50)  NOT NULL,
    `email` varchar(50) NOT NULL,
    `password` varchar(500) NOT NULL,
    `address` varchar(100) NOT NULL,
    `phone_number` varchar(11) NOT NULL,
    `role_type` varchar(20) NOT NULL,
    `salt` varchar(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `reset_pw_key` (
    `id` int NOT NULL AUTO_INCREMENT,
    `reset_key` varchar(40) NOT NULL,
    `expire_date` datetime NOT NULL,
    `email` varchar(50) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `ix_reset_key` (`reset_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
