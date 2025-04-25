package cn.mesad.mes.service.impl;

import java.util.List;
import cn.mesad.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mesad.mes.mapper.MesProductionParameterMapper;
import cn.mesad.mes.domain.MesProductionParameter;
import cn.mesad.mes.service.IMesProductionParameterService;

/**
 * 生产参数记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-25
 */
@Service
public class MesProductionParameterServiceImpl implements IMesProductionParameterService 
{
    @Autowired
    private MesProductionParameterMapper mesProductionParameterMapper;

    /**
     * 查询生产参数记录
     * 
     * @param parameterId 生产参数记录主键
     * @return 生产参数记录
     */
    @Override
    public MesProductionParameter selectMesProductionParameterByParameterId(Long parameterId)
    {
        return mesProductionParameterMapper.selectMesProductionParameterByParameterId(parameterId);
    }

    /**
     * 查询生产参数记录列表
     * 
     * @param mesProductionParameter 生产参数记录
     * @return 生产参数记录
     */
    @Override
    public List<MesProductionParameter> selectMesProductionParameterList(MesProductionParameter mesProductionParameter)
    {
        return mesProductionParameterMapper.selectMesProductionParameterList(mesProductionParameter);
    }

    /**
     * 新增生产参数记录
     * 
     * @param mesProductionParameter 生产参数记录
     * @return 结果
     */
    @Override
    public int insertMesProductionParameter(MesProductionParameter mesProductionParameter)
    {
        mesProductionParameter.setCreateTime(DateUtils.getNowDate());
        return mesProductionParameterMapper.insertMesProductionParameter(mesProductionParameter);
    }

    /**
     * 修改生产参数记录
     * 
     * @param mesProductionParameter 生产参数记录
     * @return 结果
     */
    @Override
    public int updateMesProductionParameter(MesProductionParameter mesProductionParameter)
    {
        mesProductionParameter.setUpdateTime(DateUtils.getNowDate());
        return mesProductionParameterMapper.updateMesProductionParameter(mesProductionParameter);
    }

    /**
     * 批量删除生产参数记录
     * 
     * @param parameterIds 需要删除的生产参数记录主键
     * @return 结果
     */
    @Override
    public int deleteMesProductionParameterByParameterIds(Long[] parameterIds)
    {
        return mesProductionParameterMapper.deleteMesProductionParameterByParameterIds(parameterIds);
    }

    /**
     * 删除生产参数记录信息
     * 
     * @param parameterId 生产参数记录主键
     * @return 结果
     */
    @Override
    public int deleteMesProductionParameterByParameterId(Long parameterId)
    {
        return mesProductionParameterMapper.deleteMesProductionParameterByParameterId(parameterId);
    }
}
