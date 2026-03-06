package cn.mesad.mes.service.impl;

import java.util.List;
import cn.mesad.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mesad.mes.mapper.MesReportMapper;
import cn.mesad.mes.domain.MesReport;
import cn.mesad.mes.service.IMesReportService;

/**
 * 生产报表Service业务层处理
 *
 * @author ruoyi
 * @date 2025-04-21
 */
@Service
public class MesReportServiceImpl implements IMesReportService
{
    @Autowired
    private MesReportMapper mesReportMapper;

    @Override
    public MesReport selectMesReportByReportId(Long reportId)
    {
        return mesReportMapper.selectMesReportByReportId(reportId);
    }

    @Override
    public List<MesReport> selectMesReportList(MesReport mesReport)
    {
        return mesReportMapper.selectMesReportList(mesReport);
    }

    @Override
    public int insertMesReport(MesReport mesReport)
    {
        mesReport.setCreateTime(DateUtils.getNowDate());
        return mesReportMapper.insertMesReport(mesReport);
    }

    @Override
    public int updateMesReport(MesReport mesReport)
    {
        mesReport.setUpdateTime(DateUtils.getNowDate());
        return mesReportMapper.updateMesReport(mesReport);
    }

    @Override
    public int deleteMesReportByReportIds(Long[] reportIds)
    {
        return mesReportMapper.deleteMesReportByReportIds(reportIds);
    }

    @Override
    public int deleteMesReportByReportId(Long reportId)
    {
        return mesReportMapper.deleteMesReportByReportId(reportId);
    }
}
