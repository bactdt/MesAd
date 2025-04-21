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
import cn.mesad.mes.domain.MesCustomer;
import cn.mesad.mes.service.IMesCustomerService;
import cn.mesad.common.utils.poi.ExcelUtil;
import cn.mesad.common.core.page.TableDataInfo;

/**
 * 客户Controller
 * 
 * @author ruoyi
 * @date 2025-04-20
 */
@RestController
@RequestMapping("/customer/customer")
public class MesCustomerController extends BaseController
{
    @Autowired
    private IMesCustomerService mesCustomerService;

    /**
     * 查询客户列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(MesCustomer mesCustomer)
    {
        startPage();
        List<MesCustomer> list = mesCustomerService.selectMesCustomerList(mesCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MesCustomer mesCustomer)
    {
        List<MesCustomer> list = mesCustomerService.selectMesCustomerList(mesCustomer);
        ExcelUtil<MesCustomer> util = new ExcelUtil<MesCustomer>(MesCustomer.class);
        util.exportExcel(response, list, "客户数据");
    }

    /**
     * 获取客户详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{customerId}")
    public AjaxResult getInfo(@PathVariable("customerId") Long customerId)
    {
        return success(mesCustomerService.selectMesCustomerByCustomerId(customerId));
    }

    /**
     * 新增客户
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesCustomer mesCustomer)
    {
        return toAjax(mesCustomerService.insertMesCustomer(mesCustomer));
    }

    /**
     * 修改客户
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesCustomer mesCustomer)
    {
        return toAjax(mesCustomerService.updateMesCustomer(mesCustomer));
    }

    /**
     * 删除客户
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "客户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable Long[] customerIds)
    {
        return toAjax(mesCustomerService.deleteMesCustomerByCustomerIds(customerIds));
    }
}
