package com.cm.controller;


import com.cm.pojo.CmAssessment;
import com.cm.pojo.CmPlan;
import com.cm.service.ICmAccountService;
import com.cm.service.ICmAssessmentService;
import com.cm.util.ResultJson;
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
@RequestMapping("/cm-assessment")
public class CmAssessmentController {
    @Resource
    ICmAssessmentService assessmentService;

    @Resource
    ICmAccountService accountService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) {
        return ResultJson.success(assessmentService.page(pageNo, pageSize, name));
    }

    @GetMapping("/listNoPage")
    ResultJson listNoPage() {
        return ResultJson.success(assessmentService.list());
    }

    @PostMapping("/add")
    ResultJson add(CmAssessment cmAssessment) {
        //创建新的评价
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        cmAssessment.setEvaluateTime(time);
        //清除未评价
        return ResultJson.success(assessmentService.save(cmAssessment), "添加成功");
    }

    @GetMapping("/getone")
    ResultJson getOne(Long id) {
        return ResultJson.success(assessmentService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(CmAssessment cmAssessment) {
        return ResultJson.success(assessmentService.updateById(cmAssessment), "修改成功");
    }

    @PostMapping("/del")
    ResultJson del(CmAssessment cmAssessment) {
        String message = cmAssessment.getIsActive() == 0 ? "删除成功" : "恢复成功";
        return ResultJson.success(assessmentService.updateById(cmAssessment), message);
    }

    @GetMapping("/getUnAssess")
    ResultJson getUnAssess() {
        return ResultJson.success(accountService.getUnAssess());
    }

    @GetMapping("/delUnAss")
    ResultJson delUnAss(Long id) {
        return ResultJson.success(accountService.delUnAss(id));
    }

    //读account表，作为未进行评价的一项
    //进行评价，在assess表新增一项评价

}
