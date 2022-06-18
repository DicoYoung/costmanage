package com.cm.service;

import com.cm.pojo.CmFile;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Dico
 * @since 2022-04-29
 */
public interface ICmFileService extends IService<CmFile> {
    String upload(MultipartFile file) throws IOException;
}
