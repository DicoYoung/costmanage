package com.cm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cm.pojo.CmProcess;
import com.cm.mapper.CmProcessMapper;
import com.cm.service.ICmProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Dico
 * @since 2022-05-10
 */
@Service
public class CmProcessServiceImpl extends ServiceImpl<CmProcessMapper, CmProcess> implements ICmProcessService {

    @Override
    public Map<Integer, Object> getProcess(Integer process) {
        System.out.println("process: " + process);
        QueryWrapper<CmProcess> wrapper = new QueryWrapper<>();
        wrapper.eq("procedure_id", process).eq("is_end", 0).eq("is_next", 0);
        Map<Integer, Object> result = new HashMap<>();
        result.put(0, this.list(wrapper));
        QueryWrapper<CmProcess> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("procedure_id", process).eq("is_end", 1).eq("is_next", 0);
        result.put(1, this.list(wrapper2));
        return result;
    }

    @Override
    public void toNext(CmProcess process) {
        //人为规定了6大工序编号为9到14 而14是辅助工序 不算主流程
        if (process.getProcedureId() < 13) {
            CmProcess nextProcess = new CmProcess();
            nextProcess.setJobId(process.getJobId());
            nextProcess.setProcedureId(process.getProcedureId() + 1);
            nextProcess.setEquipment("");
            nextProcess.setTeam("");
            nextProcess.setRawMaterial("");
            nextProcess.setRawNumber(0L);
            nextProcess.setBeginTime("");
            nextProcess.setEndTime("");
            this.save(nextProcess);
        }
    }

    @Override
    public List<CmProcess> getProcessUnAccount() {
        QueryWrapper<CmProcess> wrapper = new QueryWrapper<>();
        wrapper.eq("is_end", 1).eq("is_next", 1);
        return this.list(wrapper);
    }

    @Override
    public boolean endById(Long id) {
        QueryWrapper<CmProcess> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        CmProcess process = this.getOne(wrapper);
        process.setIsNext(2);
        process.setIsEnd(2);
        return this.updateById(process);
    }
}
