package com.sven.cvms.project.cv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sven.cvms.common.utils.SecurityUtils;
import com.sven.cvms.common.utils.file.FileUploadUtils;
import com.sven.cvms.project.cv.domain.CurriculumVitae;
import com.sven.cvms.project.cv.mapper.CurriculumVitaeMapper;
import com.sven.cvms.project.cv.service.CurriculumVitaeService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 简历Service业务层处理
 *
 * @author sven
 * @date 2023-12-01
 */
@Service
public class CurriculumVitaeServiceImpl implements CurriculumVitaeService {
    private final CurriculumVitaeMapper curriculumVitaeMapper;

    public CurriculumVitaeServiceImpl(CurriculumVitaeMapper curriculumVitaeMapper) {
        this.curriculumVitaeMapper = curriculumVitaeMapper;
    }

    /**
     * 查询简历
     *
     * @param id 简历主键
     * @return 简历
     */
    @Override
    public CurriculumVitae selectCurriculumVitaeById(Long id) {
        return curriculumVitaeMapper.selectCurriculumVitaeById(id);
    }

    /**
     * 查询简历列表
     *
     * @param curriculumVitae 简历
     * @return 简历
     */
    @Override
    public List<CurriculumVitae> selectCurriculumVitaeList(CurriculumVitae curriculumVitae) {
        return curriculumVitaeMapper.selectCurriculumVitaeList(curriculumVitae);
    }

    /**
     * 新增简历
     *
     * @param curriculumVitae 简历
     * @return 结果
     */
    @Override
    public int insertCurriculumVitae(CurriculumVitae curriculumVitae) {
        curriculumVitae.setCreateBy(SecurityUtils.getUsername());
        curriculumVitae.setCreateTime(LocalDateTime.now());
        return curriculumVitaeMapper.insertCurriculumVitae(curriculumVitae);
    }


    /**
     * 批量删除简历
     *
     * @param ids 需要删除的简历主键
     * @return 结果
     */
    @Override
    public int deleteCurriculumVitaeByIds(Long[] ids) {
        return curriculumVitaeMapper.deleteCurriculumVitaeByIds(ids);
    }

    /**
     * 删除简历信息
     *
     * @param id 简历主键
     * @return 结果
     */
    @Override
    public int deleteCurriculumVitaeById(Long id) {
        return curriculumVitaeMapper.deleteCurriculumVitaeById(id);
    }

    /**
     * 上载
     *
     * @param fileList      文件列表
     * @param intervieweeId 面试者id
     * @return int
     */
    @Override
    public int upload(List<MultipartFile> fileList, Long intervieweeId) throws IOException {
        int count = 0;
        List<CurriculumVitae> cvList = curriculumVitaeMapper.selectList(
                new LambdaQueryWrapper<CurriculumVitae>().eq(CurriculumVitae::getIntervieweeId, intervieweeId));
        if (CollectionUtils.isNotEmpty(cvList) && CollectionUtils.isNotEmpty(fileList)) {
            if (cvList.size() + fileList.size() > 3) {
                return 0;
            }
        }
        if (CollectionUtils.isNotEmpty(fileList)) {
            for (MultipartFile multipartFile : fileList) {
                CurriculumVitae curriculumVitae = new CurriculumVitae();
                String saveFileName = FileUploadUtils.upload(multipartFile);
                curriculumVitae.setIntervieweeId(intervieweeId);
                curriculumVitae.setName(multipartFile.getOriginalFilename());
                curriculumVitae.setFileName(multipartFile.getOriginalFilename());
                curriculumVitae.setFilePath(saveFileName);
                count += insertCurriculumVitae(curriculumVitae);
            }
        }
        return count;
    }
}
