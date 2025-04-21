package cn.mesad.mes.service.impl;

import java.util.List;
import cn.mesad.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.mesad.mes.mapper.MesProductMapper;
import cn.mesad.mes.domain.MesProduct;
import cn.mesad.mes.service.IMesProductService;

/**
 * 产品Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-04-21
 */
@Service
public class MesProductServiceImpl implements IMesProductService 
{
    @Autowired
    private MesProductMapper mesProductMapper;

    /**
     * 查询产品
     * 
     * @param productId 产品主键
     * @return 产品
     */
    @Override
    public MesProduct selectMesProductByProductId(Long productId)
    {
        return mesProductMapper.selectMesProductByProductId(productId);
    }

    /**
     * 查询产品列表
     * 
     * @param mesProduct 产品
     * @return 产品
     */
    @Override
    public List<MesProduct> selectMesProductList(MesProduct mesProduct)
    {
        return mesProductMapper.selectMesProductList(mesProduct);
    }

    /**
     * 新增产品
     * 
     * @param mesProduct 产品
     * @return 结果
     */
    @Override
    public int insertMesProduct(MesProduct mesProduct)
    {
        mesProduct.setCreateTime(DateUtils.getNowDate());
        return mesProductMapper.insertMesProduct(mesProduct);
    }

    /**
     * 修改产品
     * 
     * @param mesProduct 产品
     * @return 结果
     */
    @Override
    public int updateMesProduct(MesProduct mesProduct)
    {
        mesProduct.setUpdateTime(DateUtils.getNowDate());
        return mesProductMapper.updateMesProduct(mesProduct);
    }

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的产品主键
     * @return 结果
     */
    @Override
    public int deleteMesProductByProductIds(Long[] productIds)
    {
        return mesProductMapper.deleteMesProductByProductIds(productIds);
    }

    /**
     * 删除产品信息
     * 
     * @param productId 产品主键
     * @return 结果
     */
    @Override
    public int deleteMesProductByProductId(Long productId)
    {
        return mesProductMapper.deleteMesProductByProductId(productId);
    }
}
