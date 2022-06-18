package com.cm.controller;


import com.cm.pojo.CmDataFile;
import com.cm.pojo.CmUser;
import com.cm.service.ICmDataFileService;
import com.cm.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.rmi.ServerException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dico
 * @since 2022-05-08
 */
@RestController
@RequestMapping("/cm-data-file")
public class CmDataFileController {
    @Resource
    ICmDataFileService dataFileService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) {
        return ResultJson.success(dataFileService.page(pageNo, pageSize, name));
    }

    @PostMapping("/add")
    ResultJson add(String name, MultipartFile file) throws IOException {
        return ResultJson.success(dataFileService.upload(name, file));
    }

    @GetMapping("/getone")
    ResultJson getOne(Long id) {
        return ResultJson.success(dataFileService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(CmDataFile cmDataFile) {
        return ResultJson.success(dataFileService.updateById(cmDataFile));
    }
}
