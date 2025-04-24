package cn.mesad.mes.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.mesad.mes.domain.MesOrder;
import cn.mesad.mes.domain.MesOrderDetail;
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
import cn.mesad.mes.domain.MesOrderSchedule;
import cn.mesad.mes.service.IMesOrderScheduleService;
import cn.mesad.common.utils.poi.ExcelUtil;
import cn.mesad.common.core.page.TableDataInfo;

/**
 * 订单排产Controller
 * 
 * @author ruoyi
 * @date 2025-04-22
 */
@RestController
@RequestMapping("/productexecution/schedule")
public class MesOrderScheduleController extends BaseController
{
    @Autowired
    private IMesOrderScheduleService mesOrderScheduleService;

    /**
     * 查询订单排产列表
     */
    @PreAuthorize("@ss.hasPermi('productexecution:schedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesOrderSchedule mesOrderSchedule)
    {
        startPage();
        List<MesOrderSchedule> list = mesOrderScheduleService.selectMesOrderScheduleList(mesOrderSchedule);
        return getDataTable(list);
    }
    /**
     * 查询订单连表查询列表
     */
    @PreAuthorize("@ss.hasPermi('productexecution:schedule:list')")
    @GetMapping("/listcahngeall")
    public TableDataInfo listcahngeall(MesOrder mesOrder, MesOrderDetail mesdetail)
    {
        startPage();
        List<MesOrderSchedule> List = mesOrderScheduleService.selectMesOrderScheduleListChange(mesOrder, mesdetail);
        return getDataTable(List);
    }

    /**
     * 导出订单排产列表
     */
    @PreAuthorize("@ss.hasPermi('productexecution:schedule:export')")
    @Log(title = "订单排产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesOrderSchedule mesOrderSchedule)
    {
        List<MesOrderSchedule> list = mesOrderScheduleService.selectMesOrderScheduleList(mesOrderSchedule);
        ExcelUtil<MesOrderSchedule> util = new ExcelUtil<MesOrderSchedule>(MesOrderSchedule.class);
        util.exportExcel(response, list, "订单排产数据");
    }

    /**
     * 获取订单排产详细信息
     */
    @PreAuthorize("@ss.hasPermi('productexecution:schedule:query')")
    @GetMapping(value = "/{scheduleId}")
    public AjaxResult getInfo(@PathVariable("scheduleId") Long scheduleId)
    {
        return success(mesOrderScheduleService.selectMesOrderScheduleByScheduleId(scheduleId));
    }

    /**
     * 新增订单排产
     */
    @PreAuthorize("@ss.hasPermi('productexecution:schedule:add')")
    @Log(title = "订单排产", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesOrderSchedule mesOrderSchedule)
    {
        return toAjax(mesOrderScheduleService.insertMesOrderSchedule(mesOrderSchedule));
    }

    /**
     * 修改订单排产
     */
    @PreAuthorize("@ss.hasPermi('productexecution:schedule:edit')")
    @Log(title = "订单排产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesOrderSchedule mesOrderSchedule)
    {
        return toAjax(mesOrderScheduleService.updateMesOrderSchedule(mesOrderSchedule));
    }

    /**
     * 删除订单排产
     */
    @PreAuthorize("@ss.hasPermi('productexecution:schedule:remove')")
    @Log(title = "订单排产", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scheduleIds}")
    public AjaxResult remove(@PathVariable Long[] scheduleIds)
    {
        return toAjax(mesOrderScheduleService.deleteMesOrderScheduleByScheduleIds(scheduleIds));
    }
}
