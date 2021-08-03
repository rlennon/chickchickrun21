--CREATE DATABASE ccr;

--USE ccr;

-- DROP TABLE `UserCourses`;
-- DROP TABLE `CourseContents`;
-- DROP TABLE `Courses`;
-- DROP TABLE `UserSkills`;
-- DROP TABLE `Skills`;
-- DROP TABLE `Users`;

CREATE TABLE `Users` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(255) NOT NULL,
	`password_hash` VARCHAR(255) NOT NULL,
	`created_at` DATETIME,
	`email_verified_at` DATETIME NOT NULL,
	PRIMARY KEY (`id`,`email`)
);

CREATE TABLE `Skills` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL UNIQUE,
	`created_at` DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Courses` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255),
	`created_at` DATETIME NOT NULL,
	`updated_at` DATETIME NOT NULL,
	`skill_id` INT NOT NULL,
	`description` TEXT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `UserCourses` (
	`user_id` INT NOT NULL,
	`course_id` INT NOT NULL,
	`started_at` DATETIME NOT NULL,
	`completed_at` DATETIME NOT NULL,
	PRIMARY KEY (`user_id`,`course_id`)
);

CREATE TABLE `UserSkills` (
	`user_id` INT NOT NULL,
	`skill_id` INT NOT NULL,
	`value` INT NOT NULL,
	`created_at` DATETIME NOT NULL,
	PRIMARY KEY (`user_id`,`skill_id`)
);

CREATE TABLE `CourseContents` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`course_id` INT NOT NULL,
	`page_no` INT NOT NULL,
	`title` VARCHAR(255) NOT NULL,
	`content` TEXT NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Courses` ADD CONSTRAINT `Courses_fk0` FOREIGN KEY (`skill_id`) REFERENCES `Skills`(`id`);

ALTER TABLE `UserCourses` ADD CONSTRAINT `UserCourses_fk0` FOREIGN KEY (`user_id`) REFERENCES `Users`(`id`);

ALTER TABLE `UserCourses` ADD CONSTRAINT `UserCourses_fk1` FOREIGN KEY (`course_id`) REFERENCES `Courses`(`id`);

ALTER TABLE `UserSkills` ADD CONSTRAINT `UserSkills_fk0` FOREIGN KEY (`user_id`) REFERENCES `Users`(`id`);

ALTER TABLE `UserSkills` ADD CONSTRAINT `UserSkills_fk1` FOREIGN KEY (`skill_id`) REFERENCES `Skills`(`id`);

ALTER TABLE `CourseContents` ADD CONSTRAINT `CourseContents_fk0` FOREIGN KEY (`course_id`) REFERENCES `Courses`(`id`);

