package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cm.pojo.CmCostItem;
import com.cm.mapper.CmCostItemMapper;
import com.cm.pojo.CmResource;
import com.cm.pojo.CmUser;
import com.cm.service.ICmCostItemService;
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
 * @since 2022-05-06
 */
@Service
public class CmCostItemServiceImpl extends ServiceImpl<CmCostItemMapper, CmCostItem> implements ICmCostItemService {
    @Override
    public List<CmCostItem> getItem(Long parentId) {
        return this.getItem(parentId, null);
    }

    @Override
    public Long getProcedureCost() {
        QueryWrapper<CmCostItem> wrapper = new QueryWrapper<>();
        wrapper.eq("has_child", 0);
        List<CmCostItem> list = this.list(wrapper);
        long result = 0L;
        for (CmCostItem cmCostItem : list) {
            result += Long.parseLong(cmCostItem.getRemarks());
//            System.out.println("remarks: " + cmCostItem.getRemarks());
//            System.out.println("remarks long: " + Long.parseLong(cmCostItem.getRemarks()));
        }
        System.out.println(result);
        return result;
    }

    @Override
    public List<CmCostItem> getRawMaterial(Long procedureId) {
        QueryWrapper<CmCostItem> wrapper = new QueryWrapper<>();
        wrapper.eq("type", procedureId);
        return this.list(wrapper);
    }

    public List<CmCostItem> getItem(Long parentId, Integer active) {
        QueryWrapper<CmCostItem> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        if (active != null) {
            wrapper.eq("active", active);
        }
        List<CmCostItem> list = this.list(wrapper);
        for (CmCostItem resource : list) {
            if (resource.getActive() == 1) {
                resource.setChildren(getItem(resource.getId()));
            }
        }
        return list;
    }
}
