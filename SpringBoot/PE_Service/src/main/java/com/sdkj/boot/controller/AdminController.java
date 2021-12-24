package com.sdkj.boot.controller;

import com.sdkj.boot.domain.Admin;
import com.sdkj.boot.domain.ResultInfo;
import com.sdkj.boot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    public ResultInfo Login(@RequestParam("id") String id, @RequestParam("password") String password, HttpSession session){

        if(id == null & password == null){
            return new ResultInfo(false,null,"登陆名或密码不能为空");
        }
        Admin admin = adminService.Login(id, password);

        if(admin != null){
            session.setAttribute("admin",admin);
            return new ResultInfo(true,(Integer)admin.getAdminLeve(),"登陆成功");
        } else {
            return new ResultInfo(false,null,"用户名或密码不正确");
        }

    }

}
