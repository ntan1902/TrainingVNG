DROP DATABASE IF EXISTS `hw2`;
CREATE DATABASE `hw2`;

USE `hw2`;

DROP TABLE IF EXISTS `authors`;
CREATE TABLE `authors`(
	`author_name` VARCHAR(50),
    `book_name` VARCHAR(50)
);

DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`(
	`book_name` VARCHAR(50),
    `sold_copies` INTEGER
);

INSERT INTO `authors` VALUES
('Jackson', 'Clean Code'),
('Jackson', 'SOLID'),
('David', 'OOP'),
('Brian', 'Technical Skills'),
('Codore', 'Excel'), 
('Codore', 'Soft Skills');

INSERT INTO `books` VALUES
('Clean Code', 1500), 
('SOLID', 800),
('Excel', 700),
('OOP', 2000),
('Technical Skills', 125),
('Soft Skills', 60);

-- The TOP 3 authors who sold the most books in total! 
SELECT auth.author_name, SUM(b.sold_copies)
FROM `authors` auth 
JOIN `books` b ON auth.book_name = b.book_name
GROUP BY auth.author_name
ORDER BY SUM(b.sold_copies) DESC
LIMIT 3;