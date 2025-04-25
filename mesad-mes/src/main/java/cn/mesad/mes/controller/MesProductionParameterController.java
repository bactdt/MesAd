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
import cn.mesad.mes.domain.MesProductionParameter;
import cn.mesad.mes.service.IMesProductionParameterService;
import cn.mesad.common.utils.poi.ExcelUtil;
import cn.mesad.common.core.page.TableDataInfo;

/**
 * 生产参数记录Controller
 * 
 * @author ruoyi
 * @date 2025-04-25
 */
@RestController
@RequestMapping("/production_parameter/parameter")
public class MesProductionParameterController extends BaseController
{
    @Autowired
    private IMesProductionParameterService mesProductionParameterService;

    /**
     * 查询生产参数记录列表
     */
    @PreAuthorize("@ss.hasPermi('production_parameter:parameter:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesProductionParameter mesProductionParameter)
    {
        startPage();
        List<MesProductionParameter> list = mesProductionParameterService.selectMesProductionParameterList(mesProductionParameter);
        return getDataTable(list);
    }

    /**
     * 导出生产参数记录列表
     */
    @PreAuthorize("@ss.hasPermi('production_parameter:parameter:export')")
    @Log(title = "生产参数记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesProductionParameter mesProductionParameter)
    {
        List<MesProductionParameter> list = mesProductionParameterService.selectMesProductionParameterList(mesProductionParameter);
        ExcelUtil<MesProductionParameter> util = new ExcelUtil<MesProductionParameter>(MesProductionParameter.class);
        util.exportExcel(response, list, "生产参数记录数据");
    }

    /**
     * 获取生产参数记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('production_parameter:parameter:query')")
    @GetMapping(value = "/{parameterId}")
    public AjaxResult getInfo(@PathVariable("parameterId") Long parameterId)
    {
        return success(mesProductionParameterService.selectMesProductionParameterByParameterId(parameterId));
    }

    /**
     * 新增生产参数记录
     */
    @PreAuthorize("@ss.hasPermi('production_parameter:parameter:add')")
    @Log(title = "生产参数记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesProductionParameter mesProductionParameter)
    {
        return toAjax(mesProductionParameterService.insertMesProductionParameter(mesProductionParameter));
    }

    /**
     * 修改生产参数记录
     */
    @PreAuthorize("@ss.hasPermi('production_parameter:parameter:edit')")
    @Log(title = "生产参数记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesProductionParameter mesProductionParameter)
    {
        return toAjax(mesProductionParameterService.updateMesProductionParameter(mesProductionParameter));
    }

    /**
     * 删除生产参数记录
     */
    @PreAuthorize("@ss.hasPermi('production_parameter:parameter:remove')")
    @Log(title = "生产参数记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{parameterIds}")
    public AjaxResult remove(@PathVariable Long[] parameterIds)
    {
        return toAjax(mesProductionParameterService.deleteMesProductionParameterByParameterIds(parameterIds));
    }
}
