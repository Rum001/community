package com.itrum.community.community.domain;

import lombok.Data;

@Data
public class Question {
    private Integer id;//id 唯一标识
    private String title;//标题
    private String description;//描述
    private String tag;//标签
    private Integer creator;//创建者的ID
    private Long createTime;//创建时间
    private Long updateTime;//修改时间
    private Integer commentCount; //评论数
    private Integer viewCount;//浏览数
    private Integer likeCount;//点赞数

    private User user;
}
