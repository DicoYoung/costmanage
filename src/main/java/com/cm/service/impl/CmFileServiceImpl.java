package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cm.pojo.CmFile;
import com.cm.mapper.CmFileMapper;
import com.cm.service.ICmFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dico
 * @since 2022-04-29
 */
@Service
public class CmFileServiceImpl extends ServiceImpl<CmFileMapper, CmFile> implements ICmFileService {
    private final static String filePath = "F:/GraduationProject/files/";

    @Override
    public String upload(MultipartFile file) throws IOException {
        // 检验文件是否已经上传过
        String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
        long size = file.getSize();
        String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
        QueryWrapper<CmFile> wrapper = new QueryWrapper<>();
        wrapper.eq("MD5", md5)
                .eq("size", size)
                .eq("suffix", suffix);
        CmFile cmFile = this.getOne(wrapper);
        if (cmFile != null) {
//            System.out.println("文件路径存在");
            return cmFile.getUrl();
        } else {
            //判断本地文件夹是否建立
            File localFile = new File(filePath);
            if (!localFile.exists()) {
                localFile.mkdirs();
            }
            // 定义新文件名
            StringBuilder builder = new StringBuilder();
            LocalDateTime now = LocalDateTime.now();
            String time = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS"));
            builder.append(time);
            builder.append(RandomStringUtils.random(6, false, true));
            builder.append(".").append(suffix);
            //保存文件到本地路径
            String localPath = filePath + builder;
            file.transferTo(new File(localPath));
            //保存信息到数据库
            cmFile = new CmFile(md5, size, suffix, builder.toString(), time);
            this.save(cmFile);
            return builder.toString();
        }
    }
}
