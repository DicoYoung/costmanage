package com.cm.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cm.App;
import com.cm.pojo.CmUser;
import com.cm.service.ICmUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TestOne {
    @Resource
    ICmUserService userService;

    @Test
    public void handler() {
//        增删改
//        List<CmUser> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            CmUser cmUser = new CmUser();
//            cmUser.setLoginName("000" + i);
//            cmUser.setPassword("123" + i);
//            cmUser.setPhone("110" + i);
//            cmUser.setEmail(i + "123@139.com");
//            cmUser.setTrueName(i + "test one");
//            cmUser.setActive(1);
//            list.add(cmUser);
//        }
//        userService.saveBatch(list);

        //查询 根据不同查询条件 eq 相等 ne 不等 lt 小于 gt 大于 le 小于等于 ge 大于等于 in 范围 like 模糊查询
//        QueryWrapper<CmUser> wrapper = new QueryWrapper<>();
//        wrapper.eq("login_name", "0002");
//        System.out.println(userService.list(wrapper));
    }
}
