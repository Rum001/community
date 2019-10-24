CREATE TABLE `comment`(
    `id` bigint(32) auto_increment,
    `parent_Id` bigint(32),
    `content` VARCHAR(1024),
    `type` int(32),
    `create_time` VARCHAR(100),
    `update_time` VARCHAR(100),
    `commentator` bigint(32) DEFAULT '0',
    `like_count` bigint(32) DEFAULT '0',
    PRIMARY KEY(id)
)