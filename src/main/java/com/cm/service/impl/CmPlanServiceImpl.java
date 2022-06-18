package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cm.pojo.CmAccount;
import com.cm.pojo.CmPlan;
import com.cm.mapper.CmPlanMapper;
import com.cm.pojo.CmShare;
import com.cm.service.ICmAccountService;
import com.cm.service.ICmPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dico
 * @since 2022-05-11
 */
@Service
public class CmPlanServiceImpl extends ServiceImpl<CmPlanMapper, CmPlan> implements ICmPlanService {
    @Resource
    ICmAccountService accountService;

    @Override
    public IPage<CmPlan> page(Integer pageNo, Integer pageSize, String name) {
        QueryWrapper<CmPlan> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("plan_name", name);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }

    @Override
    public CmPlan getByProduct(Long id) {
        //通过accountID找到account
        //然后再找到产品ID
        //最后找到计划
        QueryWrapper<CmAccount> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.eq("id", id);
        CmAccount account = accountService.getOne(accountQueryWrapper);
        QueryWrapper<CmPlan> wrapper = new QueryWrapper<>();
        wrapper.eq("object_id", account.getProductId());
        return this.getOne(wrapper);
    }
}
