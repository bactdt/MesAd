package cn.mesad.mes.service;

import java.util.List;

import cn.mesad.mes.domain.MesOrder;
import cn.mesad.mes.domain.MesOrderDetail;
import cn.mesad.mes.domain.MesOrderSchedule;

/**
 * 订单排产Service接口
 * 
 * @author ruoyi
 * @date 2025-04-22
 */
public interface IMesOrderScheduleService 
{
    /**
     * 查询订单排产
     * 
     * @param scheduleId 订单排产主键
     * @return 订单排产
     */
    public MesOrderSchedule selectMesOrderScheduleByScheduleId(Long scheduleId);

    /**
     * 查询订单排产列表
     * 
     * @param mesOrderSchedule 订单排产
     * @return 订单排产集合
     */
    public List<MesOrderSchedule> selectMesOrderScheduleList(MesOrderSchedule mesOrderSchedule);

    /**
     * 新增订单排产
     * 
     * @param mesOrderSchedule 订单排产
     * @return 结果
     */
    public int insertMesOrderSchedule(MesOrderSchedule mesOrderSchedule);

    /**
     * 修改订单排产
     * 
     * @param mesOrderSchedule 订单排产
     * @return 结果
     */
    public int updateMesOrderSchedule(MesOrderSchedule mesOrderSchedule);

    /**
     * 批量删除订单排产
     * 
     * @param scheduleIds 需要删除的订单排产主键集合
     * @return 结果
     */
    public int deleteMesOrderScheduleByScheduleIds(Long[] scheduleIds);

    /**
     * 删除订单排产信息
     * 
     * @param scheduleId 订单排产主键
     * @return 结果
     */
    public int deleteMesOrderScheduleByScheduleId(Long scheduleId);

    List<MesOrderSchedule> selectMesOrderScheduleListChange(MesOrder mesOrder, MesOrderDetail mesdetail);
}
