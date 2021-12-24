package com.sdkj.boot.service;

import com.sdkj.boot.domain.Exam;
import com.sdkj.boot.mapper.ExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    ExamMapper examMapper;

    public List<Exam> QueryExam(String type){
        List<Exam> list = examMapper.QueryExam(type);
        return list;
    }

    //查询所有试题
    public List<Exam> Search(){
        List<Exam> exams = examMapper.Search();
        return exams;
    }

    //依据id
    public Exam SelectById(String id){
        Exam exam = examMapper.SelectById(id);
        return exam;
    }

    public Integer UpdateById(String id,String type,String stem,String optionA,String optionB,String optionC,String optionD,
                              String answer,String rate,String num){
        Integer res = examMapper.UpdateById(id,type,stem,optionA,optionB,optionC,optionD,answer,rate,num);
        return res;
    }

    public List<Exam> RandomSelect(String type){
        List<Exam> exams = examMapper.RandomSelect(type);
        return exams;
    }
}
