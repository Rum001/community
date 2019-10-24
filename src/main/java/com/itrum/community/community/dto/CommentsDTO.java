package com.itrum.community.community.dto;

import lombok.Data;

@Data
public class CommentsDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
