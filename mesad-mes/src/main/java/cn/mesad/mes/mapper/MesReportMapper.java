package cn.mesad.mes.mapper;

import java.util.List;
import cn.mesad.mes.domain.MesReport;
import org.apache.ibatis.annotations.Mapper;

/**
 * 生产报表Mapper接口
 *
 * @author ruoyi
 * @date 2025-04-21
 */
@Mapper
public interface MesReportMapper
{
    public MesReport selectMesReportByReportId(Long reportId);

    public List<MesReport> selectMesReportList(MesReport mesReport);

    public int insertMesReport(MesReport mesReport);

    public int updateMesReport(MesReport mesReport);

    public int deleteMesReportByReportId(Long reportId);

    public int deleteMesReportByReportIds(Long[] reportIds);
}
