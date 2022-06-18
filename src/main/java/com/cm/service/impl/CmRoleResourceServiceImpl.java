package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cm.pojo.CmRoleResource;
import com.cm.mapper.CmRoleResourceMapper;
import com.cm.service.ICmRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dico
 * @since 2022-05-02
 */
@Service
public class CmRoleResourceServiceImpl extends ServiceImpl<CmRoleResourceMapper, CmRoleResource> implements ICmRoleResourceService {
    @Override
    @Transactional
    public boolean save(Long roleId, Long[] resourcesIds) {
        QueryWrapper<CmRoleResource> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        this.remove(wrapper);
        List<CmRoleResource> list = new ArrayList<>();
        for (Long resourcesId : resourcesIds) {
            list.add(new CmRoleResource(roleId, resourcesId));
        }
        return this.saveBatch(list);
    }

    @Override
    public List<Long> getByRoleId(Long roleId) {
        QueryWrapper<CmRoleResource> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        List<CmRoleResource> list = this.list(wrapper);
        List<Long> result = new ArrayList<>();
        for (CmRoleResource roleResource : list) {
            result.add(roleResource.getResourceId());
        }
        return result;
    }
}
