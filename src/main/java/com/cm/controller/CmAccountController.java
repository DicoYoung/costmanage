package com.cm.controller;


import com.cm.pojo.CmAccount;
import com.cm.pojo.CmProcess;
import com.cm.service.ICmAccountService;
import com.cm.util.ResultJson;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dico
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/cm-account")
public class CmAccountController {
    @Resource
    ICmAccountService accountService;

    @PostMapping("/save")
    ResultJson add(CmAccount cmAccount) {
        //计算本次成本
        long result = cmAccount.getRawCost() + cmAccount.getEnergyCost() + cmAccount.getProcedureCost();
        result = result * cmAccount.getShareCost();
        //获取累计成本
        long cumulate = accountService.getCumulateBy(cmAccount.getJobId());
        cmAccount.setCumulativeCost(result + cumulate);
        //获取完结时间
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        cmAccount.setFinishTime(time);
        //业务逻辑：(操作在对应service完成)
        //在process表里将is_next变为2，使其不再显示
        //在评价表里添加一项待评价
        return ResultJson.success(accountService.save(cmAccount), "添加成功，本次总计成本为：" + result);
    }

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) {
        return ResultJson.success(accountService.page(pageNo, pageSize, name));
    }

    @GetMapping("/getOne")
    ResultJson getOne(Long id) {
        return ResultJson.success(accountService.getById(id));
    }
}
