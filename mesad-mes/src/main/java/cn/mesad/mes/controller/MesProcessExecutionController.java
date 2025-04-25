package cn.mesad.mes.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.mesad.common.annotation.Log;
import cn.mesad.common.core.controller.BaseController;
import cn.mesad.common.core.domain.AjaxResult;
import cn.mesad.common.enums.BusinessType;
import cn.mesad.mes.domain.MesProcessExecution;
import cn.mesad.mes.service.IMesProcessExecutionService;
import cn.mesad.common.utils.poi.ExcelUtil;
import cn.mesad.common.core.page.TableDataInfo;

/**
 * 生产工序执行Controller
 * 
 * @author ruoyi
 * @date 2025-04-25
 */
@RestController
@RequestMapping("/processexecution/processexecution")
public class MesProcessExecutionController extends BaseController
{
    @Autowired
    private IMesProcessExecutionService mesProcessExecutionService;

    /**
     * 查询生产工序执行列表
     */
    @PreAuthorize("@ss.hasPermi('processexecution:processexecution:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesProcessExecution mesProcessExecution)
    {
        startPage();
        List<MesProcessExecution> list = mesProcessExecutionService.selectMesProcessExecutionList(mesProcessExecution);
        return getDataTable(list);
    }

    /**
     * 导出生产工序执行列表
     */
    @PreAuthorize("@ss.hasPermi('processexecution:processexecution:export')")
    @Log(title = "生产工序执行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesProcessExecution mesProcessExecution)
    {
        List<MesProcessExecution> list = mesProcessExecutionService.selectMesProcessExecutionList(mesProcessExecution);
        ExcelUtil<MesProcessExecution> util = new ExcelUtil<MesProcessExecution>(MesProcessExecution.class);
        util.exportExcel(response, list, "生产工序执行数据");
    }

    /**
     * 获取生产工序执行详细信息
     */
    @PreAuthorize("@ss.hasPermi('processexecution:processexecution:query')")
    @GetMapping(value = "/{processExecutionId}")
    public AjaxResult getInfo(@PathVariable("processExecutionId") Long processExecutionId)
    {
        return success(mesProcessExecutionService.selectMesProcessExecutionByProcessExecutionId(processExecutionId));
    }

    /**
     * 新增生产工序执行
     */
    @PreAuthorize("@ss.hasPermi('processexecution:processexecution:add')")
    @Log(title = "生产工序执行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesProcessExecution mesProcessExecution)
    {
        return toAjax(mesProcessExecutionService.insertMesProcessExecution(mesProcessExecution));
    }

    /**
     * 修改生产工序执行
     */
    @PreAuthorize("@ss.hasPermi('processexecution:processexecution:edit')")
    @Log(title = "生产工序执行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesProcessExecution mesProcessExecution)
    {
        return toAjax(mesProcessExecutionService.updateMesProcessExecution(mesProcessExecution));
    }

    /**
     * 删除生产工序执行
     */
    @PreAuthorize("@ss.hasPermi('processexecution:processexecution:remove')")
    @Log(title = "生产工序执行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{processExecutionIds}")
    public AjaxResult remove(@PathVariable Long[] processExecutionIds)
    {
        return toAjax(mesProcessExecutionService.deleteMesProcessExecutionByProcessExecutionIds(processExecutionIds));
    }
}
