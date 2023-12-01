package com.sven.cvms.project.cv.domain;

import com.sven.cvms.framework.aspectj.lang.annotation.Excel;
import com.sven.cvms.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 简历对象 curriculum_vitae
 *
 * @author sven
 * @date 2023-12-01
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CurriculumVitae extends BaseEntity {

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

}
