package com.itrum.community.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    QUESTION_NOT_FOUND("问题找不到了或者已删除",2001),
    QUESTION_SAVE_FAILING("问题发布失败或审核没有通过",2002),
    USER_NOT_LOGIN("用户未登录,请先去登录",2003),
    COMMENT_NOT_FOUND("评论不存在或者已删除",2004),
    COMMENT_SAVE_FAILING("评论发布失败",2005),
    ;

    private String msg; //消息
    private int code; //状态码
}
