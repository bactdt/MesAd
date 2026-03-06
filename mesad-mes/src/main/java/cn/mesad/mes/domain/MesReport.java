package cn.mesad.mes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.mesad.common.annotation.Excel;
import cn.mesad.common.core.domain.BaseEntity;

/**
 * 生产报表对象 mes_report
 *
 * @author ruoyi
 * @date 2025-04-21
 */
public class MesReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报表ID */
    private Long reportId;

    /** 报表名称 */
    @Excel(name = "报表名称")
    private String reportName;

    /** 报表类型（日报 周报 月报） */
    @Excel(name = "报表类型")
    private String reportType;

    /** 报表内容 */
    @Excel(name = "报表内容")
    private String reportContent;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public Long getReportId()
    {
        return reportId;
    }

    public void setReportName(String reportName)
    {
        this.reportName = reportName;
    }

    public String getReportName()
    {
        return reportName;
    }

    public void setReportType(String reportType)
    {
        this.reportType = reportType;
    }

    public String getReportType()
    {
        return reportType;
    }

    public void setReportContent(String reportContent)
    {
        this.reportContent = reportContent;
    }

    public String getReportContent()
    {
        return reportContent;
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
            .append("reportId", getReportId())
            .append("reportName", getReportName())
            .append("reportType", getReportType())
            .append("reportContent", getReportContent())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
