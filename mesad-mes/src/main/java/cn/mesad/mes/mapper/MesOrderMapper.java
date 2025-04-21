package cn.mesad.mes.mapper;

import java.util.List;
import cn.mesad.mes.domain.MesOrder;
import cn.mesad.mes.domain.MesOrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-04-21
 */
@Mapper
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

    /**
     * 批量删除订单明细
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMesOrderDetailByOrderIds(Long[] orderIds);
    
    /**
     * 批量新增订单明细
     * 
     * @param mesOrderDetailList 订单明细列表
     * @return 结果
     */
    public int batchMesOrderDetail(List<MesOrderDetail> mesOrderDetailList);
    

    /**
     * 通过订单管理主键删除订单明细信息
     * 
     * @param orderId 订单管理ID
     * @return 结果
     */
    public int deleteMesOrderDetailByOrderId(Long orderId);
}
