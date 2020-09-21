# SpringBootDemo
springboot+redis实现秒杀场景限流处理
简单实现思路：
在数据库中创建一个秒杀表和一个商品表，定时将即将秒杀的商品从数据库中写入redis中，应为redis是高效的缓存方案，而且是单线程的，特别适合秒杀场景。
一种简单的实现，直接将商品存储到list中，秒杀的时候从list中lpop商品。
限流就可以使用滑动窗口，我们可以将请求存储在zset数组，当每一次请求进来的时候，value保持唯一，用当前时间戳表示，
score也用当前时间戳表示，我们可以用score来计算当前时间戳之内有多少的请求数量。通过统计滑动窗口内（一秒内）的数量与阈值 max_count 进行比较就可以得出当前的行为是否允许。

CREATE TABLE `seckill` (
  `seckillId` int NOT NULL COMMENT '秒杀id',
  `productId` int NOT NULL COMMENT '秒杀商品id',
  `number` int NOT NULL COMMENT '秒杀数量',
  `status` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '秒杀状态',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`seckillId`),
  KEY `productId` (`productId`),
  CONSTRAINT `productId` FOREIGN KEY (`productId`) REFERENCES `production` (`productionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `production` (
  `productionId` int NOT NULL AUTO_INCREMENT,
  `productionNm` varchar(255) NOT NULL,
  `number` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`productionId`) USING BTREE,
  KEY `productionNm` (`productionNm`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;
