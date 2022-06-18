package com.cm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cm.pojo.CmUser;
import com.cm.service.ICmFileService;
import com.cm.service.ICmRoleUserService;
import com.cm.service.ICmUserService;
import com.cm.util.ResultJson;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.rmi.ServerException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Dico
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/cm-user")
public class CmUserController {
    @Resource
    BCryptPasswordEncoder passwordEncoder;

    @Resource
    ICmUserService userService;

    @Resource
    ICmFileService fileService;

    @Resource
    ICmRoleUserService roleUserService;

    @GetMapping("/list")
    ResultJson list(Integer pageNo, Integer pageSize, String name) throws InterruptedException {
//        TimeUnit.SECONDS.sleep(1);
        return ResultJson.success(userService.page(pageNo, pageSize, name));
    }

    @PostMapping("/add")
    ResultJson add(CmUser cmUser, MultipartFile file) throws IOException {
        // encode the rawPassword
        cmUser.setPassword(passwordEncoder.encode(cmUser.getRawPassword()));
        cmUser.setIcon(fileService.upload(file));
//        System.out.println(file.getSize());
        //先存到用户表，再从里面读取出自增长的ID，再存到权限表
        userService.save(cmUser);
        CmUser theUser = userService.afterAddGetOne(cmUser);
        return ResultJson.success(roleUserService.save(Long.valueOf(theUser.getType()), theUser.getId()), "添加用户成功");
    }

    @GetMapping("/getone")
    ResultJson getOne(Long id) {
        return ResultJson.success(userService.getById(id));
    }

    @PostMapping("/update")
    ResultJson update(CmUser user, MultipartFile file) throws ServerException, IOException {
        if (file != null && file.getSize() > 0) {
            user.setIcon(fileService.upload(file));
        }
        roleUserService.save(Long.valueOf(user.getType()), user.getId());
        return ResultJson.success(userService.updateById(user), "修改用户成功");
    }

    @PostMapping("/del")
    ResultJson del(CmUser cmUser) {
        String message = cmUser.getActive() == 0 ? "删除用户成功" : "恢复用户成功";
        return ResultJson.success(userService.updateById(cmUser), message);
    }

    @PostMapping("/changePassword")
    ResultJson changePassword(String loginName, String rawPassword) {
//        System.out.println("login name: " + loginName + " rawPass: " + rawPassword);
        String password = passwordEncoder.encode(rawPassword);
        return ResultJson.success(userService.changePassword(loginName, password), "修改密码成功");
    }

    @PostMapping("/login")
    ResultJson login(String username, String password) throws Exception {
        return ResultJson.success(userService.login(username, password), "登陆成功");
    }
}
