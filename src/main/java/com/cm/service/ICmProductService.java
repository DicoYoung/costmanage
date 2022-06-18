package com.cm.service;

import com.cm.pojo.CmCostItem;
import com.cm.pojo.CmProduct;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-05-07
 */
public interface ICmProductService extends IService<CmProduct> {
    List<CmProduct> getItem(Long parentId);

    List<CmProduct> getProduct();

    List<CmProduct> getSubProduct();

    List<CmProduct> getProductById(Long id);
}
