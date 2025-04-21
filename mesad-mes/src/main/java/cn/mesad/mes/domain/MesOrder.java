package cn.mesad.mes.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oracle.webservices.internal.api.message.PropertySet;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.mesad.common.annotation.Excel;
import cn.mesad.common.core.domain.BaseEntity;
import javax.persistence.PrePersist;
/**
 * 订单管理对象 mes_order
 * 
 * @author ruoyi
 * @date 2025-04-21
 */
public class MesOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Long orderId;

    /** 订单编号 */
    @Excel(name = "订单编号")

    private String orderCode;

    /** 客户ID */
    private Long customerId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 产品ID */
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 订单数量 */
    @Excel(name = "订单数量")
    private BigDecimal orderQuantity;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 计划开始 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planStartTime;

    /** 计划结束 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结束", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planEndTime;

    /** 实际开始 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际开始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualStartTime;

    /** 实际结束 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际结束", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualEndTime;

    /** 优先级 */
    @Excel(name = "优先级")
    private Long priority;

    /** 订单状态（0待排产 1已排产 2生产中 3已完成 4已取消） */
    @Excel(name = "订单状态", readConverterExp = "0=待排产,1=已排产,2=生产中,3=已完成,4=已取消")
    private String orderStatus;



    /** 订单明细信息 */
    private List<MesOrderDetail> mesOrderDetailList;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }

    public void setOrderCode(String orderCode) 
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }

    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }

    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }

    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }

    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) 
    {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getOrderQuantity() 
    {
        return orderQuantity;
    }

    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }

    public void setPlanStartTime(Date planStartTime) 
    {
        this.planStartTime = planStartTime;
    }

    public Date getPlanStartTime() 
    {
        return planStartTime;
    }

    public void setPlanEndTime(Date planEndTime) 
    {
        this.planEndTime = planEndTime;
    }

    public Date getPlanEndTime() 
    {
        return planEndTime;
    }

    public void setActualStartTime(Date actualStartTime) 
    {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualStartTime() 
    {
        return actualStartTime;
    }

    public void setActualEndTime(Date actualEndTime) 
    {
        this.actualEndTime = actualEndTime;
    }

    public Date getActualEndTime() 
    {
        return actualEndTime;
    }

    public void setPriority(Long priority) 
    {
        this.priority = priority;
    }

    public Long getPriority() 
    {
        return priority;
    }

    public void setOrderStatus(String orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() 
    {
        return orderStatus;
    }

    public List<MesOrderDetail> getMesOrderDetailList()
    {
        return mesOrderDetailList;
    }

    public void setMesOrderDetailList(List<MesOrderDetail> mesOrderDetailList)
    {
        this.mesOrderDetailList = mesOrderDetailList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("orderCode", getOrderCode())
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("orderQuantity", getOrderQuantity())
            .append("unit", getUnit())
            .append("planStartTime", getPlanStartTime())
            .append("planEndTime", getPlanEndTime())
            .append("actualStartTime", getActualStartTime())
            .append("actualEndTime", getActualEndTime())
            .append("priority", getPriority())
            .append("orderStatus", getOrderStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("mesOrderDetailList", getMesOrderDetailList())
            .toString();
    }
}
