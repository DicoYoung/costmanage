package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cm.pojo.CmPlan;
import com.cm.pojo.CmProcedureEnergy;
import com.cm.mapper.CmProcedureEnergyMapper;
import com.cm.service.ICmProcedureEnergyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dico
 * @since 2022-05-12
 */
@Service
public class CmProcedureEnergyServiceImpl extends ServiceImpl<CmProcedureEnergyMapper, CmProcedureEnergy> implements ICmProcedureEnergyService {

    @Override
    public IPage<CmProcedureEnergy> page(Integer pageNo, Integer pageSize, String name) {
        QueryWrapper<CmProcedureEnergy> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("energy_name", name);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }

    @Override
    public List<CmProcedureEnergy> getByProcedure(Long pid) {
        QueryWrapper<CmProcedureEnergy> wrapper = new QueryWrapper<>();
        wrapper.eq("procedure_id", pid);
        return this.list(wrapper);
    }
}
