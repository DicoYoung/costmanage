package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cm.pojo.CmProcess;
import com.cm.pojo.CmUnfinishedProduct;
import com.cm.mapper.CmUnfinishedProductMapper;
import com.cm.service.ICmProcessService;
import com.cm.service.ICmUnfinishedProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class CmUnfinishedProductServiceImpl extends ServiceImpl<CmUnfinishedProductMapper, CmUnfinishedProduct> implements ICmUnfinishedProductService {
    @Resource
    ICmProcessService processService;

    @Override
    public IPage<CmUnfinishedProduct> page(Integer pageNo, Integer pageSize, String name) {
        //每次先删除已有的
        QueryWrapper<CmUnfinishedProduct> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("procedure_id", name);
        }
        //如果为空，即默认查询全表，则不需要删除
        //如果不为空，则更新对应工序里得未完成工序
        if (name != null && !name.equals("")) {
            this.remove(wrapper);
            //然后创建新的表项
            QueryWrapper<CmProcess> processQueryWrapper = new QueryWrapper<>();
            processQueryWrapper.eq("procedure_id", name);
            List<CmProcess> list = processService.list(processQueryWrapper);
            LocalDateTime now = LocalDateTime.now();
            String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            for (CmProcess process : list) {
                CmUnfinishedProduct unfinishedProduct = new CmUnfinishedProduct();
                unfinishedProduct.setUnfinishedProductId(Long.valueOf(process.getProductId()));
                unfinishedProduct.setUnfinishedProductNumber(String.valueOf(process.getProductNumber()));
                unfinishedProduct.setBeginTime(time);
                unfinishedProduct.setProcedureId(Long.valueOf(name));
                unfinishedProduct.setRemarks(name + "@" + time);
                this.save(unfinishedProduct);
            }
        }
        //再读表
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }
}
