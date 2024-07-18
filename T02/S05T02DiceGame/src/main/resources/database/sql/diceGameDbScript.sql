-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema diceGamedb
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `diceGamedb` DEFAULT CHARACTER SET utf8 ;
USE `diceGamedb` ;

-- -----------------------------------------------------
-- Table `diceGamedb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diceGamedb`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `registration_date` DATE NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table `diceGamedb`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diceGamedb`.`player` (
  `player_id` INT NOT NULL AUTO_INCREMENT,
  `player_name` VARCHAR(45) NOT NULL,
  `win_rate` INT DEFAULT NULL,
  `creation_date` DATE NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`player_id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `diceGamedb`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table `diceGamedb`.`ranking`
-- -----------------------------------------------------
CREATE TABLE `diceGamedb`.`ranking`
SELECT win_rate, player_id, player_name, 
DENSE_RANK () OVER (
ORDER BY win_rate
) AS Rank_no 
FROM player;


ALTER TABLE `diceGamedb`.`ranking` 
ADD INDEX `name_player_idx`  (`player_name` ASC) VISIBLE,
ADD INDEX `Rank_no_idx` (`Rank_no` ASC) VISIBLE;

ALTER TABLE `diceGamedb`.`ranking` 
ADD CONSTRAINT `player_id`
  FOREIGN KEY (`player_id`)
  REFERENCES `diceGamedb`.`player` (`player_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;