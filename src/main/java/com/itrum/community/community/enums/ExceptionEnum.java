package com.itrum.community.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    QUESTION_NOT_FOUND("问题找不到了或者已删除",404),
    QUESTION_SAVE_FILE("问题找不到了或者已删除",404),
    ;

    private String msg; //消息
    private int code; //状态码
}
