package cn.mesad.mes.service.impl;

import java.util.List;
import cn.mesad.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.mesad.mes.mapper.MesProductionExecutionMapper;
import cn.mesad.mes.domain.MesProductionExecution;
import cn.mesad.mes.service.IMesProductionExecutionService;

/**
 * 生产执行Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-22
 */
@Service
public class MesProductionExecutionServiceImpl implements IMesProductionExecutionService 
{
    @Autowired
    private MesProductionExecutionMapper mesProductionExecutionMapper;

    /**
     * 查询生产执行
     * 
     * @param executionId 生产执行主键
     * @return 生产执行
     */
    @Override
    public MesProductionExecution selectMesProductionExecutionByExecutionId(Long executionId)
    {
        return mesProductionExecutionMapper.selectMesProductionExecutionByExecutionId(executionId);
    }

    /**
     * 查询生产执行列表
     * 
     * @param mesProductionExecution 生产执行
     * @return 生产执行
     */
    @Override
    public List<MesProductionExecution> selectMesProductionExecutionList(MesProductionExecution mesProductionExecution)
    {
        return mesProductionExecutionMapper.selectMesProductionExecutionList(mesProductionExecution);
    }

    /**
     * 新增生产执行
     * 
     * @param mesProductionExecution 生产执行
     * @return 结果
     */
    @Override
    @Transactional
    public int insertMesProductionExecution(MesProductionExecution mesProductionExecution)
    {
        mesProductionExecution.setCreateTime(DateUtils.getNowDate());
        int rows = mesProductionExecutionMapper.insertMesProductionExecution(mesProductionExecution);
        return rows;
    }

    /**
     * 修改生产执行
     * 
     * @param mesProductionExecution 生产执行
     * @return 结果
     */
    @Override
    @Transactional
    public int updateMesProductionExecution(MesProductionExecution mesProductionExecution)
    {
        mesProductionExecution.setUpdateTime(DateUtils.getNowDate());
        return mesProductionExecutionMapper.updateMesProductionExecution(mesProductionExecution);
    }

    /**
     * 批量删除生产执行
     * 
     * @param executionIds 需要删除的生产执行主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteMesProductionExecutionByExecutionIds(Long[] executionIds)
    {
        return mesProductionExecutionMapper.deleteMesProductionExecutionByExecutionIds(executionIds);
    }

    /**
     * 删除生产执行信息
     * 
     * @param executionId 生产执行主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteMesProductionExecutionByExecutionId(Long executionId)
    {
        return mesProductionExecutionMapper.deleteMesProductionExecutionByExecutionId(executionId);
    }
    

}
