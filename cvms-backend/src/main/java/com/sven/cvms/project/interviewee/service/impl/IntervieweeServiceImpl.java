package com.sven.cvms.project.interviewee.service.impl;

import com.sven.cvms.common.utils.DateUtils;
import com.sven.cvms.project.interviewee.domain.Interviewee;
import com.sven.cvms.project.interviewee.mapper.IntervieweeMapper;
import com.sven.cvms.project.interviewee.service.IIntervieweeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人才库Service业务层处理
 *
 * @author sven
 * @date 2023-12-01
 */
@Service
public class IntervieweeServiceImpl implements IIntervieweeService {
    private final IntervieweeMapper intervieweeMapper;

    public IntervieweeServiceImpl(IntervieweeMapper intervieweeMapper) {
        this.intervieweeMapper = intervieweeMapper;
    }

    /**
     * 查询人才库
     *
     * @param id 人才库主键
     * @return 人才库
     */
    @Override
    public Interviewee selectIntervieweeById(Long id) {
        return intervieweeMapper.selectIntervieweeById(id);
    }

    /**
     * 查询人才库列表
     *
     * @param interviewee 人才库
     * @return 人才库
     */
    @Override
    public List<Interviewee> selectIntervieweeList(Interviewee interviewee) {
        return intervieweeMapper.selectIntervieweeList(interviewee);
    }

    /**
     * 新增人才库
     *
     * @param interviewee 人才库
     * @return 结果
     */
    @Override
    public int insertInterviewee(Interviewee interviewee) {
        interviewee.setCreateTime(DateUtils.getNowDate());
        return intervieweeMapper.insertInterviewee(interviewee);
    }

    /**
     * 修改人才库
     *
     * @param interviewee 人才库
     * @return 结果
     */
    @Override
    public int updateInterviewee(Interviewee interviewee) {
        interviewee.setUpdateTime(DateUtils.getNowDate());
        return intervieweeMapper.updateInterviewee(interviewee);
    }

    /**
     * 批量删除人才库
     *
     * @param ids 需要删除的人才库主键
     * @return 结果
     */
    @Override
    public int deleteIntervieweeByIds(Long[] ids) {
        return intervieweeMapper.deleteIntervieweeByIds(ids);
    }

    /**
     * 删除人才库信息
     *
     * @param id 人才库主键
     * @return 结果
     */
    @Override
    public int deleteIntervieweeById(Long id) {
        return intervieweeMapper.deleteIntervieweeById(id);
    }
}
