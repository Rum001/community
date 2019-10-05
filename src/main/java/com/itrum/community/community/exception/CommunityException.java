package com.itrum.community.community.exception;

import com.itrum.community.community.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunityException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
