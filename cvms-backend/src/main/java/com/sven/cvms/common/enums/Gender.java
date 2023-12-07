package com.sven.cvms.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 性别
 *
 * @author isven
 * @date 2023/12/07
 */
@Getter
public enum Gender implements BaseEnum {
    MALE("M", "男"),
    FEMALE("F", "女"),
    OTHER("N", "其他");

    @EnumValue
    @JsonValue
    private final String code;

    private final String name;

    Gender(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
