package com.sven.cvms.project.cv.domain;

import com.sven.cvms.framework.aspectj.lang.annotation.Excel;
import com.sven.cvms.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 简历对象 curriculum_vitae
 *
 * @author sven
 * @date 2023-12-01
 */
@Data
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setIntervieweeId(Long intervieweeId) {
        this.intervieweeId = intervieweeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
                .append("intervieweeId", getIntervieweeId())
                .append("name", getName())
                .append("fileName", getFileName())
                .append("filePath", getFilePath())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
