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
import cn.mesad.mes.domain.MesReport;
import cn.mesad.mes.service.IMesReportService;
import cn.mesad.common.utils.poi.ExcelUtil;
import cn.mesad.common.core.page.TableDataInfo;

/**
 * 生产报表Controller
 *
 * @author ruoyi
 * @date 2025-04-21
 */
@RestController
@RequestMapping("/report/report")
public class MesReportController extends BaseController
{
    @Autowired
    private IMesReportService mesReportService;

    /**
     * 查询生产报表列表
     */
    @PreAuthorize("@ss.hasPermi('report:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesReport mesReport)
    {
        startPage();
        List<MesReport> list = mesReportService.selectMesReportList(mesReport);
        return getDataTable(list);
    }

    /**
     * 导出生产报表列表
     */
    @PreAuthorize("@ss.hasPermi('report:report:export')")
    @Log(title = "生产报表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesReport mesReport)
    {
        List<MesReport> list = mesReportService.selectMesReportList(mesReport);
        ExcelUtil<MesReport> util = new ExcelUtil<MesReport>(MesReport.class);
        util.exportExcel(response, list, "生产报表数据");
    }

    /**
     * 获取生产报表详细信息
     */
    @PreAuthorize("@ss.hasPermi('report:report:query')")
    @GetMapping(value = "/{reportId}")
    public AjaxResult getInfo(@PathVariable("reportId") Long reportId)
    {
        return success(mesReportService.selectMesReportByReportId(reportId));
    }

    /**
     * 新增生产报表
     */
    @PreAuthorize("@ss.hasPermi('report:report:add')")
    @Log(title = "生产报表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesReport mesReport)
    {
        return toAjax(mesReportService.insertMesReport(mesReport));
    }

    /**
     * 修改生产报表
     */
    @PreAuthorize("@ss.hasPermi('report:report:edit')")
    @Log(title = "生产报表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesReport mesReport)
    {
        return toAjax(mesReportService.updateMesReport(mesReport));
    }

    /**
     * 删除生产报表
     */
    @PreAuthorize("@ss.hasPermi('report:report:remove')")
    @Log(title = "生产报表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{reportIds}")
    public AjaxResult remove(@PathVariable Long[] reportIds)
    {
        return toAjax(mesReportService.deleteMesReportByReportIds(reportIds));
    }
}
