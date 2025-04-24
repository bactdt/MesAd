package cn.mesad.mes.service;

import java.util.List;
import cn.mesad.mes.domain.MesProductionExecution;

/**
 * 生产执行Service接口
 * 
 * @author ruoyi
 * @date 2025-04-22
 */
public interface IMesProductionExecutionService 
{
    /**
     * 查询生产执行
     * 
     * @param executionId 生产执行主键
     * @return 生产执行
     */
    public MesProductionExecution selectMesProductionExecutionByExecutionId(Long executionId);

    /**
     * 查询生产执行列表
     * 
     * @param mesProductionExecution 生产执行
     * @return 生产执行集合
     */
    public List<MesProductionExecution> selectMesProductionExecutionList(MesProductionExecution mesProductionExecution);

    /**
     * 新增生产执行
     * 
     * @param mesProductionExecution 生产执行
     * @return 结果
     */
    public int insertMesProductionExecution(MesProductionExecution mesProductionExecution);

    /**
     * 修改生产执行
     * 
     * @param mesProductionExecution 生产执行
     * @return 结果
     */
    public int updateMesProductionExecution(MesProductionExecution mesProductionExecution);

    /**
     * 批量删除生产执行
     * 
     * @param executionIds 需要删除的生产执行主键集合
     * @return 结果
     */
    public int deleteMesProductionExecutionByExecutionIds(Long[] executionIds);

    /**
     * 删除生产执行信息
     * 
     * @param executionId 生产执行主键
     * @return 结果
     */
    public int deleteMesProductionExecutionByExecutionId(Long executionId);
}
