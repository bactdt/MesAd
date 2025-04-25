package cn.mesad.mes.mapper;

import java.util.List;
import cn.mesad.mes.domain.MesProcessExecution;

/**
 * 生产工序执行Mapper接口
 * 
 * @author ruoyi
 * @date 2025-04-25
 */
public interface MesProcessExecutionMapper 
{
    /**
     * 查询生产工序执行
     * 
     * @param processExecutionId 生产工序执行主键
     * @return 生产工序执行
     */
    public MesProcessExecution selectMesProcessExecutionByProcessExecutionId(Long processExecutionId);

    /**
     * 查询生产工序执行列表
     * 
     * @param mesProcessExecution 生产工序执行
     * @return 生产工序执行集合
     */
    public List<MesProcessExecution> selectMesProcessExecutionList(MesProcessExecution mesProcessExecution);

    /**
     * 新增生产工序执行
     * 
     * @param mesProcessExecution 生产工序执行
     * @return 结果
     */
    public int insertMesProcessExecution(MesProcessExecution mesProcessExecution);

    /**
     * 修改生产工序执行
     * 
     * @param mesProcessExecution 生产工序执行
     * @return 结果
     */
    public int updateMesProcessExecution(MesProcessExecution mesProcessExecution);

    /**
     * 删除生产工序执行
     * 
     * @param processExecutionId 生产工序执行主键
     * @return 结果
     */
    public int deleteMesProcessExecutionByProcessExecutionId(Long processExecutionId);

    /**
     * 批量删除生产工序执行
     * 
     * @param processExecutionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMesProcessExecutionByProcessExecutionIds(Long[] processExecutionIds);
}
