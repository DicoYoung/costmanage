package com.cm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cm.pojo.CmAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cm.pojo.CmPlan;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-05-11
 */
public interface ICmAccountService extends IService<CmAccount> {

    long getCumulateBy(String jobId);

    IPage<CmAccount> page(Integer pageNo, Integer pageSize, String name);

    List<CmAccount> getUnAssess();

    boolean delUnAss(Long id);
}
