package com.sven.cvms.project.interviewee.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * 人才库对象 interviewee
 *
 * @author sven
 * @date 2023-12-01
 */
@Data
@ToString
public class IntervieweeQuery {


    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    private Integer ageStart;

    private Integer ageEnd;


    /**
     * 联系方式
     */
    private String contact;


    /**
     * 学历
     */
    private List<Long> degree;

    /**
     * 工作年限
     */
    private Integer workYearStart;

    private Integer workYearEnd;

    /**
     * 应聘岗位
     */
    private String job;

    /**
     * 期望薪资
     */
    private Long salaryStart;
    private Long salaryEnd;

    /**
     * 创建者
     */
    private String createBy;

}
