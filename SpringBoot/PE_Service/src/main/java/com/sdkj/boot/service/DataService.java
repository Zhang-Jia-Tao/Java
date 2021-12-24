package com.sdkj.boot.service;

import com.sdkj.boot.domain.Data;
import com.sdkj.boot.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DataService {
    @Autowired
    DataMapper dataMapper;

    //依据资料类型查询
    public List<Data> QueryData(String DataType){
        List<Data> data = dataMapper.QueryData(DataType);
        return data;
    }

    //查询全部的数据
    public List<Data> SearchData() {
        List<Data> data = dataMapper.SearchData();
        return data;
    }

    //添加数据
    public Integer InsertData(String type,String address,String password,String content){
        Integer res = dataMapper.InsertData(type,address,password,content);

        return res;
    }


    //根据id查询信息
    public Data SelectById(String id){
        Data data = dataMapper.SelectById(id);
        return data;
    }

    //根据ID，更新数据
    public Integer UpdateById(String id,String type,String address,String password,String content){
        Integer res = dataMapper.UpdateById(id,type,address,password,content);
        return res;
    }


    //根据id，进行删除
    public Integer DeleteById(String id){
        Integer res = dataMapper.DeleteById(id);
        return res;
    }
}
