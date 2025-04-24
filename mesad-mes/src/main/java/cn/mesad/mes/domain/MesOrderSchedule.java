package cn.mesad.mes.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.mesad.common.annotation.Excel;
import cn.mesad.common.core.domain.BaseEntity;

/**
 * 订单排产对象 mes_order_schedule
 * 
 * @author ruoyi
 * @date 2025-04-22
 */
public class MesOrderSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排产ID */
    private Long scheduleId;

    /** 订单ID */
    private Long orderId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderCode;

    /** 订单明细ID */
    private Long detailId;

    /** 产品ID */
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 车间ID */
    private Long workshopId;

    /** 车间名称 */
    @Excel(name = "车间名称")
    private String workshopName;

    /** 生产线ID */
    private Long lineId;

    /** 生产线名称 */
    @Excel(name = "生产线名称")
    private String lineName;

    /** 排产数量 */
    @Excel(name = "排产数量")
    private BigDecimal scheduleQuantity;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 计划开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planStartTime;

    /** 计划结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planEndTime;

    /** 实际开始时间 */
    private Date actualStartTime;

    /** 实际结束时间 */
    private Date actualEndTime;

    /** 排产状态 */
    @Excel(name = "排产状态")
    private String scheduleStatus;

    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }

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

    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
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

    public void setWorkshopId(Long workshopId) 
    {
        this.workshopId = workshopId;
    }

    public Long getWorkshopId() 
    {
        return workshopId;
    }

    public void setWorkshopName(String workshopName) 
    {
        this.workshopName = workshopName;
    }

    public String getWorkshopName() 
    {
        return workshopName;
    }

    public void setLineId(Long lineId) 
    {
        this.lineId = lineId;
    }

    public Long getLineId() 
    {
        return lineId;
    }

    public void setLineName(String lineName) 
    {
        this.lineName = lineName;
    }

    public String getLineName() 
    {
        return lineName;
    }

    public void setScheduleQuantity(BigDecimal scheduleQuantity) 
    {
        this.scheduleQuantity = scheduleQuantity;
    }

    public BigDecimal getScheduleQuantity() 
    {
        return scheduleQuantity;
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

    public void setScheduleStatus(String scheduleStatus) 
    {
        this.scheduleStatus = scheduleStatus;
    }

    public String getScheduleStatus() 
    {
        return scheduleStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scheduleId", getScheduleId())
            .append("orderId", getOrderId())
            .append("orderCode", getOrderCode())
            .append("detailId", getDetailId())
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("workshopId", getWorkshopId())
            .append("workshopName", getWorkshopName())
            .append("lineId", getLineId())
            .append("lineName", getLineName())
            .append("scheduleQuantity", getScheduleQuantity())
            .append("unit", getUnit())
            .append("planStartTime", getPlanStartTime())
            .append("planEndTime", getPlanEndTime())
            .append("actualStartTime", getActualStartTime())
            .append("actualEndTime", getActualEndTime())
            .append("scheduleStatus", getScheduleStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
