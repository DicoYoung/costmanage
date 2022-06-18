package com.cm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cm.pojo.CmAssessment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-05-11
 */
public interface ICmAssessmentService extends IService<CmAssessment> {

    IPage<CmAssessment> page(Integer pageNo, Integer pageSize, String name);
}
