package cn.mesad.mes.mapper;

import java.util.List;
import cn.mesad.mes.domain.MesCustomer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户Mapper接口
 * 
 * @author ruoyi
 * @date 2025-04-20
 */
@Mapper
public interface MesCustomerMapper 
{
    /**
     * 查询客户
     * 
     * @param customerId 客户主键
     * @return 客户
     */
    public MesCustomer selectMesCustomerByCustomerId(Long customerId);

    /**
     * 查询客户列表
     * 
     * @param mesCustomer 客户
     * @return 客户集合
     */
    public List<MesCustomer> selectMesCustomerList(MesCustomer mesCustomer);

    /**
     * 新增客户
     * 
     * @param mesCustomer 客户
     * @return 结果
     */
    public int insertMesCustomer(MesCustomer mesCustomer);

    /**
     * 修改客户
     * 
     * @param mesCustomer 客户
     * @return 结果
     */
    public int updateMesCustomer(MesCustomer mesCustomer);

    /**
     * 删除客户
     * 
     * @param customerId 客户主键
     * @return 结果
     */
    public int deleteMesCustomerByCustomerId(Long customerId);

    /**
     * 批量删除客户
     * 
     * @param customerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMesCustomerByCustomerIds(Long[] customerIds);
}
