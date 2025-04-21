package cn.mesad.mes.service.impl;

import java.util.List;
import cn.mesad.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mesad.mes.mapper.MesCustomerMapper;
import cn.mesad.mes.domain.MesCustomer;
import cn.mesad.mes.service.IMesCustomerService;

/**
 * 客户Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-20
 */
@Service
public class MesCustomerServiceImpl implements IMesCustomerService 
{
    @Autowired
    private MesCustomerMapper mesCustomerMapper;

    /**
     * 查询客户
     * 
     * @param customerId 客户主键
     * @return 客户
     */
    @Override
    public MesCustomer selectMesCustomerByCustomerId(Long customerId)
    {
        return mesCustomerMapper.selectMesCustomerByCustomerId(customerId);
    }

    /**
     * 查询客户列表
     * 
     * @param mesCustomer 客户
     * @return 客户
     */
    @Override
    public List<MesCustomer> selectMesCustomerList(MesCustomer mesCustomer)
    {
        return mesCustomerMapper.selectMesCustomerList(mesCustomer);
    }

    /**
     * 新增客户
     * 
     * @param mesCustomer 客户
     * @return 结果
     */
    @Override
    public int insertMesCustomer(MesCustomer mesCustomer)
    {
        mesCustomer.setCreateTime(DateUtils.getNowDate());
        return mesCustomerMapper.insertMesCustomer(mesCustomer);
    }

    /**
     * 修改客户
     * 
     * @param mesCustomer 客户
     * @return 结果
     */
    @Override
    public int updateMesCustomer(MesCustomer mesCustomer)
    {
        mesCustomer.setUpdateTime(DateUtils.getNowDate());
        return mesCustomerMapper.updateMesCustomer(mesCustomer);
    }

    /**
     * 批量删除客户
     * 
     * @param customerIds 需要删除的客户主键
     * @return 结果
     */
    @Override
    public int deleteMesCustomerByCustomerIds(Long[] customerIds)
    {
        return mesCustomerMapper.deleteMesCustomerByCustomerIds(customerIds);
    }

    /**
     * 删除客户信息
     * 
     * @param customerId 客户主键
     * @return 结果
     */
    @Override
    public int deleteMesCustomerByCustomerId(Long customerId)
    {
        return mesCustomerMapper.deleteMesCustomerByCustomerId(customerId);
    }
}
