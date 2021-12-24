package com.sdkj.boot.controller;

import com.sdkj.boot.domain.Data;
import com.sdkj.boot.domain.ResultInfo;
import com.sdkj.boot.domain.Return_Data;
import com.sdkj.boot.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    DataService dataService;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public ResultInfo QueryData(@RequestParam("type")String type){
       List<Data> data = dataService.QueryData(type);

       List<Return_Data> return_data = new ArrayList<>();
        for(Data redata : data){
            Return_Data re = new Return_Data();
            re.setDataType(redata.getDataType());
            re.setContent(redata.getContent());
            re.setVerCode(redata.getVerCode());
            re.setNumberOfDown(redata.getNumberOfDown());
            re.setDataAddress(redata.getDataAddress());
            return_data.add(re);
        }

       ResultInfo resultInfo = new ResultInfo(true,return_data,"数据传送成功");
       return resultInfo;
    }


    //查询所有的数据
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ResultInfo SearchData(){
        List<Data> data = dataService.SearchData();
        if(data == null){
            return new ResultInfo(false,null,"查询错误");
        }
        return new ResultInfo(true,data,"查询成功");
    }

    //添加数据
    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public ResultInfo Upload(@RequestParam("type")String type,
                             @RequestParam("address")String address,
                             @RequestParam("password")String password,
                             @RequestParam("content")String content){
        Integer res = dataService.InsertData(type,address,password,content);
        if(res!=1){
            return new ResultInfo(false,null,"服务器端某些错误");
        }

        return new ResultInfo(true,null,"上传成功");
    }

    //查询数据
    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public ResultInfo SelectById(@RequestParam("id")String id){
        Data data = dataService.SelectById(id);
        if(data != null){
            return new ResultInfo(true,data,"查询成功");
        }
        return new ResultInfo(false,null,"查询失败");
    }

    @RequestMapping(value = "/set",method = RequestMethod.GET)
    public ResultInfo UpdateById(@RequestParam("id")String id,
                                 @RequestParam("type")String type,
                                 @RequestParam("address")String address,
                                 @RequestParam("password")String password,
                                 @RequestParam("content")String content){

        Integer res = dataService.UpdateById(id,type,address,password,content);
        if(res != 0){
            return new ResultInfo(true,null,"更新成功");
        }
        return new ResultInfo(false,null,"更新失败，原因不详");
    }

    //根据id，进行数据的删除
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    public ResultInfo DeleteById(@RequestParam("id")String id){
        Integer res = dataService.DeleteById(id);
        if(res!=0){
            return new ResultInfo(true,null,"删除成功");
        }
        return new ResultInfo(false,null,"删除失败");
    }


}
