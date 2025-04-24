package cn.mesad.mes.service.impl;

import java.util.List;
import cn.mesad.common.utils.DateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Random;

import cn.mesad.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import cn.mesad.mes.domain.MesOrderDetail;
import cn.mesad.mes.mapper.MesOrderMapper;
import cn.mesad.mes.domain.MesOrder;
import cn.mesad.mes.service.IMesOrderService;

/**
 * 订单管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-21
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
    @Transactional
    @Override
    public int insertMesOrder(MesOrder mesOrder)
    {
        mesOrder.setCreateTime(DateUtils.getNowDate());
       if (StringUtils.isEmpty(mesOrder.getOrderCode())) {
            mesOrder.setOrderCode("DDGL"+ RandomStringUtils.randomNumeric(9));
        }
        int rows = mesOrderMapper.insertMesOrder(mesOrder);
        insertMesOrderDetail(mesOrder);
        return rows;
    }

    /**
     * 修改订单管理
     * 
     * @param mesOrder 订单管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMesOrder(MesOrder mesOrder)
    {
        mesOrder.setUpdateTime(DateUtils.getNowDate());
        // 只有当订单明细列表不为空时才删除并重新插入订单明细
        List<MesOrderDetail> mesOrderDetailList = mesOrder.getMesOrderDetailList();
        if (StringUtils.isNotNull(mesOrderDetailList) && !mesOrderDetailList.isEmpty())
        {
            mesOrderMapper.deleteMesOrderDetailByOrderId(mesOrder.getOrderId());
            insertMesOrderDetail(mesOrder);
        }
        return mesOrderMapper.updateMesOrder(mesOrder);
    }

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的订单管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMesOrderByOrderIds(Long[] orderIds)
    {
        mesOrderMapper.deleteMesOrderDetailByOrderIds(orderIds);
        return mesOrderMapper.deleteMesOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单管理信息
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMesOrderByOrderId(Long orderId)
    {
        mesOrderMapper.deleteMesOrderDetailByOrderId(orderId);
        return mesOrderMapper.deleteMesOrderByOrderId(orderId);
    }

    /**
     * 新增订单明细信息
     * 
     * @param mesOrder 订单管理对象
     */
    public void insertMesOrderDetail(MesOrder mesOrder)
    {
        List<MesOrderDetail> mesOrderDetailList = mesOrder.getMesOrderDetailList();
        Long orderId = mesOrder.getOrderId();
        if (StringUtils.isNotNull(mesOrderDetailList))
        {
            List<MesOrderDetail> list = new ArrayList<MesOrderDetail>();
            for (MesOrderDetail mesOrderDetail : mesOrderDetailList)
            {
                mesOrderDetail.setOrderId(orderId);
                list.add(mesOrderDetail);
            }
            if (list.size() > 0)
            {
                mesOrderMapper.batchMesOrderDetail(list);
            }
        }
    }
}
