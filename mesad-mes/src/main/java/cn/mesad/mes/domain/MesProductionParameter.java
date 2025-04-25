package cn.mesad.mes.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.mesad.common.annotation.Excel;
import cn.mesad.common.core.domain.BaseEntity;

/**
 * 生产参数记录对象 mes_production_parameter
 * 
 * @author ruoyi
 * @date 2025-04-25
 */
public class MesProductionParameter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 参数ID */
    private Long parameterId;

    /** 工序执行ID */
    private Long processExecutionId;

    /** 参数名称 */
    @Excel(name = "参数名称")
    private String parameterName;

    /** 参数编码 */
    @Excel(name = "参数编码")
    private String parameterCode;

    /** 参数值 */
    @Excel(name = "参数值")
    private String parameterValue;

    /** 标准值 */
    @Excel(name = "标准值")
    private String standardValue;

    /** 上限 */
    @Excel(name = "上限")
    private String upperLimit;

    /** 下限 */
    @Excel(name = "下限")
    private String lowerLimit;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 是否合格（0不合格 1合格） */
    private String isQualified;

    /** 记录时间 */
    private Date recordTime;

    public void setParameterId(Long parameterId) 
    {
        this.parameterId = parameterId;
    }

    public Long getParameterId() 
    {
        return parameterId;
    }

    public void setProcessExecutionId(Long processExecutionId) 
    {
        this.processExecutionId = processExecutionId;
    }

    public Long getProcessExecutionId() 
    {
        return processExecutionId;
    }

    public void setParameterName(String parameterName) 
    {
        this.parameterName = parameterName;
    }

    public String getParameterName() 
    {
        return parameterName;
    }

    public void setParameterCode(String parameterCode) 
    {
        this.parameterCode = parameterCode;
    }

    public String getParameterCode() 
    {
        return parameterCode;
    }

    public void setParameterValue(String parameterValue) 
    {
        this.parameterValue = parameterValue;
    }

    public String getParameterValue() 
    {
        return parameterValue;
    }

    public void setStandardValue(String standardValue) 
    {
        this.standardValue = standardValue;
    }

    public String getStandardValue() 
    {
        return standardValue;
    }

    public void setUpperLimit(String upperLimit) 
    {
        this.upperLimit = upperLimit;
    }

    public String getUpperLimit() 
    {
        return upperLimit;
    }

    public void setLowerLimit(String lowerLimit) 
    {
        this.lowerLimit = lowerLimit;
    }

    public String getLowerLimit() 
    {
        return lowerLimit;
    }

    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }

    public void setIsQualified(String isQualified) 
    {
        this.isQualified = isQualified;
    }

    public String getIsQualified() 
    {
        return isQualified;
    }

    public void setRecordTime(Date recordTime) 
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() 
    {
        return recordTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("parameterId", getParameterId())
            .append("processExecutionId", getProcessExecutionId())
            .append("parameterName", getParameterName())
            .append("parameterCode", getParameterCode())
            .append("parameterValue", getParameterValue())
            .append("standardValue", getStandardValue())
            .append("upperLimit", getUpperLimit())
            .append("lowerLimit", getLowerLimit())
            .append("unit", getUnit())
            .append("isQualified", getIsQualified())
            .append("recordTime", getRecordTime())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
