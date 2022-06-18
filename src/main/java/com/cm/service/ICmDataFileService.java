package com.cm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cm.pojo.CmDataFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cm.pojo.CmUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-05-08
 */
public interface ICmDataFileService extends IService<CmDataFile> {
    String upload(String name, MultipartFile file) throws IOException;

    IPage<CmDataFile> page(Integer pageNo, Integer pageSize, String name);
}
