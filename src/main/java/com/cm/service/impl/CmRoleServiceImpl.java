package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cm.pojo.CmRole;
import com.cm.mapper.CmRoleMapper;
import com.cm.service.ICmRoleService;
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
 * @since 2022-05-02
 */
@Service
public class CmRoleServiceImpl extends ServiceImpl<CmRoleMapper, CmRole> implements ICmRoleService {

    @Override
    public List<CmRole> list(String name) {
        QueryWrapper<CmRole> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        return this.list(wrapper);
    }
}
