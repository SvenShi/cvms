package com.sven.cvms.project.interviewee.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sven.cvms.framework.aspectj.lang.annotation.Excel;
import com.sven.cvms.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;


/**
 * 人才库对象 interviewee
 *
 * @author sven
 * @date 2023-12-01
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Interviewee extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String gender;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDate birthday;


    /**
     * 年龄
     */
    @Excel(name = "年龄")
    private Integer age;

    /**
     * 联系方式
     */
    @Excel(name = "联系方式")
    private String contact;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 学历
     */
    @Excel(name = "学历")
    private Long degree;

    /**
     * 政治面貌
     */
    @Excel(name = "政治面貌")
    private String political;

    /**
     * 应聘岗位
     */
    @Excel(name = "应聘岗位")
    private String job;

    /**
     * 期望薪资
     */
    @Excel(name = "期望薪资")
    private Long salary;


}
