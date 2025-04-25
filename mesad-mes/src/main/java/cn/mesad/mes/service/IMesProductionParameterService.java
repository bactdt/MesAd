package cn.mesad.mes.service;

import java.util.List;
import cn.mesad.mes.domain.MesProductionParameter;

/**
 * 生产参数记录Service接口
 * 
 * @author ruoyi
 * @date 2025-04-25
 */
public interface IMesProductionParameterService 
{
    /**
     * 查询生产参数记录
     * 
     * @param parameterId 生产参数记录主键
     * @return 生产参数记录
     */
    public MesProductionParameter selectMesProductionParameterByParameterId(Long parameterId);

    /**
     * 查询生产参数记录列表
     * 
     * @param mesProductionParameter 生产参数记录
     * @return 生产参数记录集合
     */
    public List<MesProductionParameter> selectMesProductionParameterList(MesProductionParameter mesProductionParameter);

    /**
     * 新增生产参数记录
     * 
     * @param mesProductionParameter 生产参数记录
     * @return 结果
     */
    public int insertMesProductionParameter(MesProductionParameter mesProductionParameter);

    /**
     * 修改生产参数记录
     * 
     * @param mesProductionParameter 生产参数记录
     * @return 结果
     */
    public int updateMesProductionParameter(MesProductionParameter mesProductionParameter);

    /**
     * 批量删除生产参数记录
     * 
     * @param parameterIds 需要删除的生产参数记录主键集合
     * @return 结果
     */
    public int deleteMesProductionParameterByParameterIds(Long[] parameterIds);

    /**
     * 删除生产参数记录信息
     * 
     * @param parameterId 生产参数记录主键
     * @return 结果
     */
    public int deleteMesProductionParameterByParameterId(Long parameterId);
}
