package com.cm.controller;


import com.cm.service.ICmFileService;
import com.cm.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dico
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/cm-file")
public class CmFileController {
    @Resource
    ICmFileService fileService;

    @GetMapping("/list")
    ResultJson list() {
        return ResultJson.success(fileService.list());
    }
}
