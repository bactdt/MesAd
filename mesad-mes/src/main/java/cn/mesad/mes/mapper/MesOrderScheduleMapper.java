package cn.mesad.mes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.mesad.mes.domain.MesOrder;
import cn.mesad.mes.domain.MesOrderDetail;
import cn.mesad.mes.domain.MesOrderSchedule;

/**
 * 订单排产Mapper接口
 * 
 * @author ruoyi
 * @date 2025-04-22
 */
public interface MesOrderScheduleMapper 
{
    /**
     * 查询订单排产
     * 
     * @param scheduleId 订单排产主键
     * @return 订单排产
     */
    public MesOrderSchedule selectMesOrderScheduleByScheduleId(Long scheduleId);

    /**
     * 查询订单连表查询列表
     *
     * @param mesOrder 订单信息
     * @param mesOrderDetail 订单明细
     * @return 订单排产集合
     */
    public List<MesOrderSchedule> selectMesOrderScheduleListChange(@Param("mesOrder") MesOrder mesOrder, @Param("mesOrderDetail") MesOrderDetail mesOrderDetail);
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
     * 删除订单排产
     * 
     * @param scheduleId 订单排产主键
     * @return 结果
     */
    public int deleteMesOrderScheduleByScheduleId(Long scheduleId);

    /**
     * 批量删除订单排产
     * 
     * @param scheduleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMesOrderScheduleByScheduleIds(Long[] scheduleIds);
}
