package com.sven.cvms.project.interviewee.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sven.cvms.common.utils.SecurityUtils;
import com.sven.cvms.common.utils.StringUtils;
import com.sven.cvms.common.utils.bean.BeanUtils;
import com.sven.cvms.project.cv.service.CurriculumVitaeService;
import com.sven.cvms.project.interviewee.domain.Interviewee;
import com.sven.cvms.project.interviewee.domain.IntervieweeQuery;
import com.sven.cvms.project.interviewee.domain.IntervieweeRegisterDTO;
import com.sven.cvms.project.interviewee.mapper.IntervieweeMapper;
import com.sven.cvms.project.interviewee.service.IntervieweeService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 人才库Service业务层处理
 *
 * @author sven
 * @date 2023-12-01
 */
@Service
public class IntervieweeServiceImpl implements IntervieweeService {
    /**
     * 面试人服务
     */
    private final IntervieweeMapper intervieweeMapper;

    /**
     * 简历服务
     */
    private final CurriculumVitaeService curriculumVitaeService;

    public IntervieweeServiceImpl(IntervieweeMapper intervieweeMapper, CurriculumVitaeService curriculumVitaeService) {
        this.intervieweeMapper = intervieweeMapper;
        this.curriculumVitaeService = curriculumVitaeService;
    }

    /**
     * 查询人才库
     *
     * @param id 人才库主键
     * @return 人才库
     */
    @Override
    public Interviewee selectIntervieweeById(Long id) {
        return intervieweeMapper.selectById(id);
    }

    /**
     * 查询人才库列表
     *
     * @param query 人才库
     * @return 人才库
     */
    @Override
    public List<Interviewee> selectIntervieweeList(IntervieweeQuery query) {
        return intervieweeMapper.selectList(
                new LambdaQueryWrapper<Interviewee>().like(StringUtils.isNotBlank(query.getName()),
                                Interviewee::getName, query.getName())
                        .like(StringUtils.isNotBlank(query.getJob()), Interviewee::getJob, query.getJob())
                        .eq(Objects.nonNull(query.getGender()), Interviewee::getGender, query.getGender())
                        .like(StringUtils.isNotBlank(query.getContact()), Interviewee::getContact, query.getContact())
                        .in(CollectionUtils.isNotEmpty(query.getDegree()), Interviewee::getDegree, query.getDegree())
                        .ge(Objects.nonNull(query.getSalaryStart()), Interviewee::getSalary, query.getSalaryStart())
                        .le(Objects.nonNull(query.getSalaryEnd()), Interviewee::getSalary, query.getSalaryEnd())
                        .like(StringUtils.isNotBlank(query.getCreateBy()), Interviewee::getCreateBy,
                                query.getCreateBy())
                        .ge(Objects.nonNull(query.getAgeStart()), Interviewee::getAge, query.getAgeStart())
                        .le(Objects.nonNull(query.getAgeEnd()), Interviewee::getAge, query.getAgeEnd()));
    }

    /**
     * 新增人才库
     *
     * @param intervieweeDTO 人才库
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerInterviewee(IntervieweeRegisterDTO intervieweeDTO) throws IOException {
        Interviewee interviewee = new Interviewee();
        BeanUtils.copyProperties(intervieweeDTO, interviewee);
        interviewee.setCreateBy(SecurityUtils.getUsername());
        interviewee.setCreateTime(LocalDateTime.now());
        interviewee.setUpdateBy(SecurityUtils.getUsername());
        interviewee.setUpdateTime(LocalDateTime.now());
        int insertCount = intervieweeMapper.insert(interviewee);
        curriculumVitaeService.upload(intervieweeDTO.getFileList(), interviewee.getId());
        return insertCount;
    }

    /**
     * 修改人才库
     *
     * @param interviewee 人才库
     * @return 结果
     */
    @Override
    public int updateInterviewee(Interviewee interviewee) {
        interviewee.setUpdateBy(SecurityUtils.getUsername());
        interviewee.setUpdateTime(LocalDateTime.now());
        return intervieweeMapper.updateById(interviewee);
    }

    /**
     * 批量删除人才库
     *
     * @param ids 需要删除的人才库主键
     * @return 结果
     */
    @Override
    public int deleteIntervieweeByIds(Long[] ids) {
        return intervieweeMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除人才库信息
     *
     * @param id 人才库主键
     * @return 结果
     */
    @Override
    public int deleteIntervieweeById(Long id) {
        return intervieweeMapper.deleteById(id);
    }
}
