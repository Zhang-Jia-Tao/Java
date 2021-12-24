package com.sdkj.boot.controller;

import com.sdkj.boot.domain.ResultInfo;
import com.sdkj.boot.domain.User;
import com.sdkj.boot.service.UserService;
import com.sdkj.boot.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    //用户登录
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResultInfo login(@RequestParam("username") String username, @RequestParam("password") String password,
                            @RequestParam("checkcode")String checkcode,HttpSession session) {
        if(StringUtils.isEmpty(checkcode)){
            return new ResultInfo(false,null,"请填写验证码");
        }

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) ) {
            return new ResultInfo(false, null, "请正确填写用户名或密码");
        } else {
            if(session.getAttribute("checkCode").equals(checkcode)){
                User fUser = service.UserLogin(username, password);
                if (fUser == null) {
                    return new ResultInfo(false, null, "用户名或密码错误");
                }

                session.setAttribute("user", fUser);
                return new ResultInfo(true, null, "登录成功");
            } else {
                return new ResultInfo(false,null,"验证码错误");
            }
        }
    }



    /*
    * UpdateById(String username,String password,String sex,String age,String pe_dir,
                          String phone,String userid);
    * */
    //修改个人信息
    @RequestMapping(value = "update")
    public ResultInfo UpdateById(@RequestParam("username")String username,@RequestParam("password")String password,
                                 @RequestParam("sex")String sex,@RequestParam("age")String age,
                                 @RequestParam("pe_dir")String pe_dir,@RequestParam("phone")String phone,
                                 HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getUserId()+"";
        int i = service.UpdateById(username, password, sex, age, pe_dir, phone, userid);
        if(i != 0){
            return new ResultInfo(true,null,"个人信息修改成功");
        }
        return  new ResultInfo(false,null,"个人信息修改失败");
    }

    //用户注册
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResultInfo register(@RequestParam("username") String username,@RequestParam("password") String password,
                               @RequestParam("sex") String sex,@RequestParam("age") String age,@RequestParam("dir") String dir,
                               @RequestParam("phone") String phone){
        if(StringUtils.isEmpty(username) | StringUtils.isEmpty(password) | StringUtils.isEmpty(dir)){
            return new ResultInfo(false,null,"请填写正确的用户名或密码以及必须填写考研方向");
        } else {
            int i = service.UserRegister(username, password, sex, age, dir,phone);
            if(i!=1){
                //这里需要 一个检查是否存在的一个方法 ---》
                return new ResultInfo(false,null,"该手机号码已经注册过");
            }

            return new ResultInfo(true,null,"注册成功");
        }
    }

    //更新IP
    @RequestMapping("/ip")
    public ResultInfo UpdateIP(@RequestParam("IP") String IP,@RequestParam("username") String username){
        int i = service.UpdateIP(IP, username);
        return new ResultInfo(true,null,"victory");
    }

    //添加好友
    @RequestMapping("friend")
    public ResultInfo SelectFriend(@RequestParam("id") String id){
        List<User> users = service.SelectFriend(id);
        if(users != null){
            return new ResultInfo(true,users,"用户信息");
        } else {
            return new ResultInfo(false,null,"用户不存在或输入错误");
        }
    }

}
