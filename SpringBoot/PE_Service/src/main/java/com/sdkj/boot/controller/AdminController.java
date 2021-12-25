package com.sdkj.boot.controller;

import com.sdkj.boot.domain.Admin;
import com.sdkj.boot.domain.ResultInfo;
import com.sdkj.boot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

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


    //查询全部管理员
    @RequestMapping("/query")
    public ResultInfo selectmis(){
        List<Admin> adminList = adminService.selectmis();
        if(adminList.isEmpty()){
            return new ResultInfo(false,null,"服务器bug");
        } else {
            return new ResultInfo(true,adminList,"victory");
        }
    }

    @RequestMapping("/delete")
    public ResultInfo DeleteById(@RequestParam("id") String id){
        int res = adminService.DeleteById(id);
        if(res != 1){
            return new ResultInfo(false,null,"删除失败");
        } else {
            return new ResultInfo(true,null,"删除成功");
        }
    }

    @RequestMapping("/add")
    public ResultInfo Add(@RequestParam("name")String name,
                          @RequestParam("password")String password,
                          @RequestParam("level")String level){
        int res = adminService.add(name,password,level);
        if(res != 1){
            return new ResultInfo(false,null,"添加失败");
        } else {
            return new ResultInfo(true,null,"添加成功");
        }
    }

    @RequestMapping("/selectmis")
    public ResultInfo Select(@RequestParam("id")String id){
            Admin admin = adminService.SelectById(id);
            if(admin == null){
                return new ResultInfo(false,null,"查询失败");
            } else {
                return new ResultInfo(true,admin,"victory");
            }
    }

    @RequestMapping("/set")
    public ResultInfo updateById(@RequestParam("id")String id,
                                 @RequestParam("level")String level,
                                 @RequestParam("name")String name,
                                 @RequestParam("password")String password){
        int res = adminService.updateById(id,name,password,level);
        if(res != 1){
            return new ResultInfo(false,null,"更新数据失败");
        } else {
            return new ResultInfo(true,null,"数据更新成功");
        }
    }

}
