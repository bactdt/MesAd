package cn.mesad.mes.service.impl;

import java.util.List;
import cn.mesad.common.utils.DateUtils;
import cn.mesad.mes.domain.MesOrder;
import cn.mesad.mes.domain.MesOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mesad.mes.mapper.MesOrderScheduleMapper;
import cn.mesad.mes.domain.MesOrderSchedule;
import cn.mesad.mes.service.IMesOrderScheduleService;

/**
 * 订单排产Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-22
 */
@Service
public class MesOrderScheduleServiceImpl implements IMesOrderScheduleService 
{
    @Autowired
    private MesOrderScheduleMapper mesOrderScheduleMapper;

    /**
     * 查询订单排产
     * 
     * @param scheduleId 订单排产主键
     * @return 订单排产
     */
    @Override
    public MesOrderSchedule selectMesOrderScheduleByScheduleId(Long scheduleId)
    {
        return mesOrderScheduleMapper.selectMesOrderScheduleByScheduleId(scheduleId);
    }

    /**
     * 查询订单排产列表
     * 
     * @param mesOrderSchedule 订单排产
     * @return 订单排产
     */
    @Override
    public List<MesOrderSchedule> selectMesOrderScheduleList(MesOrderSchedule mesOrderSchedule)
    {
        return mesOrderScheduleMapper.selectMesOrderScheduleList(mesOrderSchedule);
    }
    /**
     * 查询订单连表查询列表
     *
     * @param mesOrderScheduleList 订单排产
     * @return 订单排产
     */
    @Override
    public List<MesOrderSchedule> selectMesOrderScheduleListChange(MesOrder mesOrder, MesOrderDetail mesOrderDetail)
    {
        return mesOrderScheduleMapper.selectMesOrderScheduleListChange(mesOrder, mesOrderDetail);
    }

    /**
     * 新增订单排产
     * 
     * @param mesOrderSchedule 订单排产
     * @return 结果
     */
    @Override
    public int insertMesOrderSchedule(MesOrderSchedule mesOrderSchedule)
    {
        mesOrderSchedule.setCreateTime(DateUtils.getNowDate());
        return mesOrderScheduleMapper.insertMesOrderSchedule(mesOrderSchedule);
    }

    /**
     * 修改订单排产
     * 
     * @param mesOrderSchedule 订单排产
     * @return 结果
     */
    @Override
    public int updateMesOrderSchedule(MesOrderSchedule mesOrderSchedule)
    {
        mesOrderSchedule.setUpdateTime(DateUtils.getNowDate());
        return mesOrderScheduleMapper.updateMesOrderSchedule(mesOrderSchedule);
    }

    /**
     * 批量删除订单排产
     * 
     * @param scheduleIds 需要删除的订单排产主键
     * @return 结果
     */
    @Override
    public int deleteMesOrderScheduleByScheduleIds(Long[] scheduleIds)
    {
        return mesOrderScheduleMapper.deleteMesOrderScheduleByScheduleIds(scheduleIds);
    }

    /**
     * 删除订单排产信息
     * 
     * @param scheduleId 订单排产主键
     * @return 结果
     */
    @Override
    public int deleteMesOrderScheduleByScheduleId(Long scheduleId)
    {
        return mesOrderScheduleMapper.deleteMesOrderScheduleByScheduleId(scheduleId);
    }
}
