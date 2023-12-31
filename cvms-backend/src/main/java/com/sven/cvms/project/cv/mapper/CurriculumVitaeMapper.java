package com.sven.cvms.project.cv.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sven.cvms.project.cv.domain.CurriculumVitae;

import java.util.List;

/**
 * 简历Mapper接口
 *
 * @author sven
 * @date 2023-12-01
 */
public interface CurriculumVitaeMapper extends BaseMapper<CurriculumVitae> {
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
     * 修改简历
     *
     * @param curriculumVitae 简历
     * @return 结果
     */
    int updateCurriculumVitae(CurriculumVitae curriculumVitae);

    /**
     * 删除简历
     *
     * @param id 简历主键
     * @return 结果
     */
    int deleteCurriculumVitaeById(Long id);

    /**
     * 批量删除简历
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCurriculumVitaeByIds(Long[] ids);
}
