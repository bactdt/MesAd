package cn.mesad.mes.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.mesad.common.annotation.Excel;
import cn.mesad.common.core.domain.BaseEntity;

/**
 * 生产执行对象 mes_production_execution
 * 
 * @author ruoyi
 * @date 2025-04-22
 */
public class MesProductionExecution extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 执行ID */
    private Long executionId;

    /** 排产ID */
    private Long scheduleId;

    /** 订单ID */
    private Long orderId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderCode;

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

    /** 计划生产数量 */
    @Excel(name = "计划生产数量")
    private BigDecimal planQuantity;

    /** 实际生产数量 */
    @Excel(name = "实际生产数量")
    private BigDecimal actualQuantity;

    /** 合格数量 */
    @Excel(name = "合格数量")
    private BigDecimal qualifiedQuantity;

    /** 不合格数量 */
    @Excel(name = "不合格数量")
    private BigDecimal unqualifiedQuantity;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 执行状态（0未开始 1进行中 2已暂停 3已完成 4已取消） */
    @Excel(name = "执行状态", readConverterExp = "0=未开始,1=进行中,2=已暂停,3=已完成,4=已取消")
    private String executionStatus;

    public void setExecutionId(Long executionId) 
    {
        this.executionId = executionId;
    }

    public Long getExecutionId() 
    {
        return executionId;
    }

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

    public void setPlanQuantity(BigDecimal planQuantity) 
    {
        this.planQuantity = planQuantity;
    }

    public BigDecimal getPlanQuantity() 
    {
        return planQuantity;
    }

    public void setActualQuantity(BigDecimal actualQuantity) 
    {
        this.actualQuantity = actualQuantity;
    }

    public BigDecimal getActualQuantity() 
    {
        return actualQuantity;
    }

    public void setQualifiedQuantity(BigDecimal qualifiedQuantity) 
    {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    public BigDecimal getQualifiedQuantity() 
    {
        return qualifiedQuantity;
    }

    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) 
    {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    public BigDecimal getUnqualifiedQuantity() 
    {
        return unqualifiedQuantity;
    }

    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setExecutionStatus(String executionStatus) 
    {
        this.executionStatus = executionStatus;
    }

    public String getExecutionStatus() 
    {
        return executionStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("executionId", getExecutionId())
            .append("scheduleId", getScheduleId())
            .append("orderId", getOrderId())
            .append("orderCode", getOrderCode())
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("workshopId", getWorkshopId())
            .append("workshopName", getWorkshopName())
            .append("lineId", getLineId())
            .append("lineName", getLineName())
            .append("planQuantity", getPlanQuantity())
            .append("actualQuantity", getActualQuantity())
            .append("qualifiedQuantity", getQualifiedQuantity())
            .append("unqualifiedQuantity", getUnqualifiedQuantity())
            .append("unit", getUnit())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("executionStatus", getExecutionStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
