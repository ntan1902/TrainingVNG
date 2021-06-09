DROP DATABASE IF EXISTS `hw1`;
CREATE DATABASE `hw1`;
USE `hw1`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`(
	`id` INTEGER AUTO_INCREMENT,
    `name` VARCHAR(50),
    `email` VARCHAR(50),
	PRIMARY KEY(`id`)
);

INSERT INTO users(`name`, `email`) VALUES
('an', 'ntan@gmail.com'),
('thanh', 'htthanh@gmail.com'),
('nguyen', 'ohnguyen@gmail.com'),
('trong', 'hntrong@gmail.com'),
('an', 'ntan@gmail.com'),
('trong', 'hntrong@gmail.com'),
('an', 'ntan@gmail.com');

-- The duplicate row count (name, email) in the table. 
SELECT `name`, `email`, count(*) AS `Duplicate Count`
FROM users 
GROUP BY `name`, `email`
HAVING COUNT(*) > 1;