package com.sven.cvms.project.interviewee.service.impl;

import com.sven.cvms.common.utils.DateUtils;
import com.sven.cvms.common.utils.bean.BeanUtils;
import com.sven.cvms.common.utils.file.FileUploadUtils;
import com.sven.cvms.project.cv.domain.CurriculumVitae;
import com.sven.cvms.project.cv.service.CurriculumVitaeService;
import com.sven.cvms.project.interviewee.domain.Interviewee;
import com.sven.cvms.project.interviewee.domain.IntervieweeRegisterDTO;
import com.sven.cvms.project.interviewee.mapper.IntervieweeMapper;
import com.sven.cvms.project.interviewee.service.IntervieweeService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
     * @param intervieweeDTO 人才库
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerInterviewee(IntervieweeRegisterDTO intervieweeDTO) throws IOException {
        Interviewee interviewee = new Interviewee();
        BeanUtils.copyProperties(intervieweeDTO, interviewee);
        int insertCount = intervieweeMapper.insertInterviewee(interviewee);
        if (CollectionUtils.isNotEmpty(intervieweeDTO.getFileList())){
            for (MultipartFile multipartFile : intervieweeDTO.getFileList()) {
                CurriculumVitae curriculumVitae = new CurriculumVitae();
                String saveFileName = FileUploadUtils.upload(multipartFile);
                curriculumVitae.setIntervieweeId(interviewee.getId());
                curriculumVitae.setName(multipartFile.getOriginalFilename());
                curriculumVitae.setFilePath(saveFileName);
                curriculumVitae.setCreateTime(DateUtils.getNowDate());
                curriculumVitaeService.insertCurriculumVitae(curriculumVitae);
            }
        }
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
