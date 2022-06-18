package com.cm.controller;


import com.cm.pojo.CmProcess;
import com.cm.pojo.CmUser;
import com.cm.service.ICmProcessService;
import com.cm.util.ResultJson;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dico
 * @since 2022-05-10
 */
@RestController
@RequestMapping("/cm-process")
public class CmProcessController {
    @Resource
    ICmProcessService processService;

    @GetMapping("/list")
    ResultJson list(Integer process) {
        return ResultJson.success(processService.getProcess(process));
    }

    @PostMapping("/add")
    ResultJson add(CmProcess cmProcess) {
        StringBuilder builder = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        builder.append(time);
        builder.append(RandomStringUtils.random(4, false, true));
        cmProcess.setJobId(builder.toString());

        return ResultJson.success(processService.save(cmProcess), "添加成功");
    }

    @GetMapping("/getone")
    ResultJson getOne(Long id) {
        return ResultJson.success(processService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(CmProcess process) {
        return ResultJson.success(processService.updateById(process), "修改成功");
    }

    @PostMapping("/finish")
    ResultJson finish(CmProcess process) {
        process.setIsEnd(1);
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        process.setEndTime(time);
        return ResultJson.success(processService.updateById(process), "成功完成");
    }

    @PostMapping("/next")
    ResultJson next(CmProcess process) {
        process.setIsNext(1);
        processService.toNext(process);
        return ResultJson.success(processService.updateById(process), "已进入下一工序");
    }

    @PostMapping("/back")
    ResultJson back(CmProcess process) {
        process.setIsEnd(0);
        process.setEndTime("");
        return ResultJson.success(processService.updateById(process), "成功退回");
    }

    @GetMapping("/getProcess")
    ResultJson getProcess() {
        return ResultJson.success(processService.getProcessUnAccount());
    }

    @PostMapping("/endProcess")
    ResultJson endProcess(Long id) {
        return ResultJson.success(processService.endById(id));
    }

//    @GetMapping("/getMaterial")
//    ResultJson getMaterial(){
//
//    }
}
