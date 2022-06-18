package com.cm.service;

import com.cm.pojo.CmRole;
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
public interface ICmRoleService extends IService<CmRole> {
    List<CmRole> list(String name);
}
