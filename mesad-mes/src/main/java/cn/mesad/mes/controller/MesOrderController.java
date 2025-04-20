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
import cn.mesad.mes.domain.MesOrder;
import cn.mesad.mes.service.IMesOrderService;
import cn.mesad.common.utils.poi.ExcelUtil;
import cn.mesad.common.core.page.TableDataInfo;

/**
 * 订单管理Controller
 * 
 * @author ruoyi
 * @date 2025-04-20
 */
@RestController
@RequestMapping("/mesoder/order")
public class MesOrderController extends BaseController
{
    @Autowired
    private IMesOrderService mesOrderService;

    /**
     * 查询订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('mesoder:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesOrder mesOrder)
    {
        startPage();
        List<MesOrder> list = mesOrderService.selectMesOrderList(mesOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('mesoder:order:export')")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesOrder mesOrder)
    {
        List<MesOrder> list = mesOrderService.selectMesOrderList(mesOrder);
        ExcelUtil<MesOrder> util = new ExcelUtil<MesOrder>(MesOrder.class);
        util.exportExcel(response, list, "订单管理数据");
    }

    /**
     * 获取订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('mesoder:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(mesOrderService.selectMesOrderByOrderId(orderId));
    }

    /**
     * 新增订单管理
     */
    @PreAuthorize("@ss.hasPermi('mesoder:order:add')")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesOrder mesOrder)
    {
        return toAjax(mesOrderService.insertMesOrder(mesOrder));
    }

    /**
     * 修改订单管理
     */
    @PreAuthorize("@ss.hasPermi('mesoder:order:edit')")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesOrder mesOrder)
    {
        return toAjax(mesOrderService.updateMesOrder(mesOrder));
    }

    /**
     * 删除订单管理
     */
    @PreAuthorize("@ss.hasPermi('mesoder:order:remove')")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(mesOrderService.deleteMesOrderByOrderIds(orderIds));
    }
}
