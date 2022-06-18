package com.cm.service;

import com.cm.pojo.CmProcess;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-05-10
 */
public interface ICmProcessService extends IService<CmProcess> {
    Map<Integer, Object> getProcess(Integer process);

    void toNext(CmProcess process);

    List<CmProcess> getProcessUnAccount();

    boolean endById(Long id);
}
