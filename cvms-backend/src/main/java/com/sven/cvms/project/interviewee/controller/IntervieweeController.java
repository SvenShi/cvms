package com.sven.cvms.project.interviewee.controller;

import com.sven.cvms.common.utils.poi.ExcelUtil;
import com.sven.cvms.framework.aspectj.lang.annotation.Log;
import com.sven.cvms.framework.aspectj.lang.enums.BusinessType;
import com.sven.cvms.framework.web.controller.BaseController;
import com.sven.cvms.framework.web.domain.AjaxResult;
import com.sven.cvms.framework.web.page.TableDataInfo;
import com.sven.cvms.project.interviewee.domain.Interviewee;
import com.sven.cvms.project.interviewee.service.IntervieweeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 人才库Controller
 *
 * @author sven
 * @date 2023-12-01
 */
@RestController
@RequestMapping("/interviewee/interviewee")
public class IntervieweeController extends BaseController {
    private final IntervieweeService intervieweeService;

    public IntervieweeController(IntervieweeService intervieweeService) {
        this.intervieweeService = intervieweeService;
    }

    /**
     * 查询人才库列表
     */
    @PreAuthorize("@ss.hasPermi('interviewee:interviewee:list')")
    @GetMapping("/list")
    public TableDataInfo list(Interviewee interviewee) {
        startPage();
        List<Interviewee> list = intervieweeService.selectIntervieweeList(interviewee);
        return getDataTable(list);
    }

    /**
     * 导出人才库列表
     */
    @PreAuthorize("@ss.hasPermi('interviewee:interviewee:export')")
    @Log(title = "人才库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Interviewee interviewee) {
        List<Interviewee> list = intervieweeService.selectIntervieweeList(interviewee);
        ExcelUtil<Interviewee> util = new ExcelUtil<Interviewee>(Interviewee.class);
        util.exportExcel(response, list, "人才库数据");
    }

    /**
     * 获取人才库详细信息
     */
    @PreAuthorize("@ss.hasPermi('interviewee:interviewee:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(
            @PathVariable("id")
            Long id) {
        return success(intervieweeService.selectIntervieweeById(id));
    }

    /**
     * 新增人才库
     */
    @PreAuthorize("@ss.hasPermi('interviewee:interviewee:add')")
    @Log(title = "人才库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(
            @RequestBody
            Interviewee interviewee) {
        return toAjax(intervieweeService.insertInterviewee(interviewee));
    }

    /**
     * 修改人才库
     */
    @PreAuthorize("@ss.hasPermi('interviewee:interviewee:edit')")
    @Log(title = "人才库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(
            @RequestBody
            Interviewee interviewee) {
        return toAjax(intervieweeService.updateInterviewee(interviewee));
    }

    /**
     * 删除人才库
     */
    @PreAuthorize("@ss.hasPermi('interviewee:interviewee:remove')")
    @Log(title = "人才库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(
            @PathVariable
            Long[] ids) {
        return toAjax(intervieweeService.deleteIntervieweeByIds(ids));
    }
}
