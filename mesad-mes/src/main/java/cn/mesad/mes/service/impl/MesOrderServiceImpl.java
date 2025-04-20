package cn.mesad.mes.service.impl;

import java.util.List;
import cn.mesad.common.utils.DateUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mesad.mes.mapper.MesOrderMapper;
import cn.mesad.mes.domain.MesOrder;
import cn.mesad.mes.service.IMesOrderService;

/**
 * 订单管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-20
 */
@Service
public class MesOrderServiceImpl implements IMesOrderService 
{
    @Autowired
    private MesOrderMapper mesOrderMapper;

    /**
     * 查询订单管理
     * 
     * @param orderId 订单管理主键
     * @return 订单管理
     */
    @Override
    public MesOrder selectMesOrderByOrderId(Long orderId)
    {
        return mesOrderMapper.selectMesOrderByOrderId(orderId);
    }

    /**
     * 查询订单管理列表
     * 
     * @param mesOrder 订单管理
     * @return 订单管理
     */
    @Override
    public List<MesOrder> selectMesOrderList(MesOrder mesOrder)
    {
        return mesOrderMapper.selectMesOrderList(mesOrder);
    }

    /**
     * 新增订单管理
     * 
     * @param mesOrder 订单管理
     * @return 结果
     */
    @Override
    public int insertMesOrder(MesOrder mesOrder)
    {
        mesOrder.setCreateTime(DateUtils.getNowDate());
        return mesOrderMapper.insertMesOrder(mesOrder);
    }

    /**
     * 修改订单管理
     * 
     * @param mesOrder 订单管理
     * @return 结果
     */
    @Override
    public int updateMesOrder(MesOrder mesOrder)
    {
        mesOrder.setUpdateTime(DateUtils.getNowDate());
        return mesOrderMapper.updateMesOrder(mesOrder);
    }

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteMesOrderByOrderIds(Long[] orderIds)
    {
        return mesOrderMapper.deleteMesOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单管理信息
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteMesOrderByOrderId(Long orderId)
    {
        return mesOrderMapper.deleteMesOrderByOrderId(orderId);
    }
}
