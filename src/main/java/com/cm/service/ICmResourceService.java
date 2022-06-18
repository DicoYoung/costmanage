package com.cm.service;

import com.cm.pojo.CmResource;
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
public interface ICmResourceService extends IService<CmResource> {
    List<CmResource> getResource(Long parentId);

    List<Long> getLast();

    List<CmResource> getByUserId(Long userId);
}
