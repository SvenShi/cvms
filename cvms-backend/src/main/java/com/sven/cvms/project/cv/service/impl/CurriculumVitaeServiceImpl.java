package com.sven.cvms.project.cv.service.impl;

import com.sven.cvms.common.utils.DateUtils;
import com.sven.cvms.project.cv.domain.CurriculumVitae;
import com.sven.cvms.project.cv.mapper.CurriculumVitaeMapper;
import com.sven.cvms.project.cv.service.CurriculumVitaeService;
import org.springframework.stereotype.Service;

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
        curriculumVitae.setCreateTime(DateUtils.getNowDate());
        return curriculumVitaeMapper.insertCurriculumVitae(curriculumVitae);
    }

    /**
     * 修改简历
     *
     * @param curriculumVitae 简历
     * @return 结果
     */
    @Override
    public int updateCurriculumVitae(CurriculumVitae curriculumVitae) {
        curriculumVitae.setUpdateTime(DateUtils.getNowDate());
        return curriculumVitaeMapper.updateCurriculumVitae(curriculumVitae);
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
}
