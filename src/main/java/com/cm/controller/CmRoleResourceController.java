package com.cm.controller;


import com.cm.service.ICmResourceService;
import com.cm.service.ICmRoleResourceService;
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
@RequestMapping("/cm-role-resource")
public class CmRoleResourceController {
    @Resource
    ICmResourceService resourceService;
    @Resource
    ICmRoleResourceService roleResourceService;

    @GetMapping("/getData")
    ResultJson getData(Long roleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("resources", resourceService.getResource(0L));
        map.put("values", roleResourceService.getByRoleId(roleId));
        map.put("last", resourceService.getLast());
        return ResultJson.success(map);
    }

    @PostMapping("/save")
    ResultJson save(Long roleId, Long[] resourceIds) {
        return ResultJson.success(roleResourceService.save(roleId, resourceIds), "角色和资源关联成功");
    }
}
