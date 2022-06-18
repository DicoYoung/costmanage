package com.cm.controller;


import com.cm.pojo.CmShare;
import com.cm.pojo.CmUser;
import com.cm.service.ICmShareService;
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
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/cm-share")
public class CmShareController {
    @Resource
    ICmShareService shareService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) {
        return ResultJson.success(shareService.page(pageNo, pageSize, name));
    }

    @PostMapping("/add")
    ResultJson add(CmShare cmShare) {
        return ResultJson.success(shareService.save(cmShare), "添加成功");
    }

    @GetMapping("/getone")
    ResultJson getOne(Long id) {
        return ResultJson.success(shareService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(CmShare cmShare) {
        return ResultJson.success(shareService.updateById(cmShare), "修改成功");
    }

    @PostMapping("/del")
    ResultJson del(CmShare cmShare) {
        String message = cmShare.getIsActive() == 0 ? "删除成功" : "恢复成功";
        return ResultJson.success(shareService.updateById(cmShare), message);
    }

    @GetMapping("/getShare")
    ResultJson getShare(Long procedureId) {
        return ResultJson.success(shareService.getShareByProID(procedureId));
    }
}
