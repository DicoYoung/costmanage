package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cm.pojo.CmAssessment;
import com.cm.mapper.CmAssessmentMapper;
import com.cm.pojo.CmPlan;
import com.cm.service.ICmAssessmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dico
 * @since 2022-05-11
 */
@Service
public class CmAssessmentServiceImpl extends ServiceImpl<CmAssessmentMapper, CmAssessment> implements ICmAssessmentService {

    @Override
    public IPage<CmAssessment> page(Integer pageNo, Integer pageSize, String name) {
        QueryWrapper<CmAssessment> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("plan_id", name);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }
}
