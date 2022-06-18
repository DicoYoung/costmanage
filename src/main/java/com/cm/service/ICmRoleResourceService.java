package com.cm.service;

import com.cm.pojo.CmRoleResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-05-02
 */
public interface ICmRoleResourceService extends IService<CmRoleResource> {
    boolean save(Long roleId, Long[] resourcesIds);

    List<Long> getByRoleId(Long roleId);
}
