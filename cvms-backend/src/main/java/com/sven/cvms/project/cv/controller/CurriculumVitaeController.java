package com.sven.cvms.project.cv.controller;

import com.sven.cvms.common.utils.poi.ExcelUtil;
import com.sven.cvms.framework.aspectj.lang.annotation.Log;
import com.sven.cvms.framework.aspectj.lang.enums.BusinessType;
import com.sven.cvms.framework.web.controller.BaseController;
import com.sven.cvms.framework.web.domain.AjaxResult;
import com.sven.cvms.framework.web.page.TableDataInfo;
import com.sven.cvms.project.cv.domain.CurriculumVitae;
import com.sven.cvms.project.cv.service.CurriculumVitaeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 简历Controller
 *
 * @author sven
 * @date 2023-12-01
 */
@RestController
@RequestMapping("/cv/cv")
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
    @PreAuthorize("@ss.hasPermi('cv:cv:export')")
    @Log(title = "简历", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CurriculumVitae curriculumVitae) {
        List<CurriculumVitae> list = curriculumVitaeService.selectCurriculumVitaeList(curriculumVitae);
        ExcelUtil<CurriculumVitae> util = new ExcelUtil<CurriculumVitae>(CurriculumVitae.class);
        util.exportExcel(response, list, "简历数据");
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
