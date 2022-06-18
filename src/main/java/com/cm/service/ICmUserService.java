package com.cm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cm.pojo.CmUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-04-26
 */
public interface ICmUserService extends IService<CmUser> {
    IPage<CmUser> page(Integer pageNo, Integer pageSize, String name);

    List<CmUser> getAll();

    boolean changePassword(String username, String rawPassword);

    Map<String, Object> login(String username, String password) throws Exception;

    CmUser afterAddGetOne(CmUser cmUser);
}
