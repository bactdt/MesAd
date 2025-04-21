package cn.mesad.mes.service;

import java.util.List;
import cn.mesad.mes.domain.MesProduct;

/**
 * 产品Service接口
 * 
 * @author ruoyi
 * @date 2025-04-21
 */
public interface IMesProductService 
{
    /**
     * 查询产品
     * 
     * @param productId 产品主键
     * @return 产品
     */
    public MesProduct selectMesProductByProductId(Long productId);

    /**
     * 查询产品列表
     * 
     * @param mesProduct 产品
     * @return 产品集合
     */
    public List<MesProduct> selectMesProductList(MesProduct mesProduct);

    /**
     * 新增产品
     * 
     * @param mesProduct 产品
     * @return 结果
     */
    public int insertMesProduct(MesProduct mesProduct);

    /**
     * 修改产品
     * 
     * @param mesProduct 产品
     * @return 结果
     */
    public int updateMesProduct(MesProduct mesProduct);

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的产品主键集合
     * @return 结果
     */
    public int deleteMesProductByProductIds(Long[] productIds);

    /**
     * 删除产品信息
     * 
     * @param productId 产品主键
     * @return 结果
     */
    public int deleteMesProductByProductId(Long productId);
}
