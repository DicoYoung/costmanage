package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cm.pojo.CmRoleUser;
import com.cm.mapper.CmRoleUserMapper;
import com.cm.service.ICmRoleUserService;
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
public class CmRoleUserServiceImpl extends ServiceImpl<CmRoleUserMapper, CmRoleUser> implements ICmRoleUserService {
    @Override
    @Transactional
    public boolean save(Long roleId, Long[] userIds) {
        QueryWrapper<CmRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        this.remove(wrapper);

        List<CmRoleUser> list = new ArrayList<>();
        for (Long userId : userIds) {
            list.add(new CmRoleUser(roleId, userId));
        }
        return this.saveBatch(list);
    }

    @Override
    public List<CmRoleUser> getUsersByRoleId(Long roleId) {
        QueryWrapper<CmRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        return this.list(wrapper);

    }

    @Override
    public boolean save(Long roleId, Long userId) {
        QueryWrapper<CmRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        this.remove(wrapper);
        return this.save(new CmRoleUser(roleId, userId));
    }
}
