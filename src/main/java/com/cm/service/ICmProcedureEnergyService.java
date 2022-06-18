package com.cm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cm.pojo.CmPlan;
import com.cm.pojo.CmProcedureEnergy;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-05-12
 */
public interface ICmProcedureEnergyService extends IService<CmProcedureEnergy> {
    IPage<CmProcedureEnergy> page(Integer pageNo, Integer pageSize, String name);

    List<CmProcedureEnergy> getByProcedure(Long pid);
}
