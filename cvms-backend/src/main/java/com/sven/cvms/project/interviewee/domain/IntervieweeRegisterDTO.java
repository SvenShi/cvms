package com.sven.cvms.project.interviewee.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sven.cvms.common.enums.Degree;
import com.sven.cvms.common.enums.Gender;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author shiwei
 * @date 2023/12/04 下午 03:59
 * @email shiwei@rmitec.cn
 */
@Data
public class IntervieweeRegisterDTO {

    private List<MultipartFile> fileList;
    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 学历
     */
    private Degree degree;

    /**
     * 政治面貌
     */
    private String political;

    /**
     * 应聘岗位
     */
    private String job;

    /**
     * 期望薪资
     */
    private Long salary;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
