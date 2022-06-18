package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cm.pojo.CmAccount;
import com.cm.mapper.CmAccountMapper;
import com.cm.pojo.CmPlan;
import com.cm.pojo.CmProcess;
import com.cm.service.ICmAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cm.service.ICmProcessService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dico
 * @since 2022-05-11
 */
@Service
public class CmAccountServiceImpl extends ServiceImpl<CmAccountMapper, CmAccount> implements ICmAccountService {

    @Override
    public long getCumulateBy(String jobId) {
        QueryWrapper<CmAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("job_id", jobId);
        CmAccount target = this.getOne(wrapper);
        if (target == null || target.getCumulativeCost() == 0) {
            return 0;
        } else {
            return target.getCumulativeCost();
        }
    }

    @Override
    public IPage<CmAccount> page(Integer pageNo, Integer pageSize, String name) {
        QueryWrapper<CmAccount> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("procedure_id", name);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }

    @Override
    public List<CmAccount> getUnAssess() {
        QueryWrapper<CmAccount> wrapper = new QueryWrapper<>();
        wrapper.like("is_eva", 0);
        return this.list(wrapper);
    }

    @Override
    public boolean delUnAss(Long id) {
        QueryWrapper<CmAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        CmAccount account = this.getOne(wrapper);
        account.setIsEva(1);
        return this.updateById(account);
    }
}
