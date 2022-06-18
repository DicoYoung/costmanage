package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cm.pojo.CmShare;
import com.cm.mapper.CmShareMapper;
import com.cm.pojo.CmUser;
import com.cm.service.ICmShareService;
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
 * @since 2022-05-11
 */
@Service
public class CmShareServiceImpl extends ServiceImpl<CmShareMapper, CmShare> implements ICmShareService {
    @Override
    public IPage<CmShare> page(Integer pageNo, Integer pageSize, String name) {
        QueryWrapper<CmShare> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("ruler_name", name);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }

    @Override
    public List<CmShare> getShareByProID(Long procedureId) {
        QueryWrapper<CmShare> wrapper = new QueryWrapper<>();
        wrapper.eq("procedure_id", procedureId).eq("is_active", 1);
        return this.list(wrapper);
    }
}
