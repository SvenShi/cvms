package com.sven.cvms.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 学历
 *
 * @author isven
 * @date 2023/12/07
 */
@Getter
public enum Degree implements BaseEnum {
    ES("0", "小学", "Elementary School"),
    JHS("1", "初中", "Junior High School"),
    SHS("2", "高中", "Junior High School"),
    AC("3", "专科", "Associate College"),
    B_D("4", "本科", "Bachelor’s Degree"),
    M_D("5", "硕士", "Master’s Degree"),
    DOC("6", "博士", "Doctor"),
    POSTDOC("7", "博士后", "Postdoctoral Researcher");


    /**
     * 字典映射值
     */
    @EnumValue
    @JsonValue
    private final String code;

    /**
     * 中文名称
     */
    private final String name;
    /**
     * 英文名称
     */
    private final String enName;

    Degree(String code, String name, String enName) {
        this.code = code;
        this.name = name;
        this.enName = enName;
    }


}
