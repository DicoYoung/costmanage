package com.cm.controller;


import com.cm.pojo.CmResource;
import com.cm.service.ICmResourceService;
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
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/cm-resource")
public class CmResourceController {
    @Resource
    ICmResourceService resourceService;

    @GetMapping("/list")
    ResultJson list() {
        return ResultJson.success(resourceService.getResource(0L));
    }

    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(resourceService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(CmResource cmResource) {
        return ResultJson.success(resourceService.updateById(cmResource), "修改资源成功");
    }

    @PostMapping("/add")
    ResultJson add(CmResource cmResource) {
        return ResultJson.success(resourceService.save(cmResource), "添加资源成功");
    }

    @PostMapping("/del")
    ResultJson del(CmResource cmResource) {
        String message = cmResource.getActive() == 0 ? "删除资源成功" : "恢复资源成功";
        return ResultJson.success(resourceService.updateById(cmResource), message);
    }
}
