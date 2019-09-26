CREATE TABLE `question`(
    `id` bigint(32) auto_increment,
    `title` VARCHAR(50),
    `description` VARCHAR(200),
    `creator` int(32),
    `create_time` VARCHAR(100),
    `update_time` VARCHAR(100),
    `comment_count` bigint(32) DEFAULT '0',
    `view_count` bigint(32) DEFAULT '0',
    `like_count` bigint(32) DEFAULT '0',
    PRIMARY KEY(id)
)