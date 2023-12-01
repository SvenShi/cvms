package com.sven.cvms.project.interviewee.mapper;

import com.sven.cvms.project.interviewee.domain.Interviewee;

import java.util.List;

/**
 * 人才库Mapper接口
 *
 * @author sven
 * @date 2023-12-01
 */
public interface IntervieweeMapper {
    /**
     * 查询人才库
     *
     * @param id 人才库主键
     * @return 人才库
     */
    Interviewee selectIntervieweeById(Long id);

    /**
     * 查询人才库列表
     *
     * @param interviewee 人才库
     * @return 人才库集合
     */
    List<Interviewee> selectIntervieweeList(Interviewee interviewee);

    /**
     * 新增人才库
     *
     * @param interviewee 人才库
     * @return 结果
     */
    int insertInterviewee(Interviewee interviewee);

    /**
     * 修改人才库
     *
     * @param interviewee 人才库
     * @return 结果
     */
    int updateInterviewee(Interviewee interviewee);

    /**
     * 删除人才库
     *
     * @param id 人才库主键
     * @return 结果
     */
    int deleteIntervieweeById(Long id);

    /**
     * 批量删除人才库
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteIntervieweeByIds(Long[] ids);
}
