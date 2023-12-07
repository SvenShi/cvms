package com.sven.cvms.project.cv.controller;

import com.sven.cvms.common.utils.file.FileDownloadUtils;
import com.sven.cvms.framework.aspectj.lang.annotation.Log;
import com.sven.cvms.framework.aspectj.lang.enums.BusinessType;
import com.sven.cvms.framework.web.controller.BaseController;
import com.sven.cvms.framework.web.domain.AjaxResult;
import com.sven.cvms.framework.web.page.TableDataInfo;
import com.sven.cvms.project.cv.domain.CurriculumVitae;
import com.sven.cvms.project.cv.service.CurriculumVitaeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 简历Controller
 *
 * @author sven
 * @date 2023-12-01
 */
@RestController
@RequestMapping("/cv")
public class CurriculumVitaeController extends BaseController {

    private final CurriculumVitaeService curriculumVitaeService;

    public CurriculumVitaeController(CurriculumVitaeService curriculumVitaeService) {
        this.curriculumVitaeService = curriculumVitaeService;
    }

    /**
     * 查询简历列表
     */
    @PreAuthorize("@ss.hasPermi('interviewee:cv:query')")
    @GetMapping("/list")
    public TableDataInfo list(CurriculumVitae curriculumVitae) {
        startPage();
        List<CurriculumVitae> list = curriculumVitaeService.selectCurriculumVitaeList(curriculumVitae);
        return getDataTable(list);
    }

    /**
     * 下载简历
     */
    @PreAuthorize("@ss.hasPermi('interviewee:cv:download')")
    @Log(title = "简历", businessType = BusinessType.OTHER)
    @GetMapping("/download/{id}")
    public void download(HttpServletResponse response, HttpServletRequest request,
                         @PathVariable("id")
                         Long intervieweeId) throws IOException {
        CurriculumVitae curriculumVitae = curriculumVitaeService.selectCurriculumVitaeById(intervieweeId);
        if (curriculumVitae != null) {
            FileDownloadUtils.downloadToHttp(request, response, curriculumVitae.getFilePath(),
                    curriculumVitae.getFileName());
        }
    }


    /**
     * 上传简历
     */
    @PreAuthorize("@ss.hasPermi('interviewee:cv:upload')")
    @Log(title = "简历", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult upload(List<MultipartFile> fileList, Long intervieweeId) throws IOException {
        return toAjax(curriculumVitaeService.upload(fileList, intervieweeId));
    }

    /**
     * 删除简历
     */
    @PreAuthorize("@ss.hasPermi('interviewee:cv:remove')")
    @Log(title = "简历", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(
            @PathVariable
            Long[] ids) {
        return toAjax(curriculumVitaeService.deleteCurriculumVitaeByIds(ids));
    }
}
