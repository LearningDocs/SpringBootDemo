/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : article

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-07-08 23:04:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article_tag
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `article_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  KEY `FK5w1ff6or3kcmyalvlaco3yfjy` (`tag_id`),
  KEY `FKra3mh0ablclqyr8426rq9kwry` (`article_id`),
  CONSTRAINT `FK5w1ff6or3kcmyalvlaco3yfjy` FOREIGN KEY (`tag_id`) REFERENCES `t_tag` (`id`),
  CONSTRAINT `FKra3mh0ablclqyr8426rq9kwry` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `count` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
