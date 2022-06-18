package com.cm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cm.pojo.CmCostItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cm.pojo.CmResource;
import com.cm.pojo.CmUser;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-05-06
 */
public interface ICmCostItemService extends IService<CmCostItem> {
    List<CmCostItem> getItem(Long parentId);

    Long getProcedureCost();

    List<CmCostItem> getRawMaterial(Long procedureId);
}
