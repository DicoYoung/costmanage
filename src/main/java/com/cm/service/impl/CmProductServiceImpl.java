package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cm.pojo.CmCostItem;
import com.cm.pojo.CmProcess;
import com.cm.pojo.CmProduct;
import com.cm.mapper.CmProductMapper;
import com.cm.service.ICmProcessService;
import com.cm.service.ICmProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dico
 * @since 2022-05-07
 */
@Service
public class CmProductServiceImpl extends ServiceImpl<CmProductMapper, CmProduct> implements ICmProductService {
    @Resource
    ICmProcessService processService;

    @Override
    public List<CmProduct> getItem(Long parentId) {
        return this.getItem(parentId, null);
    }

    @Override
    public List<CmProduct> getProduct() {
        QueryWrapper<CmProduct> wrapper = new QueryWrapper<>();
        wrapper.eq("type", 0).eq("has_child", 0).eq("active", 1);
        return this.list(wrapper);
    }

    @Override
    public List<CmProduct> getSubProduct() {
        QueryWrapper<CmProduct> wrapper = new QueryWrapper<>();
        wrapper.eq("type", 1).eq("has_child", 0).eq("active", 1);
        return this.list(wrapper);
    }

    @Override
    public List<CmProduct> getProductById(Long id) {
        //这是process的id，跟这去找对应的procedure的ID
        QueryWrapper<CmProcess> processQueryWrapper = new QueryWrapper<>();
        processQueryWrapper.eq("id", id);
        CmProcess process = processService.getOne(processQueryWrapper);
        long procedureID = process.getProcedureId();
        //再返回此工序对应的产品
        QueryWrapper<CmProduct> wrapper = new QueryWrapper<>();
        wrapper.eq("type", 0).eq("has_child", 0).eq("active", 1).eq("parent_id", procedureID);
        return this.list(wrapper);
    }

    public List<CmProduct> getItem(Long parentId, Integer active) {
        QueryWrapper<CmProduct> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        if (active != null) {
            wrapper.eq("active", active);
        }
        List<CmProduct> list = this.list(wrapper);
        for (CmProduct resource : list) {
            if (resource.getActive() == 1) {
                resource.setChildren(getItem(resource.getId()));
            }
        }
        return list;
    }
}
