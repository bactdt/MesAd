package cn.mesad.mes.service;

import java.util.List;
import cn.mesad.mes.domain.MesOrder;

/**
 * 订单管理Service接口
 * 
 * @author ruoyi
 * @date 2025-04-21
 */
public interface IMesOrderService 
{
    /**
     * 查询订单管理
     * 
     * @param orderId 订单管理主键
     * @return 订单管理
     */
    public MesOrder selectMesOrderByOrderId(Long orderId);

    /**
     * 查询订单管理列表
     * 
     * @param mesOrder 订单管理
     * @return 订单管理集合
     */
    public List<MesOrder> selectMesOrderList(MesOrder mesOrder);

    /**
     * 新增订单管理
     * 
     * @param mesOrder 订单管理
     * @return 结果
     */
    public int insertMesOrder(MesOrder mesOrder);

    /**
     * 修改订单管理
     * 
     * @param mesOrder 订单管理
     * @return 结果
     */
    public int updateMesOrder(MesOrder mesOrder);

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的订单管理主键集合
     * @return 结果
     */
    public int deleteMesOrderByOrderIds(Long[] orderIds);

    /**
     * 删除订单管理信息
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    public int deleteMesOrderByOrderId(Long orderId);
}
