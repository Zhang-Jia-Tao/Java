package com.sdkj.boot.mapper;

import com.sdkj.boot.domain.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExamMapper {

    //依据试题类型查询试题
    List<Exam> QueryExam(String type);

    //查询所有试题
    List<Exam> Search();

    //依据id，进行查询
    Exam SelectById(String id);

    Integer UpdateById(String id,String type,String stem,String optionA,String optionB,String optionC,String optionD,
                       String answer,String rate,String num);


    List<Exam> RandomSelect(String type);

}
