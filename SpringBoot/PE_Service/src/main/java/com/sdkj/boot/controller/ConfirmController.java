package com.sdkj.boot.controller;

import com.sdkj.boot.domain.Admin;
import com.sdkj.boot.domain.Confirm;
import com.sdkj.boot.domain.ResultInfo;
import com.sdkj.boot.domain.User;
import com.sdkj.boot.service.AdminService;
import com.sdkj.boot.service.ConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/confirm")
public class ConfirmController {

    @Autowired
    ConfirmService confirmService;

    //发送方发送添加好友的请求
    @RequestMapping(value = "/add")
    public ResultInfo AddConfirm(HttpSession session,@RequestParam("Receive_id")String Receive_id){
        User user = (User) session.getAttribute("user");
        String Send_id =  user.getUserId()+"";
        int res = confirmService.AddConfirm(Send_id, Receive_id);
        if(res == 1){
            return new ResultInfo(true,null,"好友申请崇高");
        } else {
            return new ResultInfo(false, null, "false");
        }
    }


    @RequestMapping(value = "/query")
    public ResultInfo QueryConfirm(HttpSession session,@RequestParam("Receive_id")String Receive_id){
        User user = (User) session.getAttribute("user");
        String Send_id = user.getUserId()+"";
        int res = confirmService.QueryConfirm(Send_id,Receive_id);
        if(res>2){
            return new ResultInfo(false,null,"不能再次添加了");
        } else {
            return new ResultInfo(true,null,"ok");
        }
    }


    //返回的数据是用户作为接受申请的人
    @RequestMapping(value = "/compare")
    public ResultInfo compareuser(HttpSession session){
        User user = (User) session.getAttribute("user");
        String userid = user.getUserId()+"";
        List<Confirm> confirmList = confirmService.compareuser(userid);
        if(confirmList.isEmpty()){
            return new ResultInfo(false,null,"好友申请列表为空");
        }
        return new ResultInfo(true,confirmList,"好友申请类标以传送");
    }




    @RequestMapping(value = "/queryBySendId")
    public ResultInfo queryBySendId(@RequestParam("send_id")String send_id){
        User user = confirmService.queryBySendId(send_id);
        if(user == null){
            return new ResultInfo(false,null,"null");
        }
        return new ResultInfo(true,user,"victory");
    }


    //同意 or 拒绝
    @RequestMapping(value = "/setstatus")
    public ResultInfo setstatus(@RequestParam("send_id")String send_id,
                                @RequestParam("flag")String flag,
                                HttpSession session){


        User user = (User) session.getAttribute("user");
        String receive_id = user.getUserId()+"";

        if("agree".equals(flag)){
            int i = confirmService.setstatus_agree(send_id, receive_id);
            if(i!=0){
                return new ResultInfo(true,null,"你同意了他的申请");
            } else {
                return new ResultInfo(false,null,"服务器问题");
            }
        }

        if("refuse".equals(flag)){
            int i = confirmService.setstatus_refuse(send_id,receive_id);
            if(i!=0){
                return new ResultInfo(true,null,"你已成功拒绝了他的申请");
            } else {
                return new ResultInfo(false,null,"服务器问题");
            }
        }

        return new ResultInfo(false,null,"服务器出现严重bug");
    }

    @RequestMapping("/queue")
    public ResultInfo selectByRecId(HttpSession session){
        User user = (User) session.getAttribute("user");
        String id = user.getUserId()+"";
        if(id == ""){
            System.out.println("big bug");
            return new ResultInfo(false,null,"big bug");
        }

        List<User> users = confirmService.selectByRecId(id);
        if(users.isEmpty()){
            return new ResultInfo(false,null,"暂无申请信息");
        } else {
            return new ResultInfo(true,users,"runnable");
        }
    }

    @RequestMapping("/agree")
    public ResultInfo agree(@RequestParam("requestname") String username,
                            @RequestParam("requestphone") String phone,
                            HttpSession session){
        User user = (User)session.getAttribute("user");
        String receive_id = user.getUserId()+"";
        int agree = confirmService.agree(receive_id, username, phone);
        if(agree != 0){
            return new ResultInfo(true,null,"已同意，请稍后退出程序查看好友列表");
        } else {
            return new ResultInfo(false,null,"服务器bug");
        }
    }

    @RequestMapping("/refuse")
    public ResultInfo refuse(@RequestParam("requestname")String username,
                             @RequestParam("requestphone")String phone,
                             HttpSession session){
        User user = (User)session.getAttribute("user");
        String receive_id = user.getUserId()+"";
        int agree = confirmService.refuse(receive_id, username, phone);
        if(agree != 0){
            return new ResultInfo(true,null,"已拒绝，请稍后退出程序查看好友列表");
        } else {
            return new ResultInfo(false,null,"服务器bug");
        }
    }

}
