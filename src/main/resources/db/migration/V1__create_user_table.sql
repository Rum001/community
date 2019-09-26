CREATE TABLE `user`(
    `id` bigint(32) auto_increment,
    `account_id` VARCHAR(50),
    `name` VARCHAR(50),
    `token` VARCHAR(100),
    `create_time` VARCHAR(100),
    `update_time` VARCHAR(100),
    PRIMARY KEY(id)
)