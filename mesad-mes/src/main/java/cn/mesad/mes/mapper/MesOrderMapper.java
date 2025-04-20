package cn.mesad.mes.mapper;

import java.util.List;
import cn.mesad.mes.domain.MesOrder;

/**
 * 订单管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-04-20
 */
public interface MesOrderMapper 
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
     * 删除订单管理
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    public int deleteMesOrderByOrderId(Long orderId);

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMesOrderByOrderIds(Long[] orderIds);
}
