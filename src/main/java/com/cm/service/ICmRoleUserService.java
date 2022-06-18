package com.cm.service;

import com.cm.pojo.CmRoleUser;
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
public interface ICmRoleUserService extends IService<CmRoleUser> {
    boolean save(Long roleId, Long[] userIds);

    List<CmRoleUser> getUsersByRoleId(Long id);

    boolean save(Long roleId, Long userId);
}
