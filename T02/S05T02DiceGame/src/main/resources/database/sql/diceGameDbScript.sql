-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema diceGamedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema diceGamedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `diceGamedb` DEFAULT CHARACTER SET utf8mb3 ;
USE `diceGamedb` ;

-- -----------------------------------------------------
-- Table `diceGamedb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diceGamedb`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `registration_date` DATETIME(6) NULL DEFAULT NULL,
  `role` ENUM('ADMIN', 'NO_ROLE', 'USER') NOT NULL,
  `user_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `diceGamedb`.`players`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diceGamedb`.`players` (
  `player_id` INT NOT NULL AUTO_INCREMENT,
  `creation_date` DATETIME(6) NULL DEFAULT NULL,
  `player_name` VARCHAR(255) NULL DEFAULT NULL,
  `win_rate` DOUBLE NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`player_id`),
  INDEX `FK3rfv9832bif6rea5edetib8it` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK3rfv9832bif6rea5edetib8it`
    FOREIGN KEY (`user_id`)
    REFERENCES `diceGamedb`.`users` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 55
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `diceGamedb`.`users_players`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diceGamedb`.`users_players` (
  `user_user_id` INT NOT NULL,
  `players_player_id` INT NOT NULL,
  UNIQUE INDEX `UKlw7bh6f34qr2fucwobbegbck5` (`players_player_id` ASC) VISIBLE,
  INDEX `FK842nuow3cwbsns8w0636lwugj` (`user_user_id` ASC) VISIBLE,
  CONSTRAINT `FK842nuow3cwbsns8w0636lwugj`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `diceGamedb`.`users` (`user_id`),
  CONSTRAINT `FKq44jpc6e6ib7q05mo03udi3og`
    FOREIGN KEY (`players_player_id`)
    REFERENCES `diceGamedb`.`players` (`player_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
