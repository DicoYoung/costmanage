package com.cm.controller;


import com.cm.pojo.CmPlan;
import com.cm.pojo.CmShare;
import com.cm.service.ICmPlanService;
import com.cm.service.ICmShareService;
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
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/cm-plan")
public class CmPlanController {
    @Resource
    ICmPlanService planService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) {
        return ResultJson.success(planService.page(pageNo, pageSize, name));
    }

    @PostMapping("/add")
    ResultJson add(CmPlan cmPlan) {
        return ResultJson.success(planService.save(cmPlan), "添加用户成功");
    }

    @GetMapping("/getone")
    ResultJson getOne(Long id) {
        return ResultJson.success(planService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(CmPlan cmPlan) {
        return ResultJson.success(planService.updateById(cmPlan), "修改成功");
    }

    @PostMapping("/del")
    ResultJson del(CmPlan cmPlan) {
        String message = cmPlan.getIsActive() == 0 ? "删除成功" : "恢复成功";
        return ResultJson.success(planService.updateById(cmPlan), message);
    }

    @GetMapping("/getByProduct")
    ResultJson getByProduct(Long id) {
        return ResultJson.success(planService.getByProduct(id));
    }
}
