package com.sdkj.boot.controller;

import com.sdkj.boot.domain.Post;
import com.sdkj.boot.domain.ResultInfo;
import com.sdkj.boot.domain.User;
import com.sdkj.boot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @RequestMapping("/query")
    public ResultInfo QueryPost(@RequestParam("type") String type,HttpSession session){

        List<Post> postList = postService.QueryPost(type);
        if(postList!=null){
            ResultInfo resultInfo = new ResultInfo(true,postList,"帖子传送成功");
            return resultInfo;
        } else {
            return new ResultInfo(false,null,"帖子传送失败");
        }
    }

    @RequestMapping("/insert")
    public ResultInfo InsertPost(@RequestParam("Content") String Content, @RequestParam("type") String type,HttpSession session){
        User user = (User) session.getAttribute("user");

        int res = postService.InsertPost(user.getUserId(), Content, 0, 0, type,user.getUserName());
        if(res == 1){
            //通过message 来传送username
            ResultInfo resultInfo = new ResultInfo(true,null,"帖子发送成功");
            return resultInfo;
        } else {
            ResultInfo resultInfo = new ResultInfo(false,null,"帖子发送失败");
            return resultInfo;
        }
    }

    //用于点赞
    @RequestMapping("/update")
    public ResultInfo UpdateLike(@RequestParam("id") String PostId){
        int res = postService.UpdateLike(PostId);
        if(res == 1){
            Integer result = postService.UpdatePostLike(PostId);  //result 是点赞后的点赞数
            if(result != null){
                return new ResultInfo(true,result,"点赞成功");
            }
            return new ResultInfo(false,null,"点赞失败");
        }
        return new ResultInfo(false,null,"点赞失败");

    }


    //用于查询帖子
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ResultInfo SearchPost(){
        List<Post> posts = postService.SearchPost();
        if(posts == null){
            return new ResultInfo(false,null,"查询异常");
        } else {
            return new ResultInfo(true,posts,"查询成功");
        }
    }


    //查询帖子内容
    @RequestMapping(value = "/querycontent",method = RequestMethod.GET)
    public ResultInfo QueryContent(@RequestParam("id")String id){
        String res = postService.QueryContent(id);
        return new ResultInfo(true,res,"查询成功");
    }


    //根据id，进行删帖
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    public ResultInfo DeleteById(@RequestParam("id")String id){
        Integer res = postService.DeleteById(id);
        if(res != 1){
            return new ResultInfo(false,null,"删帖失败");
        }
         return new ResultInfo(true,null,"删帖成功");
    }

    //根据id，进行封号处理
    @RequestMapping(value = "/prohibit",method = RequestMethod.GET)
    public ResultInfo UpdateById(@RequestParam("id")String id){
        Integer res = postService.UpdateById(id);
        if(res != 1){
            return new ResultInfo(false,null,"封号异常");
        }
        return new ResultInfo(true,null,"封号已受理");
    }


    //查看用户个人信息
    @RequestMapping(value = "/queryuser")
    public ResultInfo QueryUser(@RequestParam("id")String id){
        User user = postService.QueryUser(id);
        if(user != null){
            return new ResultInfo(true,user,"successful");
        }
        return new ResultInfo(false,null,"查询失败，服务器问题");
    }


    //依据当前登陆的用户，查询当前用户的帖子信息
    @RequestMapping(value = "/selectbyid")
    public ResultInfo SelectById(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Post> posts = postService.SelectById(user.getUserId()+"");
        if(posts != null){
            return new ResultInfo(true,posts,"帖子查询成功");
        }
        return new ResultInfo(false,"","帖子查询失败");
    }

}
