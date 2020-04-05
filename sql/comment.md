CREATE TABLE `comment` (
  `comment_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `comment_name` varchar(6) NOT NULL,
  `comment_context` mediumtext NOT NULL,
  `comment_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;