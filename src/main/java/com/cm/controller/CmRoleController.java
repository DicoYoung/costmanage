package com.cm.controller;


import com.cm.pojo.CmRole;
import com.cm.service.ICmRoleService;
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
@RequestMapping("/cm-role")
public class CmRoleController {
    @Resource
    ICmRoleService roleService;

    @GetMapping("/list")
    ResultJson list(String name) {
        return ResultJson.success(roleService.list(name));
    }

    @PostMapping("/add")
    ResultJson add(CmRole cmRole) {
        return ResultJson.success(roleService.save(cmRole), "添加角色成功");
    }

    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(roleService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(CmRole cmRole) {
        return ResultJson.success(roleService.updateById(cmRole));
    }

    @PostMapping("/del")
    ResultJson del(CmRole cmRole) {
        String message = cmRole.getActive() == 0 ? "删除角色成功" : "恢复角色成功";
        return ResultJson.success(roleService.updateById(cmRole), message);
    }
}
