package com.cm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cm.pojo.CmShare;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cm.pojo.CmUser;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-05-11
 */
public interface ICmShareService extends IService<CmShare> {
    IPage<CmShare> page(Integer pageNo, Integer pageSize, String name);

    List<CmShare> getShareByProID(Long procedureId);
}
