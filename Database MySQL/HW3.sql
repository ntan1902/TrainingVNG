DROP DATABASE IF EXISTS `hw3`;
CREATE DATABASE `hw3`;

USE `hw3`;

DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees`(
	`id` VARCHAR(50),
    `department_name` VARCHAR(50),
    `employee_name` VARCHAR(50),
    PRIMARY KEY(`id`)
);


DROP TABLE IF EXISTS `salaries`;
CREATE TABLE `salaries`(
	`employee_id` VARCHAR(50),
    `salary` FLOAT,
    PRIMARY KEY(`employee_id`),
    FOREIGN KEY(`employee_id`) REFERENCES `employees`(`id`)
);

INSERT INTO `employees` VALUES 
('vng-123', 'Tech', 'nambd2'),
('vng-124', 'Tech', 'annt12'),
('vng-211', 'FA', 'phucnh'),
('vng-212', 'FA', 'vntrong14'),
('vng-556', 'HR', 'nhank'),
('vng-235', 'Marketing', 'khoinn'),
('vng-236', 'Marketing', 'binhbt');

INSERT INTO `salaries` VALUES
('vng-123', 300),
('vng-124', 400),
('vng-211', 500),
('vng-212', 400),
('vng-556', 600),
('vng-235', 1200),
('vng-236', 200);

-- Print every department 
-- where the average salary per employee is lower than $500!.
SELECT emp.department_name, AVG(sal.salary) as 'Average salary'
FROM `employees` emp
JOIN `salaries` sal ON emp.id = sal.employee_id
GROUP BY emp.department_name
HAVING AVG(sal.salary) < 500;