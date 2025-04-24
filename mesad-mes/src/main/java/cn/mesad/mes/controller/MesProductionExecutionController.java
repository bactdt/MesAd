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
import cn.mesad.mes.domain.MesProductionExecution;
import cn.mesad.mes.service.IMesProductionExecutionService;
import cn.mesad.common.utils.poi.ExcelUtil;
import cn.mesad.common.core.page.TableDataInfo;

/**
 * 生产执行Controller
 * 
 * @author ruoyi
 * @date 2025-04-22
 */
@RestController
@RequestMapping("/productexecution/execution")
public class MesProductionExecutionController extends BaseController
{
    @Autowired
    private IMesProductionExecutionService mesProductionExecutionService;

    /**
     * 查询生产执行列表
     */
    @PreAuthorize("@ss.hasPermi('productexecution:execution:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesProductionExecution mesProductionExecution)
    {
        startPage();
        List<MesProductionExecution> list = mesProductionExecutionService.selectMesProductionExecutionList(mesProductionExecution);
        return getDataTable(list);
    }

    /**
     * 导出生产执行列表
     */
    @PreAuthorize("@ss.hasPermi('productexecution:execution:export')")
    @Log(title = "生产执行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesProductionExecution mesProductionExecution)
    {
        List<MesProductionExecution> list = mesProductionExecutionService.selectMesProductionExecutionList(mesProductionExecution);
        ExcelUtil<MesProductionExecution> util = new ExcelUtil<MesProductionExecution>(MesProductionExecution.class);
        util.exportExcel(response, list, "生产执行数据");
    }

    /**
     * 获取生产执行详细信息
     */
    @PreAuthorize("@ss.hasPermi('productexecution:execution:query')")
    @GetMapping(value = "/{executionId}")
    public AjaxResult getInfo(@PathVariable("executionId") Long executionId)
    {
        return success(mesProductionExecutionService.selectMesProductionExecutionByExecutionId(executionId));
    }

    /**
     * 新增生产执行
     */
    @PreAuthorize("@ss.hasPermi('productexecution:execution:add')")
    @Log(title = "生产执行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesProductionExecution mesProductionExecution)
    {
        return toAjax(mesProductionExecutionService.insertMesProductionExecution(mesProductionExecution));
    }

    /**
     * 修改生产执行
     */
    @PreAuthorize("@ss.hasPermi('productexecution:execution:edit')")
    @Log(title = "生产执行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesProductionExecution mesProductionExecution)
    {
        return toAjax(mesProductionExecutionService.updateMesProductionExecution(mesProductionExecution));
    }

    /**
     * 删除生产执行
     */
    @PreAuthorize("@ss.hasPermi('productexecution:execution:remove')")
    @Log(title = "生产执行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{executionIds}")
    public AjaxResult remove(@PathVariable Long[] executionIds)
    {
        return toAjax(mesProductionExecutionService.deleteMesProductionExecutionByExecutionIds(executionIds));
    }
}
