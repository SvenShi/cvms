package com.sven.cvms.project.cv.controller;

import com.sven.cvms.common.utils.file.FileUploadUtils;
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
    @PreAuthorize("@ss.hasPermi('cv:cv:list')")
    @GetMapping("/list")
    public TableDataInfo list(CurriculumVitae curriculumVitae) {
        startPage();
        List<CurriculumVitae> list = curriculumVitaeService.selectCurriculumVitaeList(curriculumVitae);
        return getDataTable(list);
    }

    /**
     * 导出简历列表
     */
    @PreAuthorize("@ss.hasPermi('cv:cv:download')")
    @Log(title = "简历", businessType = BusinessType.OTHER)
    @PostMapping("/export/{id}")
    public void download(HttpServletResponse response, HttpServletRequest request,
                         @PathVariable("id")
                         Long id) throws IOException {
        CurriculumVitae curriculumVitae = curriculumVitaeService.selectCurriculumVitaeById(id);
        // 本地资源路径

        String localPath = FileUploadUtils.getDefaultBaseDir();
        // TODO 2023/12/7 0:40 shiwei: 需要重新写上传和下载工具类来


        // // 数据库资源地址
        // String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
        // // 下载名称
        // String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        // response.setCharacterEncoding("utf-8");
        // response.setContentType("multipart/form-data");
        // response.setHeader("Content-Disposition",
        //         "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        // FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }

    /**
     * 获取简历详细信息
     */
    @PreAuthorize("@ss.hasPermi('cv:cv:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(
            @PathVariable("id")
            Long id) {
        return success(curriculumVitaeService.selectCurriculumVitaeById(id));
    }

    /**
     * 新增简历
     */
    @PreAuthorize("@ss.hasPermi('cv:cv:add')")
    @Log(title = "简历", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(
            @RequestBody
            CurriculumVitae curriculumVitae) {
        return toAjax(curriculumVitaeService.insertCurriculumVitae(curriculumVitae));
    }

    /**
     * 修改简历
     */
    @PreAuthorize("@ss.hasPermi('cv:cv:edit')")
    @Log(title = "简历", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(
            @RequestBody
            CurriculumVitae curriculumVitae) {
        return toAjax(curriculumVitaeService.updateCurriculumVitae(curriculumVitae));
    }

    /**
     * 删除简历
     */
    @PreAuthorize("@ss.hasPermi('cv:cv:remove')")
    @Log(title = "简历", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(
            @PathVariable
            Long[] ids) {
        return toAjax(curriculumVitaeService.deleteCurriculumVitaeByIds(ids));
    }
}
