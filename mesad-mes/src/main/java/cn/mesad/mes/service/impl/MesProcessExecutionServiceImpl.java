package cn.mesad.mes.service.impl;

import java.util.List;
import cn.mesad.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mesad.mes.mapper.MesProcessExecutionMapper;
import cn.mesad.mes.domain.MesProcessExecution;
import cn.mesad.mes.service.IMesProcessExecutionService;

/**
 * 生产工序执行Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-25
 */
@Service
public class MesProcessExecutionServiceImpl implements IMesProcessExecutionService 
{
    @Autowired
    private MesProcessExecutionMapper mesProcessExecutionMapper;

    /**
     * 查询生产工序执行
     * 
     * @param processExecutionId 生产工序执行主键
     * @return 生产工序执行
     */
    @Override
    public MesProcessExecution selectMesProcessExecutionByProcessExecutionId(Long processExecutionId)
    {
        return mesProcessExecutionMapper.selectMesProcessExecutionByProcessExecutionId(processExecutionId);
    }

    /**
     * 查询生产工序执行列表
     * 
     * @param mesProcessExecution 生产工序执行
     * @return 生产工序执行
     */
    @Override
    public List<MesProcessExecution> selectMesProcessExecutionList(MesProcessExecution mesProcessExecution)
    {
        return mesProcessExecutionMapper.selectMesProcessExecutionList(mesProcessExecution);
    }

    /**
     * 新增生产工序执行
     * 
     * @param mesProcessExecution 生产工序执行
     * @return 结果
     */
    @Override
    public int insertMesProcessExecution(MesProcessExecution mesProcessExecution)
    {
        mesProcessExecution.setCreateTime(DateUtils.getNowDate());
        return mesProcessExecutionMapper.insertMesProcessExecution(mesProcessExecution);
    }

    /**
     * 修改生产工序执行
     * 
     * @param mesProcessExecution 生产工序执行
     * @return 结果
     */
    @Override
    public int updateMesProcessExecution(MesProcessExecution mesProcessExecution)
    {
        mesProcessExecution.setUpdateTime(DateUtils.getNowDate());
        return mesProcessExecutionMapper.updateMesProcessExecution(mesProcessExecution);
    }

    /**
     * 批量删除生产工序执行
     * 
     * @param processExecutionIds 需要删除的生产工序执行主键
     * @return 结果
     */
    @Override
    public int deleteMesProcessExecutionByProcessExecutionIds(Long[] processExecutionIds)
    {
        return mesProcessExecutionMapper.deleteMesProcessExecutionByProcessExecutionIds(processExecutionIds);
    }

    /**
     * 删除生产工序执行信息
     * 
     * @param processExecutionId 生产工序执行主键
     * @return 结果
     */
    @Override
    public int deleteMesProcessExecutionByProcessExecutionId(Long processExecutionId)
    {
        return mesProcessExecutionMapper.deleteMesProcessExecutionByProcessExecutionId(processExecutionId);
    }
}
