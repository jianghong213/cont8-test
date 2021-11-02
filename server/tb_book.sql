CREATE TABLE `tb_book` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `book_name` VARCHAR(20) NOT NULL COMMENT '书名',
  `date` VARCHAR(20) NOT NULL COMMENT '时间',
  `type` VARCHAR(30) DEFAULT NULL COMMENT '类型',
,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
INSERT INTO `tb_book` (`id`, `book_name`, `date`, `type`) VALUES ('1', 'sadsa','2020', 'novel');
INSERT INTO `tb_book` (`id`, `book_name`, `date`, `type`) VALUES ('2', 'fasdas','2019', 'novel');
INSERT INTO `tb_book` (`id`, `book_name`, `date`, `type`) VALUES ('3', 'fsafsadsadsa','2021','poetry');
INSERT INTO `tb_book` (`id`, `book_name`, `date`, `type`) VALUES ('4', 'xzczxc','2021', 'poetry');
INSERT INTO `tb_book` (`id`, `book_name`, `date`, `type`) VALUES ('5', 'sadasdas','2000', 'novel');