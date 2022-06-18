package com.cm.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cm.pojo.CmResource;
import com.cm.pojo.CmUser;
import com.cm.mapper.CmUserMapper;
import com.cm.service.ICmResourceService;
import com.cm.service.ICmUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dico
 * @since 2022-04-26
 */
@Service
public class CmUserServiceImpl extends ServiceImpl<CmUserMapper, CmUser> implements ICmUserService {
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Resource
    ICmResourceService resourceService;

    @Override
    public IPage<CmUser> page(Integer pageNo, Integer pageSize, String name) {
        QueryWrapper<CmUser> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("true_name", name);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }

    @Override
    public List<CmUser> getAll() {
        QueryWrapper<CmUser> wrapper = new QueryWrapper<>();
        wrapper.eq("active", 1);
        return this.list(wrapper);
    }

    @Override
    public boolean changePassword(String username, String rawPassword) {
        UpdateWrapper<CmUser> wrapper = new UpdateWrapper<>();
//        System.out.println("rawPassword: " + rawPassword);
        wrapper.set("password", rawPassword).eq("login_name", username);
        return this.update(wrapper);
    }

    @Override
    public Map<String, Object> login(String username, String password) throws Exception {
        QueryWrapper<CmUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", username);
        CmUser user = this.getOne(wrapper);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        if (user.getActive() == 0) {
            throw new Exception("该用户已失效");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("密码错误");
        }
        // 如果登录成功，需要获取到用户权限
        List<CmResource> resources = resourceService.getByUserId(user.getId());
        Map<String, Object> split = split(resources);
        Map<String, Object> result = new HashMap<>();
        result.put("frontUrl", split.get("frontUrl"));
        String token = JWT.create().withClaim("username", username)
                .withClaim("backUrl", (List<String>) split.get("backUrl"))
                .sign(Algorithm.HMAC256("dc"));
        result.put("token", token);
        return result;
    }

    @Override
    public CmUser afterAddGetOne(CmUser cmUser) {
        QueryWrapper<CmUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", cmUser.getLoginName());
        return this.getOne(wrapper);
    }

    private Map<String, Object> split(List<CmResource> resources) {
        Map<String, Object> map = new HashMap<>();
        List<String> backUrl = new ArrayList<>();
        List<CmResource> frontUrl = getByParentId(resources, 0L);
        for (CmResource resource : resources) {
            if (StringUtils.isNotBlank(resource.getBackUrl())) {
                backUrl.add(resource.getBackUrl());
            }
        }
        map.put("backUrl", backUrl);
        map.put("frontUrl", frontUrl);
        return map;
    }

    private List<CmResource> getByParentId(List<CmResource> list, Long parentId) {
        List<CmResource> result = new ArrayList<>();
        for (CmResource resource : list) {
            if (resource.getParentId().longValue() == parentId.longValue() && resource.getType() == 0) {
                resource.setChildren(getByParentId(list, resource.getId()));
                result.add(resource);
            }
        }
        return result;
    }
}
