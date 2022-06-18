package com.cm.controller;


import com.cm.pojo.CmPlan;
import com.cm.pojo.CmProcedureEnergy;
import com.cm.service.ICmPlanService;
import com.cm.service.ICmProcedureEnergyService;
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
 * @since 2022-05-12
 */
@RestController
@RequestMapping("/cm-procedure-energy")
public class CmProcedureEnergyController {
    @Resource
    ICmProcedureEnergyService energyService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) {
        return ResultJson.success(energyService.page(pageNo, pageSize, name));
    }

    @PostMapping("/add")
    ResultJson add(CmProcedureEnergy procedureEnergy) {
        procedureEnergy.setAllCost(procedureEnergy.getEnergyPrice() * procedureEnergy.getEnergyNumber());
        return ResultJson.success(energyService.save(procedureEnergy), "添加成功");
    }

    @GetMapping("/getone")
    ResultJson getOne(Long id) {
        return ResultJson.success(energyService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(CmProcedureEnergy procedureEnergy) {
        procedureEnergy.setAllCost(procedureEnergy.getEnergyPrice() * procedureEnergy.getEnergyNumber());
        return ResultJson.success(energyService.updateById(procedureEnergy), "修改成功");
    }

    @PostMapping("/del")
    ResultJson del(CmProcedureEnergy procedureEnergy) {
        String message = procedureEnergy.getActive() == 0 ? "删除成功" : "恢复成功";
        return ResultJson.success(energyService.updateById(procedureEnergy), message);
    }

    @GetMapping("/getByProcedure")
    ResultJson getByProcedure(Long procedureId) {
        return ResultJson.success(energyService.getByProcedure(procedureId));
    }
}
