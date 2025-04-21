package cn.mesad.mes.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.mesad.common.annotation.Excel;
import cn.mesad.common.core.domain.BaseEntity;

/**
 * 订单明细对象 mes_order_detail
 * 
 * @author ruoyi
 * @date 2025-04-21
 */
public class MesOrderDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long detailId;

    /** 订单ID */
    @Excel(name = "订单ID")
    private Long orderId;

    /** 产品ID */
    @Excel(name = "产品ID")
    private Long productId;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String specification;

    /** 数量 */
    @Excel(name = "数量")
    private BigDecimal quantity;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 已排产数量 */
    @Excel(name = "已排产数量")
    private BigDecimal planQuantity;

    /** 已生产数量 */
    @Excel(name = "已生产数量")
    private BigDecimal producedQuantity;

    /** 合格数量 */
    @Excel(name = "合格数量")
    private BigDecimal qualifiedQuantity;

    /** 不合格数量 */
    @Excel(name = "不合格数量")
    private BigDecimal unqualifiedQuantity;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
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
    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
    }
    public void setQuantity(BigDecimal quantity) 
    {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() 
    {
        return quantity;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setPlanQuantity(BigDecimal planQuantity) 
    {
        this.planQuantity = planQuantity;
    }

    public BigDecimal getPlanQuantity() 
    {
        return planQuantity;
    }
    public void setProducedQuantity(BigDecimal producedQuantity) 
    {
        this.producedQuantity = producedQuantity;
    }

    public BigDecimal getProducedQuantity() 
    {
        return producedQuantity;
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
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("detailId", getDetailId())
            .append("orderId", getOrderId())
            .append("productId", getProductId())
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("specification", getSpecification())
            .append("quantity", getQuantity())
            .append("unit", getUnit())
            .append("planQuantity", getPlanQuantity())
            .append("producedQuantity", getProducedQuantity())
            .append("qualifiedQuantity", getQualifiedQuantity())
            .append("unqualifiedQuantity", getUnqualifiedQuantity())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
