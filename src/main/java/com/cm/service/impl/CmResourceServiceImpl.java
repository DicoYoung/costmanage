package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cm.pojo.CmResource;
import com.cm.mapper.CmResourceMapper;
import com.cm.service.ICmResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CmResourceServiceImpl extends ServiceImpl<CmResourceMapper, CmResource> implements ICmResourceService {
    @Resource
    CmResourceMapper resourceMapper;

    public List<CmResource> getResource(Long parentId, Integer active) {
        QueryWrapper<CmResource> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        if (active != null) {
            wrapper.eq("active", active);
        }
        List<CmResource> list = this.list(wrapper);
        for (CmResource resource : list) {
            if (resource.getActive() == 1) {
                resource.setChildren(getResource(resource.getId()));
            }
        }
        return list;
    }

    @Override
    public List<CmResource> getResource(Long parentId) {
        return this.getResource(parentId, null);
    }

    @Override
    public List<Long> getLast() {
        QueryWrapper<CmResource> wrapper = new QueryWrapper<>();
        wrapper.eq("haschildren", 0);
        List<CmResource> list = this.list(wrapper);
        List<Long> result = new ArrayList<>();
        list.forEach(umsResource -> {
            result.add(umsResource.getId());
        });
        return result;
    }

    @Override
    public List<CmResource> getByUserId(Long userId) {
        return resourceMapper.getByUserId(userId);
    }
}
