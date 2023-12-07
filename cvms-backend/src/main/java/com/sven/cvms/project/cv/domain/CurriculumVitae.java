package com.sven.cvms.project.cv.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sven.cvms.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;


/**
 * 简历对象 curriculum_vitae
 *
 * @author sven
 * @date 2023-12-01
 */
@Data
@ToString
public class CurriculumVitae{

    /**
     * id
     */
    private Long id;

    /**
     * 人才id
     */
    @Excel(name = "人才id")
    private Long intervieweeId;

    /**
     * 简历名称
     */
    @Excel(name = "简历名称")
    private String name;

    /**
     * 简历文件名称
     */
    @Excel(name = "简历文件名称")
    private String fileName;

    /**
     * 文件保存路径
     */
    @Excel(name = "文件保存路径")
    private String filePath;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
