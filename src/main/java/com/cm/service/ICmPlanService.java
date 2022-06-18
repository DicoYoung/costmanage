package com.cm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cm.pojo.CmPlan;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-05-11
 */
public interface ICmPlanService extends IService<CmPlan> {

    IPage<CmPlan> page(Integer pageNo, Integer pageSize, String name);

    CmPlan getByProduct(Long id);
}
