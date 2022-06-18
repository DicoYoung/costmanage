package com.cm.controller;


import com.cm.pojo.CmCostItem;
import com.cm.pojo.CmProduct;
import com.cm.service.ICmCostItemService;
import com.cm.service.ICmProductService;
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
 * @since 2022-05-07
 */
@RestController
@RequestMapping("/cm-product")
public class CmProductController {
    @Resource
    ICmProductService productService;

    @GetMapping("/list")
    ResultJson list() {
        return ResultJson.success(productService.getItem(0L));
    }

    @GetMapping("/getone")
    ResultJson getone(Long id) {
        return ResultJson.success(productService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(CmProduct cmProduct) {
        return ResultJson.success(productService.updateById(cmProduct), "修改成功");
    }

    @PostMapping("/add")
    ResultJson add(CmProduct cmProduct) {
        return ResultJson.success(productService.save(cmProduct), "添加成功");
    }

    @PostMapping("/del")
    ResultJson del(CmProduct cmProduct) {
        String message = cmProduct.getActive() == 0 ? "删除资源成功" : "恢复资源成功";
        return ResultJson.success(productService.updateById(cmProduct), message);
    }

    @GetMapping("/getProduct")
    ResultJson getProduct() {
        return ResultJson.success(productService.getProduct());
    }

    @GetMapping("/getSubProduct")
    ResultJson getSubProduct() {
        return ResultJson.success(productService.getSubProduct());
    }


    @GetMapping("/getProductById")
    ResultJson getProductById(Long id) {
        return ResultJson.success(productService.getProductById(id));
    }
}
