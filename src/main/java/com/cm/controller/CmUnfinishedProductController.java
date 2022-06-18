package com.cm.controller;


import com.cm.service.ICmUnfinishedProductService;
import com.cm.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dico
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/cm-unfinished-product")
public class CmUnfinishedProductController {
    @Resource
    ICmUnfinishedProductService unfinishedProductService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) {
        return ResultJson.success(unfinishedProductService.page(pageNo, pageSize, name));
    }
}
