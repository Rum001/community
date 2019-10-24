package com.itrum.community.community.dto;

import com.itrum.community.community.domain.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private String content;
    private Integer type;
    private Long createTime;
    private Long updateTime;
    private Long commentator;
    private Long likeCount;
    private User user;
}
