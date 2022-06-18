package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cm.pojo.CmDataFile;
import com.cm.mapper.CmDataFileMapper;
import com.cm.pojo.CmFile;
import com.cm.pojo.CmUser;
import com.cm.service.ICmDataFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2022-05-08
 */
@Service
public class CmDataFileServiceImpl extends ServiceImpl<CmDataFileMapper, CmDataFile> implements ICmDataFileService {
    private final static String filePath = "F:/GraduationProject/files/dataFiles/";

    @Override
    public String upload(String name, MultipartFile file) throws IOException {
        // 检验文件是否已经上传过
        String md5 = DigestUtils.md5DigestAsHex(file.getInputStream());
        long size = file.getSize();
        String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
        QueryWrapper<CmDataFile> wrapper = new QueryWrapper<>();
        wrapper.eq("MD5", md5)
                .eq("size", size)
                .eq("suffix", suffix);
        CmDataFile cmDataFile = this.getOne(wrapper);
        if (cmDataFile != null) {
//            System.out.println("文件路径存在");
            return cmDataFile.getUrl();
        } else {
            //判断本地文件夹是否建立
            File localFile = new File(filePath);
            if (!localFile.exists()) {
                localFile.mkdirs();
            }
            // 定义新文件名
            StringBuilder builder = new StringBuilder();
            LocalDateTime now = LocalDateTime.now();
            String timeUrl = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS"));
            String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss:SSSS"));
            builder.append(timeUrl);
            builder.append(RandomStringUtils.random(6, false, true));
            builder.append(".").append(suffix);
            //保存文件到本地路径
            String localPath = filePath + builder;
            file.transferTo(new File(localPath));
            //保存信息到数据库
            cmDataFile = new CmDataFile(name, suffix, builder.toString(), size, md5, time);
            this.save(cmDataFile);
            return builder.toString();
        }
    }

    @Override
    public IPage<CmDataFile> page(Integer pageNo, Integer pageSize, String name) {
        QueryWrapper<CmDataFile> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        return this.page(new Page<>(pageNo, pageSize), wrapper);
    }
}
