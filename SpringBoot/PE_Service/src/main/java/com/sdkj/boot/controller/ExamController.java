package com.sdkj.boot.controller;

import com.sdkj.boot.domain.Exam;
import com.sdkj.boot.domain.ResultInfo;
import com.sdkj.boot.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    ExamService examService;


    //依据试题类型查询试题
    @RequestMapping("/query")
    public ResultInfo QueryExam(@RequestParam("type") String type){
        List<Exam> exams = examService.QueryExam(type);
        ResultInfo resultInfo = new ResultInfo();
        if(exams!=null){
            resultInfo.setFlag(true);
            resultInfo.setData(exams);
            resultInfo.setMessage("试题传送成功");
            return resultInfo;
        }
        resultInfo.setFlag(false);
        resultInfo.setMessage("试题传送失败");
        return resultInfo;
    }

    //查询所有的数据
    @RequestMapping("/search")
    public ResultInfo Search(){
        List<Exam> exams = examService.Search();
        if(exams.isEmpty()){
            return new ResultInfo(false,null,"查询失败");
        }
        return new ResultInfo(true,exams,"查询成功");
    }

    //依据id，进行查询
    @RequestMapping("/select")
    public ResultInfo SelectById(@RequestParam("id")String id){
        Exam exam = examService.SelectById(id);
        if(exam == null){
            return new ResultInfo(false,null,"查询失败");
        }
        return new ResultInfo(true,exam,"查询成功");
    }

    @RequestMapping("/update")
    public ResultInfo UpdateById(@RequestParam("id")String id,
                                 @RequestParam("type")String type,
                                 @RequestParam("stem")String stem,
                                 @RequestParam("optionA")String optionA,
                                 @RequestParam("optionB")String optionB,
                                 @RequestParam("optionC")String optionC,
                                 @RequestParam("optionD")String optionD,
                                 @RequestParam("answer")String answer,
                                 @RequestParam("rate")String rate,
                                 @RequestParam("num")String num){

        Integer res = examService.UpdateById(id,type,stem,optionA,optionB,optionC,optionD,answer,rate,num);
        if(res != 1){
            return new ResultInfo(false,null,"更新失败");
        }
        return new ResultInfo(true,null,"更新成功");

    }


    //随机获取试题，用于考试
    @RequestMapping("/get")
    public ResultInfo RandomSelect(@RequestParam("type")String type){
        List<Exam> exams = examService.RandomSelect(type);
        if(exams != null){
            return new ResultInfo(true,exams,"victory");
        }
        return new ResultInfo(false,null,"false");
    }
}
