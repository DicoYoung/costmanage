package com.cm.controller;


import com.cm.service.ICmRoleUserService;
import com.cm.service.ICmUserService;
import com.cm.util.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dico
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/cm-role-user")
public class CmRoleUserController {
    @Resource
    ICmUserService userService;
    @Resource
    ICmRoleUserService roleUserService;

    @GetMapping("/getData")
    ResultJson getData(Long roleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("users", userService.getAll());
        map.put("values", roleUserService.getUsersByRoleId(roleId));
        return ResultJson.success(map);
    }

    @PostMapping("/save")
    ResultJson save(Long roleId, Long[] userIds) {
        return ResultJson.success(roleUserService.save(roleId, userIds), "角色与用户关联成功");
    }

}
