DROP TABLE `UserCourses`;
DROP TABLE `CourseContents`;
DROP TABLE `Courses`;
DROP TABLE `UserSkills`;
DROP TABLE `Skills`;
DROP TABLE `Users`;


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
	`completed_at` DATETIME NULL,
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

INSERT INTO `Users`
VALUES(1231, "user1@chickchickrun21.com", "password", "2018-11-11 13:23:44", "2018-11-11 18:40:44"),
      (1232, "user2@chickchickrun21.com", "password", "2018-11-12 13:23:44", "2018-11-12 18:40:44"),
			(1233, "user3@chickchickrun21.com", "password", "2018-11-13 13:23:44", "2018-11-13 18:40:44");

INSERT INTO `Skills`
VALUES(1, "Java", "2018-10-29 14:56:59"),
			(2, "Infrastructure", "2018-10-29 14:57:00"),
			(3, "Ruby", "2018-10-29 14:56:50"),
			(4, "Scala", "2018-10-29 14:56:30"),
			(5, "Agile", "2018-10-29 14:56:47");

INSERT INTO `Courses`
VALUES(1, "Kubernetes", "2018-11-11 18:40:44", "2018-11-11 18:40:48", 2, "Kubernetes is a is an open-source system for automating deployment"),
			(2, "Java", "2018-11-11 18:40:44", "2018-11-11 18:40:48", 1, "Java is a programming language"),
			(3, "Agile", "2018-11-11 18:40:44", "2018-11-11 18:40:48", 5, "Agile is a is an iterative approach to project management and software development"),
			(4, "Lean 6 Sigma", "2018-11-11 18:40:44", "2018-11-11 18:40:48", 5, "Is a quality-control methodology"),
			(5, "Terraform", "2018-11-11 18:40:44", "2018-11-11 18:40:48", 2, "Terraform is an open-source infrastructure as code software tool");

INSERT INTO `UserCourses` (`user_id`, `course_id`, `started_at`)
VALUES(1231, 2, "2018-10-29 14:56:00");

INSERT INTO `UserSkills`
VALUES(1231, 4, 1, "2018-11-11 18:40:44");
