package cn.mesad.mes.service;

import java.util.List;
import cn.mesad.mes.domain.MesReport;

/**
 * 生产报表Service接口
 *
 * @author ruoyi
 * @date 2025-04-21
 */
public interface IMesReportService
{
    public MesReport selectMesReportByReportId(Long reportId);

    public List<MesReport> selectMesReportList(MesReport mesReport);

    public int insertMesReport(MesReport mesReport);

    public int updateMesReport(MesReport mesReport);

    public int deleteMesReportByReportIds(Long[] reportIds);

    public int deleteMesReportByReportId(Long reportId);
}
