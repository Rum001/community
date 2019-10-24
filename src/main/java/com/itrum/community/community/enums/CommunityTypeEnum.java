package com.itrum.community.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CommunityTypeEnum {
    QUESTION(1),
    COMMENT(2)
    ;
    private Integer type;

    public static boolean isExists(Integer type){
        for (CommunityTypeEnum value : values()) {
            if (type==value.getType()){
                return true;
            }
        }
        return false;
    }
}
