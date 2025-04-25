package cn.mesad.mes.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.mesad.common.annotation.Excel;
import cn.mesad.common.core.domain.BaseEntity;

/**
 * 生产工序执行对象 mes_process_execution
 * 
 * @author ruoyi
 * @date 2025-04-25
 */
public class MesProcessExecution extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序执行ID */
    private Long processExecutionId;

    /** 生产执行ID */
    @Excel(name = "生产执行ID")
    private Long executionId;

    /** 工序ID */
    @Excel(name = "工序ID")
    private Long processId;

    /** 工序编码 */
    @Excel(name = "工序编码")
    private String processCode;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

    /** 工位ID */
    @Excel(name = "工位ID")
    private Long workstationId;

    /** 工位编码 */
    @Excel(name = "工位编码")
    private String workstationCode;

    /** 工位名称 */
    @Excel(name = "工位名称")
    private String workstationName;

    /** 操作工ID */
    @Excel(name = "操作工ID")
    private Long workerId;

    /** 操作工姓名 */
    @Excel(name = "操作工姓名")
    private String workerName;

    /** 计划生产数 */
    @Excel(name = "计划生产数")
    private BigDecimal planQuantity;

    /** 实际生产数 */
    @Excel(name = "实际生产数")
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
    private Date startTime;

    /** 结束时间 */
    private Date endTime;

    /** 工序状态 */
    @Excel(name = "工序状态")
    private String processStatus;

    public void setProcessExecutionId(Long processExecutionId) 
    {
        this.processExecutionId = processExecutionId;
    }

    public Long getProcessExecutionId() 
    {
        return processExecutionId;
    }

    public void setExecutionId(Long executionId) 
    {
        this.executionId = executionId;
    }

    public Long getExecutionId() 
    {
        return executionId;
    }

    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }

    public void setProcessCode(String processCode) 
    {
        this.processCode = processCode;
    }

    public String getProcessCode() 
    {
        return processCode;
    }

    public void setProcessName(String processName) 
    {
        this.processName = processName;
    }

    public String getProcessName() 
    {
        return processName;
    }

    public void setWorkstationId(Long workstationId) 
    {
        this.workstationId = workstationId;
    }

    public Long getWorkstationId() 
    {
        return workstationId;
    }

    public void setWorkstationCode(String workstationCode) 
    {
        this.workstationCode = workstationCode;
    }

    public String getWorkstationCode() 
    {
        return workstationCode;
    }

    public void setWorkstationName(String workstationName) 
    {
        this.workstationName = workstationName;
    }

    public String getWorkstationName() 
    {
        return workstationName;
    }

    public void setWorkerId(Long workerId) 
    {
        this.workerId = workerId;
    }

    public Long getWorkerId() 
    {
        return workerId;
    }

    public void setWorkerName(String workerName) 
    {
        this.workerName = workerName;
    }

    public String getWorkerName() 
    {
        return workerName;
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

    public void setProcessStatus(String processStatus) 
    {
        this.processStatus = processStatus;
    }

    public String getProcessStatus() 
    {
        return processStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("processExecutionId", getProcessExecutionId())
            .append("executionId", getExecutionId())
            .append("processId", getProcessId())
            .append("processCode", getProcessCode())
            .append("processName", getProcessName())
            .append("workstationId", getWorkstationId())
            .append("workstationCode", getWorkstationCode())
            .append("workstationName", getWorkstationName())
            .append("workerId", getWorkerId())
            .append("workerName", getWorkerName())
            .append("planQuantity", getPlanQuantity())
            .append("actualQuantity", getActualQuantity())
            .append("qualifiedQuantity", getQualifiedQuantity())
            .append("unqualifiedQuantity", getUnqualifiedQuantity())
            .append("unit", getUnit())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("processStatus", getProcessStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
