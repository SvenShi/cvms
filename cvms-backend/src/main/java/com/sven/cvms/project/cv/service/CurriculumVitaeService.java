package com.sven.cvms.project.cv.service;

import com.sven.cvms.project.cv.domain.CurriculumVitae;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 简历Service接口
 *
 * @author sven
 * @date 2023-12-01
 */
public interface CurriculumVitaeService {
    /**
     * 查询简历
     *
     * @param id 简历主键
     * @return 简历
     */
    CurriculumVitae selectCurriculumVitaeById(Long id);

    /**
     * 查询简历列表
     *
     * @param curriculumVitae 简历
     * @return 简历集合
     */
    List<CurriculumVitae> selectCurriculumVitaeList(CurriculumVitae curriculumVitae);

    /**
     * 新增简历
     *
     * @param curriculumVitae 简历
     * @return 结果
     */
    int insertCurriculumVitae(CurriculumVitae curriculumVitae);

    /**
     * 批量删除简历
     *
     * @param ids 需要删除的简历主键集合
     * @return 结果
     */
    int deleteCurriculumVitaeByIds(Long[] ids);

    /**
     * 删除简历信息
     *
     * @param id 简历主键
     * @return 结果
     */
    int deleteCurriculumVitaeById(Long id);

    /**
     * 上载
     *
     * @param fileList      文件列表
     * @param intervieweeId 面试者id
     * @return int
     */
    int upload(List<MultipartFile> fileList, Long intervieweeId) throws IOException;
}
