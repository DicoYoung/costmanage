package com.cm.controller;


import com.cm.pojo.CmCostItem;
import com.cm.pojo.CmResource;
import com.cm.service.ICmCostItemService;
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
 * @since 2022-05-06
 */
@RestController
@RequestMapping("/cm-cost-item")
public class CmCostItemController {
    @Resource
    ICmCostItemService itemService;

    @GetMapping("/list")
    ResultJson list() {
        return ResultJson.success(itemService.getItem(0L));
    }

    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(itemService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(CmCostItem cmCostItem) {
        return ResultJson.success(itemService.updateById(cmCostItem), "修改成功");
    }

    @PostMapping("/add")
    ResultJson add(CmCostItem cmCostItem) {
        return ResultJson.success(itemService.save(cmCostItem), "添加成功");
    }

    @PostMapping("/del")
    ResultJson del(CmCostItem cmCostItem) {
        String message = cmCostItem.getActive() == 0 ? "删除资源成功" : "恢复资源成功";
        return ResultJson.success(itemService.updateById(cmCostItem), message);
    }

    @GetMapping("/getProcedureCost")
    ResultJson getProcedureCost() {
        return ResultJson.success(itemService.getProcedureCost());
    }

    @GetMapping("/getRawMaterial")
    ResultJson getRawMaterial(Long procedureId) {
        return ResultJson.success(itemService.getRawMaterial(procedureId));
    }
}
